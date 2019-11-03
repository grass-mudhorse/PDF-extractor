package com.qianxinyao.analysis.jieba.keyword;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.huaban.analysis.jieba.JiebaSegmenter;

/**
 * @author Tom Qian
 * @email tomqianmaple@outlook.com
 * @github https://github.com/bluemapleman
 * @date Oct 20, 2018
 * tfidf算法原理参考：http://www.cnblogs.com/ywl925/p/3275878.html
 * 部分实现思路参考jieba分词：https://github.com/fxsjy/jieba
 */
public class TFIDFAnalyzer
{
	
	static HashMap<String,Double> idfMap;
	static HashSet<String> stopWordsSet;
	static double idfMedian;
	
	/**
	 * tfidf分析方法
	 * @param content 需要分析的文本/文档内容
	 * @param topN 需要返回的tfidf值最高的N个关键词，若超过content本身含有的词语上限数目，则默认返回全部
	 * @return
	 */
	public List<Keyword> analyze(String content,int topN){
		List<Keyword> keywordList=new ArrayList<Keyword>();
		
		if(stopWordsSet==null) {
			stopWordsSet=new HashSet<String>();
			loadStopWords(stopWordsSet, this.getClass().getResourceAsStream("/stop_words.txt"));
		}
		if(idfMap==null) {
			idfMap=new HashMap<String, Double>();
			loadIDFMap(idfMap, this.getClass().getResourceAsStream("/idf_dict.txt"));
		}
		
		Map<String, Double> tfMap=getTF(content);
		for(String word:tfMap.keySet()) {
			// 若该词不在idf文档中，则使用平均的idf值(可能定期需要对新出现的网络词语进行纳入)
			if(idfMap.containsKey(word)) {
				keywordList.add(new Keyword(word,idfMap.get(word)*tfMap.get(word)));
			}else
				keywordList.add(new Keyword(word,idfMedian*tfMap.get(word)));
		}		
		Collections.sort(keywordList);		
		if(keywordList.size()>topN) {
			int num=keywordList.size()-topN;
			for(int i=0;i<num;i++) {
				keywordList.remove(topN);
			}
		}
		return keywordList;
	}
	
	/**
	 * tf值计算公式
	 * tf=N(i,j)/(sum(N(k,j) for all k))
	 * N(i,j)表示词语Ni在该文档d（content）中出现的频率，sum(N(k,j))代表所有词语在文档d中出现的频率之和
	 * @param content
	 * @return
	 */
	private Map<String, Double> getTF(String content) {
		Map<String,Double> tfMap=new HashMap<String, Double>(); //Map记录关键词和计算结果
		if(content==null || content.equals(""))
			return tfMap;     //内容为空时直接返回Map		
		JiebaSegmenter segmenter = new JiebaSegmenter();   //创建jieba分词器对象
		List<String> segments=segmenter.sentenceProcess(content);  //分词
		Map<String,Integer> freqMap=new HashMap<String, Integer>(); //创建 freqMap记录单词和词频
		
		int wordSum=0; //创建wordSum记录总词数
		//遍历分词结果记录词频
		for(String segment:segments) {
			//停用词不予考虑
			if(!stopWordsSet.contains(segment) && segment.length()>1 ) {
				wordSum++;
				if(freqMap.containsKey(segment) ) {
					freqMap.put(segment,freqMap.get(segment)+1);
				}else {
					freqMap.put(segment, 1);
				}
			}
		}		
		// 计算double型的tf值
		for(String word:freqMap.keySet()) {
			tfMap.put(word,freqMap.get(word)*1.0/wordSum);
		}
		
		return tfMap; 
	}
	
	/**
	 * 默认jieba分词的停词表
	 * url:https://github.com/yanyiwu/nodejieba/blob/master/dict/stop_words.utf8
	 * @param set
	 * @param filePath
	 */
	private void loadStopWords(Set<String> set, InputStream in){
		BufferedReader bufr;
		try
		{
			bufr = new BufferedReader(new InputStreamReader(in));
			String line=null;
			while((line=bufr.readLine())!=null) {
				set.add(line.trim());
			}
			try
			{
				bufr.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * idf值本来需要语料库来自己按照公式进行计算，不过jieba分词已经提供了一份很好的idf字典，所以默认直接使用jieba分词的idf字典
	 * url:https://raw.githubusercontent.com/yanyiwu/nodejieba/master/dict/idf.utf8
	 * @param set
	 * @param filePath
	 */
	private void loadIDFMap(Map<String,Double> map, InputStream in ){
		BufferedReader bufr;
		try
		{
			bufr = new BufferedReader(new InputStreamReader(in));
			String line=null;
			while((line=bufr.readLine())!=null) {
				String[] kv=line.trim().split(" ");
				map.put(kv[0],Double.parseDouble(kv[1]));
			}
			try
			{
				bufr.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			// 计算idf值的中位数
			List<Double> idfList=new ArrayList<Double>(map.values());
			Collections.sort(idfList);
			idfMedian=idfList.get(idfList.size()/2);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String[] run(String str){
		String s = str;
		int i = 1;
	//	int j = 0;
		String[] wl = new String[11];
		int topN=5;
		TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
		List<Keyword> list=tfidfAnalyzer.analyze(s,topN);
		for(Keyword word:list){
	//	System.out.print(word.getName()+":"+word.getTfidfvalue()+",");
			wl[i] = word.getName();
			i++;
			wl[i] =String.valueOf(word.getTfidfvalue());
			i++;
		}				
		return wl;	
	}
	
	public static void main(String[] args)
	{
		String s = "卡农是一种音乐体裁，卡农的最早历史，可以追溯至13世纪的民间音乐形式，如狩猎曲、轮唱曲等。轮唱曲是一种小型声乐曲，其形式为各声部以相同间距进入的同度无终卡农，13世纪以后流行于英国。15世纪出现了完整的卡农曲，并为佛兰德乐派的作曲家所喜用。此后，卡农经常作为一种独立的小型乐曲或大型乐曲中的一个段落而被运用，许多交响作品里都会用《卡农》的技巧部分，比如贝多芬的《命运交响曲》、巴赫的《五首卡农变奏曲》等。";
		TFIDFAnalyzer t = new TFIDFAnalyzer();
		String[] wl = new String[]{};
		wl = t.run(s);
		for(int i = 1; i<11;i = i+2){
			System.out.print(wl[i]+":"+wl[i+1]+"\n");
		}
		
	
	/*	String content="";
		int topN=5;
		TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
		List<Keyword> list=tfidfAnalyzer.analyze(content,topN);
		for(Keyword word:list)
			System.out.print(word.getName()+":"+word.getTfidfvalue()+",");
	*/
	}
}

