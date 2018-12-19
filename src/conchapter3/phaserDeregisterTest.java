package conchapter3;

import java.util.concurrent.Phaser;


/**
 * 
 * Phaser �࣬Phaser(int parties) ����parties���߳��໥�ȴ�
 * ����;�����˳�����������arriveAndDeregister����ʹ������������
 * 
 * arriveAndAwaitAdvance()���ȵ������̵߳��������������У����߳�������ʱ���߳�����
 * arriveAndDeregister() : �ǵ�ǰ�߳��˳���������ʹע����߳�����partiesֵ-1�������ȴ����߳�-1��������л�ȴ��������߳��˳��������������
 * getArrivedParties() :  �ﵽ���ε���߳�����
 * getRegisteredParties(): ���ע��parties������
 * register(); ��̬���һ��partiesֵ--��̬���Ŷ
 * bulkRegister()����̬��Ӷ��parties
 * getArrivedParties()�� ��õ�����߳�����
 * getUnarrivedParties():���δ������߳�����
 * 
 * ����Phaserԭ��ֻ��2���߳�ע�ᣬ����B��������arriveAndDeregister������������ע����߳�������Phaser������������׶Σ�
 * ��һ���׶�A,B�໥�ȴ� ���߳�ע����2
 * �ڶ����׶�Aִ�У�       �߳�ע����1
 * �������׶�Bִ��         �߳�ע����1
 * @author LiZhenhua
 *
 */
public class phaserDeregisterTest {
	public static void main(String[] args){
		phaserDeregisterTest thisClass = new phaserDeregisterTest();
		Phaser phaser = new Phaser(2);
		Service service = thisClass.new Service(phaser);
		Thread1 th1 = thisClass.new Thread1(service);
		Thread2 th2 = thisClass.new Thread2(service);
		th1.setName("ThreadA");
		th2.setName("ThreadB");
		
		th1.start();
		th2.start();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("������߳�����"+phaser.getArrivedParties());
		System.out.println("δ������߳�����"+phaser.getUnarrivedParties());
	}	

	class Service{
		public Phaser phaser;	
		
		public Service(Phaser phaser){
			this.phaser = phaser;
		}
		public void methodA(){
			System.out.println(Thread.currentThread().getName()+ System.currentTimeMillis()+" A1��ʼ");
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A�������ε���߳�������"+phaser.getArrivedParties());
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " ����ע����߳�������"+phaser.getRegisteredParties());
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A ���õ�һ��arriveAndAwaitAdvance��,�������ε���߳�������"+phaser.getArrivedParties());
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A1����");
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A2��ʼ");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A�߳�˯5��󣬵������ε���߳�������"+phaser.getArrivedParties());
			
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A2 ���õڶ���arriveAndAwaitAdvance��,phaser�ڼ������ε㣺"+phaser.getPhase());
			System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " A2����");
		}
		
		
		public void methodB(){			
			try {
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B1 ��ʼ");
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B �������ε���߳�������"+phaser.getArrivedParties());
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B �߳�˯3��󣬵������ε���߳�������"+phaser.getArrivedParties());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " ����ע����߳�������"+phaser.getRegisteredParties());
				phaser.arriveAndDeregister();
//				phaser.arriveAndAwaitAdvance();
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " ����ע����߳�������"+phaser.getRegisteredParties());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " ���� arriveAndDeregister()��B�������ε���߳�������"+phaser.getArrivedParties());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B1phaser �ڼ������ε㣺"+phaser.getPhase());
				
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B2 ��ʼ");
				Thread.sleep(2000);
//				phaser.register(); //��̬���һ��parties
				phaser.bulkRegister(10); //��̬��Ӷ��parties
				phaser.arriveAndAwaitAdvance();

				
				System.out.println("����ע����߳�������"+phaser.getRegisteredParties());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B2 �߳�˯3��󣬵���phaser arriveAndAwaitAdvance�󣬵ڼ������ε㣺"+phaser.getPhase());
				System.out.println(Thread.currentThread().getName()+System.currentTimeMillis()+ " B2 ����");
				
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
