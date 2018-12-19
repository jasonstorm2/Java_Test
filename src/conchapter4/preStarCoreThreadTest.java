package conchapter4;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * �̳߳�Ԥ�����������߳�׼����������
 * 
 * executor.prestartCoreThread()��ÿ������һ�������̣߳��ɹ�����true����������߳�����������false
 * executor.prestartAllCoreThreads()�� �������еĺ����̣߳����������ĺ����߳�����
 * @author LiZhenhua
 *
 */
public class preStarCoreThreadTest {
	
	public static void main(String[] args) {
		preStarCoreThreadTest thisClass = new 
				preStarCoreThreadTest();	
//		thisClass.preStarCoreThreadTest();
		thisClass.preStarCoreThreadTest2();		
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
	public void preStarCoreThreadTest(){		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		System.out.println("�Ƿ������˺����̣߳�"+executor.prestartCoreThread());
		System.out.println("��ǰ�̳߳��߳�������"+executor.getPoolSize());
		System.out.println("�Ƿ������˺����̣߳�"+executor.prestartCoreThread());
		System.out.println("��ǰ�̳߳��߳�������"+executor.getPoolSize());
		System.out.println("�Ƿ������˺����̣߳�"+executor.prestartCoreThread());
		System.out.println("��ǰ�̳߳��߳�������"+executor.getPoolSize());
		System.out.println("�Ƿ������˺����̣߳�"+executor.prestartCoreThread());
		System.out.println("��ǰ�̳߳��߳�������"+executor.getPoolSize());
		executor.allowCoreThreadTimeOut(true);
		
	}
	
	/**
	 * �̳߳� ����� δ�����̵߳Ĵ���
	 * �м����߳�δ������м�����Ӧ�Ķ���Ĵ���
	 * @param runnable
	 */
	public void preStarCoreThreadTest2(){	
		
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());	
		
		System.out.println("�Ƿ������˺����̣߳�"+executor.prestartAllCoreThreads());
		System.out.println("��ǰ�̳߳��߳�������"+executor.getPoolSize());
		System.out.println("�Ƿ������˺����̣߳�"+executor.prestartCoreThread());
		System.out.println("��ǰ�̳߳��߳�������"+executor.getPoolSize());
		System.out.println("�Ƿ������˺����̣߳�"+executor.prestartCoreThread());
		System.out.println("��ǰ�̳߳��߳�������"+executor.getPoolSize());
		System.out.println("�Ƿ������˺����̣߳�"+executor.prestartCoreThread());
		System.out.println("��ǰ�̳߳��߳�������"+executor.getPoolSize());
		executor.allowCoreThreadTimeOut(true);
	
	}

}
