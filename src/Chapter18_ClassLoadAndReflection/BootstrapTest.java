package Chapter18_ClassLoadAndReflection;

import java.net.URL;


//�� JVM ����ʱ�����γ��� �������������ɵ� ��ʼ���������νṹ
// Bootstrap ClassLoader
// Extension ClassLoader
// System ClassLoader  JVM����ʱ���� java���� -classpathѡ�java.class.pathϵͳ���ԣ���CLASSPATH����������ָ����JAR������·����
public class BootstrapTest {
	public static void main(String[] args) {
		// ��ȡ��������������ص�ȫ��URL����
//		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();	//������ᾯ��
//		//���� ������������ ���ص�ȫ��URL
//		for (int i = 0; i < urls.length; i++) {
//			System.out.println(urls[i].toExternalForm());
//		}
		
	}

}
