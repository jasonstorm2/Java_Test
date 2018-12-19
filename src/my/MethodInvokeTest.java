package my;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Method���䣺
 * 1��Class����ͨ��getMethod�������Method����
 * 2��Method����ͨ��invoke��Class.newInstance���������÷���
 * Field���䣺
 * 1��Class����ͨ��getField�������Field����
 * 2��Field����ͨ��get��Class.newInstance������ �����ֵ
 * @author LiZhenhua
 *
 */
public class MethodInvokeTest {
	public static void main(String[] args){
		
		try {
			Class<?> newoneClass = Class.forName("my.Bird");//�ڱ����С���public��
			Object obj = newoneClass.newInstance();//ͨ�������ʵ����
			
			/**
			 * invoke(�������ڵĶ��󣬷����Ĳ���);----���󷽷�
			 * invoke(null,��������)��------------�෽��
			 */
			if(obj instanceof Bird){
				Method getType = newoneClass.getMethod("getType");//ͨ������󣬻�÷�������
				getType.invoke(obj);//��������invoke�����ʵ������������÷���
				
				Method setAge  = newoneClass.getMethod("setAge", int.class);
				setAge.invoke(obj, 121);//���󷽷�
				
				Method statement = newoneClass.getMethod("statement");
				statement.invoke(null);//�෽��
				
				Method statement2 = newoneClass.getMethod("statement2",String.class);
				statement2.invoke(null,"����");//�෽��
			}
			System.out.println("----------------------------------------------");
			if(newoneClass.isAssignableFrom(Bird.class)){
				Method getType = Bird.class.getMethod("getType");
				getType.invoke(obj);
				
				Method setAge  = Bird.class.getMethod("setAge", int.class);
				setAge.invoke(obj, 122);
				
				Method statement = Bird.class.getMethod("statement");//�෽��
				statement.invoke(null);//�෽��
				
				Method statement2 = Bird.class.getMethod("statement2",String.class);//�෽��
				statement2.invoke(null,"����");//�෽��
			}
			
			System.out.println("----------------------");
			/**�����������֮������Field��ֵ�Ƿ�ı�**/
			//���Ըı䣬��Ϊʵ���Ѿ�����--newoneClass.newInstance()�������Ѿ�Ϊʵ������Ĵ���ռ�
			Field age = newoneClass.getField("age");
			Field weight = newoneClass.getField("weight");
			Class<?> clazz= age.getType();
			if(clazz.isAssignableFrom(int.class)){
				int o = (int)age.get(obj);//��������ֵ
				System.out.println("�Ƿ�ı�����ֵ����"+o);
			}else{
				System.out.println("�Ⲣ����һ��int��ֵ");
			}
			int i = (int) weight.get(obj);
			System.out.println("������"+i);
			System.out.println("----------------------");
			/****ֱ������Field��ֵ����ͨ�����÷������ؼ��ǿ��ܲ�֪��Field�����͡�ǿ�Ƹ�ֵ�����*****/
			/**private ��ֵ����**/
			Field type = newoneClass.getDeclaredField("type");
			type.setAccessible(true);
			System.out.println("ԭ����ֵ��"+type.get(obj));
			type.set(obj, "fuckyou");
			System.out.println("�ı� ���ֵ��"+type.get(obj));
			/**public ��ֵ����**/
			Field weight2 = newoneClass.getField("weight");
			/**getInt��֪����������***/
			System.out.println("weightԭֵ��"+weight2.getInt(obj));
			/**get��obj������֪����������***/
			System.out.println("weightԭֵ��"+weight2.get(obj));
			weight2.setInt(obj, 50);
			System.out.println("weight�ı���ֵ��"+weight2.getInt(obj));
			weight2.set(obj, 51);
			System.out.println("weight�ı���ֵ��"+weight2.getInt(obj));

			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class Bird{
	public int age = 10;
	public int weight = 15;
	private String type = "little";
	
	public void getType(){
		System.out.println("����1getType:"+type);
	}
	
	public void setAge(int age){
		this.age = age;
		System.out.println("����2age:"+age);
	}
	
	public static void statement(){
		System.out.println("����3statement:����Է�");
	}
	
	public static void statement2(String str){
		System.out.println("����4statement:"+str+"���Է�");
	}	
}
