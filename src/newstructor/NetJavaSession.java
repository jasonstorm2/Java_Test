package newstructor;

import java.lang.reflect.Field;// field �ĵ����
import java.lang.reflect.Method;//method�ĵ����
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import my.LoggerTest;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mysql.fabric.xmlrpc.base.Array;

//��д�������ݿ��dao��
public class NetJavaSession {
	static Logger logger = Logger.getLogger(LoggerTest.class);
	static{
		PropertyConfigurator.configure("log4j.properties");
		}

    /**
     * ��������������sql���
     * ����sql���
     * @param object
     *            ����Ҫ����Ķ���
     * @return����������sql���
     */
	public static String getSaveObjectSql(Object object){
        // ����һ��sql�ַ���
        String sql = "insert into ";
        // �õ��������
        Class c = object.getClass();
        // �õ����������еķ���
        Method[] methods = c.getMethods();
        // �õ����������е�����
        Field[] fields = c.getFields();
        // �õ������������
        String cName = c.getName();
        logger.debug("�õ������������:"+cName);
        
        // ����==������
        String tableName = cName.substring(cName.lastIndexOf(".") + 1,
                cName.length());
        
        sql += tableName + "("; //  sql���
        
        List<String> mList = new ArrayList<>();
        List<Object> vList = new ArrayList<Object>();
        List<Object> mm =  Arrays.asList("");	

        //�������������еķ���
        //�õ����з��������֣�����list�У������list
        for (Method method : methods){
        	//�õ�����������
            String mName = method.getName();
            
            if (mName.startsWith("get") && !mName.startsWith("getClass")){
            	//������ȥ��get֮����Դ������ݿ����ֶ���
                String fieldName = mName.substring(3, mName.length());
                //������һ����mlist����
                mList.add(fieldName);
                System.out.println("�ֶ�����----->" + fieldName);
                
                try {
                	//����  ������ô˷����ķ���ֵ
//                	UserInfo users = new UserInfo();
//                	users.setName("edison");
//                	users.getName();
                    Object value = method.invoke(object);
                    System.out.println("ִ�з������ص�ֵ��" + value);
                    if (value instanceof String) {
                        vList.add("\"" + value+ "\"");
                        System.out.println("�ֶ�ֵ------>" + value);
                    } else {
                        vList.add(value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //��һȡ��mList�����Ԫ��
        for (int i = 0; i < mList.size(); i++) {
            if (i < mList.size() - 1) {
                sql += mList.get(i) + ",";
            } else {
                sql += mList.get(i) + ") values(";
            }
        }
        //��һȡ��vList�����Ԫ��
        for (int i = 0; i < vList.size(); i++) {
            if (i < vList.size() - 1) {
                sql += vList.get(i) + ",";
            } else {
                sql += vList.get(i) + ")";
            }
        }
 
        return sql;
    }
 
    public static List getDatasFromDB(String tableName, int Id) {
 
        return null;
 
    }
 
    /**
     * �����󱣴浽���ݿ���
     *
     * @param object
     *            ����Ҫ����Ķ���
     * @return������ִ�еĽ��;1:��ʾ�ɹ���0:��ʾʧ��
     */
    public int saveObject(Object object) {
    	//�������ݿ�
        Connection con = Connect2DBFactory.getDBConnection();
        //���sql���
        String sql = getSaveObjectSql(object);
        //�������ݿ�
        try {
            // Statement statement=(Statement) con.createStatement();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.executeUpdate();
            logger.error("lalalalalala");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
 
    /**
     * �����ݿ���ȡ�ö���
     *
     * @param arg0
     *            ��������������
     * @param id
     *            �������id
     * @return:��Ҫ���ҵĶ���
     */
    public Object getObject(String className, int Id) {
        // �õ�������
        String tableName = className.substring(className.lastIndexOf(".") + 1,
                className.length());
        // ��������������Class����
        Class c = null;
        try {
            c = Class.forName(className);
 
        } catch (ClassNotFoundException e1) {
 
            e1.printStackTrace();
        }
        // ƴ�ղ�ѯsql���
        String sql = "select * from " + tableName + " where Pwd=" + Id;
        System.out.println("����sql��䣺" + sql);
        // ������ݿ�����
        Connection con = Connect2DBFactory.getDBConnection();
        // �������ʵ��
        Object obj = null;
        try { 
            Statement stm = con.createStatement();
            // �õ�ִ�в�Ѱ��䷵�صĽ����
            ResultSet set = stm.executeQuery(sql);
            // �õ�����ķ�������
            Method[] methods = c.getMethods();
            // ���������
            int i=1;
            while (set.next()) {
            	System.out.println("while�������ٱ飺"+i);
            	i++;
                obj = c.newInstance();
                
                // ��������ķ���
                for (Method method : methods) {
                    String methodName = method.getName();
                    // �������ķ�����set��ͷ
                    if (methodName.startsWith("set")) {
                        // ���ݷ������ֵõ����ݱ�����ֶε�����
                        String columnName = methodName.substring(3,
                                methodName.length());
                        // �õ������Ĳ�������
                        Class[] parmts = method.getParameterTypes();
                        if (parmts[0] == String.class) {
                            // �������ΪString���ͣ���ӽ�����а�������ȡ�ö�Ӧ��ֵ������ִ�и�set����
                            method.invoke(obj, set.getString(columnName));                            
                        }
                        if (parmts[0] == int.class) {
                            method.invoke(obj, set.getInt(columnName));
                        }
                    }
 
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
