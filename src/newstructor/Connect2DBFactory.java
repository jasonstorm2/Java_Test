package newstructor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//��д������ݿ����ӵĹ����ࣺ
public class Connect2DBFactory {
    public static Connection getDBConnection(String databaseName) {
        Connection conn = null;
        try {
        	//�������ݿ�
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "root";            
            //����
            conn = DriverManager.getConnection(url, user, password);           
            
    		if (conn != null) {// ������ݿⲻ���� �ʹ����ÿ�
    			Statement smt = conn.createStatement();
    			smt.executeUpdate("CREATE DATABASE IF NOT EXISTS  `" + databaseName+ "`  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci");
    			smt.close();
    		}
    		conn = DriverManager.getConnection(url+databaseName, user, password);          
            
            
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
 
        return conn;
    }
	
    /**
     * �������ݿ� �� �������ݿ���
     * @param databasename
     */
	public static void createDatabase(String databasename){		
        try {  
        	//�������ݿ�
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "root";            
            //����
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st =  con.createStatement(); 
            st.execute("create database "+databasename);            
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    } 
	
	/**
	 * �������
	 * 
	 * �жϱ���Ƿ���ڣ��������˳����������򴴽�
	 * 
	 * @param tableName
	 */
	public static void createTable(String tableName, String databaseName) {
		String sqlStr = "create table " + tableName + "( id int not null auto_increment primary key,"
				+ "name varchar(25)," + "pwd varchar(25),"
				// + "age int(11))engine=innodb auto_increment=7 default
				// charset=latin1;"
				+ "age int(11));"
		;

		try {
			// �������ݿ�
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/" + databaseName;
			String user = "root";
			String password = "root";
			// �õ����Ӷ���
			Connection con = DriverManager.getConnection(url, user, password);
			DatabaseMetaData metadata = con.getMetaData();
			ResultSet rs = metadata.getTables(null, null, tableName, null);
			if (rs.next()) {
				System.out.println("�� " + tableName + " ���ڣ��˳�");
				return;
				/*
				 * �жϱ����Ƿ��� ĳ���ֵ��� rs = metadata.getColumns(null, null, tableName,
				 * columnName);
				 * 
				 * if (rs.next()) { System.out.println("Table " + tableName +
				 * " exist in Table " + tableName); } else { System.out.println(
				 * "Column " + tableName + " not exist in Table " + tableName);
				 * }
				 */
			} else {
				System.out.println("�� " + tableName + " �����ڣ���ô�����ñ�");
				Statement st = con.createStatement();
				// �������ݿ�
				st.execute(sqlStr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	/**
//	 * ���ĳ���ֶ��Ƿ����
//	 * @param id
//	 * @param conn
//	 * @return
//	 * @throws Exception
//	 */
//	public static boolean isIdExist(int id,Connection conn) throws Exception{
//		boolean isExist = false;
//		DatabaseMetaData metadata = conn.getMetaData();
//		ResultSet rs = metadata.getColumns(null, null, id, null);
//		if (rs.next()) {
//			isExist = true;
//		}
//		return isExist;
//	}
	
}