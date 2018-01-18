package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore��Ĳ���
 * ���������������̲߳�����������
 * ����ʹ�õ�����:Semaphore
 * ����ʹ�õ����У�Semaphore��acquireUninterruptibly()��release()
 * 
 * ʹ��acquireUninterruptibly() �� Semaphore��acquireUninterruptibly(int permits)�����󣬵ȴ����̲߳��ܱ��ж�
 * @author Administrator
 *
 */
public class semaphoreTest4 {
	public static void main(String[] args) throws Exception {
		semaphoreTest4 test1 =  new semaphoreTest4();
		Service service =  test1.new Service();
		
		Thread1 a =  test1.new Thread1(service);
		Thread1 b =  test1.new Thread1(service);
		Thread1 c =  test1.new Thread1(service);
		a.setName("a");
		b.setName("b");
		c.setName("c");		
		// ����semaphoreTest1�����ƣ���3���߳���ͬ����
		a.start();
		b.start();
		c.start();		
		
		Thread.sleep(500);
		a.interrupt();
		System.out.println("a�Ƿ��ж�");
	}
	
	/**
	 * Semaphore���� ʹ��acquireUninterruptibly() �� Semaphore��acquireUninterruptibly(int permits)�����󣬵ȴ����̲߳��ܱ��ж�
	 * @author Administrator
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(1);		
		public void testMethod(){
			semaphore.acquireUninterruptibly();
			System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
			for(int i=0;i<Integer.MAX_VALUE/20;i++){
				//�����һ��ҵ���߼�
				String str = new String();
				Math.random();					
			}
			System.out.println(Thread.currentThread().getName() + " end timer="+System.currentTimeMillis());
			semaphore.release();

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
