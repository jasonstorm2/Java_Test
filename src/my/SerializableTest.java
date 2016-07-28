package my;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Administrator
 *1.���л��ܱ����Ԫ�� 
 *  ���л�ֻ�ܱ������ķǾ�̬��Ա���������ܱ����κεĳ�Ա�����;�̬�ĳ�Ա���������Ҵ��л������ֻ�Ǳ�����ֵ�����ڱ������κ����η������ܱ��档
 *  
 *  2.transient�ؼ��� 

            ����ĳЩ���͵Ķ�����״̬��˲ʱ�ģ������Ķ������޷�������״̬�ġ�����һ��Thread�����һ��FileInputStream���� ��
            ������Щ�ֶΣ����Ǳ�����transient�ؼ��ֱ�������������������롣 
            
            ���� �����л������漰�������ŵ� �����ϻ��������Ϸ������ݣ���ʱ��ͻ������ȫ���⡣��Ϊ����λ��Java���л���֮�⣬����Java��ȫ���ƵĿ���֮�С�
            ������Щ��Ҫ���ܵ��ֶΣ���Ӧ���������ý����� �����߲�Ӧ�򵥵ز��Ӵ���ر������� ��Ϊ�˱�֤��ȫ�ԡ�
            Ӧ������Щ�ֶ�ǰ����transient�ؼ��֡�
 */
public class SerializableTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(11);
		list.add(22);
		Student stu2 = new Student("jason", 229, "male",list);
		/*
		 * "/"���ŵȼ���"\\"
		 * �������� FileOutputStream��·����·��ָʾ���ļ������ڣ���ô������������ļ�
		 * 
		 */
		/********д��ָ��λ���ļ�*********/
//		try {
//			FileOutputStream fo = new FileOutputStream("E:/Serializable.ser");
//			ObjectOutputStream oo = new ObjectOutputStream(fo);
//			
//			oo.writeObject(stu2);
//			oo.close();
//			System.out.println("д��ɹ�");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		/********����ָ��λ���ļ�*********/
		

		try {
			System.out.println("����.....");
			FileInputStream fi = new FileInputStream("E:/Serializable.ser");
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Student s = (Student)oi.readObject();
			System.out.println("���֣�"+s.name);
			System.out.println("�Ա�"+s.sex);
			System.out.println("���䣺"+s.age);
			List<Integer> l = s.list;
			for(Integer i:l){
				System.out.println("list:"+i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}


class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List list;
	
	String name;
	int age ;
	String sex;
	
	public Student(String name,int age,String sex,List<Integer> list) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.list = list;
	}	
}

class LoggingInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Date loggingDate = new Date();
	private String uid;
	private transient String pwd;

	LoggingInfo(String user, String password) {
		uid = user;
		pwd = password;
	}

	public String toString() {
		String password = null;
		if (pwd == null) {
			password = "NOT SET";
		} else {
			password = pwd;
		}
		return "logon info: \n   " + "user: " + uid + "\n   logging date : "
				+ loggingDate.toString() + "\n   password: " + password;
	}

	public static void main(String[] args) {
		LoggingInfo logInfo = new LoggingInfo("MIKE", "MECHANICS");
		System.out.println(logInfo.toString());
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
					"logInfo.out"));
			o.writeObject(logInfo);
			o.close();
		} catch (Exception e) {// deal with exception
		}
		// To read the object back, we can write
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"logInfo.out"));
			LoggingInfo logInfo1 = (LoggingInfo) in.readObject();
			System.out.println(logInfo1.toString());
		} catch (Exception e) {// deal with exception
		}
	}
}