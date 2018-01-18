package conchapter5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * �̳߳��Զ��屻�ܾ�����
 * ThreadPoolExecutor.setRejectedExecutionHandler(RejectedExecutionHandler handler)
 * 
 * submit�ύ�̱߳�����ܷ��أ�
 * execute�ύ�Ļ����
 * 
 * @author Administrator
 *
 */
public class RejectedExecutionHandlerTest {
	
	public static void main(String[] args) {
		RejectedExecutionHandlerTest thisClass = new RejectedExecutionHandlerTest();
		
		ExecutorService service = Executors.newCachedThreadPool();
		ThreadPoolExecutor executor = (ThreadPoolExecutor)service;
		//���þܾ�����
		executor.setRejectedExecutionHandler(thisClass.new MyRejectedExecutionHandler());
		executor.submit(thisClass.new MyRunnable("A"));
		executor.submit(thisClass.new MyRunnable("B"));
		executor.submit(thisClass.new MyRunnable("C"));
		executor.shutdown();
		executor.submit(thisClass.new MyRunnable("D"));
		
//		executor.execute(thisClass.new MyRunnable("A"));
//		executor.execute(thisClass.new MyRunnable("B"));
//		executor.execute(thisClass.new MyRunnable("C"));
//		executor.shutdown();
//		executor.execute(thisClass.new MyRunnable("D"));
//		
		
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
		}
		
	}

}
