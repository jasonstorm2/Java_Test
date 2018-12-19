package conchapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * �ӿ�CompletionService�Ľ���
 * 
 * CompletionService���첽��ʽһ�������µ�����һ�ߴ����ѽ���ɵ����� 
 * ʹ��submit�ύ����ʹ��takeȡ������ɵ�����
 * 
 * CompletionService�ӿ�ֻ��һ��ʵ����ExecutorCompletionService><V v>(Executor executor)
 * 
 * CompletionService����submit�������������ɺ���ȷ��أ�����������
 * 
 * ʹ��take��ȡFuture����
 * ˭��ִ���꣬take����ȡ��˭��Future,���û����ɵģ����ȵȴ��������ǻ���һ������,����Futrue��get��һ��
 * 
 * ʹ��poll()������������������Ч��������У���Future���󷵻أ�û���򷵻�null
 * 
 *  poll(long timeout,TimeUnit unit)  :�ȴ�ָ����timeoutʱ�䣬�ڸ�ʱ���ڻ�ȡ��ֵʱ�������������С������ʱҲ��������ִ��
 * 
 * @author LiZhenhua
 *
 */
public class CompletionServiceTest {
	
	public static void main(String[] args) {
		CompletionServiceTest thisClass = new CompletionServiceTest();
		thisClass.takeTest();
//		thisClass.pollTest();
//		thisClass.pollTest2();
		
		
	}
	
	class MyCallable implements Callable<String>{
		
		private String name;
		private int times;
		
		public MyCallable(String name,int times){
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
	
	/**
	 * ��ʼ����߳�����
	 * ʹ��take������������ֱ����һ���߳�������񣬸������Future�������û����һֱ����
	 */
	public void takeTest(){
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable a = new MyCallable("A",5000);
		MyCallable b = new MyCallable("B",4000);
		MyCallable c = new MyCallable("C",3000);
		MyCallable d = new MyCallable("D",2000);
		MyCallable e = new MyCallable("E",1000);
		List<Future<String>> fuList = new ArrayList<Future<String>>();
		System.out.println("��ʼʱ�䣺"+System.currentTimeMillis());
		try {
			fuList.add(csRef.submit(a));// �����쳣
			fuList.add(csRef.submit(b));// �����쳣
			fuList.add(csRef.submit(c));// �����쳣
			fuList.add(csRef.submit(d));// �����쳣
			fuList.add(csRef.submit(e));// �����쳣
			
			for (int i = 0; i < 6; i++) {
				System.out.println("�ȴ���ӡ��"+(i+1)+"��ֵ��");
				System.out.println("���֣�"+csRef.take()+"  ʱ�䣺"+System.currentTimeMillis());
				System.out.println("list��size��"+fuList.size());
			}
//			for (int i = 0; i < 6; i++) {
//				System.out.println("�ȴ���ӡ��"+(i+1)+"��ֵ��");
//				System.out.println("���֣�"+csRef.take().get()+"  ʱ�䣺"+System.currentTimeMillis());
//				System.out.println("list��size��"+fuList.size());
//			}
			System.out.println("ִ����ϣ���"+System.currentTimeMillis());

		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("���쳣�׳�");
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * ��ʼ����߳�����
	 * ʹ��poll�����������future���󷵻أ����û��ֱ�ӷ���null����������
	 */
	public void pollTest(){
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable a = new MyCallable("A",5000);
		MyCallable b = new MyCallable("B",4000);
		MyCallable c = new MyCallable("C",3000);
		MyCallable d = new MyCallable("D",2000);
		MyCallable e = new MyCallable("E",1000);
		List<Future<String>> fuList = new ArrayList<Future<String>>();
		System.out.println("��ʼʱ�䣺"+System.currentTimeMillis());
		try {
			fuList.add(csRef.submit(a));// �����쳣
			fuList.add(csRef.submit(b));// �����쳣
			fuList.add(csRef.submit(c));// �����쳣
			fuList.add(csRef.submit(d));// �����쳣
			fuList.add(csRef.submit(e));// �����쳣
			
			for (int i = 0; i < 6; i++) {
				System.out.println("�ȴ���ӡ��"+(i+1)+"��ֵ��");
				System.out.println("poll�����"+csRef.poll());
				System.out.println("ʱ��"+System.currentTimeMillis());
				System.out.println("list��size��"+fuList.size());
			}
			System.out.println("ִ����ϣ���"+System.currentTimeMillis());

		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("���쳣�׳�");
			e1.printStackTrace();
		}
	}
	
	/**
	 * poll(long timeout,TimeUnit unit)����
	 */
	public void pollTest2(){
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		CompletionService<String> csRef = new ExecutorCompletionService<String>(executor);
		
		MyCallable a = new MyCallable("A",5000);
		MyCallable b = new MyCallable("B",4000);
//		MyCallable c = new MyCallable("C",3000);
//		MyCallable d = new MyCallable("D",2000);
//		MyCallable e = new MyCallable("E",1000);
		List<Future<String>> fuList = new ArrayList<Future<String>>();
		System.out.println("��ʼʱ�䣺"+System.currentTimeMillis());
		try {
			fuList.add(csRef.submit(a));// �����쳣
			fuList.add(csRef.submit(b));// �����쳣
//			fuList.add(csRef.submit(c));// �����쳣
//			fuList.add(csRef.submit(d));// �����쳣
//			fuList.add(csRef.submit(e));// �����쳣
			
			for (int i = 0; i < 3; i++) {
				System.out.println("�ȴ���ӡ��"+(i+1)+"��ֵ��");
				System.out.println("poll�����"+csRef.poll(3,TimeUnit.SECONDS));
				System.out.println("ʱ��"+System.currentTimeMillis());
				System.out.println("list��size��"+fuList.size());
			}
			System.out.println("ִ����ϣ���"+System.currentTimeMillis());

		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("���쳣�׳�");
			e1.printStackTrace();
		}
	}

}
