package conchapter4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * �̳߳��У����ֶ�������ܴ�������������Լ������������ı���
 * LinkedBlockingDeque
 * ArrayBlockingQueue
 * SynchronousQueue
 * 
 * @author LiZhenhua
 *
 */
public class QueueMaxFullTest {
	
	public static void main(String[] args) {
		QueueMaxFullTest thisClass = new 
				QueueMaxFullTest();	
//		thisClass.LinkedBlockingDequeTest();
//		thisClass.ArrayBlockingQueueTest();		
		thisClass.SynchronousQueueTest();
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
	 * 
	 * LinkedBlockingDeque �������������
	 * 
	 * ������ʹ���вι��죬maxֵ���ο�
	 * �̳߳����������ǣ�max����3+��������2 =5���������������󣬶�������񽫻ᱨ��
	 * 
	 * ��ʹ���޲νṹ�����е����������޵ġ�ÿ��ִ�����񣬵�ǰ�߳���core�̣߳�������ڶ��еȴ�ִ��
	 * @param runnable
	 */
	public void LinkedBlockingDequeTest(){		
		MyRunnable myRunnable = new MyRunnable("A1");
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	
//		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>();		

		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
//		executor.execute(myRunnable);//6	
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
	}
	
	
	/**
	 * ArrayBlockingQueue<Runnable>���У�ֻ���в����Ĺ�������û���޲����Ĺ�����
	 * 
	 * ��Ҫִ�е��߳��������� ���д�С+ max�߳�ʱ ������������޷�ִ�У��ᱨ��
	 * 
	 */
	public void ArrayBlockingQueueTest(){		
		MyRunnable myRunnable = new MyRunnable("A1");
//		ArrayBlockingQueue<Runnable> linked = new ArrayBlockingQueue<Runnable>(2);	
		ArrayBlockingQueue<Runnable> linked = new ArrayBlockingQueue<Runnable>(1);		

		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
//		executor.execute(myRunnable);//6	
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
	}
	
	
	/**
	 * 
	 * SynchronousQueue<Runnable> ��ֻ���޲����Ĺ�����
	 * �̳߳��ܴ��������߳����� max,һ�������ͻᱨ��
	 * 
	 */
	public void SynchronousQueueTest(){
		MyRunnable myRunnable = new MyRunnable("A1");
		SynchronousQueue<Runnable> linked = new SynchronousQueue<Runnable>();	

		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
	}

}
