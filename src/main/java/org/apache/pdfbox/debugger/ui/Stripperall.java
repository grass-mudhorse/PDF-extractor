package org.apache.pdfbox.debugger.ui;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.qianxinyao.analysis.jieba.keyword.TFIDFAnalyzer;
 

 
public class Stripperall{
	/**
	 * simply reader all the text from a pdf file. 
	 * You have to deal with the format of the output text by yourself.
	 * 2008-2-25
	 * @param pdfFilePath file path
	 * @return all text in the pdf file
	 */
	public static String getTextFromPDF(String pdfFilePath) 
	{
		String str = null;
		FileInputStream is = null;
		PDDocument document = null;
		try {
			File file = new File(pdfFilePath);
			document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			str = pdfStripper.getText(document);
			document.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return str;
	}

	
	public void keyWord(){
		String[] str = SelectAll.selectF();
		for(int i = 0; i<str.length ;i++){
			
			String ss = getTextFromPDF(str[i]);
			ss = ss.replaceAll("\\s", "");
			String[] wl = new String[]{};
			wl = TFIDFAnalyzer.run(ss);
			
			 String sss ="";
			
			for(int j = 1; j < 11; j = j+2){				
				  sss = sss+ wl[j];
				  if(j<9){
					  sss+="-";
				  }
          	  System.out.println(wl[j]+": "+wl[j+1]);
             }
			Write2Sql.writeTo(str[i],sss);
		}
	}
	
	
	public  static void main(String[] args)
	{
		new Stripperall().keyWord();
		//System.out.println(str);
	
	}
}

