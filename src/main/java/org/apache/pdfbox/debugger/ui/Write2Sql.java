package org.apache.pdfbox.debugger.ui;


import java.sql.*;

public class Write2Sql {
	
	static String Driver = "com.mysql.jdbc.Driver";
	static String    url = "jdbc:mysql://localhost:3306/net1?useUnicode=true&characterEncoding=utf-8";
	static String   root = "root";
	static String     ps = "123456";
	
	static Connection conn = null;
	static Statement st = null;
	ResultSet rs = null;
	public static void writeTo(String path , String kword){
		String com = "INSERT INTO k_word (id,file_path,keyword) VALUES(?,?,?)";
		try {
			conn = DriverManager.getConnection(url,root,ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PreparedStatement stmt = conn.prepareStatement(com);
			 stmt.setInt(1, 1);
			 stmt.setString(2, path);
			 stmt.setString(3, kword);
			 
			 int i = stmt.executeUpdate();            //执行插入数据操作，返回影响的行数
			          if (i == 1) {
			              System.out.println("insert successfully");
			          }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		Write2Sql.writeTo("F:\\hello","show me money");

	}

}
