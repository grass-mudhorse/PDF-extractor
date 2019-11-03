package org.apache.pdfbox.debugger.ui;
import javax.swing.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
public class BoxLayout
{   
	public BoxLayout(String str){
        
		File f = new File(str);
		try {
			PDDocument pd = PDDocument.load(f);
			PDDocumentInformation pdd = pd.getDocumentInformation();
			
			
			
			JFrame jf=new JFrame("pdf属性");    //创建Frame窗口
			   //   JPanel jPanel=new JPanel();    //创建面板
			   /*     JTextArea ft1 = new JTextArea(10,5);
			        JTextArea ft2 = new JTextArea(10,5);
			        JTextArea ft3 = new JTextArea(10,5);
			        JTextArea ft4 = new JTextArea(10,5);
			        JTextArea ft5 = new JTextArea(10,5);
			     */   
			        JPanel jp=new JPanel();
			        jf.add(jp);
			        
			        
			        JLabel jb1 = new JLabel("作者:"+pdd.getAuthor());
			        JLabel jb2 = new JLabel("标题:"+pdd.getTitle());
			        JLabel jb3 = new JLabel("创建人:"+pdd.getCreator());
			        JLabel jb4 = new JLabel("关键词:"+pdd.getKeywords());
			        pd.close();
			        
			        jp.setLayout(null);
			        jp.setPreferredSize(new Dimension(500,300));
			        
			        
			        Font font = new Font("宋体", Font.PLAIN, 25);
			        jb1.setFont(font);
			        jb2.setFont(font);
			        jb3.setFont(font);
			        jb4.setFont(font);
			       
			        
			        jb1.setBounds(150, 5, 2000, 50);
			        jb2.setBounds(150, 35,2000,100);
			        jb3.setBounds(150, 65,2000,150);
			        jb4.setBounds(150, 95,2000,200);
			        
			               
			        jp.add(jb1);
			        jp.add(jb2);
			        jp.add(jb3);
			        jp.add(jb4);
			       
			        			        
			        
			    /*    
			        Box b1 = Box.createHorizontalBox();        
			        Box b2 = Box.createHorizontalBox();
			        Box vBox = Box.createVerticalBox();
			        
			        
			        b2.add(Box.createRigidArea(new Dimension(100,20))); 
			        b1.add(new JLabel("test"));
			        b1.add(Box.createHorizontalStrut(10));
			        b1.add(ft1);
			        
			        
			        b2.add(new JLabel("pork"));
			        b2.add(Box.createHorizontalStrut(10));
			        b2.add(ft2);
			        
			        vBox.add(b1);
			        vBox.add(b2);
			        
			        jf.setContentPane(vBox);
			        
			       
			        
			   /*   
			        jPanel.add(ft1);    //面板中添加按钮
			        jPanel.add(ft2);
			        jPanel.add(ft3);
			        jPanel.add(ft4);
			        jPanel.add(ft5);
			        //向JPanel添加FlowLayout布局管理器，将组件间的横向和纵向间隙都设置为20像素
			        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			     //   jPanel.setBackground(Color.gray);    //设置背景色
			        jf.add(jPanel); 
			  */
			        jf.setBounds(300,200,800,400);    //设置容器的大小
			        jf.setVisible(true);
			    //  jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
			        jf.setLocationRelativeTo(null); 
		
		
		
		
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 


		
	
	}
	
	
	public static void main(String[] agrs)
    {		
			String str = "F:/sample2.pdf";
			new BoxLayout(str);
    }
}
