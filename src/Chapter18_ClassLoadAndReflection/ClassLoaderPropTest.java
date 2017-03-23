package Chapter18_ClassLoadAndReflection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * ���ϵͳ��������� ClassLoader.getSystemClassLoader() 
 * ϵͳ������� �ļ���·��--ͨ����CLASSPATH �������� ָ����
 * ���������ϵͳ û��ָ��CLASSPATH���������� �� Ĭ���Ե�ǰ·����Ϊ ϵͳ������� �ļ���·��
 * @author Administrator
 *
 */
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
		
		System.out.println("------------------------------");
		System.out.println(ClassLoaderPropTest.class.getResource(""));
		System.out.println(ClassLoaderPropTest.class.getResource("/"));
		
		URL url = ClassLoaderPropTest.class.getResource("");	
		String ss = "D:\\jj\\kk";			//����·����D:\jj\kk
//		String ss = "file:///f:/1.swf";		//����·����D:\WorkSpace1\java_test\file:\f:\1.swf
//		String ss = "/long";				//����·����D:\long
//		String ss = "lo/ng";				//����·����D:\WorkSpace1\java_test\lo\ng
//		String ss = "/lo/ng";				//����·����D:\lo\ng
//		String ss = "\\long";				//����·����D:\long
//		String ss = "//long";				// ����·����\\long
//		String ss = "file:///D:/Test/source/xxx.rmvb";
		
		ClassLoaderPropTest.printContent(ss);
	}
	
	public static void printContent(String path) throws IOException{
		
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL url               = cl.getResource("");		
		Enumeration<URL> dirs = cl.getResources("");

		System.out.println("·������"+url);
		while(dirs.hasMoreElements()){				
				System.out.println("URL·����"+ dirs.nextElement());
			}
		System.out.println("----------------");
		File file = new File(path);
		String ab = file.getAbsolutePath();
		System.out.println("user.dir·����"+System.getProperty("user.dir"));
		System.out.println("����·����"+ab);
		System.out.println("File�Ƿ���ڣ�"+file.exists());
		System.out.println("last modify:"+file.lastModified());
		
		
	}
    
}
