package conchapter1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Semaphore��Ĳ���
 * 
 * ���·-������-���·����
 * 
 * ��������̣߳����еĴ���ģ��ֻ��3����ɵ����
 * ReentrantLock���Ĺ��ܣ�ͬ������飬�������̣߳�����Ҳ�Ƶ�����
 * 
 * ��ʱ�����м�����ɣ�ֻ����һ��ִ�У��������߳��ڵȴ������Ի�����ֻ��һ�����
 * @author Administrator
 *
 */
public class semaphoreSingleTest {
	public static void main(String[] args) {
		semaphoreSingleTest test1 =  new semaphoreSingleTest();
		Service service =  test1.new Service();
		
		Thread1[] a =  new Thread1[12];
		for(int i = 0;i<a.length;i++){
			a[i] =  test1.new Thread1(service);
			a[i].start();
		}

	}
	
	/**
	 * ReentrantLock ��������ʹ�ã�ÿ��ֻ��һ���̻߳�ø��������߳�ִ������ͷ����������̲߳��ܻ����
	 * @author Administrator
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(3);		
		private ReentrantLock lock = new ReentrantLock();
		public void testMethod(){
			try{
				semaphore.acquire();
				lock.lock();
				System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
				for(int i=0;i<Integer.MAX_VALUE/20;i++){
//					System.out.println(Thread.currentThread().getName()+"��ӡ��");
					//�����һ��ҵ���߼�
					String str = new String();
					Math.random();	
				}
				System.out.println(Thread.currentThread().getName() + " end timer="+System.currentTimeMillis());
				lock.unlock();
				semaphore.release();
			}catch(InterruptedException e){
				e.printStackTrace();				
			}

		}
		
	}
	
	class Thread1 extends Thread{
		private Service service;
		public Thread1(Service service){
			super();
			this.service = service;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			service.testMethod();			
		}
		
	}

}
