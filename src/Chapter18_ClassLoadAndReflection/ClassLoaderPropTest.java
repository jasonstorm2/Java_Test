package Chapter18_ClassLoadAndReflection;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;


public class ClassLoaderPropTest {
	static{
		System.out.println("����ʱ���Ƿ��ʼ������");
	}
	public static void main(String[] args) throws IOException {
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();//  get system loader ���ϵͳ���������
	
		System.out.println("ϵͳ�������:        "+systemLoader);
		/*
		 * ��ȡ ϵͳ������� �ļ���·��--ͨ����CLASSPATH �������� ָ����
		 * ���������ϵͳ û��ָ��CLASSPATH���������� �� Ĭ���Ե�ǰ·����Ϊ ϵͳ������� �ļ���·��
		 */
		Enumeration<URL> eml = systemLoader.getResources("");
		while(eml.hasMoreElements()){
	    System.out.println("SYSTEMClassLoader Route:  "+eml.nextElement());
		}
		//��ȡ ϵͳ������� �ĸ�����������õ� ��չ�������
		ClassLoader extenionLader = systemLoader.getParent();
		System.out.println("��չ�������:            "+extenionLader);
		System.out.println("��չ��������� ·��:      "+System.getProperty("java.ext.dirs"));
		System.out.println("��չ��������� ���ࣺ"+extenionLader.getParent());//The parent Classload is Bootstrap ClassLoader.BC is not use Java Language...So..
		//�������� û�� �̳�ClassLoader�����ࡣ���ԣ����ص���Null
		//��ʵ���� ��չ������� �� ��������� �� �����������ֻ�ǣ���������� ������ javaʵ�ֵġ�
		
	}
}
