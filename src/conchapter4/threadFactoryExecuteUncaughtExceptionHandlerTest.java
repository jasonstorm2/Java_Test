package conchapter4;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ��ʱ����Ҫ���̳߳��е��߳����� ���ж��ƻ��������Ҫ����ThreadFactory�̹߳���
 * 
 * ThreadFactory + execute() + ExecuteUncaughtExceptionHandler
 * 
 * �Զ�����̹߳������Բ����̳߳��ֵ��쳣��Thread.setUncaughtExceptionHandler(UncaughtExceptionHandler eh)
 * 
 * ThreadPoolExecutor�̳߳�����Զ����̹߳��������ַ�����
 *    1.�ڹ��캯������Ӳ�����
 *    2.ThreadPoolExecutor����setThreadFactory(threadFactory);
 * 
 * 
 * @author Administrator
 *
 */
public class threadFactoryExecuteUncaughtExceptionHandlerTest {
	
	public static void main(String[] args) {
		threadFactoryExecuteUncaughtExceptionHandlerTest thisClass = new 
				threadFactoryExecuteUncaughtExceptionHandlerTest();
		
		MyThreadFactory myFa = thisClass.new MyThreadFactory();
		MyRunnable myRunnable = thisClass.new MyRunnable();

		thisClass.catchExceptionTest(myRunnable,myFa);
	}
	
	// �Զ����̹߳���
	class MyThreadFactory  implements ThreadFactory{
		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			Thread myThread = new Thread(r);
			myThread.setName("�Զ�����߳�");
			myThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {				
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.out.println("�Զ��崦���쳣���ã�"+t.getName() + " "+e.getMessage());
					e.printStackTrace();//��ӡ�쳣·����Ϣ
					
				}
			});
			return myThread;
		}	
	}
	
	// �Զ����߳�
	class MyRunnable implements Runnable{

		@Override
		public void run() {
			try {
				
				System.out.println(Thread.currentThread().getName() +" run "+System.currentTimeMillis());
				String str = null;
				int len = str.indexOf(10);
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() +" over "+System.currentTimeMillis());			

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @param runnable
	 */
	public void catchExceptionTest(Runnable runnable,ThreadFactory threadFactory){
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//		ThreadPoolExecutor executor = new 
//				ThreadPoolExecutor(2, 999, 999, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),threadFactory);
		
		executor.setThreadFactory(threadFactory);		
		try {
		executor.execute(runnable);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
