package org.apache.pdfbox.debugger.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Testsave {
	public static void saveFile(String s) {
		//�����ļ�ѡ���
		String string = s;
		JFileChooser chooser = new JFileChooser();
		
		//��׺��������
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "*.txt", "txt");
		chooser.setFileFilter(filter);

		
		//����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��
		int option = chooser.showSaveDialog(null);
		if(option==JFileChooser.APPROVE_OPTION){	//�����û�ѡ���˱���
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

	public static void main(String args[]){
		Testsave t = new Testsave();
		t.saveFile("123");
		
	}
}

