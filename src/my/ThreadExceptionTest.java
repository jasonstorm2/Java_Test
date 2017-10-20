package my;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

/**
 * δ����쳣��������ERROR �� RuntimeException�������쳣
 * �Ѽ���쳣�� ��δ����쳣����쳣
 * 
 * һ�����������������п����׳����Ѽ���쳣����δ����쳣Ҫ�𲻿ɿ���(ERROR)��Ҫô��Ӧ�ñ��ⷢ��(RuntimeException)
 * @author Administrator
 *
 */
public class ThreadExceptionTest {
	public static void main(String[] args) {
		ThreadExceptionTest thisClass = new ThreadExceptionTest();
//		thisClass.tracePrint();
//		thisClass.tracePrint2();		
//		thisClass.tracePrint3();		
//		thisClass.tracePrint4();		
//		thisClass.tracePrint4();	
		thisClass.factorial(5);
	}
	
	/**
	 * �߳��׳��쳣������trycatch���񣬲�Ȼ�ᱨ����
	 * ���̵߳��ã����׳��쳣��ӡ��Ϣ
	 * 
	 */
	private void thread1(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
					try {
						throw new Exception("this is a exception");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();						
						System.out.println("�쳣���֣�"+e.getClass().getName());
					}				
			}
		}).start();
	}
	
	private void thread2(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
					try {
//						throw new ThreadExceptionTest(); //�׳��Ķ��������һ���쳣
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();						
						System.out.println("�쳣���֣�"+e.getClass().getName());
					}				
			}
		}).start();
	}
	
	/**
	 * ���Դ�ӡһ���׳��쳣�Ķ�ջ����
	 * ��ӡ��Throwable���ڵ���
	 */
	private void tracePrint(){
		Throwable t = new Throwable();		
		t.printStackTrace();		
	}
	
	/**
	 * ���Դ�ӡһ���׳��쳣�Ķ�ջ����
	 * û�д�ӡ����
	 */
	private void tracePrint2(){
		Throwable t = new Throwable();		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream print = new PrintStream(out);
		t.printStackTrace(print);
		String description = print.toString();
		System.out.println("��ӡ��"+description);
	}
	
	/**
	 * ��ӡ��ջ������Ϣ��ͨ��getStackTrace����
	 * ��ӡ����Throwable���ڵ��У��Լ����ú���Throwable�����ĵ���������
	 * 
	 */
	private void tracePrint3(){
		Throwable t = new Throwable();		
		StackTraceElement[] elements = t.getStackTrace();
		for (StackTraceElement stackTraceElement : elements) {
			
			System.out.println("����  ��"+stackTraceElement.getClassName());
			System.out.println("�ļ�����"+stackTraceElement.getFileName());
			System.out.println("�ڼ��У�"+stackTraceElement.getLineNumber());
			System.out.println("��������"+stackTraceElement.getMethodName());			
			System.out.println("��ӡλ�ã�"+stackTraceElement.toString());
			System.out.println(stackTraceElement.toString());
			System.out.println(stackTraceElement);
			
		}

	}
	
	/**
	 * ��ȡ�����̵߳Ķ�ջ��Ϣ
	 */
	private void tracePrint4(){
		Map<Thread,StackTraceElement[]> map = Thread.getAllStackTraces();
		
		for(Thread t:map.keySet()){
			System.out.println("�̵߳�����****��"+t.getName());
			StackTraceElement[] elements = map.get(t);
			for (StackTraceElement stackTraceElement : elements) {				
				System.out.println("����  ��"+stackTraceElement.getClassName());
				System.out.println("�ļ�����"+stackTraceElement.getFileName());
				System.out.println("�ڼ��У�"+stackTraceElement.getLineNumber());
				System.out.println("��������"+stackTraceElement.getMethodName());			
				System.out.println("��ӡλ�ã�"+stackTraceElement.toString());
				System.out.println(stackTraceElement.toString());				
			}			
		}
	}
	
	
	private int factorial(int n){
		
		System.out.println("factorial("+n+")");
		Throwable t = new Throwable();		
		StackTraceElement[] elements = t.getStackTrace();
		for (StackTraceElement stackTraceElement : elements) {
			System.out.println(stackTraceElement.toString()+":"+n);
		}
		int r;
		if(n <= 1)r = 1;
		else r = n* factorial(n-1);
		System.out.println("return"+r);
		return r;		
	}

}
