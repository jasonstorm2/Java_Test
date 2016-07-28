package Chapter18;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ObjectPoolFactory {
	/*
	 * ����һ������أ�ǰ���Ƕ��������������Ǽ�����
	 */
	private Map<String, Object> objectPool = new HashMap<String, Object>();
	private Properties props = new Properties();
	
	/*��������������ʵ����һ�������*/
	private Object createObject(String clazzName) throws Exception{
		/*
		 * ʹ��װ�ڵ�ǰ�ࣨObjectPoolFactory����װ������������Class.forName(className,true, this.getClass().getClassLoader())
		 * loading,linking,initializing
		 * �ڶ�������true��˵��Ҫinitializing�����
		 */
		Class<?> clazz = Class.forName(clazzName);//--ʹ�õ��Ǹ��ֵ���İ����ļ���ȫ��������classpath�ļ���ȫ���������Ǿ���·����
		/*
		 * ClassLoader.loadClass(className)�����ڲ�����ClassLoader.loadClass(name,false)�������ڶ�������ָ��Class��load֮���Ƿ����link������
         * ����ͳ����ˡ�Class.forName(className)װ�ص�class�Ѿ���ʵ��������ClassLoader.loadClass(className)װ�ص�class��û�б�link�����Ծ͸�̸����ʵ�����ˡ�
		 */
	    //ClassLoader cl = new  ClassLoader();  //--ʹ��һ���Լ�ָ����װ����
		//Class c1= cl.loadClass(String className, boolean resolve );  
		return clazz.newInstance();
	}
	
	/*
	 * �÷��� ����ָ���ļ� ����ʼ�� �����
	 * ������� �����ļ� ����������
	 */
	
	public void init(String fileName) throws Exception{
		try(FileInputStream fis = new FileInputStream(fileName)){
			//ֱ�Ӵ������ļ�����ȡ���Զ���
			props.load(fis);

		}catch(IOException ex){
			System.out.println("��ȡ"+fileName+"�쳣");
		}
	}
	/*
	 * ���ַ�ʽҪ��properties�ļ� ����Ŀ�µĸ�������Ӧ�İ���--���·��1
	 */
	public void getPropertiesFromHome(){
		try {
			InputStream in = getClass().getResourceAsStream("properties.properties");
//			FileInputStream in = new FileInputStream("properties.properties"); //����������һ��
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * ���ַ�ʽҪ��properties�ļ� ����Ŀ��--���·��2
	 */
	public void getPropertiesFromProject(){
		try {
			FileInputStream in = new FileInputStream("properties.properties");
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printNames() throws Exception{
		for (String name : props.stringPropertyNames()) {
			System.out.println(name);
		}
	}
	/*
	 * ÿȡ��һ��key-value�ԣ��͸���value����һ������
	 * ����createObject�������󣬲���������ӵ��������
	 */
	public void initPool() throws Exception{
		for (String name : props.stringPropertyNames()) {
			if(!name.contains("%")){
				objectPool.put(name, createObject(props.getProperty(name)));
			}
		}
	}
	
	/*
	 * �÷���������� �����ļ� ������ ָ������� setter����
	 */
	public void intProperty() throws Exception{
		for (String name : props.stringPropertyNames()) {
			if(name.contains("%")){
				String[] strArr = name.split("%");
				
				Object target = getObject(strArr[0]);
				
				String mtdName = "set"+strArr[1].substring(0, 1).toUpperCase() + strArr[1].substring(1);
				
				Class<?> targetClass = target.getClass();
				
				Method mtd = targetClass.getMethod(mtdName, String.class);//����ָ�����ƣ��������͵� public Method ����
				//invoke��Ҫ������ ���ʵ�Ȩ�� �����÷���setAccessible(boolean b)������ private ����
				mtd.invoke(target, props.getProperty(name));	//target���������Ķ��󣬷�������Ĳ���
			}			
		}
	}
	
	
	public Object getObject(String name){
		return objectPool.get(name);
	}
	
	public static void main(String[] args) throws Exception {
		/***����·������--��ʼ***/
//		String str = "E:/my/myl/moon.class";
//		Class<?> clazz = Class.forName(str);//--�����þ���·���������
		/***����·������--����***/
		ObjectPoolFactory pf = new ObjectPoolFactory();
		pf.init("E:/properties.properties");//���������ļ�--����·��
		pf.initPool();//
		pf.intProperty();
		System.out.println("pf************:"+pf);
		System.out.println("a: "+pf.getObject("a"));
		System.out.println("b: "+(JFrame)pf.getObject("b"));
		System.out.println("c: "+((Hello)pf.getObject("c")).hh);
		JFrame j = (JFrame)pf.getObject("b");
		JLabel b = (JLabel)pf.getObject("d");
		j.add(b);
		j.setSize(500, 500);
		j.setVisible(true);
		j.setTitle("����");		
		
		/**�����Ƚ�**/
//		pf.getPropertiesFromProject();--���·��1
//		pf.printNames();
//		System.out.println("---------------------------");
//		pf.getPropertiesFromHome();--���·��2
//		pf.printNames();
	}

}
