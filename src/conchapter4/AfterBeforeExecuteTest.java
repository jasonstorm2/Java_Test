package conchapter4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * �̳߳ش�����һ������ ǰ��Ĳ���
 * �̳߳� remove(runnable)����
 * 
 * �̳�ThreadPoolExecutor�࣬��д����������afterExecute beforeExecute
 * 
 * @author LiZhenhua
 *
 */
public class AfterBeforeExecuteTest {
	
	public static void main(String[] args) {
		AfterBeforeExecuteTest thisClass = new 
				AfterBeforeExecuteTest();	
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
	 * @author LiZhenhua
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
			System.out.println("�̳߳ش����������,������:"+((MyRunnable)r).username);
		}
		
		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			// TODO Auto-generated method stub
			super.beforeExecute(t, r);
			System.out.println("�̳߳ؿ�ʼ����һ������,������:"+((MyRunnable)r).getUsername());
		}
		
	}


	/**
	 * 
	 * LinkedBlockingDeque �������������
	 * @param runnable
	 */
	public void executeTest(){		
		MyRunnable myRunnable1 = new MyRunnable("A1");
		MyRunnable myRunnable2 = new MyRunnable("A2");
		MyRunnable myRunnable3 = new MyRunnable("A3");
		
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		MyThreadPoolExecutor executor = new 
				MyThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked);		
		
		executor.allowCoreThreadTimeOut(true);		
		executor.execute(myRunnable1);//1
		executor.execute(myRunnable2);//2
		executor.execute(myRunnable3);//3
	
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
	}	


}
