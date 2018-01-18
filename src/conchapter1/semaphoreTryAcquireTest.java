package conchapter1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore��Ĳ���
 * ���������������̲߳�����������
 * 
 * �޲η���tryAcquire(),���Ի��1����ɣ��޷���÷���false����if�����ʹ�ã�����������
 * �вη���tryAcquire(int permits),���Ի��x����ɣ��޷���÷���false��
 * �вη���tryAcquire(long timeout,TimeUnit unit),�����޵�ʱ���ڳ��Ի��1����ɣ��޷���÷���false��
 * �вη���tryAcquire(int permits, long timeout, TimeUnit unit),�����޵�ʱ���ڳ��Ի��x����ɣ��޷���÷���false��
 * @author Administrator
 *
 */
public class semaphoreTryAcquireTest {
	public static void main(String[] args) {
		semaphoreTryAcquireTest test1 =  new semaphoreTryAcquireTest();
		Service service =  test1.new Service();
		Thread1[] a = new Thread1[3];
		for(int i=0;i<a.length;i++){
			a[i] = test1.new Thread1(service);
			a[i].start();
		}		
	}
	
	/**
	 * tryAcquire ����
	 * @author Administrator
	 *
	 */
	class Service{
		
		private Semaphore semaphore = new Semaphore(1);
		
		//�޲η���tryAcquire()
		public void testMethod1(){
			if(semaphore.tryAcquire()){
				System.out.println(Thread.currentThread().getName() + "��ѡ���룡");
				for(int i=0;i<Integer.MAX_VALUE/50;i++){
					//�����һ��ҵ���߼�
					String str = new String();
					Math.random();					
				}
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "���н���");
			}else{
				System.out.println(Thread.currentThread().getName() + "δ�ɹ�����");
			}			
		}	
		
		//�вη���tryAcquire(int permits)
		public void testMethod2(){
			if(semaphore.tryAcquire(2)){
				System.out.println(Thread.currentThread().getName() + "��ѡ���룡");
				for(int i=0;i<Integer.MAX_VALUE/50;i++){
					//�����һ��ҵ���߼�
					String str = new String();
					Math.random();					
				}
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "���н���");
			}else{
				System.out.println(Thread.currentThread().getName() + "δ�ɹ�����");
			}			
		}
		
		//�вη���tryAcquire(long timeout,TimeUnit unit)
		public void testMethod3() throws Exception{
			if(semaphore.tryAcquire(3,TimeUnit.SECONDS)){
				System.out.println(Thread.currentThread().getName() + "��ѡ���룡");
				for(int i=0;i<Integer.MAX_VALUE/10;i++){
					//�����һ��ҵ���߼�
					String str = new String();
					Math.random();					
				}
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "���н���");
			}else{
				System.out.println(Thread.currentThread().getName() + "δ�ɹ�����");
			}			
		}
		
		// �вη���tryAcquire(int permits, long timeout, TimeUnit unit)
		public void testMethod4() throws Exception{
			if(semaphore.tryAcquire(2,3,TimeUnit.SECONDS)){
				System.out.println(Thread.currentThread().getName() + "��ѡ���룡");
				for(int i=0;i<Integer.MAX_VALUE/10;i++){
					//�����һ��ҵ���߼�
					String str = new String();
					Math.random();					
				}
				semaphore.release();
				System.out.println(Thread.currentThread().getName() + "���н���");
			}else{
				System.out.println(Thread.currentThread().getName() + "δ�ɹ�����");
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
			System.out.println(this.getName()+"������");
			try {
//			service.testMethod1();		
//			service.testMethod2();			
//			service.testMethod3();
			service.testMethod4();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
		
	}

}
