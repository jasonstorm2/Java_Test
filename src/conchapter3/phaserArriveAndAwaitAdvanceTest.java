package conchapter3;

import java.util.concurrent.Phaser;


/**
 * 
 * Phaser �࣬Phaser(int parties) ����parties���߳��໥�ȴ�
 * 
 * arriveAndAwaitAdvance()���ȵ������̵߳��������������У����߳�������ʱ���߳�����
 * �� CountDownLatch��await()��������һ����
 * Phaser �������ö����ϵĹ��ܣ����Ʊ��������Ρ�
 * @author Administrator
 *
 */
public class phaserArriveAndAwaitAdvanceTest {
	public static void main(String[] args){
		phaserArriveAndAwaitAdvanceTest thisClass = new phaserArriveAndAwaitAdvanceTest();
		Phaser phaser = new Phaser(2);
		Service service = thisClass.new Service(phaser);
		Thread1 th1 = thisClass.new Thread1(service);
		Thread2 th2 = thisClass.new Thread2(service);
		th1.start();
		th2.start();
	}	

	class Service{
		public Phaser phaser;	
		
		public Service(Phaser phaser){
			this.phaser = phaser;
		}
		public void methodA(){
			System.out.println(Thread.currentThread().getName()+ "A1��ʼ"+System.currentTimeMillis());
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName()+ "A1����"+System.currentTimeMillis());
			System.out.println(Thread.currentThread().getName()+ "A2��ʼ"+System.currentTimeMillis());
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName()+ "A2����"+System.currentTimeMillis());
		}
		
		public void methodB(){			
			try {				
				System.out.println("����ע����߳�������"+phaser.getRegisteredParties());
				System.out.println(Thread.currentThread().getName()+ "B1��ʼ"+System.currentTimeMillis());
				Thread.sleep(3000);
				phaser.arriveAndAwaitAdvance();
				System.out.println("����ע����߳�������"+phaser.getRegisteredParties());
				System.out.println(Thread.currentThread().getName()+ "B1����"+System.currentTimeMillis());
				System.out.println(Thread.currentThread().getName()+ "B2��ʼ"+System.currentTimeMillis());
				Thread.sleep(3000);
				phaser.arriveAndAwaitAdvance();
				System.out.println(Thread.currentThread().getName()+ "B2����"+System.currentTimeMillis());
				System.out.println("����ע����߳�������"+phaser.getRegisteredParties());
				
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
			service.methodA();
		}
		
	}
	
	class Thread2 extends Thread{
		private Service service;
		public Thread2(Service service){
			super();
			this.service = service;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			service.methodB();
		}
		
	}
}
