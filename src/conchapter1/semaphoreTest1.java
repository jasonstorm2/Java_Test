package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore��Ĳ���
 * ���������������̲߳�����������
 * ����ʹ�õ�����:Semaphore
 * ����ʹ�õķ����У�Semaphore��acquire()��release()
 * 
 * �����߳� ����ͬһ��������Semaphore��ķ��������ƴ��������߳�����
 * @author LiZhenhua
 *
 */
public class semaphoreTest1 {
	public static void main(String[] args) {
		semaphoreTest1 test1 =  new semaphoreTest1();
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
	}
	
	/**
	 * Semaphore(int permits)
	 * ͬһ��ʱ��ֻ����permits���߳�ͬʱִ��acquire() �� release()֮��Ĵ���
	 * ���permits>1�����ܱ�֤�̰߳�ȫ��������������
	 * 
	 * @author LiZhenhua
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(2);		
		public void testMethod(){
			try{
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " end timer="+System.currentTimeMillis());
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
