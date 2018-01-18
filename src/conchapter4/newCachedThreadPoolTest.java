package conchapter4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * 
 * ThreadPoolExecutor��ʹ���ϲ�������ô���㣬��ʵ����ʱ��Ҫ����ܶ��������
 * Ҫ�����̵߳Ĳ����������̳߳�����Ч���йصĲ��������Թٷ�����ʹ��Executors�������������̳߳ض���
 * 
 * 
 * ʹ�ö����̹߳��� ThreadFactory�ӿڵ� ʵ��implements
 * Executors.newCachedThreadPool(ThreadFactory threadFactory) 
 * �����̳߳صĴ��������ʹ�ö��ƹ���
 *  Executors.newCachedThreadPool();
 *  Executors.newFixedThreadPool(3);
 *  Executors.newSingleThreadExecutor();
 *  
 *  Executor�����÷�����Executor.execute(Runnable command)������һ���µ��߳�
 *  
 * @author Administrator
 *
 */
public class newCachedThreadPoolTest {
	
	public static void main(String[] args) {
		newCachedThreadPoolTest thisClass = new newCachedThreadPoolTest();		
		thisClass.newCachedThreadPoolMethod();
//		thisClass.newFixedThreadPoolMethod();
//		thisClass.newSingleThreadMethod();
		
	}
	
	
	// �Զ����̹߳���
	class MyThreadFactory  implements ThreadFactory{

		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			Thread myThread = new Thread(r);
			myThread.setName("�Զ�����߳�");
			return myThread;
		}	
	}
	
	/**
	 * �޽��̳߳أ�����ʵ���߳��Զ�����
	 */
	public void newCachedThreadPoolMethod(){
		MyThreadFactory myFa = new MyThreadFactory();
		ExecutorService service = Executors.newCachedThreadPool(myFa);// �����߳�
		ExecutorService service2 = Executors.newCachedThreadPool(); //�Ƕ����߳�
		// ExecutorServiceִ�ж����߳�
		for(int i=0;i<5;i++){
			service.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("�̳߳������У�"+System.currentTimeMillis()+" �߳����֣�"+Thread.currentThread().getName()+" �̵߳�id:"+Thread.currentThread().getId());
					
				}
			});
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i=0;i<5;i++){
			service2.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("�̳߳������У�"+System.currentTimeMillis()+" �߳����֣�"+Thread.currentThread().getName()+" �̵߳�id:"+Thread.currentThread().getId());
					
				}
			});
		}
		// �̵߳��ظ�ʹ�ò���
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i=0;i<5;i++){
			service2.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("�̳߳������У�"+System.currentTimeMillis()+" �߳����֣�"+Thread.currentThread().getName()+" �̵߳�id:"+Thread.currentThread().getId());
					
				}
			});
		}
	}
	
	
	public void newFixedThreadPoolMethod(){
		MyThreadFactory myFa = new MyThreadFactory();
		ExecutorService service = Executors.newFixedThreadPool(3);// �Ƕ����߳�
		ExecutorService service2 = Executors.newFixedThreadPool(3,myFa); //�����߳�
		// ExecutorServiceִ�ж����߳�
		for(int i=0;i<5;i++){
			service.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("�̳߳������У�"+System.currentTimeMillis()+" �߳����֣�"+Thread.currentThread().getName()+" �̵߳�id:"+Thread.currentThread().getId());
					
				}
			});
		}

		for(int i=0;i<5;i++){
			service2.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("�̳߳������У�"+System.currentTimeMillis()+" �߳����֣�"+Thread.currentThread().getName()+" �̵߳�id:"+Thread.currentThread().getId());
					
				}
			});
		}
	}
	
	/**
	 * ��һ�̳߳ؿ���ʵ���Զ��еķ�ʽ��ִ������
	 */
	public void newSingleThreadMethod(){
		MyThreadFactory myFa = new MyThreadFactory();
		ExecutorService service = Executors.newSingleThreadExecutor();// �Ƕ����߳�
		ExecutorService service2 = Executors.newSingleThreadExecutor(myFa); //�����߳�
		// ��ͨ�߳�
		for(int i=0;i<5;i++){
			service.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("�̳߳������У�"+System.currentTimeMillis()+" �߳����֣�"+Thread.currentThread().getName()+" �̵߳�id:"+Thread.currentThread().getId());
					
				}
			});
		}
		// �ƶ��߳�
		for(int i=0;i<5;i++){
			service2.execute(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("�̳߳������У�"+System.currentTimeMillis()+" �߳����֣�"+Thread.currentThread().getName()+" �̵߳�id:"+Thread.currentThread().getId());
					
				}
			});
		}
	}

}
