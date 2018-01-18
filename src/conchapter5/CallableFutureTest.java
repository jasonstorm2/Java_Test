package conchapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * Callable�̺߳�Runnable�̵߳�����
 * 
 * Callable<V> �ӿڵ�call()�����з���ֵ,����V���ͣ�����ֵ��ͨ��Future�ӿڽ��л�õġ�   run()�޷���ֵ
 * 				call()���������׳��쳣��			run()���������׳��쳣
 * 
 * submit ,callable,��Future get()�Ĺ�ϵ���̳߳��������callableȡ�÷���ֵ
 * Future<String> java.util.concurrent.AbstractExecutorService.submit(Callable<String> task)
 * �̳߳ص�submit�������ύcallable�̣߳�����һ��Future����
 * ����Future��get()����ȡ�÷���ֵ��
 * 
 * submit����Ҳ���Դ���runnable�ӿ�
 * 
 * Future�ӿڵĸ�������
 * get()���õ�����ֵ,��������
 * 
 * isDone()���ж��߳��Ƿ���ϣ�����Ƿ񷵻أ�������
 * 
 * 
 * boolean canceled(boolean mayInterruptIfRunning)
 * 
 * boolean isCancelled()��
 * 
 * Future.get(long timeout, TimeUnit unit) 
 * 
 * @author Administrator
 */
public class CallableFutureTest {
	
	public static void main(String[] args) {
		CallableFutureTest thisClass = new 
				CallableFutureTest();	
//		thisClass.CallableTest();
//		thisClass.RunnableTest();
//		thisClass.futureCancelTest();
//		thisClass.futureGetByTimeTest();
		thisClass.callableExceptionTest();
	}	

	
	// �Զ���Callable�߳�
	class MyCallable implements Callable<String>{
		private String username;
		
		public MyCallable(String username){
			this.username = username;
		}
		
		public String getUsername(){
			return username;
		}
		
		public void setUsername(String username){
			this.username = username;
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			Thread.sleep(2000);
			String str = "�����߳�����"+username;
			System.out.println(System.currentTimeMillis()+"ִ���С�����");
			Integer.parseInt("a");
			return str;
		}
		
	}
	
	// �Զ���Runnable�߳�
	class MyRunable implements Runnable{
		private String username;
		
		public MyRunable(String username){
			this.username = username;
		}
		
		public String getUsername(){
			return username;
		}
		
		public void setUsername(String username){
			this.username = username;
		}

		@Override
		public void run(){
			// TODO Auto-generated method stub
			try {
				
				String str = "callable�߳�ִ���ˣ��߳�����"+username;				
				while(true){
					System.out.println("ִ���С�����");
					if(Thread.interrupted()){
						throw new InterruptedException();
					}
				}				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	


	/**
	 * submit(Callable<String> task)������
	 * @param runnable
	 */
	public void CallableTest(){		
		MyCallable myCallable = new MyCallable("loliy");
		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		Future<String> fu = executor.submit(myCallable);
		System.out.println(System.currentTimeMillis()+"��ʼִ��");
		try {
			System.out.println("future�Ƿ���ɣ�"+fu.isDone());
			System.out.println("�߳�ִ�н����"+fu.get());
			System.out.println("future�Ƿ���ɣ�"+fu.isDone());
			System.out.println(System.currentTimeMillis()+"ִ�����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.allowCoreThreadTimeOut(true);
		
	}
	
	
	/**
	 * submit(Runnable task, T result)������
	 * T������Ϊִ�н���ķ���ֵ��������Ҫʹ��get()���������л��
	 * 
	 */
	public void RunnableTest(){	
		String name = "lolita";
		MyRunable myRunnable = new MyRunable(name);

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		
		Future<String> fu = executor.submit(myRunnable,name);
		System.out.println(System.currentTimeMillis()+"��ʼִ��");
		try {
			System.out.println("�߳�ִ�н����"+fu.get());
			System.out.println(System.currentTimeMillis()+"ִ�����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.allowCoreThreadTimeOut(true);
		
	}
	
	
	/**
	 * Future����������
	 * boolean canceled(boolean mayInterruptIfRunning)���Ƿ���ȡ�����������Ƿ��ж��������е�����ȡ����mayInterruptIfRunning ����Ҫif(Thread.isInterrupted())�������
	 *         �÷������ص��� ����ȡ����������� �Ƿ�ɹ����
	 * boolean isCancelled()����ʾ�����Ƿ�ȡ����
	 */
	public void futureCancelTest(){
		String name = "lolita";
		MyRunable myRunnable = new MyRunable(name);

		ThreadPoolExecutor executor = new 
				ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		Future<String> fu = executor.submit(myRunnable,name);	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(fu.cancel(true) + " "+fu.isCancelled());
		System.out.println(fu.cancel(false) + " "+fu.isCancelled());
		
	}
	
	/**
	 * Future.get(long timeout, TimeUnit unit) 
	 * ��һ��ʱ���ڵȴ�����ֵ�����û�з��أ��򱨴�
	 */
	public void futureGetByTimeTest(){	
		try {
			String name = "lolita";
			MyCallable myCallable = new MyCallable(name);

			ThreadPoolExecutor executor = new 
					ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			executor.allowCoreThreadTimeOut(true);
			
			Future<String> fu = executor.submit(myCallable);			
			System.out.println("��÷���ֵ��"+fu.get(1, TimeUnit.SECONDS));			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * callable�ӿ��ڱ���Ĵ���
	 *    ������������catch���飬��������ִ��get()����
	 */
	public void callableExceptionTest(){	
		try {
			String name = "lolita";
			MyCallable myCallable = new MyCallable(name);

			ThreadPoolExecutor executor = new 
					ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
			executor.allowCoreThreadTimeOut(true);
			
			Future<String> fu = executor.submit(myCallable);			
			System.out.println("��÷���ֵ��"+fu.get(3, TimeUnit.SECONDS));			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			System.out.println("��������");
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
