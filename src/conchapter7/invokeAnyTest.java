package conchapter7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * invokeAny ���̳߳�����һ���߳̽���ʱ�����ظ��̣߳����ҵ���interrupt()�ж������������е��߳�
 * �����߳̿��Խ��if(Thread.currentThread().isInterrupted() == true)���ж��߳��Ƿ��������
 * ��������߳����жϣ����쳣����������ô���Զ���÷���
 * ��û�У���ó����������
 * 
 * ��������߳���Thread.currentThread().isInterrupted()�жϣ���throw new Exception(),��Ȼ�׳����쳣����main�߳��в����ܲ����쳣��
 * ������callable��ʹ��try-catch��ʽ����,�����ڿ���̨��ӡ���쳣��Ϣ
 * @author Administrator
 *
 */
public class invokeAnyTest {
	
	public static void main(String[] args) {
		invokeAnyTest thisClass = new invokeAnyTest();
		
		
		/********************/
//		try {
//			thisClass.normalThrows();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			System.out.println("��ͨ�����׳��쳣�Ĵ���");
//		}
		/********************/
//		thisClass.invokeAny();
		
		/********************/
//		thisClass.invokeAny2();//���벶���쳣�����򱨴�
//		try {//main��û�а취�����쳣
//			thisClass.invokeAny2();
//		} catch (Exception e) {
//			System.out.println("*************���������쳣");
//			System.out.println("���߳��Ƿ�׽���쳣�ˣ���������������");
//			e.printStackTrace();
//		}
		
		/********************/
//		thisClass.invokeAny3();
		
		/********************/
//		try {
//			thisClass.invokeAny4();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		System.out.println("���߳��Ƿ�׽���쳣�ˣ���������������");
//		}
		
		/********************/
//		try {
//			thisClass.invokeAny5();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("���߳��Ƿ�׽���쳣�ˣ���������������");
//			e.printStackTrace();
//		}
		
		/********************/
//		try {
//			thisClass.invokeAny6();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("���߳��Ƿ�׽���쳣�ˣ���������������");
//			e.printStackTrace();
//		}
		
		/********************/
//		try {
//			thisClass.invokeAny7();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("mainx�̲߳�׽�����쳣�����ڴ���");
//			e.printStackTrace();
//		}
		
		/********************/
		try {
			thisClass.invokeAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("main�������쳣��");
			e.printStackTrace();
		}
		
		
	}
	
	public void shutDownPoll(ExecutorService executor){
		executor.shutdown();
	}
	
	/**
	 * û���߳��жϴ�����������
	 * @author Administrator
	 *
	 */
	class Callable1 implements Callable<String>{
		int nums = 0;
		
		public Callable1(int nums){
			this.nums = nums;			
		}
		@Override
		public String call(){
			System.out.println("�߳�1--��ʼ");
			for(int i=0;i<nums;i++){
				Math.random();
				Math.random();
				Math.random();	
				Math.random();
				Math.random();
				System.out.println("�߳�1�����"+i);
			}
			System.out.println("�߳�1---------����"+nums);
			return "1������";			
		}		
	}
	
	/**
	 * ���߳��жϴ����׳��쳣
	 * 
	 * ����trycatch���в���
	 * 
	 * @author Administrator
	 *
	 */
	class Callable2 implements Callable<String>{
		
		int nums = 0;
		
		public Callable2(int nums){
			this.nums = nums;			
		}
		
		@Override
		public String call() throws Exception{
			try {
				System.out.println("�߳�2--��ʼ");
				for(int i=0;i<nums;i++){
					if(!Thread.currentThread().isInterrupted()){
						Math.random();
						Math.random();
						Math.random();		
						System.out.println("�߳�2�����"+i);
					}else{
						System.out.println("2�߳��쳣�ж���");
						throw new Exception("2�쳣���ж��߳�");
					}
					
				}
				System.out.println("�߳�2--����"+nums);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ͨ����ʽ����trycatch�����쳣");
				throw e;
			}

			return "2������";			
		}		
	}
	
	/**
	 * ���߳��жϴ����׳��쳣
	 * 
	 * ��trycatch���в���
	 * 
	 * @author Administrator
	 *
	 */
	class Callable3 implements Callable<String> {
		
		int nums = 0;
		
		public Callable3(int nums){
			this.nums = nums;			
		}
		@Override
		public String call() throws Exception {
			System.out.println("�߳�3--��ʼ");		
			for (int i = 0; i < nums; i++) {
				if (!Thread.currentThread().isInterrupted()) {
					Math.random();
					Math.random();
					Math.random();
					System.out.println("�߳�3�����" + i);
				} else {
					System.out.println("3�߳��쳣�ж���"+nums);
					throw new Exception("3�쳣���ж��߳�"+nums);
				}

			}
			System.out.println("�߳�4--����"+nums);		
			return "4������";
		}			
	}
	
