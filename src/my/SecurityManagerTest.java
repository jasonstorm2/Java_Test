package my;

import java.io.IOException;

/**
 * java ��ȫ���������
 * 
 * SecurityManagerӦ�ó���
 * ������δ֪��Java�����ʱ�򣬸ó�������ж�����루ɾ��ϵͳ�ļ�������ϵͳ�ȣ���Ϊ�˷�ֹ���ж�������ϵͳ����Ӱ�죬
 * ��Ҫ�����еĴ����Ȩ�޽��п��ƣ���ʱ���Ҫ����Java��ȫ��������
 * 
 * ������ȫ���������ַ�ʽ������ʹ������������ʽ��
 * 1.
 * ���������ʱ��ͨ�����Ӳ���������ȫ��������
 * -Djava.security.manager
 * 
 * ��Ҫͬʱָ�������ļ���λ����ôʾ�����£�
 * -Djava.security.manager -Djava.security.policy="E:/java.policy"
 * 
 * 2.
 * Ҳ����ͨ�����뷽ʽ���������������飺
 * System.setSecurityManager(new SecurityManager());
 * 
 * Runtime.exec("cmd����");
 * Runtime���װ������ʱ�Ļ�����ÿ�� Java Ӧ�ó�����һ�� Runtime ��ʵ����ʹӦ�ó����ܹ��������еĻ��������ӡ�
 * 
 * @author LiZhenhua
 *
 */
public class SecurityManagerTest {
	public static void main(String[] args) {
		
		   System.out.println("SecurityManager: " + System.getSecurityManager());
		
		try {//rmdir /s/q 222  ɾ������Ŀ¼			
			
			/**
			 * 
			 * rmdir/rd  ɾ���ļ���
			 * del ɾ���ļ�
			 * 
			 * ΪʲôҪ���� /c��/k ����ɾ���ļ���
			 * 
			 * /s���ǽ�Ŀ��Ŀ¼�µ������ļ�����Ŀ¼�ļ�ɾ�� 
			 * /q������ȷ��ɾ�� 
			 * 
			 * cmd /c dir����ִ����dir�����ر�����ڣ�	
			 * cmd /k dir����ִ����dir����󲻹ر�����ڡ�
			 * /F ǿ��
			 * 
			 */
			Runtime.getRuntime().exec("cmd /k rmdir  /S /Q D:\\java��ȫ�������\\aa");
			// ɾ��ָ�����ļ���/k del /f/s/q dirname> nul
//			Runtime.getRuntime().exec("cmd /k del /F /S /Q D:\\java��ȫ�������\\aa\\dd.txt");
//			Runtime.getRuntime().exec("cmd /k rmdir  /S /Q D:\\java��ȫ�������\\aa");
//			Runtime.getRuntime().exec("cmd /k rmdir  /S /Q D:\\java��ȫ�������\\aa");
			Runtime.getRuntime().exec("Shutdown.exe -r -t 5");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
