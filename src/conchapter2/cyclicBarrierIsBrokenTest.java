package conchapter2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * CyclicBarrier �� 
 * isBroken() ��ѯ�����Ƿ�����״̬
 * reset() �������� ,�����߳��ڵȴ�ʱ����ᱨ��
 * getParties() �õ�CyclicBarrier�����̵߳�����
 * getNumberWaiting() �õ��Ѿ��������ε���̵߳�����
 * 
 * ����ĳһ���߳������쳣�����˳��������̼߳����ȴ�
 * ����ĳһ���߳���;�ж�interrupt(),�����̶߳��˳�
 * ����ĳһ���߳����׳��쳣,�����̶߳��˳�
 * @author LiZhenhua
 *
 */
public class cyclicBarrierIsBrokenTest {
	public static void main(String[] args){
		int parties = 4;
		CyclicBarrier cyc = new CyclicBarrier(parties, new Runnable() {	
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("������");				
			}
		});
//		cyclicBarrierIsBrokenTest thisClass = new cyclicBarrierIsBrokenTest();
//		Service service = thisClass.new Service(cyc);
//		Thread1[] threadArray = new Thread1[3];
//		for (int i = 0; i < threadArray.length; i++) {
//			threadArray[i] =  thisClass.new Thread1(service);
//		}
//		for (int i = 0; i < threadArray.length; i++) {
//			threadArray[i].start();
//		}
		System.out.println("CyclicBarrierҪ���Ƶ��߳�����"+cyc.getParties());
		
		testReset(cyc);
	}	

	class Service{
		private CyclicBarrier cyc;
		public Service(CyclicBarrier cyc){
			this.cyc = cyc;
		}		
		public void beginRun(int count){
			try {
				System.out.println(Thread.currentThread().getName() + "׼��" + System.currentTimeMillis());
				if(Thread.currentThread().getName().equals("Thread-2")){
					System.out.println("Thread-2������");
					Thread.sleep(3000);
					/* һ���̳߳����쳣���������̼߳����ȴ�������ȴ�������һֱһֱ�ȡ�����*/					
//					Integer.parseInt("a");
					/*   */
//					Thread.currentThread().interrupt();
				}
				
				System.out.println("��ǰ�������ε���߳��У�"+cyc.getNumberWaiting());
				System.out.println("��ǰȡ��parties�������̣߳�"+cyc.getParties());
				//�ȴ�5���û��permits���̣߳��򱨳�ʱ�쳣�����е��߳��˳�
				if(Thread.currentThread().getName().equals("Thread-3")){
					cyc.await(5,TimeUnit.SECONDS);
				}				
				
				/* interrupt()�������ú� ����sleep�ᱨ�� */ 
//				Thread.sleep((int)(Math.random()*100));
//				System.out.println("�ȴ��߳�����"+cyc.getNumberWaiting());
				cyc.await();
				System.out.println(Thread.currentThread().getName() + "����" +count);			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("������InterruptedException,IsBroken="+cyc.isBroken());
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				System.out.println("������BrokenBarrierException,IsBroken="+cyc.isBroken());				
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void testA(){
			for (int i = 0; i < 1; i++) {
				beginRun(i+1);
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
			service.testA();
		}
		
	}
	
	/**
	 * ����reset����
	 * @param cyc
	 */
	public static void testReset(CyclicBarrier cyc){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName());
					cyc.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		try {
			System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName());
			System.out.println("��ǰ�������ε���߳��У�"+cyc.getNumberWaiting());
			
			System.out.println("��ǰ�������ε���߳��У�"+cyc.getNumberWaiting());
			Thread.sleep(2000);
			cyc.reset();	
			cyc.await();
			System.out.println("����");
			System.out.println("��ǰ�������ε���߳��У�"+cyc.getNumberWaiting());
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
