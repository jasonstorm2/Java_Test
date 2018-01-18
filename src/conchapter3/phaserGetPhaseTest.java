package conchapter3;

import java.util.concurrent.Phaser;


/**
 * 
 * Phaser �࣬Phaser(int parties) ����parties���߳��໥�ȴ�
 * 
 * getPhase() ��ȡ�߳������ĵڼ������ε�
 * onAdvance() Phaser��д�÷�����ÿ��ͨ�����ε�ʱ������������trueʱ�����ϱ�ȡ��������false Phaser��������
 * @author Administrator
 *
 */
public class phaserGetPhaseTest {
	public static void main(String[] args){
		phaserGetPhaseTest thisClass = new phaserGetPhaseTest();
		Phaser phaser = new Phaser(2){
		@Override
		protected boolean onAdvance(int phase, int registeredParties) {
			// TODO Auto-generated method stub
			System.out.println("��"+phase+"�׶α�����"+",phaserע���߳�����"+registeredParties);
			System.out.println("������߳�������"+getArrivedParties());
			if(phase == 3){
				System.out.println("����true");
				return true;
			}else{
				System.out.println("����false");
				return false;
			}
			
			
		}};
		Thread1 th1 = thisClass.new Thread1(phaser);
		th1.start();
		Thread1 th2= thisClass.new Thread1(phaser);
		th2.start();		
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
			System.out.println("�̴߳ﵽ�Ľ׶Σ�"+phaser.getPhase());
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			phaser.arriveAndAwaitAdvance();
			System.out.println("�̴߳ﵽ�Ľ׶Σ�"+phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			System.out.println("�̴߳ﵽ�Ľ׶Σ�"+phaser.getPhase());
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("true֮�󿴿��仯");
			phaser.arriveAndAwaitAdvance();
			System.out.println("�̴߳ﵽ�Ľ׶Σ�"+phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("�̴߳ﵽ�Ľ׶Σ�"+phaser.getPhase());
			phaser.arriveAndAwaitAdvance();			
		}		
	}
}
