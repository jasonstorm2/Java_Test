package conchapter4;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * �����Ѿ�ִ����ϵ���������
 * @author Administrator
 *
 */
public class getCompletedTaskCountTest {
	
	public static void main(String[] args) {
		getCompletedTaskCountTest thisClass = new 
				getCompletedTaskCountTest();	
		thisClass.getCompletedTaskCount();
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
	 * @param runnable
	 */
	public void getCompletedTaskCount(){	

		try {
			MyRunnable myRunnable = new MyRunnable("A1");
			ThreadPoolExecutor executor = new 
					ThreadPoolExecutor(2, 5, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			executor.allowCoreThreadTimeOut(true);
			
			executor.execute(myRunnable);
			executor.execute(myRunnable);
			executor.execute(myRunnable);
			executor.execute(myRunnable);
			executor.execute(myRunnable);
			System.out.println("��ǰ�̳߳��߳�������" + executor.getPoolSize());		
			System.out.println("�Ѿ�ִ����ϵ�����������"+executor.getCompletedTaskCount());
			Thread.sleep(300);
			System.out.println("��ǰ�̳߳��߳�������" + executor.getPoolSize());		
			System.out.println("�Ѿ�ִ����ϵ�����������"+executor.getCompletedTaskCount());
			Thread.sleep(1000);
			System.out.println("��ǰ�̳߳��߳�������" + executor.getPoolSize());		
			System.out.println("�Ѿ�ִ����ϵ�����������"+executor.getCompletedTaskCount());
			Thread.sleep(4000);
			System.out.println("��ǰ�̳߳��߳�������" + executor.getPoolSize());		
			System.out.println("�Ѿ�ִ����ϵ�����������"+executor.getCompletedTaskCount());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
