package org.apache.pdfbox.debugger.ui;


import java.sql.*;

public class Getsql {
	static String Driver = "com.mysql.jdbc.Driver";
	   static String    url = "jdbc:mysql://localhost:3306/net1?useUnicode=true&characterEncoding=utf-8";
	   static String   root = "root";
	   static String     ps = "123456";
	
	static Connection coon = null;
	static Statement st = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		 String sql = "SELECT file_path,keyword FROM k_word";
			
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
				while(rs.next()){
					String p = rs.getString("file_path");
					String k = rs.getString("keyword");
					
					System.out.println(p);
					System.out.println(k);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
