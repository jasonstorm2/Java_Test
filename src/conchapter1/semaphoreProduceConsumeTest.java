package conchapter1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ���߳��µ�������������ģ��
 * 
 * ReentrantLock lock���������һ��ֻ����һ���̻߳�ø���---��������ȷ���Ƿ���ȷ
 * Condition setCondition = lock.newCondition() ���� �����̵߳�״̬���еȴ����ͻ��������̵߳ȹ���
 * volatile ���÷�
 * 
 * @author LiZhenhua
 *
 */
public class semaphoreProduceConsumeTest {
	public static void main(String[] args) throws InterruptedException {
		semaphoreProduceConsumeTest thisClass = new semaphoreProduceConsumeTest();
		Service service = thisClass.new Service();
		setThread[] threadSet = new setThread[50];
		getThread[] threadGet = new getThread[50];
		
		for (int i = 0; i < threadGet.length; i++) {
			threadSet[i] =thisClass. new setThread(service);
			threadGet[i] =thisClass. new getThread(service);			
		}
		Thread.sleep(2000);
		for (int i = 0; i < threadGet.length; i++) {
			threadSet[i].start();
			threadGet[i].start();
		}
		
	}
	
	class Service{
		volatile private Semaphore setSemaphore = new Semaphore(10);
		volatile private Semaphore getSemaphore = new Semaphore(10);
		
		volatile private ReentrantLock lock = new ReentrantLock();
		volatile private Condition setCondition = lock.newCondition();
		volatile private Condition getCondition = lock.newCondition();
		volatile private Object[] goods = new Object[4];
		
		
		private boolean isEmpty(){
			boolean isEmpty = true;
			for (int i = 0; i < goods.length; i++) {
				if(goods[i] != null){
					isEmpty = false;
					break;
				}				
			}
			return isEmpty;
		}
		
		private boolean isFull(){
			boolean isFull = true;
			for (int i = 0; i < goods.length; i++) {
				if(goods[i] == null){
					isFull = false;
					break;
				}				
			}
			return isFull;
		}
		
		
		public void set(){
			try {
				setSemaphore.acquire();
				lock.lock();
				while(isFull()){
					//���˵Ļ������������߳���ͣ
					setCondition.await();
				}
				for (int i = 0; i < goods.length; i++) {
					if(goods[i] == null){
						goods[i] = Thread.currentThread().getName()+"��Ʒ";
						System.out.println(Thread.currentThread().getName() + "������"+goods[i]);
						break;
					}
				}
				getCondition.signalAll();//�������еȴ��е������߳�			
				lock.unlock();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				setSemaphore.release();
			}
		}
		
		public void get(){
			try {
				getSemaphore.acquire();
				lock.lock();
				while(isEmpty()){
					getCondition.await();
				}
				for (int i = 0; i < goods.length; i++) {
					if(goods[i] != null){
						System.out.println(Thread.currentThread().getName() + "������"+goods[i]);
						goods[i] = null;
						break;
					}
				}
				setCondition.signalAll();//֪ͨ�����߿���������				
				lock.unlock();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				getSemaphore.release();
			}
		}		
	}
	
	class setThread extends Thread{
		private Service service;
		
		public setThread(Service service){
			this.service = service;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			service.set();			
		}
	}
	
	class getThread extends Thread{
		private Service service;
		
		public getThread(Service service){
			this.service = service;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();			
			service.get();			
		}
	}

}
