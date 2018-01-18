package conchapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier ����ԣ�CyclicBarrier��CountDown����Щ���ƣ����ǿ��Կ����̵߳ȴ�
 * CyclicBarrier(int parties, Runnable barrierAction) �� �ȴ�parties���̶߳�����await��ִ��barrierAction�����ȴ����̼߳���ִ��
 * ����ȴ����̲߳���parties������һֱ����
 * CyclicBarrier.getNumberWaiting() ����Ŀǰ�������ڵȴ����̸߳���
 * @author Administrator
 *
 */
public class cyclicBarrierTest {
	public static void main(String[] args) throws InterruptedException {
		int threadNums = 3;
		cyclicBarrierTest thisClass =  new cyclicBarrierTest();
		CyclicBarrier cyc =  new CyclicBarrier(threadNums, new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("���е��̶߳�������");
			}
		});
		
		for (int i = 0; i <3; i++) {
			Thread1 thread =thisClass. new Thread1(threadNums,cyc);
			thread.start();
			Thread.sleep(2000);
		}
		
	}
	
	class Thread1 extends Thread{
		private CyclicBarrier cyc;
		private int threadNums;
		public Thread1(int threadNums,CyclicBarrier cyc){
			super();
			this.cyc = cyc;
			this.threadNums = threadNums;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println(Thread.currentThread().getName() + "��ʼʱ�� = "+System.currentTimeMillis()+"�ȴ�����������������");
				System.out.println("�ȴ����߳��У�"+cyc.getNumberWaiting()+"��");			
				cyc.await();
//				System.out.println("�ȴ����߳��У�"+cyc.getNumberWaiting()+"��");
				System.out.println(Thread.currentThread().getName() + "����ʱ�� = "+System.currentTimeMillis()+"�Ѿ�����������������");
				
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
