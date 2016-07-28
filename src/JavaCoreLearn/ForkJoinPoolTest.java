package JavaCoreLearn;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest {
	public static void main(String[] args) throws InterruptedException {

		ForkJoinPool pool = new ForkJoinPool();
		//�ύ�ɷֽ��printtask����
		pool.submit(new PrintTask(0, 300));
		pool.awaitTermination(2, TimeUnit.SECONDS);
		//�ر��̳߳�
		pool.shutdown();
		
	}
}

class PrintTask extends RecursiveAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//ÿ�� С���� ��� ֻ��ӡ 50 ����
	private static final int THRESHOLD = 50;
	private int start;
	private int end;
	
	public PrintTask(int start,int end){
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		System.out.println("�̣߳�"+Thread.currentThread().getName());

		// TODO Auto-generated method stub
		if(end - start<THRESHOLD){
			for (int i = start; i < end; i++) {
				System.out.println(Thread.currentThread().getName()+"��iֵ"+i);
			}
		}else{
			int times = 1;
			System.out.println();
			int middle = (start+end)/2;
			PrintTask left = new PrintTask(start,middle);
			PrintTask right = new PrintTask(middle,end);
			//������������
			left.fork();
			right.fork();			
		}		
	}	
}
