package conchapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * ����֤���̳߳ص������ԣ���ִ�е�˳�����ʼ��˳��һ��һ��
 * Ϊ�˽��������⣬����������˽������
 * @author Administrator
 *
 */
public class FutureNotGoodTest {
	
	public static void main(String[] args) {
		FutureNotGoodTest thisClass = new FutureNotGoodTest();
		
		ThreadPoolExecutor executor = new  ThreadPoolExecutor(5, 10, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		executor.allowCoreThreadTimeOut(true);
		
		MyCallable a = thisClass.new MyCallable("A",5000);
		MyCallable b = thisClass.new MyCallable("B",4000);
		MyCallable c = thisClass.new MyCallable("C",3000);
		MyCallable d = thisClass.new MyCallable("D",2000);
		MyCallable e = thisClass.new MyCallable("E",1000);
		List<Future<String>> fuList = new ArrayList<Future<String>>();
		System.out.println("��ʼʱ�䣺"+System.currentTimeMillis());
		try {
			fuList.add(executor.submit(a));// �����쳣
			fuList.add(executor.submit(b));// �����쳣
			fuList.add(executor.submit(c));// �����쳣
			fuList.add(executor.submit(d));// �����쳣
			fuList.add(executor.submit(e));// �����쳣
			
			for (int i = 0; i < fuList.size(); i++) {
				System.out.println("���֣�"+fuList.get(i).get());
				System.out.println("ʱ��"+System.currentTimeMillis());
			}
			System.out.println("ִ����ϣ���"+System.currentTimeMillis());

		} catch (Exception e1) {
			// TODO: handle exception
			System.out.println("���쳣�׳�");
			e1.printStackTrace();
		}
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
			System.out.println(name+"��������");	
			try {
				Thread.sleep(times);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+"�������");
			return name;			
		}
		
	}

}
