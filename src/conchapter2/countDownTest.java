package conchapter2;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch���ʹ��
 * ��������������������ж�count������Ϊ0ʱ���������ǰ�̳߳�wait״̬��Ϊ0���������
 * 
 * ʹ�õķ����У�
 * CountDownLatch.await()       ���жϼ����Ƿ�Ϊ0����Ϊ0��ȴ�     ----------��Condition.await()�ıȽ�
 * CountDownLatch.countDown()   ��������-1������һֱ����0ʱ�����еȴ��̼߳�������
 * CountDownLatch.getCount()    ����ȡ��ǰ����
 * 
 * һ���߳��������õȴ�����await()�����������߳�countDown()������ֱ��Ϊ0 ʱ���ȴ����߳̽���������
 * @author Administrator
 *
 */
public class countDownTest {
	public static void main(String[] args) throws InterruptedException {
		countDownTest thisClass =  new countDownTest();
		Service service =  thisClass.new Service();
		Thread1 thread = thisClass.new Thread1(service);
		thread.start();
		Thread.sleep(2000);
		service.downMethod();
	}
	
	/**
	 * getQueueLength() ��ȡ�ȴ���ɵ��߳�����
	 * hasQueuedThreads() �ж��Ƿ����߳��ڵȴ�
	 * @author Administrator
	 *
	 */
	class Service{
		private CountDownLatch down = new CountDownLatch(1);		
		public void testMethod(){
			try{
				
				System.out.println("��ǰ����Ϊ��"+down.getCount());
				System.out.println("�߳���������await()");
				down.await();
				System.out.println("�ȴ��������̼߳���ִ��");
			}catch(InterruptedException e){
				e.printStackTrace();				
			}

		}
		
		public void downMethod(){
			System.out.println("���̵߳���countDown()");
			down.countDown(); //Ϊ0ʱ�������������ȴ����߳�
			try {
				down.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<Integer.MAX_VALUE/20;i++){
				//�����һ��ҵ���߼�
				String str = new String();
				Math.random();					
			}
			System.out.println("���߳�,��ǰ����Ϊ��"+down.getCount());
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
