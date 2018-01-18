package conchapter2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch���ʹ��
 * @author Administrator
 * 
 * CountDownLatch.await(3,TimeUnit.SECONDS); ʹ����CountDownLatch������̵߳ȴ�ָ����ʱ��󣬼�������
 * CountDownLatch.getCount() ��ȡ��ǰ����ֵ
 *
 */
public class countDownWaitTest {
	public static void main(String[] args) throws InterruptedException {
		countDownWaitTest thisClass = new countDownWaitTest();
		Service service = thisClass.new Service();
		Thread1[] threadArray = new Thread1[3];
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i] =  thisClass.new Thread1(service);
		}
		for (int i = 0; i < threadArray.length; i++) {
			threadArray[i].start();
		}
	}	

	class Service{
		CountDownLatch count = new CountDownLatch(1);
		
		public void testMethod(){
			try {
				System.out.println(Thread.currentThread().getName() + "׼��" + System.currentTimeMillis());
				count.await(3,TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName() + "����" + System.currentTimeMillis());			
				System.out.println("CountDownLatch��ǰ����ֵ��"+count.getCount());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
