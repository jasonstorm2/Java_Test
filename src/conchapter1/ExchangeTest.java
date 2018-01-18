package conchapter1;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * ���������̼߳��ͨ��
 * ʹ��Exchanger������������̶߳�ʹ����ͬһ��Exchanger�����������߳̿��Ի���ͨ��
 * 
 * exchanger.exchange("Thread1")
 * exchanger.exchange(V value,long timeout,TimeUnit unit) ָ��ʱ����û�������߳�ͨ�ţ�������쳣
 * @author Administrator
 *
 */
public class ExchangeTest {
	
	public static void main(String[] args) {
		ExchangeTest thisClass = new ExchangeTest();
		Exchanger<String> exchanger =  new Exchanger<String>();
		Thread1 t1 = thisClass.new Thread1(exchanger);
		Thread2 t2 = thisClass.new Thread2(exchanger);
		t1.start();
		t2.start();		
		System.out.println("main end");
	}
	
	
	class Thread1 extends Thread{
		private Exchanger<String> exchanger;
		
		public Thread1(Exchanger<String> exchanger){
			this.exchanger = exchanger;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println("Thread1 �õ� �����̵߳�ֵ="+ exchanger.exchange("Thread1"));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
	}
	
	
	class Thread2 extends Thread{
		private Exchanger<String> exchanger;
		
		public Thread2(Exchanger<String> exchanger){
			this.exchanger = exchanger;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				System.out.println("Thread2 �õ� Thread1��ֵ="+ exchanger.exchange("Thread2",3,TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				System.out.println("û��ͨ�ŵ��̡߳�����");
				e.printStackTrace();
			}		
			
		}
	}

}
