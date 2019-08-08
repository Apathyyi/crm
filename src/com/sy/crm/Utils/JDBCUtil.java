package com.sy.crm.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {
	/*
	 * 一般数据库连接
	 */
//	static String driverclass = null;
//	static String url = null;
//	static String name = null;
//	static String password = null;
//	static {
//	Properties properties = new Properties();
//	InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
//	try {
//		properties.load(is);
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	driverclass = properties.getProperty("driverclass");
//	url = properties.getProperty("url");
//	name = properties.getProperty("name");
//	password = properties.getProperty("password");
//} 
    static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	//获取连接对象
    public static DataSource getdatasorce(){
    	return dataSource;
    }
	public static Connection getConn() {
//			Class.forName(driverclass);
//			conn = DriverManager.getConnection(url, name,password);
//			System.out.println("连接数据库成功");
		
			/*
			 * c3p0数据库连接池
			 */
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void release(Connection conn,Statement st,ResultSet rs){
		closeConn(conn);
		closeRs(rs);
		closeSt(st);
	}
	public static void release(Connection conn,Statement st){
		closeConn(conn);
		closeSt(st);
	}
	private static void closeRs(ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		
	}
	private static void closeSt(Statement st){
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				st = null;
			}
		}
	}
	private static void closeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	}
}
