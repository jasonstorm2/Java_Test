package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore��Ĳ���
 * 
 * ���·-�ദ��-���·����
 * 
 * ��������̣߳����еĴ���ģ��ֻ��3����ɵ��������ͬ��
 * @author Administrator
 *
 */
public class semaphoreMoreTest {
	public static void main(String[] args) {
		semaphoreMoreTest test1 =  new semaphoreMoreTest();
		Service service =  test1.new Service();
		
		Thread1[] a =  new Thread1[12];
		for(int i = 0;i<a.length;i++){
			a[i] =  test1.new Thread1(service);
			a[i].start();
		}

	}
	
	/**
	 * Semaphore(int permits)
	 * ͬһ��ʱ��ֻ����permits���߳�ͬʱִ��acquire() �� release()֮��Ĵ���
	 * ���permits>1�����ܱ�֤�̰߳�ȫ��������������
	 * 
	 * @author Administrator
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(3);		
		public void testMethod(){
			try{
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
				for(int i=0;i<5;i++){
					System.out.println(Thread.currentThread().getName()+"��ӡ��");					
				}
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
