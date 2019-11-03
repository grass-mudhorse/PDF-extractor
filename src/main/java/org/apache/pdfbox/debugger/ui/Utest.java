package org.apache.pdfbox.debugger.ui;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class Utest {
	static String Driver = "com.mysql.jdbc.Driver";
	   static String    url = "jdbc:mysql://localhost:3306/net1?useUnicode=true&characterEncoding=utf-8";
	   static String   root = "root";
	   static String     ps = "123456";
	
	static Connection coon = null;
	static Statement st = null;
	ResultSet rs = null;
	
public void queryDocter()  {
		
	try
    {
	 	
	 	BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        UIManager.put("RootPane.setupButtonVisible", false);
        
    }
    catch(Exception e)
    {
    	
    }
	
	JTextField xField = new JTextField(20);
    JTextField yField = new JTextField(20);
    JPanel Panel1 = new JPanel();
    Panel1.setPreferredSize(new Dimension(300,40));
    JPanel Panel2 = new JPanel();
    Panel1.setPreferredSize(new Dimension(300,40));
    
    JPanel myPanel = new JPanel();
    myPanel.add(Panel1);
    myPanel.add(Panel2);
    myPanel.setPreferredSize(new Dimension(300,100)); 
//  Panel1.add(Box.createHorizontalStrut(5));
    Panel1.add(new JLabel("账号:"));
    Panel1.add(xField);
//   myPanel.add(Box.createHorizontalStrut(7)); // a spacer
    Panel2.add(new JLabel("密码:"));
    Panel2.add(yField);

    int result = JOptionPane.showConfirmDialog(null, myPanel,
        "", JOptionPane.OK_CANCEL_OPTION,1);
    
    if (result == JOptionPane.OK_OPTION) {
    	
    	String xs = xField.getText();
        String ys = yField.getText();
        
        String sql = "SELECT * FROM user where name='"+xs+"'&& password="+"'"+ys+"'";
		
		try {
			coon = DriverManager.getConnection(url,root,ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			st = coon.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()){
				System.out.println("账号是"+rs.getString("name"));

			}else{
				
				System.out.println("用户账号不存在或者密码错误");
				int rs1 = JOptionPane.showConfirmDialog(myPanel,"账号或密码错误！",
						"错误 ", JOptionPane.OK_CANCEL_OPTION,1);
				if(rs1 == JOptionPane.OK_OPTION){
					queryDocter();
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	
	
	}
	public static void main(String arg[]){
		   
		   new Utest().queryDocter();
	}
}
