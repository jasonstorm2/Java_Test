package JavaCoreLearn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


public class ThreadThird{
	public static void main(String[] args) throws InterruptedException {		
		
		//ĳһ������� ��װ�� futuretask,������ʱ���̵߳���
		FutureTask<Integer> task = new FutureTask<Integer>(
				(Callable<Integer>)()->{
					int i = 0;
					for(;i<100;i++){
						System.out.println(Thread.currentThread().getName()+" ��ѭ������i��ֵ"+i);
					}
					return i;
				}
			);	
		
		FutureTask<Integer> task2 = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("���ظ�ֵ");
				return -1;
			}
		});
		
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" ��ѭ������i��ֵ"+i);			
			if(i == 20){
				new Thread(task,"�з���ֵ���߳�").start();
			}
		}
		
		try {
			System.out.println("��ӡ");
			if(task.isDone()){
				System.out.println("task������");
			}else{
				System.out.println("task���ڼ���");
			}
			System.out.println("����������");
			System.out.println("���̵߳ķ���ֵ��"+task.get());
			System.out.println("���߳�����������");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(10000);
		System.out.println("˯��10��");
		
	}
}
