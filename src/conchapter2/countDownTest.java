package conchapter2;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * ����̹߳���һ��CountDownLatch���󣬸ö�����һ����ʼ�ļ���ֵcount����new CountDownLatch(count)��
 * 1����һ���̵߳���CountDownLatch.countDown������count-1��
 * 2�����̵߳���CountDownLatch.await()����������ȴ�״̬��ֱ������̵߳���CountDownLatch.countDown��
 * ʹcount����Ϊ0�����д��ڵȴ����̼߳�����������
 * 
 * 
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
	
	public static final int AWAITNUM = 2;
	
	public static void main(String[] args) throws InterruptedException {
		countDownTest thisClass =  new countDownTest();
		Service service =  thisClass.new Service();
		Thread1 thread = thisClass.new Thread1(service);
		thread.start();
		Thread1 thread2 = thisClass.new Thread1(service);
		thread2.start();
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
		private CountDownLatch down = new CountDownLatch(AWAITNUM);		
		public void testMethod(){
			try{
				
				System.out.println(Thread.currentThread().getName() + ":��ʼʱ�� = "+System.currentTimeMillis()+"��ǰ����Ϊ��"+down.getCount());
				System.out.println(Thread.currentThread().getName() + ":��ʼʱ�� = "+System.currentTimeMillis()+"�߳���������await()");
				down.countDown();
				System.out.println(Thread.currentThread().getName() + ":��ʼʱ�� = "+System.currentTimeMillis()+"��ǰ����Ϊ��"+down.getCount());
				
				down.await();
				System.out.println(Thread.currentThread().getName() + ":��ʼʱ�� = "+System.currentTimeMillis()+"�ȴ��������̼߳���ִ��");
			}catch(InterruptedException e){
				e.printStackTrace();				
			}

		}
		
		public void downMethod(){
			System.out.println(Thread.currentThread().getName() + ":��ʼʱ�� = "+System.currentTimeMillis()+"���̵߳���countDown()");
			try {
				down.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("main�����߼����㣺");
			long beginTime = System.nanoTime();
			long beginTime2 = System.currentTimeMillis();
			
			for(int i=0;i<Integer.MAX_VALUE/20;i++){
				//�����һ��ҵ���߼�
				String str = new String();
				Math.random();					
			}
			long endTime = System.nanoTime();
			long endTime2 = System.currentTimeMillis();
			
			System.out.println("���߳��߼������ʱ�䣺"+(endTime-beginTime));
			System.out.println("���߳��߼������ʱ��2��"+(endTime2-beginTime2));
			
			System.out.println(Thread.currentThread().getName() + ":����ʱ�� = "+System.currentTimeMillis()+"���߳�,��ǰ����Ϊ��"+down.getCount());
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
