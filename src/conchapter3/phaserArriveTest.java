package conchapter3;

import java.util.concurrent.Phaser;


/**
 * Phaser arrive()��������parties����+1�����Ҳ������δ��ȴ�����������������룬
 * Phaser�������ù��ܣ���parties�����ﵽ���ֵ�����á�
 * 
 * 
 * @author Administrator
 *
 */
public class phaserArriveTest {
	public static void main(String[] args){
		phaserArriveTest thisClass = new phaserArriveTest();
		Phaser phaser = new Phaser(2);
		Service service = thisClass.new Service(phaser);
		Thread1 th1 = thisClass.new Thread1(service);		
		Thread2 th2 = thisClass.new Thread2(service);
		Thread3 th3 = thisClass.new Thread3(service);
		
		th1.setName("Athread");
		th2.setName("Bthread");
		th3.setName("Cthread");
		
		th1.start();
		th2.start();
		th3.start();
		
	}	

	class Service{
		public Phaser phaser;	
		
		public Service(Phaser phaser){
			this.phaser = phaser;
		}
		public void methodA(){
			try {
				System.out.println(Thread.currentThread().getName() + " bgn a1 " + System.currentTimeMillis());
				Thread.sleep(3000);
				System.out.println("����ý׶ε��߳�����"+phaser.getArrivedParties());
				phaser.arriveAndAwaitAdvance();
				System.out.println(Thread.currentThread().getName() + " end a1 " + System.currentTimeMillis());
				
				System.out.println(Thread.currentThread().getName() + " bgn a2 " + System.currentTimeMillis());
				Thread.sleep(3000);
				System.out.println("����ý׶ε��߳�����"+phaser.getArrivedParties());
				phaser.arriveAndAwaitAdvance();
				System.out.println(Thread.currentThread().getName() + " end a2 " + System.currentTimeMillis());
				
				System.out.println(Thread.currentThread().getName() + " bgn a3 " + System.currentTimeMillis());
				Thread.sleep(3000);
				phaser.arriveAndAwaitAdvance();
				System.out.println(Thread.currentThread().getName() + " end a3 " + System.currentTimeMillis());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			phaser.arriveAndAwaitAdvance();

		}
		
		
		public void methodB(){
			try {
				System.out.println(Thread.currentThread().getName() + " bgn b1 " + System.currentTimeMillis());				
				System.out.println("�ﵽ�Ľ׶Σ�"+phaser.arrive()+"������߳�����"+phaser.getArrivedParties());
				System.out.println(Thread.currentThread().getName() + " end b1 " + System.currentTimeMillis());
				
				System.out.println(Thread.currentThread().getName() + " bgn b2 " + System.currentTimeMillis());
				System.out.println("�ﵽ�Ľ׶Σ�"+phaser.arrive()+"������߳�����"+phaser.getArrivedParties());
				System.out.println(Thread.currentThread().getName() + " end b2 " + System.currentTimeMillis());
				
				System.out.println(Thread.currentThread().getName() + " bgn b3 " + System.currentTimeMillis());
				System.out.println("�ﵽ�Ľ׶Σ�"+phaser.arrive()+"������߳�����"+phaser.getArrivedParties());
				System.out.println(Thread.currentThread().getName() + " end b3 " + System.currentTimeMillis());				
			} catch (Exception e) {
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
			service.methodA();
		}		
	}
	
	class Thread3 extends Thread{
		private Service service;
		public Thread3(Service service){
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
