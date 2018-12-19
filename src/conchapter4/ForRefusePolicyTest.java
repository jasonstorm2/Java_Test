package conchapter4;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 
 * �̳߳��е���Դȫ����ռ��ʱ�����ֶ����������Ĳ���
 * 
 * 1:AbortPolicy  �̳߳���ʱ�������������ʱ���׳��쳣RejectedExecutionException
 * 
 * 2:CallerRunsPolicy �̳߳���ʱ��ʹ�����̳߳ص��߳�ִ�и����񣬴˴���main�߳�ִ������,main������
 * 
 * 3.DiscardOldestPolicy ������ɵ��������������
 * 
 * 4.DiscardPolicy ��������ӵ��̳߳��б��ܾ�ʱ,�ѱ��ܾ�����������
 * 
 * 
 * @author LiZhenhua
 *
 */
public class ForRefusePolicyTest {
	
	public static void main(String[] args) {
		ForRefusePolicyTest thisClass = new 
				ForRefusePolicyTest();	
//		thisClass.AbortPolicyTest();
//		thisClass.CallerRunsPolicyTest();		
//		thisClass.DiscardOldestPolicyTest();
		thisClass.DiscardPolicyTest();
		
		
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
	 * 
	 * LinkedBlockingDeque �������������
	 * @param runnable
	 */
	public void AbortPolicyTest(){		
		MyRunnable myRunnable = new MyRunnable("A1");
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked,new ThreadPoolExecutor.AbortPolicy());
		
		
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
		executor.execute(myRunnable);//6	
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
	}
	
	
	/**
	 * 
	 * ���ԣ������̳߳ص��߳�ִ�ж��������
	 * 
	 * һ�������е��߳��� �� �̳߳�����߳��� max + �����̳߳ص��߳�1  =  max +1 
	 * 
	 */
	public void CallerRunsPolicyTest(){		
		MyRunnable myRunnable = new MyRunnable("A1");
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked,new ThreadPoolExecutor.CallerRunsPolicy());
		
		
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(myRunnable);//1
		executor.execute(myRunnable);//2
		executor.execute(myRunnable);//3
		executor.execute(myRunnable);//4
		executor.execute(myRunnable);//5
		executor.execute(myRunnable);//6	
		executor.execute(myRunnable);//7
		executor.execute(myRunnable);//8
		executor.execute(myRunnable);//9
		executor.execute(myRunnable);//10
		executor.execute(myRunnable);//11
		executor.execute(myRunnable);//12
		
		
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
	}
	
	
	/**
	 * DiscardOldestPolicy���ԣ���������ӵ��̳߳��б��ܾ�ʱ���̳߳ػ�����ȴ������У�
	 *                          ��ɵ�δ�������񣬰ѱ��ܾ���������ӽ��ȴ�����
	 */
	public void DiscardOldestPolicyTest(){		
		MyRunnable a1 = new MyRunnable("A1");
		MyRunnable a2 = new MyRunnable("A2");
		MyRunnable a3 = new MyRunnable("A3");
		MyRunnable a4 = new MyRunnable("A4");
		MyRunnable a5 = new MyRunnable("A5");
		MyRunnable a6 = new MyRunnable("A6");
		MyRunnable a7 = new MyRunnable("A7");
		
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked,new ThreadPoolExecutor.DiscardOldestPolicy());		
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(a1);//1
		executor.execute(a2);//2
		executor.execute(a3);//3	
		executor.execute(a4);//4	
		executor.execute(a5);//4	
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
		Iterator<Runnable> iterator = linked.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			System.out.println("����������֣�"+((MyRunnable)obj).getUsername());
			 
		}		
		executor.execute(a6);//6
		executor.execute(a7);//6
		
		System.out.println("------------------");
		iterator = linked.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			System.out.println("����������֣�"+((MyRunnable)obj).getUsername());
			 
		}
		
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
	}
	
	
	
	/**
	 * DiscardPolicy���ԣ���������ӵ��̳߳��б��ܾ�ʱ,�ѱ��ܾ�����������
	 */
	public void DiscardPolicyTest(){		
		MyRunnable a1 = new MyRunnable("A1");
		MyRunnable a2 = new MyRunnable("A2");
		MyRunnable a3 = new MyRunnable("A3");
		MyRunnable a4 = new MyRunnable("A4");
		MyRunnable a5 = new MyRunnable("A5");
		MyRunnable a6 = new MyRunnable("A6");
		MyRunnable a7 = new MyRunnable("A7");
		
		LinkedBlockingDeque<Runnable> linked = new LinkedBlockingDeque<Runnable>(2);	

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS,linked,new ThreadPoolExecutor.DiscardPolicy());		
		executor.allowCoreThreadTimeOut(true);
		
		executor.execute(a1);//1
		executor.execute(a2);//2
		executor.execute(a3);//3	
		executor.execute(a4);//4	
		executor.execute(a5);//4	
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
		Iterator<Runnable> iterator = linked.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			System.out.println("����������֣�"+((MyRunnable)obj).getUsername());
			 
		}		
		executor.execute(a6);//6
		executor.execute(a7);//6
		
		System.out.println("------------------");
		iterator = linked.iterator();
		while(iterator.hasNext()){
			Object obj = iterator.next();
			System.out.println("����������֣�"+((MyRunnable)obj).getUsername());
			 
		}
		
		System.out.println("�̳߳ش�С��"+executor.getPoolSize()+" ���еĴ�С��"+linked.size());
	}

}
