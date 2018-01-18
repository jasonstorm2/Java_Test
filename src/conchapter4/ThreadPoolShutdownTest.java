package conchapter4;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ����shutdown()�̳߳�״̬��ΪSHUTDOWN,��ǰδִ������̼߳���ִ�У���������µ�task�����е����̹رա��������������߳̿��Խ�����
 *     �رպ�����ִ���̳߳ص�executor���ᱨ��
 *     
 * ����shutdownNow() �̳߳�״̬��ΪSTOP,��ͼ�ر����������е��̣߳����е��߳����̹رգ�����������
 *     �������е��߳���if�ж���� �� �׳��쳣�Ĵ��룬����Ϊ���׳��쳣
 *     ���û��if��� �� �쳣�׳��Ĵ��룬���̻߳��������
 *     �رպ�����ִ���̳߳ص�executor���ᱨ��
 *     shutdownNow()�����᷵��δִ�е��߳��б�
 *     
 *     
 *     isShutDown()���ж��̳߳��Ƿ񱻹ر�
 *     isTerminating �� isTerminated�������Ƿ������жϣ��Ƿ��Ѿ��ж�
 *     awaitTermination(long timeout, TimeUnit unit)���ȴ��̳߳عر����
 * @author Administrator
 *
 */
public class ThreadPoolShutdownTest {
	
	public static void main(String[] args) {
		ThreadPoolShutdownTest thisClass = new ThreadPoolShutdownTest();		
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {				
				try {
					System.out.println(Thread.currentThread().getName() +" run "+System.currentTimeMillis());
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());
					
					if(Thread.currentThread().isInterrupted()){
						System.out.println("���߳��Ѿ�Interrupted�ж�");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};	
//		thisClass.initExecutor(runnable);
//		thisClass.runExecutor(runnable);
		thisClass.shutdowenExecutor(runnable);
//		thisClass.shutdowenExecutor2(runnable);
//		thisClass.shutdownNowExecutor();
//		thisClass.shutdownNowExecutor2();
//		thisClass.isShutdownExecutor(runnable);
//		thisClass.isTerminatingExecutor();
//		thisClass.awaitTerminationExecutor(runnable);
	}	
	/**
	 * ʵ�����̳߳ز��������̣߳�ʵ������������
	 * @param runnable
	 */
	public void initExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		System.out.println("main end");
	}
	
	/**
	 * ʵ�����̳߳أ���ִ��һ���̣߳��߳�ִ����󣬼������ڣ��ȴ�ִ��������
	 * @param runnable
	 */	
	public void runExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(runnable);
		System.out.println("main end");
	}
	
	/**
	 * ʵ�����̳߳أ���ִ��һ���̣߳��߳�ִ����󣬼������ڣ��ȴ�ִ��������
	 * ����shutdown()�������ȴ����̹߳ر�
	 * @param runnable
	 */	
	public void shutdowenExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(runnable);
		System.out.println("�̳߳عر�ǰ���̳߳��е��߳�������"+executor.getPoolSize());
		executor.shutdown();
		System.out.println("�̳߳��Ƿ񱻹رգ�"+executor.isShutdown());
		try {
			Thread.sleep(1000);
			System.out.println("�̳߳عرպ��̳߳��е��߳�������"+executor.getPoolSize());
			Thread.sleep(4000);
			System.out.println("�̳߳عرպ��̳߳��е��߳�������"+executor.getPoolSize());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main end");
	}
	
	/**
	 * ����shutdown()�̳߳عر�
	 * ���߳ǳعرգ��̳߳���ִ������ͻᱨ��
	 * @param runnable
	 */
	public void shutdowenExecutor2(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.shutdown();
		executor.execute(runnable);
		System.out.println("main end");
	}
	
	/**
	 * shutdownNow()�Ĳ��� isInterrupted()�ж��Ƿ��ж��̣߳����׳��쳣��
	 * ����û���׳��쳣������һֱ������ȥ
	 * ����List<Runnable> runList Ϊ���е��߳�
	 */
	public void shutdownNowExecutor(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		Runnable runnable = new Runnable() {	
			@Override
			public void run() {
				
				try {
					
//					for(int i=0;i<Integer.MAX_VALUE / 50;i++){
//						String newString = new String();
//						Math.random();
//						Math.random();
//						Math.random();
//						Math.random();
//						Math.random();
//						Math.random();
//						if(Thread.currentThread().isInterrupted() == true){
//							System.out.println("����û����ɣ����ж���");
////							throw new InterruptedException(); //�׳��쳣
//						}
//						
//					}			
					
					System.out.println("������ɣ�");
				} catch (Exception e) {            //�����׳����쳣
					System.out.println("����catch�ж�������");
					e.printStackTrace();					
				}
				
			}
		};
		try {
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);		
//		Thread.sleep(1000);
		List<Runnable> runList = executor.shutdownNow();	
		System.out.println("δִ�е��߳�������"+runList.size());
		System.out.println("main end");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * shutdownNow()�Ĳ��� �߳���û��if�жϳ����Ƿ��жϵ��жϣ���ִ�еĻ���ִ�������
	 * 
	 * ����List<Runnable> runList Ϊ���е��߳�
	 */
	public void shutdownNowExecutor2(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		Runnable runnable = new Runnable() {	
			@Override
			public void run() {
					for(int i=0;i<Integer.MAX_VALUE / 1000;i++){
						Math.random();
						Math.random();
						Math.random();
						Math.random();
						Math.random();
						Math.random();						
					}
					System.out.println("������ɣ�");			
				
			}
		};
		try {
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);		
		Thread.sleep(1000);
		List<Runnable> runList = executor.shutdownNow();	
		System.out.println("main end");
		System.out.println("δִ�е��߳�������"+runList.size());		

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ThreadPoolExecutor.isShutdown()  �����Ĳ���
	 * @param runnable
	 */
	public void isShutdownExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 4, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		executor.execute(runnable);
		executor.shutdown();
		System.out.println("�̳߳��Ƿ񱻹رգ�"+executor.isShutdown());
		System.out.println("main end");
	}
	
	/**
	 * isTerminating() �Ƿ�����ֹͣ 
	 * isTerminated()  �Ѿ�ֹͣ�����Ĳ���
	 */
	public void isTerminatingExecutor(){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		Runnable runnable = new Runnable() {	
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName()+":���п�ʼ");
					Thread.sleep(2000);
					System.out.println(Thread.currentThread().getName()+":�������");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		try {
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);
		executor.execute(runnable);		
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());
		executor.shutdown();
		Thread.sleep(1000);
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());		
		Thread.sleep(1000);
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());
		Thread.sleep(1000);
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());
		Thread.sleep(1000);
		System.out.println(executor.isTerminating() + " "+ executor.isTerminated());
		
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * awaitTermination(long timeout, TimeUnit unit)
	 * �ڵ�λʱ���ڲ鿴�̳߳��Ƿ��Ѿ�ֹͣ�������˷�����������ֱ��ʱ�䵽�˻����̳߳ع�������
	 * @param runnable
	 */
	public void awaitTerminationExecutor(Runnable runnable){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		try {
		System.out.println(System.currentTimeMillis()+" ϵͳʱ��");	
		executor.execute(runnable);		
		executor.shutdown();		
		System.out.println(System.currentTimeMillis()+" ��ʼʱ��");	
		System.out.println(" ��ָ����ʱ�����̳߳�ʱ��ֹͣ������"+executor.awaitTermination(5, TimeUnit.SECONDS));		
		System.out.println(System.currentTimeMillis()+" ����ʱ��");	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
