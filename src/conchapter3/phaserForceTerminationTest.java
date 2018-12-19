package conchapter3;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * 
 *  Phaser.forceTermination():ʹphaser��������ι���ʧЧ��ʧЧ���̼߳�����������
 *  ��CyclicBarrier�� reset()������ȣ�resetִ��ʱ�����쳣
 *  Phaser.isTerminated()���ж��Ƿ�ǿ��ʧЧ
 * @author LiZhenhua
 *
 */
public class phaserForceTerminationTest {
	public static void main(String[] args) throws Exception{
		phaserForceTerminationTest thisClass = new phaserForceTerminationTest();
		Phaser phaser = new Phaser(3);
		Thread1 th1 = thisClass.new Thread1(phaser);		
		Thread2 th2 = thisClass.new Thread2(phaser);
		
		th1.setName("Athread");
		th2.setName("Bthread");		
		th1.start();
		th2.start();	
		Thread.sleep(2000);
		
		if(!phaser.isTerminated()){
			System.out.println("ǿ�ƹص����ι���");
			phaser.forceTermination();
			System.out.println("isTerminated() = "+phaser.isTerminated());			
			System.out.println("������߳�:"+phaser.getArrivedParties());
		}

	}	

	class Thread1 extends Thread{
		private Phaser phaser;
		public Thread1(Phaser phaser){
			super();
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println(Thread.currentThread().getName() + "A1 bgn="+System.currentTimeMillis());
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + "A1 end="+System.currentTimeMillis());			
		}
		
	}
	
	class Thread2 extends Thread{
		private Phaser phaser;
		public Thread2(Phaser phaser){
			super();
			this.phaser = phaser;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println(Thread.currentThread().getName() + "A1 bgn="+System.currentTimeMillis());
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + "A1 end="+System.currentTimeMillis());			
		}	
	}
}
