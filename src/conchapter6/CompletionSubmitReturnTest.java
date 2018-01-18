package conchapter6;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * submit�ύ���̵߳����ַ��ط�ʽ��
 * 
 * CompletionService.submit(Callable);�з���ֵ 		ʹ��Callable
 * CompletionService.submit(Runnable,V v);�з���ֵ	ʹ��Runnable,����ӷ��ص�����
 * 
 * @author Administrator
 *
 */
public class CompletionSubmitReturnTest {

	public static void main(String[] args) throws Exception {
		CompletionSubmitReturnTest thisClass =  new CompletionSubmitReturnTest();
//		thisClass.submintReturnTest();
		thisClass.submintReturnTest2();
		
	}
	
	class MyObj{
		private String name;
		
		public void setName(String name){
			this.name = name;
			
		}
		public String getName(){
			return name;
		}
		
	}
	
	class MyObj2{
		private final String name = "initjiba";		

		public String getName(){
			return name;
		}
		
	}
	
	class MyRunnable implements Runnable{
		
		private MyObj obj;
		
		
		public MyRunnable(MyObj obj){
			this.obj = obj;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+" "+"��������");	
			try {
				Thread.sleep(1000);
				obj.setName("jiba");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+" "+"�������");
		}		
	}
	
	class MyRunnable2 implements Runnable{
		
		private String str;
		
		
		public MyRunnable2(String str){
			this.str = str;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+" "+"��������");	
			try {
				Thread.sleep(1000);
				str = "jiba";
				str = str.replace("mimi", "jiba");
				str = new String("fuck");
				str = str.substring(0, 1);
				System.out.println("�������ֵ��"+str);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+" "+"�������");
		}		
	}
	
	/**
	 * ����ֵ���� ���ض���Ĵ���
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public void submintReturnTest() throws Exception, ExecutionException{
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		CompletionService<MyObj> csRef = new ExecutorCompletionService<MyObj>(executor);		
		
		MyObj obj = new MyObj();
		obj.setName("mimi");
		MyRunnable runnable = new MyRunnable(obj);		
		Future<MyObj> future = csRef.submit(runnable, obj);		
		
		System.out.println(future.get().getName());
	}
	
	/**
	 * String,Integer,Double��immutable����û���ṩ�޸�����ĺ�����ÿ�β�����������һ���µĶ���
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public void submintReturnTest2() throws Exception, ExecutionException{
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);		
		
		String str = new String("mimi");
		MyRunnable2 runnable = new MyRunnable2(str);		
		Future<String> future = csRef.submit(runnable, str);		
		
		System.out.println(future.get());
	}
	
}
