package conchapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Semaphore��Ĳ���
 * 
 * �ַ��ص����
 * 
 * Semaphore�ź����Ŀ��ƣ�����������м����������߳�����
 * ReentrantLock ͬ�������߲����¼Ӵ���һ��ֻ��һ���߳��ڴ����ȵ��ͷź�ȴ����̲߳��ܴ�������
 * Condition �����̵߳ĵȴ��ͻ��ѣ���ĳ�̻߳������ȴ����ȴ�״̬condition.await()���������߳̿��԰�������������
 * @author LiZhenhua
 *
 */
public class semaphorePoolTest {
	public static void main(String[] args) throws InterruptedException {
		semaphorePoolTest thisClass = new semaphorePoolTest();
		ListPool pool = thisClass.new ListPool();
//		Thread1[] threadArray = new Thread1[12];
//		for (int i = 0; i < threadArray.length; i++) {
//			
//			threadArray[i] = thisClass.new Thread1(pool);
//		}
//		
//		for (int i = 0; i < threadArray.length; i++) {
//			threadArray[i].start();
//		}
		
		// ��ͬһ��lock����������
		Thread1 thread1 =  thisClass.new Thread1(pool);
		Thread2 thread2 =  thisClass.new Thread2(pool);
		thread1.start();
		thread2.start();
		
	}
	
	/**
	 * Semaphore(int permits)
	 * ͬһ��ʱ��ֻ����permits���߳�ͬʱִ��acquire() �� release()֮��Ĵ���
	 * ���permits>1�����ܱ�֤�̰߳�ȫ��������������
	 * 
	 * @author LiZhenhua
	 *
	 */
	class ListPool{
		private int size = 5;
		private int semaphorePermits = 3;
		private List<String> list = new ArrayList<String>();
		private Semaphore concurrencySemaphore = new Semaphore(semaphorePermits);
		private ReentrantLock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		
		public ListPool(){
//			super();
//			for(int i=0;i<size;i++){
//				list.add("jason"+(i+1));
//			}
		}
		
		public String get(){
			String getStr =  null;
			try {
				concurrencySemaphore.acquire();
				System.out.println(Thread.currentThread().getName()+"get׼�������");
				lock.lock();
				System.out.println(Thread.currentThread().getName()+"get�����");
				while(list.size() == 0){
					//�߳�����ȴ�״̬
					System.out.println("��������ȴ�״̬");
					condition.await();
				}
				getStr = list.remove(0);
				for(int i=0;i<Integer.MAX_VALUE/20;i++){
					//�����һ��ҵ���߼�
					String str = new String();
					Math.random();					
				}
				System.out.println("------------------------------");
				lock.unlock();				
				System.out.println(Thread.currentThread().getName()+"get�ͷ���");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return getStr;
		}
		public void put(String value){
			System.out.println(Thread.currentThread().getName()+"put׼�������");
			lock.lock();
			System.out.println(Thread.currentThread().getName()+"put�����");
			list.add(value);
			//�����ڴ�condition�ȴ��������߳�
			condition.signalAll();
			for(int i=0;i<Integer.MAX_VALUE/20;i++){
				//�����һ��ҵ���߼�
				String str = new String();
				Math.random();					
			}
			System.out.println("------------------------------");
			lock.unlock();		
			System.out.println(Thread.currentThread().getName()+"put�ͷ���");			
			concurrencySemaphore.release();
		}
		
	}
	
	class Thread1 extends Thread{
		private ListPool listPool;
		public Thread1(ListPool listPool){
			super();
			this.listPool = listPool;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				String getString = listPool.get();
				System.out.println(Thread.currentThread().getName()+"ȡ��ֵ��"+getString);
//				listPool.put(getString);
//				System.out.println(Thread.currentThread().getName()+"���룺"+getString);
			}
					
		}
		
	}
	
	class Thread2 extends Thread{
		private ListPool listPool;
		public Thread2(ListPool listPool){
			super();
			this.listPool = listPool;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				listPool.put("����");
				System.out.println(Thread.currentThread().getName()+"���룺"+"����");
			}
					
		}
		
	}

}
