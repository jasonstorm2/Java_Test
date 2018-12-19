package conchapter3;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * 
 *  Phaser.awaitAdvanceInterruptibly(int,long,TimeUnit): ��ָ����λ���ȴ�xʱ�䣬��λδ��������쳣�������������
 *  ��ָ����λ�ǵ�ǰ��λ��������ǰ��λ����С�ڵ�ǰ��λ���÷������ᱨ���쳣���߳�ֱ����������
 *  awaitAdvanceInterruptibly ������;�ȴ��Ĺ������̱߳��жϣ������׳��߳��ж��쳣
 * 
 * @author LiZhenhua
 *
 */
public class phaserAwaitAdvanceInterruptiblyTest {
	public static void main(String[] args){
		phaserAwaitAdvanceInterruptiblyTest thisClass = new phaserAwaitAdvanceInterruptiblyTest();
		Phaser phaser = new Phaser(1);
		Thread1 th1 = thisClass.new Thread1(phaser);		
		Thread2 th2 = thisClass.new Thread2(phaser);
		
		th1.setName("Athread");
		th2.setName("Bthread");		
		th1.start();
		th2.start();	
		
		/* ����awaitAdvanceInterruptibly���ж��쳣 */
//		try {
//			Thread.sleep(1000);
//			th2.interrupt();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			System.out.println("��;����̣߳��׳��߳��ж��쳣��"+System.currentTimeMillis());
//			e.printStackTrace();
//		}
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
			System.out.println(Thread.currentThread().getName() + "A1 �������λ"+phaser.getPhase());
			phaser.arrive(); 
			phaser.arrive(); 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			phaser.arrive(); 
//			phaser.arrive();
			System.out.println(Thread.currentThread().getName() + "A1 �������λ"+phaser.getPhase());
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
			try {
				Thread.sleep(3000);
				phaser.awaitAdvanceInterruptibly(1, 2, TimeUnit.SECONDS);
//				phaser.awaitAdvanceInterruptibly(phaser.getPhase(), 2, TimeUnit.SECONDS); //getPhaseȡ��ǰ�Ľ׶�
			} catch (InterruptedException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "A1 end="+System.currentTimeMillis());			
		}	
	}
}
