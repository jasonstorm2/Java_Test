package my;

import java.util.Date;

/**
 * join()�ȴ��߳���ֹ�����̲߳ż�������
 * 
 * join(x) �ȴ�xʱ��������̻߳�û�����꣬���̼߳�������
 * @author Administrator
 *
 */
public class JoinTest {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable1(), "r1");
		Thread t2 = new Thread(new Runnable2(), "r2");
		System.out.println("main start"+":"+new Date());
		t1.start();
		t2.start();
		
		try {
			//����join��
			//�ӵ�һ��join��ʱ������
			
			
			t1.join();
			t2.join(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main over"+":"+new Date());
		
	}

}

class Runnable1 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("r1 beging"+":"+new Date());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("r1 over"+":"+new Date());
		
	}
	
}

class Runnable2 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("r2 beging"+":"+new Date());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("r2 over"+":"+new Date());
		
	}
	
}

