package org.apache.pdfbox.debugger.ui;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Stripperone {	
	
	public static String getTextFromPDF(String pdfFilePath,int starPage,int endPage) 
	{
		String str = null;
		FileInputStream is = null;
		PDDocument document = null;
		int startPageValue = starPage;
		int endPageValue = endPage;
		try {
			File file = new File(pdfFilePath);
			document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			pdfStripper.setSortByPosition(true);
			
			pdfStripper.setStartPage(startPageValue);
			
			pdfStripper.setEndPage(endPageValue);
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
	
	public static void saveFile(String s) {
		
		String string = s;
		JFileChooser chooser = new JFileChooser();
		
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "*.txt", "txt");
		chooser.setFileFilter(filter);
		
		
		int option = chooser.showSaveDialog(null);
		if(option==JFileChooser.APPROVE_OPTION){	
			File file = chooser.getSelectedFile();
			
			String fname = chooser.getName(file);	

			if(fname.indexOf(".txt")==-1){
				file=new File(chooser.getCurrentDirectory(),fname+".txt");
				System.out.println("renamed");
				System.out.println(file.getName());
			}
			JOptionPane.showMessageDialog(null,"保存成功","提示",1);
			try {
				FileOutputStream fos = new FileOutputStream(file);
				FileWriter writer = new FileWriter(file);
			    writer.append(string);
			    writer.flush();
				
				fos.close();
				
				
			} catch (IOException e) {
				System.err.println("IO Exception");
				e.printStackTrace();
			}	
		}
	}
	
	
	
	public static void Stripper(String str){
			int page = 10;
			JTextField xField = new JTextField(5);
		    JTextField yField = new JTextField(5);
		    xField.setDocument(new NumberTextField());
		    yField.setDocument(new NumberTextField());

		    JPanel myPanel = new JPanel();
		    myPanel.setPreferredSize(new Dimension(300,50)); 
		    myPanel.add(new JLabel("起始页:"));
		    myPanel.add(xField);
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("结束页:"));
		    myPanel.add(yField);

		    int result = JOptionPane.showConfirmDialog(null, myPanel,
		        "请输入起始页和结束页", JOptionPane.OK_CANCEL_OPTION,1);
		      
		    if (result == JOptionPane.OK_OPTION) {
		    	
		    	String xs = xField.getText();
		        String ys = yField.getText();
		    	
		        if(!xs.trim().equals("") && !ys.trim().equals("")){
		    		if(Integer.parseInt(xs) <= Integer.parseInt(ys) && Integer.parseInt(xs) < page && Integer.parseInt(ys) < page
		    			&& Integer.parseInt(xs) >0 && Integer.parseInt(ys) > 0){
		    			//stripper	
		    			//Stripperone sp = new Stripperone();
		    			String s = null;
		    			s = Stripperone.getTextFromPDF(str,Integer.parseInt(xs)-1,Integer.parseInt(ys)-1);
		    			Stripperone.saveFile(s);		    					    			
		    		}
		    		else{
		    			JOptionPane.showMessageDialog(null,"输入不符合规则！","错误 ",0);
		    		}
		    		  		
		    	}
		    	else{
		    		JOptionPane.showMessageDialog(null,"输入不能为空！","错误 ",0);
		    	}
		    		
		    }
		
	}
		
	
  public static void main(String[] args) {
	  	new Stripperone().Stripper("F:/sample2.pdf");

  }
}