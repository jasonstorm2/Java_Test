package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore��Ĳ���
 * ���������������̲߳�����������
 * ����ʹ�õ�����:Semaphore
 * ����ʹ�õķ����У�Semaphore��acquire(int permits)��release(int permits)
 * @author Administrator
 *
 */
public class semaphoreTest2 {
	public static void main(String[] args) {
		semaphoreTest2 test1 =  new semaphoreTest2();
		Service service =  test1.new Service();
		Thread1[] a = new Thread1[10];
		for(int i=0;i<10;i++){
			a[i] = test1.new Thread1(service);
			a[i].start();
		}
	}
	
	/**
	 * Semaphore(int permits)
	 * ͬһ��ʱ��ֻ����permits���߳�ͬʱִ��acquire() �� release()֮��Ĵ���
	 * ���permits>1�����ܱ�֤�̰߳�ȫ��������������
	 * 
	 * acquire(int permits) ÿ�ε��ô˷�������ʹ��permits����ɣ�������permits�����
	 * ������10����ɣ�ÿ�ε���-2��������ֻ�ܵ���5��
	 * 
	 * @author Administrator
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(10);		
		public void testMethod(){
			try{
				semaphore.acquire(2);
				System.out.println(Thread.currentThread().getName() + " begin timer="+System.currentTimeMillis());
				int sleepValue = ((int)(Math.random()*10000));
				System.out.println(Thread.currentThread().getName()+"�߳�ֹͣ"+2+"��");
				Thread.sleep(2000);				
				System.out.println(Thread.currentThread().getName() + " end timer="+System.currentTimeMillis());
				semaphore.release(2);
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
