package newstructor;

import java.sql.Connection;
import java.sql.DriverManager;

//��д������ݿ����ӵĹ����ࣺ
public class Connect2DBFactory {
    public static Connection getDBConnection() {
        Connection conn = null;
        try {
        	//�������ݿ�
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/blogsystem";
            String user = "root";
            String password = "root";
            //����
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return conn;
    }
}