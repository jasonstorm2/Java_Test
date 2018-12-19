package conchapter4;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * �̳߳� ִ���߳����񱻾ܾ�ʱ�Ĵ�����Ϊ��������ĳ���̱߳��ܾ����������Ӧ�Ĳ���
 * 
 * ���Զ���һ��RejectedExecutionHandler�ӿڵ�ʵ���࣬
 * Ȼ�����ThreadPoolExecutor.setRejectedExecutionHandler(RejectedExecutionHandler handler)
 * ֻҪ��δ������̣߳������Ե���RejectedExecutionHandlerʵ����Ĵ�����
 * 
 * 
 * 
 * @author LiZhenhua
 *
 */
public class setGetRejectedExceptionHandlerTest {
	
	public static void main(String[] args) {
		setGetRejectedExceptionHandlerTest thisClass = new 
				setGetRejectedExceptionHandlerTest();	
//		thisClass.catchExceptionTest();
		thisClass.catchExceptionTest2();		
	}	

	
	// �Զ����߳�
	class MyRunnable implements Runnable{
		private String username;
		
		public MyRunnable(String username){
			this.username = username;
		}
		
		public String getUsername(){
			return username;
		}
		
		public void setUsername(String username){
			this.username = username;
		}

		@Override
		public void run() {
			try {
				
				System.out.println(Thread.currentThread().getName() +" run! "+System.currentTimeMillis());
				Thread.sleep(4000);
				System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());			

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * �Զ��屻�ܾ�ִ�еĴ���Handler
	 * @author LiZhenhua
	 *
	 */
	class MyRejectedExecutionHandler implements RejectedExecutionHandler{
		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			System.out.println(((MyRunnable) r).getUsername() + "���ܾ�ִ��");
			
		}		
	}

	/**
	 * ��ͨ�̳߳�ִ���̣߳�û�����δ�����߳��쳣����
	 * @param runnable
	 */
	public void catchExceptionTest(){
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");

		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 999, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());		
		executor.execute(myRunnable1);		
		executor.execute(myRunnable2);		
		executor.execute(myRunnable3);		
		executor.execute(myRunnable4);		
	}
	
	/**
	 * �̳߳� ����� δ�����̵߳Ĵ���
	 * �м����߳�δ������м�����Ӧ�Ķ���Ĵ���
	 * @param runnable
	 */
	public void catchExceptionTest2(){
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");
		MyRunnable myRunnable5 = new MyRunnable("A5");
		MyRunnable myRunnable6 = new MyRunnable("A6");
		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 999, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());	
		executor.setRejectedExecutionHandler(new MyRejectedExecutionHandler());		
		executor.execute(myRunnable1);		
		executor.execute(myRunnable2);		
		executor.execute(myRunnable3);		
		executor.execute(myRunnable4);	
		executor.execute(myRunnable5);		
		executor.execute(myRunnable6);			
		
		System.out.println(executor.getRejectedExecutionHandler().toString());		
	}

}
