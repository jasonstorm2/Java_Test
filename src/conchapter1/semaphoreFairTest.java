package conchapter1;

import java.util.concurrent.Semaphore;

/**
 * Semaphore��Ĳ���
 * ���������������̲߳�����������
 * ����ʹ�õĹ��캯��:Semaphore(int permits, boolean fair)
 * 
 * �������鹫ƽ��ǹ�ƽ�ź������� �߳�����˳������� acquire()��˳��Ĺ�ϵ
 * fair true ʱ�߳�����˳�����ȡ���ִ�д����˳��һ��  --  ������100%�������Ǹ����ϵõ���֤
 * fair falseʱ�߳�����˳�����ȡ���ִ�д����˳��һ��
 * 
 * 
 * @author Administrator
 *
 */
public class semaphoreFairTest {
	public static void main(String[] args) {
		semaphoreFairTest test1 =  new semaphoreFairTest();
		Service service =  test1.new Service();
		Thread1 th = test1.new Thread1(service);
		th.start();
		Thread1[] a = new Thread1[5];
		for(int i=0;i<a.length;i++){
			a[i] = test1.new Thread1(service);
			a[i].start();
		}		
	}
	
	/**
	 * Semaphore(int permits, boolean fair)
	 * fair ������״̬���µĽ��
	 * @author Administrator
	 *
	 */
	class Service{
//		private boolean isFair = false;
		private boolean isFair = true;
		
		private Semaphore semaphore = new Semaphore(1,isFair);		
		public void testMethod(){
			try{
				semaphore.acquire();
				System.out.println("ȡ����ɣ���ʼִ�д���顣�߳����֣�"+Thread.currentThread().getName());				
				
			}catch(InterruptedException e){
				e.printStackTrace();				
			}finally{
				semaphore.release();
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
			System.out.println("�߳�����"+this.getName()+"������");
			service.testMethod();			
		}
		
	}

}
