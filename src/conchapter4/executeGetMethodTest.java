package conchapter4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 
 * �������ThreadPoolExecutor�ĸ���get������
 * 
 * 
 * executor.getActiveCount());  			����ִ��������߳���
 * executor.getCompletedTaskCount());		�ж��ٸ��߳��Ѿ�ִ��������
 * executor.getCorePoolSize());	        	�̳߳صĺ����߳���
 * executor.getMaximumPoolSize());			�̳߳ص�����߳���
 * executor.getPoolSize());					�̳߳��е��߳����������Ƿ����
 * executor.getTaskCount());				�ж��ٸ����񷢸����̳߳�
 * 
 * �Լ����ӿ�runnable���̳߳���ִ��ʱ����ġ�������
 * 
 * @author Administrator
 *
 */
public class executeGetMethodTest {
	
	public static void main(String[] args) {
		executeGetMethodTest thisClass = new 
				executeGetMethodTest();	
		thisClass.executeTest();
		
		
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
				
				System.out.println(this.username + " "+Thread.currentThread().getName() +" run! "+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println(this.username + " "+Thread.currentThread().getName() +" over "+System.currentTimeMillis());			

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * �̳�ThreadPoolExecutor����дִ��ǰ��ִ�к���������
	 * ������������д
	 * @author Administrator
	 *
	 */
	class  MyThreadPoolExecutor extends ThreadPoolExecutor{

		public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
				long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			// TODO Auto-generated method stub
			super.afterExecute(r, t);
		}
		
		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			// TODO Auto-generated method stub
			super.beforeExecute(t, r);
		}
		
	}


	/**
	 * 
	 * @param runnable
	 */
	public void executeTest(){		
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		MyRunnable myRunnable4 = new MyRunnable("A4");
		MyRunnable myRunnable5 = new MyRunnable("A5");
		
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>();	

		MyThreadPoolExecutor executor = new 
				MyThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);		
		
		executor.allowCoreThreadTimeOut(true);		
		executor.execute(myRunnable1);//1
		executor.execute(myRunnable2);//2
		executor.execute(myRunnable3);//3
		executor.execute(myRunnable4);//3
		executor.execute(myRunnable5);//3
		executor.execute(myRunnable5);//3
		executor.execute(myRunnable5);//3
		executor.execute(myRunnable5);//3
		
	
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
		
		System.out.println("�ж��ٸ��߳�����ִ������"+executor.getActiveCount());
		System.out.println("�ж��ٸ��߳��Ѿ�ִ��������"+executor.getCompletedTaskCount());
		System.out.println("corePool�߳��м�����"+executor.getCorePoolSize());
		System.out.println("maxPool�߳��м���"+executor.getMaximumPoolSize());
		System.out.println("�̳߳��У��߳��м���"+executor.getPoolSize());
		System.out.println("�ж��ٸ����񷢸����̳߳�"+executor.getTaskCount());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�ж��ٸ��߳��Ѿ�ִ��������"+executor.getCompletedTaskCount());
		
		
	}	


}
