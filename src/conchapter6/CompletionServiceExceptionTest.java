package conchapter6;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * 
 * �쳣����  �̳߳�Ϊ���߳�
 * @author LiZhenhua
 *
 */
public class CompletionServiceExceptionTest {
	
	public static void main(String[] args) throws Exception {
		CompletionServiceExceptionTest thisClass =  new CompletionServiceExceptionTest();
//		thisClass.useTakeException();
//		thisClass.useGetException();
//		thisClass.useGetException2();
//		thisClass.usePollException();
//		thisClass.usePollGet1Exception();
		thisClass.usePollGet1Exception2();
		
	}
	
	class MyCallable1 implements Callable<String>{
		
		private String name;
		private int times;
		
		public MyCallable1(String name,int times){
			this.name = name;
			this.times = times;
		}

		@Override
		public String call() {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+" "+name+"��������");	
			try {
				Thread.sleep(times);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+" "+name+"�������");
			return name;			
		}		
	}
	
	class MyCallable2 implements Callable<String>{
		
		private String name;
		private int times;
		
		public MyCallable2(String name,int times){
			this.name = name;
			this.times = times;
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+" "+name+"��������");	
			try {
				Thread.sleep(times);
				int i = 0;
				if(i == 0){
					throw new Exception("�׳��쳣");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis()+" "+name+"�������");
			return name;			
		}		
	}
	
	
	/**
	 * ֻʹ��take��ȡFuture�Ļ���B��������쳣
	 * @throws Exception
	 */
	public void useTakeException() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		
		csRef.submit(b);
		csRef.submit(a);
		
		for(int i=0;i<2;i++){
			System.out.println("�����������"+csRef.take());
		}
		System.out.println("main end");
		
		
	}
	
	/**
	 * a�����У���ȷ����
	 * b�������У��׳� �쳣
	 * @throws Exception
	 */
	public void useGetException() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		csRef.submit(a);//a������
		csRef.submit(b);//b��������
		
		
		for(int i=0;i<2;i++){
			System.out.println("�ȴ������"+i);
			System.out.println("�����������"+csRef.take().get());
		}
		System.out.println("main end");		
	}
	
	
	/**
	 * b�����У��׳��쳣,get()����û��ȡ��������ȴ�
	 * a�������У����ڵ�һ��getû�еȴ��������һֱ�ڵȴ�b�Ľ����������aû�з���
	 * @throws Exception
	 */
	public void useGetException2() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		csRef.submit(b);//b������
		csRef.submit(a);
		
		
		
		for(int i=0;i<2;i++){
			System.out.println("�ȴ������"+i);
			System.out.println("�����������"+csRef.take().get());
		}
		System.out.println("main end");		
	}
	
	/**
	 * poll ֻ�õ�Futrue���׳��쳣
	 * @throws Exception
	 */
	public void usePollException() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		
		csRef.submit(b);
		csRef.submit(a);
		
		for(int i=0;i<2;i++){
			System.out.println("�����������"+csRef.poll());
		}
		Thread.sleep(6000);
		System.out.println("���1��������"+csRef.poll());
		System.out.println("���2��������"+csRef.poll());
		
		System.out.println("main end");
		
		
	}
	
	
	/**
	 * a�з��ؽ��
	 * b�׳��쳣��һֱ�ȴ�get()�����޷��õ�������������
	 * @throws Exception
	 */
	public void usePollGet1Exception() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",4000);
		
		
		csRef.submit(a);
		csRef.submit(b);
		
		for(int i=0;i<2;i++){
			System.out.println("�����������"+csRef.poll());
		}
		Thread.sleep(6000);
		System.out.println("���1��������"+csRef.poll().get());
		System.out.println("���2��������"+csRef.poll().get());
		
		System.out.println("main end");
		
		
	}
	
	/**
	 * �׳��쳣���ȴ�b��get�����һֱ����
	 * aû�з��ؽ��
	 * @throws Exception
	 */
	public void usePollGet1Exception2() throws Exception{
		Executor executor = Executors.newSingleThreadExecutor();
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable1 a = new MyCallable1("A",1000);
		MyCallable2 b = new MyCallable2("B",3000);
		
		
		csRef.submit(b);//b������
		csRef.submit(a);
		
		for(int i=0;i<2;i++){
			System.out.println("�����������"+csRef.poll());
		}
		Thread.sleep(6000);
		System.out.println("���1��������"+csRef.poll().get());
		System.out.println("���2��������"+csRef.poll().get());
		
		System.out.println("main end");
		
		
	}
	
	

}