	/**
	 * �����׳��쳣����trycatch����
	 * @author Administrator
	 *
	 */
	class Callable4 implements Callable<String> {
		int nums = 0;
		
		public Callable4(int nums){
			this.nums = nums;			
		}
		@Override
		public String call() throws Exception {
			System.out.println("�߳�4--��ʼ");		
			for (int i = 0; i < nums; i++) {
				Math.random();
				Math.random();
				Math.random();
				System.out.println("�߳�4�����" + i);
			}
			throw new Exception("4�쳣���ж��߳�"+nums);
		}			
	}
	
	
	/**
	 * �����׳��쳣, ��trycatch����
	 * 
	 * 
	 * ���쳣����trycatch�������ܴ�ӡ���쳣·��
	 * ���catch��û�����׳��쳣����main�̻߳�ȡ���Ǳ��̵߳ķ���ֵ
	 * ������׳��쳣��main�̻߳�ȡ������һ���̵߳ķ���ֵ
	 * 
	 * û�����׳��쳣������main�߳���Ϊ���߳�����ȷ�ģ���ȡ���Ǳ��̵߳ķ���
	 * 
	 * @author Administrator
	 *
	 */
	class Callable5 implements Callable<String> {
		int nums = 0;
		
		public Callable5(int nums){
			this.nums = nums;			
		}
		@Override
		public String call() throws Exception {			
			try {
				System.out.println("�߳�5--��ʼ");		
				for (int i = 0; i < nums; i++) {
					Math.random();
					Math.random();
					Math.random();
					System.out.println("�߳�5�����" + i);
				}
				if(1 == 1){
					throw new Exception("5�쳣���ж��̣߳����߳��Լ������쳣"+nums);//�˴��߳��Լ������˱��̵߳��쳣�����ڿ���̨��ӡ����
				}				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw e; //���׳��쳣�򷵻ص��Ǳ��̵߳ķ��أ��׳��쳣��main�߳̿��Բ���
			}
			return "return 5 "+nums;
			
		}			
	}
	
	/**
	 * �������׳��쳣�����жϴ���
	 * @author Administrator
	 *
	 */
	class Callable6 implements Callable<String>{
		ExecutorService executor = null;
		int nums = 0;
		
