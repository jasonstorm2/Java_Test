package conchapter5;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * submit �� execute������
 * 
 * submit�� �з���ֵ  ���Բ����쳣������get������
 * execute���޷���ֵ  �����쳣ֱ�Ӵ�ӡ��Ϣ  Ҳ���Բ����쳣���Զ����̹߳�����
 * 
 * @author Administrator
 *
 */
public class submitAndExecuteTest {
	
	public static void main(String[] args) {
		submitAndExecuteTest thisClass = new submitAndExecuteTest();
		
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(2, 3, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		try {
			Future<String> fu = executor.submit(thisClass.new MyCallable("A"));// �����쳣
			System.out.println("Future�����쳣��"+fu.get());  //��get�������ܲ����쳣
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("���쳣�׳�");
			e.printStackTrace();
		}

		executor.execute(thisClass.new MyRunnable("B"));// ֱ���׳��쳣		
//		executor.setThreadFactory(new ThreadFactory() {
//			
//			@Override
//			public Thread newThread(Runnable r) {
//				Thread t = new Thread(r);
//				t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
//					
//					@Override
//					public void uncaughtException(Thread t, Throwable e) {
//						System.out.println("execute()����ͨ��ʹ���Զ���");
//						System.out.println("ThreadFactoryҲ�ܲ����쳣��");
//						e.printStackTrace();					
//					}
//				});
//				
//				return t;
//			}
//		});
//		executor.execute(thisClass.new MyRunnable("B"));//

	}
	
	
	
	
	class MyRejectedExecutionHandler implements RejectedExecutionHandler{

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//			 TODO Auto-generated method stub
//			System.out.println(((FutureTask)r).toString() + "���ܾ�");//submit���ᱨ��
//			System.out.println(((MyRunnable)r).name + "���ܾ�");//execute���ᱨ��
			System.out.println("��ӳ�����");
		}
		
	}
	
	
	class MyRunnable implements Runnable{
		
		private String name;
		
		public MyRunnable(String name){
			this.name = name;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(name+"��������");		
			Integer.parseInt("a");
			System.out.println("�������");
		}
		
	}
	
	class MyCallable implements Callable<String>{
		
		private String name;
		
		public MyCallable(String name){
			this.name = name;
		}

		@Override
		public String call() {
			// TODO Auto-generated method stub
			System.out.println(name+"��������");		
			Integer.parseInt("a");
			System.out.println("�������");
			return name;			
		}
		
	}

}
