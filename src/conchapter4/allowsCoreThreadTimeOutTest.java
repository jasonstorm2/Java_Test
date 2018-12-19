package conchapter4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * �̳߳��Ƿ���������̳߳�ʱ��������������߳����������񣬴��ڿ���״̬
 *                             �Ƿ��ڳ�ʱʱ���ɱ���̡߳�
 *                             
 * ThreadPoolExecutor.allowCoreThreadTimeOut(boolean value)�����������Ƿ�ɱ�����еĺ����߳�
 * 
 * �����̵߳Ķ�����new LinkedBlockingQueue<Runnable>() ���� new SynchronousQueue<Runnable>()
 * @author LiZhenhua
 *
 */
public class allowsCoreThreadTimeOutTest {
	
	public static void main(String[] args) {
		allowsCoreThreadTimeOutTest thisClass = new 
				allowsCoreThreadTimeOutTest();	
		thisClass.allowsCoreThreadTimeOutTest();
		thisClass.allowsCoreThreadTimeOutTest2();		
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
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());			

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	/**
	 * ��ͨ�̳߳�ִ���̣߳�û�����δ�����߳��쳣����
	 * @param runnable
	 */
	public void allowsCoreThreadTimeOutTest(){
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");

		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(4, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());	
		System.out.println("�Ƿ���������̳߳��У�"+executor.allowsCoreThreadTimeOut());
		executor.execute(myRunnable1);		
		executor.execute(myRunnable2);		
		executor.execute(myRunnable3);		
		executor.execute(myRunnable4);		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�̳߳�������߳�������"+executor.getPoolSize());
		
	}
	
	/**
	 * �̳߳� ����� δ�����̵߳Ĵ���
	 * �м����߳�δ������м�����Ӧ�Ķ���Ĵ���
	 * @param runnable
	 */
	public void allowsCoreThreadTimeOutTest2(){
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");

//		ThreadPoolExecutor executor = new 
//				ThreadPoolExecutor(4, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());	
//		
		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(4, 5, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());	
		
		executor.allowCoreThreadTimeOut(true);
		System.out.println("�Ƿ���������̳߳��У�"+executor.allowsCoreThreadTimeOut());
		executor.execute(myRunnable1);		
		executor.execute(myRunnable2);		
		executor.execute(myRunnable3);		
		executor.execute(myRunnable4);	
	    try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�̳߳�������߳�������"+executor.getPoolSize());		
	}

}
