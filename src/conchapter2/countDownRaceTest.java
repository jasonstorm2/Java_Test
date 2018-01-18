package conchapter2;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch���ʹ��
 * @author Administrator
 * 
 * 
 * ����߳� ͨ��ͬʱ������ͬ�Ķ��CountDownLatch������ģ��һ���ܲ�����
 * 
 * �ɼ���CountDownLatch �ڲ�ͬ���߳��о���ԭ���ԣ���
 *
 */
public class countDownRaceTest {
	public static void main(String[] args) throws InterruptedException {
		countDownRaceTest thisClass = new countDownRaceTest();
		CountDownLatch comingTag = new CountDownLatch(10);
		CountDownLatch waitTag = new CountDownLatch(1);
		CountDownLatch waitRunTag = new CountDownLatch(10);
		CountDownLatch begingTag = new CountDownLatch(1);
		CountDownLatch endTag = new CountDownLatch(10);
		
		Thread1[] threadArray = new Thread1[10];
		for(int i=0;i<threadArray.length;i++){
			threadArray[i] = thisClass.new Thread1(comingTag, waitTag, waitRunTag, begingTag, endTag);
			threadArray[i].start();
		}
		
		System.out.println("����Ա�ȴ�ѡ�ֵĵ���");
		comingTag.await();
		System.out.println("�˶�Ա���룬��׼����");
		Thread.sleep(2000);
		waitTag.countDown();
		System.out.println("���͸�λ");
		waitRunTag.await();
		Thread.sleep(2000);
		System.out.println("ǹ������");
		begingTag.countDown();
		endTag.await();
		System.out.println("�����˶�Ա���ͳ�Ʊ�������");
	}	

	
	class Thread1 extends Thread{
		private CountDownLatch comingTag;   //���еȴ��˶�Ա����
		private CountDownLatch waitTag;     //�ȴ�����˵׼����ʼ
		private CountDownLatch waitRunTag;  //�ȴ�����
		private CountDownLatch begingTag;   //��ʼ����
		private CountDownLatch endTag;      //�����˶�Ա�����յ�
		public Thread1(CountDownLatch comingTag,CountDownLatch waitTag,CountDownLatch waitRunTag,CountDownLatch begingTag,CountDownLatch endTag){
			super();
			this.comingTag = comingTag;
			this.waitTag = waitTag;
			this.waitRunTag = waitRunTag;
			this.begingTag = begingTag;
			this.endTag = endTag;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			try {
				System.out.println("�˶�Ա���ڼ����С�����");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "�����ܵ���");
				comingTag.countDown();
				System.out.println("�ȴ�����˵׼��");
				waitTag.await();
				System.out.println("���͸�λ׼������");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "�˶�Ա����׼�����ܶ���");
				waitRunTag.countDown();				
				begingTag.await();
				System.out.println(Thread.currentThread().getName() + "���ܣ��ܲ��С�������");
				Thread.sleep((int)(Math.random() * 10000));
				System.out.println(Thread.currentThread().getName() + "�˶�Ա�ﵽ�յ�");
				endTag.countDown();								
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
