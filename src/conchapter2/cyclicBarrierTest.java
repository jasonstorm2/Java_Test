package conchapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier ����ԣ�CyclicBarrier��CountDown����Щ���ƣ����ǿ��Կ����̵߳ȴ�
 * 
 * 
 * 
 * ʵ����һ��CyclicBarrier(int parties, Runnable barrierAction)���󣬶���̹߳���
 * �̵߳���CyclicBarrier.await()����ȴ�״̬��
 * ֱ��parties���̵߳���CyclicBarrier.await()����,�����̲߳��ٵȴ�����ִ�У�����barrierAction�߳�Ҳ��ʼִ��
 * ����ȴ����̲߳���parties������һֱ����
 * 
 * CyclicBarrier(int parties, Runnable barrierAction) �� 	�ȴ�parties���̶߳�����await()��ִ��barrierAction�����ȴ����̼߳���ִ��
 * CyclicBarrier(int parties) : 							�ȴ�parties���̶߳�����await()��,���еȴ����̼߳���ִ�С�
 * CyclicBarrier.await() :									�߳̽���ȴ�״̬ 
 * CyclicBarrier.getNumberWaiting()�� 						����Ŀǰ�������ڵȴ����̸߳���
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class cyclicBarrierTest {
	
	public static final int THREADNUMS = 3;
	
	public static void main(String[] args) throws InterruptedException {
		cyclicBarrierTest thisClass =  new cyclicBarrierTest();
		CyclicBarrier cyc =  new CyclicBarrier(THREADNUMS, new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("���е��̶߳�������");
			}
		});
		
		CyclicBarrier cyc2 = new CyclicBarrier(THREADNUMS);
		
		for (int i = 0; i <6; i++) {
			Thread1 thread =thisClass. new Thread1(i,cyc);
			thread.start();
			Thread.sleep(2000);
		}
		
	}
	
	class Thread1 extends Thread{
		private CyclicBarrier cyc;
		public Thread1(int id,CyclicBarrier cyc){
			super();
			this.cyc = cyc;
			this.setName("�߳�"+id);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println(Thread.currentThread().getName() + ":��ʼʱ�� = "+System.currentTimeMillis()+"�ȴ�����������������");
				System.out.println("�ȴ����߳��У�"+cyc.getNumberWaiting()+"��");			
				cyc.await();
				System.out.println("�����ȴ��ˣ��ȴ����߳��У�"+cyc.getNumberWaiting()+"��");
				System.out.println(Thread.currentThread().getName() + ":����ʱ�� = "+System.currentTimeMillis()+"�Ѿ�����������������");
				
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
