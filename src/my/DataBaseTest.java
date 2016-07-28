package my;

import java.sql.*;

public class DataBaseTest {
	ResultSet results;
	ResultSetMetaData rsmd;
	DatabaseMetaData dma;
	Connection conn;
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/blogsystem";
	    String user = "root";
        String password = "root";
		
		
		
	}

	public void JdbcOdbc_test() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/blogsystem";
	    String user = "root";
        String password = "root";
		try {
			// ���� JDBC-ODBC ����������
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);// �������ݿ�
			
			
			DatabaseMetaData metadata = conn.getMetaData();   
            System.out.println("���ݿ���֪���û�: "+ metadata.getUserName());   
            System.out.println("���ݿ��ϵͳ�����Ķ��ŷָ��б�: "+ metadata.getSystemFunctions());   
            System.out.println("���ݿ��ʱ������ں����Ķ��ŷָ��б�: "+ metadata.getTimeDateFunctions());   
            System.out.println("���ݿ���ַ��������Ķ��ŷָ��б�: "+ metadata.getStringFunctions());   
            System.out.println("���ݿ⹩Ӧ������ 'schema' ����ѡ����: "+ metadata.getSchemaTerm());   
            System.out.println("���ݿ�URL: " + metadata.getURL());   
            System.out.println("�Ƿ�����ֻ��:" + metadata.isReadOnly());   
            System.out.println("���ݿ�Ĳ�Ʒ����:" + metadata.getDatabaseProductName());   
            System.out.println("���ݿ�İ汾:" + metadata.getDatabaseProductVersion());   
            System.out.println("�������������:" + metadata.getDriverName());   
            System.out.println("��������İ汾:" + metadata.getDriverVersion());   
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}