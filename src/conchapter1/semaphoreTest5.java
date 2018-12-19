package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore��Ĳ���
 * ���������������̲߳�����������
 * ����ʹ�õ�����:Semaphore
 * ����ʹ�õķ����У�Semaphore��getQueueLength()��hasQueuedThreads()
 * @author LiZhenhua
 *
 */
public class semaphoreTest5 {
	public static void main(String[] args) {
		semaphoreTest5 test1 =  new semaphoreTest5();
		Service service =  test1.new Service();
		Thread1[] a = new Thread1[10];
		for(int i=0;i<10;i++){
			a[i] = test1.new Thread1(service);
			a[i].start();
		}		
	}
	
	/**
	 * getQueueLength() ��ȡ�ȴ���ɵ��߳�����
	 * hasQueuedThreads() �ж��Ƿ����߳��ڵȴ�
	 * @author LiZhenhua
	 *
	 */
	class Service{
		private Semaphore semaphore = new Semaphore(1);		
		public void testMethod(){
			try{
				semaphore.acquire();
				Thread.sleep(1000);
				System.out.println("���д�Լ"+semaphore.getQueueLength()+"���߳��ڵȴ�");				
				System.out.println("�Ƿ����߳����ڵȴ��ź�����"+semaphore.hasQueuedThreads());
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
