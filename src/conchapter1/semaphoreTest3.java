package conchapter1;

import java.util.concurrent.Semaphore;


/**
 * Semaphore��Ĳ���
 * ���������������̲߳�����������
 * ����ʹ�õ�����:Semaphore
 * ����ʹ�õķ����У�Semaphore��acquire(int permits)��release(int permits)��availablePermits()��drainPermits()
 * 
 * ����֤��Semaphore(int permits)ֻ�Ƕ����˳�ʼ���������Ϊint permits��������ͨ��acquire���ٺ�release����
 * @author Administrator
 *
 */
public class semaphoreTest3 {
	/**
	 * availablePermits() ���ش�Semaphore�����е�ǰ���õ������
	 * drainPermits() ��ȡ�������������õ�������ɸ������������Ϊ0
	 * @param args
	 */
	
	public static void main(String[] args){
		try{
		Semaphore semaphore = new Semaphore(5);
		System.out.println(semaphore.availablePermits());
		semaphore.acquire();
		System.out.println(semaphore.availablePermits());
		semaphore.acquire(2);
		semaphore.acquire();
		semaphore.acquire();
		System.out.println(semaphore.availablePermits());
		semaphore.release(10);
		System.out.println(semaphore.availablePermits());
		semaphore.drainPermits();
		System.out.println(semaphore.availablePermits());
		
		}catch(InterruptedException e){
			e.printStackTrace();
			System.out.println("����");
		}		
	}
}
