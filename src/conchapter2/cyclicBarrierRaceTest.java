package conchapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch���ʹ��
 * @author LiZhenhua
 * 
 * 
 * ����߳� ͨ��ͬʱ������ͬ�Ķ��CountDownLatch������ģ��һ���ܲ�����
 * 
 * �ɼ���CountDownLatch �ڲ�ͬ���߳��о���ԭ���ԣ���
 *
 */
public class cyclicBarrierRaceTest {
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		cyclicBarrierRaceTest thisClass = new cyclicBarrierRaceTest();
		CyclicBarrier cyc =  new CyclicBarrier(11,new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		CountDownLatch waitTag = new CountDownLatch(1);
		CountDownLatch begingTag = new CountDownLatch(1);
		System.out.println("����Ա�ȴ�ѡ�ֵĵ���");
		Thread1[] threadArray = new Thread1[10];
		for(int i=0;i<threadArray.length;i++){
			threadArray[i] = thisClass.new Thread1(cyc, waitTag, begingTag);
			threadArray[i].start();
		}
		cyc.await();
		
		
		System.out.println("�˶�Ա���룬��׼����");
		Thread.sleep(2000);
		waitTag.countDown();
		System.out.println("���͸�λ");
		cyc.await();
		Thread.sleep(2000);
		System.out.println("ǹ������");
		begingTag.countDown();
		cyc.await();
		System.out.println("�����˶�Ա���ͳ�Ʊ�������");
	}	

	
	class Thread1 extends Thread{
		private CyclicBarrier cyc;
		private CountDownLatch waitTag;     //�ȴ�����˵׼����ʼ
		private CountDownLatch begingTag;   //��ʼ����
		public Thread1(CyclicBarrier cyc,CountDownLatch waitTag,CountDownLatch begingTag){
			super();
			this.waitTag = waitTag;
			this.begingTag = begingTag;
			this.cyc = cyc;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			try {
				System.out.println(Thread.currentThread().getName() +"�˶�Ա���ڼ����С�����");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "�����ܵ���");
				cyc.await();
				System.out.println("�ȴ�����˵׼��");
				waitTag.await();
				System.out.println("���͸�λ׼������");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "�˶�Ա����׼�����ܶ���");
				cyc.await();			
				begingTag.await();
				System.out.println(Thread.currentThread().getName() + "���ܣ��ܲ��С�������");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "�˶�Ա�ﵽ�յ�");
				cyc.await();							
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