		public Callable6(int nums,ExecutorService executor){
			this.nums = nums;	
			this.executor = executor;
		}
		@Override
		public String call(){		
			try {				
				System.out.println(nums+"���߳�Callable6--��ʼ");
				for(int i=0;i<nums;i++){
					Math.random();
					Math.random();
					Math.random();	
					Math.random();
					Math.random();
					System.out.println(nums+"���߳�Callable6�����"+i);
					//��������쳣û�д������߳�һֱ�ȴ�����������̲�����
					if(Thread.currentThread().isInterrupted() == true){
						System.out.println("Thread.currentThread().isInterrupted() == true");
						shutDownPoll(executor);
						throw new NullPointerException();
//						return "�����жϷ���";// ��Ȼû���׳��̣߳������߳��Ӳ����˳�ʱ�쳣
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("call�̲߳����쳣");
			}
		
			System.out.println(nums+"���߳�Callable6---------����"+nums);
			return nums+"Callable6������";			
		}		
	}
	
	/**
	 * ��ͨ�����׳��쳣����
	 * 
	 * �������׳��쳣������Ҫtrycatch�����ڷ��������׳��쳣
	 * @throws Exception
	 */
	public void normalThrows() throws Exception{
		throw new Exception();		
	}
	
	/**
	 * ���̳߳�����һ���߳̽���ʱ�����ظ��̣߳����ҵ���interrupt()�ж������������е��߳�
	 * �����߳�û��Thread.currentThread().isInterrupted()�жϣ����쳣��������
	 * ���������̼߳�������ֱ������
	 */
	public void invokeAny(){
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable1(123));
		call.add(new Callable1(1234));
		
		ExecutorService executor = Executors.newCachedThreadPool();
		try {
			String str = executor.invokeAny(call);
			System.out.println("������Ϣ��"+str);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		shutDownPoll(executor);		
	}
	
	/**
	 * 
	 * invokeAny��ִ������������쳣
	 * ���ؿ�����ķ��أ����������ж�
	 * 
	 * Callable2�߳��׳�Exception�쳣�����Ը÷���ҲҪ�׳��쳣������try����
	 * 
	 * Callable2�߳��ж��Ƿ��жϣ������жϽ��д����׳����ж��쳣
	 * ����Callable2��trycatch�����쳣������trycatch�쳣���������ܲ����쳣
	 * ˵�����̵߳��쳣�ǲ�Ӱ��main�̵߳������̵�
	 * 
	 * @throws Exception 
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public void invokeAny2() throws Exception{
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable1(123));
		call.add(new Callable2(1234));
		
		ExecutorService executor = Executors.newCachedThreadPool();

		String str = executor.invokeAny(call);
		System.out.println("������Ϣ��"+str);
		shutDownPoll(executor);	
	}
	
	/**
	 * 
	 * invokeAny��ִ������������쳣
	 * ���ؿ�����ķ��أ����������ж�
	 * 
	 * Callable3�߳��׳����쳣���ڱ��������в�����
	 * 
	 * Callable3�ж��߳��Ƿ��жϣ������жϽ��д����׳����ж��쳣
	 * ����Callable3������trycatch�����쳣������̨�޷���ӡ�쳣��Ϣ
	 * 
	 * @throws Exception 
	 * @throws InterruptedException 
	 */
	public void invokeAny3() {
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable1(123));
		call.add(new Callable3(1234));
		ExecutorService executor = Executors.newCachedThreadPool();
		String str;
		try {
			str = executor.invokeAny(call);
			System.out.println("������Ϣ��" + str);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shutDownPoll(executor);	
	}
	
	/**
	 * ��invokeAny3()һ����ֻ�����ǰ�trycatch�����׳��쳣
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public void invokeAny4() throws Exception {
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable1(123));
		call.add(new Callable3(1234));
		ExecutorService executor = Executors.newCachedThreadPool();
		String str;
		str = executor.invokeAny(call);
		System.out.println("������Ϣ��" + str);
		shutDownPoll(executor);	
	}	
	
	/**
	 * invokeAny()���������̶߳��׳��쳣����ô�����ճ��ֵ��쳣�����һ���쳣
	 * @throws Exception
	 */
	public void invokeAny5() throws Exception {
		List<Callable<String>> call = new ArrayList<Callable<String>>();
		call.add(new Callable4(12345));
		call.add(new Callable4(123456));
		ExecutorService executor = Executors.newCachedThreadPool();
		String str;
		str = executor.invokeAny(call);
		System.out.println("������Ϣ��" + str);
		shutDownPoll(executor);	
	}
	
	/**
	 * ִ�п����������쳣����ִ������û���쳣�����
	 * ���ص���ִ�����쳣�ķ���
	 * ����������쳣�򷵻����һ�����ֵ��쳣
	 * 
	 * ����������catch��û�����׳��쳣����main�̻߳�ȡ���ǿ������̵߳ķ���ֵ
	 * ������׳��쳣��main�̻߳�ȡ������һ���̵߳ķ���ֵ
	 * @throws Exception
	 */
	public void invokeAny6() throws Exception {
			List<Callable<String>> call = new ArrayList<Callable<String>>();
//			call.add(new Callable4(123));//����ӡ�쳣
//			call.add(new Callable5(12));//��ӡ�쳣
//			call.add(new Callable5(50));//��ӡ�쳣
			
			call.add(new Callable4(50));//��ӡ�쳣
			
			call.add(new Callable1(183));
			ExecutorService executor = Executors.newCachedThreadPool();
			String str;
			str = executor.invokeAny(call);
			System.out.println("������Ϣ��" + str);
			shutDownPoll(executor);	
	}	
	
	/**
	 * �ڵ�λʱ����ȡ�õ�һ��ִ��������Ľ��ֵ�������߳��ж�
	 * invokeAny(Collection<? extends Callable<String>> tasks, long timeout, TimeUnit unit) 
	 * ��ֻ��һ���̣߳����ҳ�ʱ����ô�߳��׳��쳣��main�߳��ܲ���ʱ�쳣
	 * 
	 * @throws Exception
	 */
	public void invokeAny7() throws Exception {
		List<Callable<String>> call = new ArrayList<Callable<String>>();

		ExecutorService executor = Executors.newCachedThreadPool();
		call.add(new Callable6(199988, executor));
//		call.add(new Callable6(19, executor));
		
		String str;
		str = executor.invokeAny(call, 1, TimeUnit.SECONDS);
		System.out.println("������Ϣ��" + str);
		shutDownPoll(executor);
	}
	
	
	/**
	 * invokeAll()�������������ִ�н�����˷���������
	 * ������������쳣����ȷ��ӡ��������ķ��ؽ����main�߳̿��Բ�����������쳣
	 *     ���������Լ��������쳣��main�߳�Ҳ�ǿ��Բ����쳣����Ϊ�������׳����쳣
	 * 
	 * @throws Exception
	 */
	public void invokeAll() throws Exception {
		List<Callable<String>> call = new ArrayList<Callable<String>>();

		ExecutorService executor = Executors.newCachedThreadPool();
		/** ����ȷ�����쳣����� **/
//		call.add(new Callable6(12, executor));
//		call.add(new Callable4(50));
////		call.add(new Callable5(50));

		/** ����ȷ�����쳣����� **/
		
		 call.add(new Callable4(12));
//		 call.add(new Callable5(12));
		 call.add(new Callable6(50000, executor));

		List<Future<String>> list = executor.invokeAll(call);
		for (Future<String> future : list) {
			String str = future.get();
			System.out.println("������Ϣ��" + str);
		}
		System.out.println("�Ƿ��������1");
		shutDownPoll(executor);
	}

}
