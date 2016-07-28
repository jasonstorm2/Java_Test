package Chapter18;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;



public class URLClassLoaderTest {
	private static Connection conn;
	
	private String url;
	private String driver;
	
	//����һ����ȡ���ݿ� ���ӵķ���
	public static Connection getConn(String url,String user,String pass) throws Exception{
		if(conn==null){
			//����һ��URL����,ֻ��һ��Ԫ��
			//��װ��·������Ϊһ������
			//��ǰ·���µ�mysql-connector-java-5.1.30-bin.jar�ļ�
			//file �����Ǵ� �����ļ�ϵͳ���أ�
			//http:Ϊǰ׺�������ӻ�����ͨ��HTTP������
			URL[] urls = {new URL("file:mysql-connector-java-5.1.30-bin.jar")};
			
			//�� Ĭ�ϵ� ClassLoader ��Ϊ�� ClassLoader,���� URLClassLoader
			URLClassLoader myClassLoader = new URLClassLoader(urls);
			
			//����MYSQL��JDBC������ ������Ĭ��ʵ��-------�� ��ͨ��ͨ�� new ����ʵ���ķ��� �Ա�
			Driver driver = (Driver)myClassLoader.loadClass("com.mysql.jdbc.Driver").newInstance();
			//����һ�� ����JDBC�������Ե� Properties����
			Properties props = new Properties();			
			//������ҪΪ�Ķ����� user��password��������
			props.setProperty("user", user);
			props.setProperty("password", pass);
			//����Driver ����� connect������ȡ�����ݿ�����
			conn = driver.connect(url, props);
		}
		return conn;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getConn("jdbc:mysql://localhost:3306/gamedb", "root", "root"));
		
		Statement st = conn.createStatement();
		
		ResultSet re = st.executeQuery("SELECT NAME,LEVEL, COMBAT,actvaluemax FROM human WHERE NAME = '���˰�ѩ'");
		while(re.next()){
			System.out.println("NAME��"+re.getString(1)+"\n"
			+"LEVEL:"+re.getString(2)+"\n\n"
			+"COMBAT:"+re.getString(3)+"\n"
			+"actvaluemax:"+re.getString(4));
		}
		
		System.out.println("lala");
	}

}
