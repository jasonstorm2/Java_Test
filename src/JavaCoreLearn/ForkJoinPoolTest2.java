package JavaCoreLearn;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest2 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int arr[]	= new int[100];
		Random rand = new Random();		
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			int tmp = rand.nextInt(20);
			
			total +=(arr[i] = tmp);			
		}
		System.out.println("total= "+total);
		//����һ��ͨ�ó�
		ForkJoinPool pool = ForkJoinPool.commonPool();
		Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
		System.out.println(future.get());		
		pool.shutdown();
		
	}

}

class CalTask extends RecursiveTask<Integer>{
	//ÿ��С���� ��� �ۼ�20����
	private static final int THRESHOLD = 20;
	private int arr[];
	private int start;
	private int end;
	
	//�ۼӴ�start ��end ������Ԫ��
	public CalTask(int[] arr,int start,int end){
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub
		int sum = 0;
		if(end - start <THRESHOLD){
			for (int i = start;i < end; i++) {
				sum += arr[i];
			}
			return sum;
		}else{
			//��end��start ֮��Ĳ�����THRESHOLD����Ҫ�ۼӵ�������20��ʱ
			//�������� �ֽ������ С����
			int middle = (start+end)/2;
			CalTask left = new CalTask(arr,start,middle);
			CalTask right = new CalTask(arr,middle,end);
			//����ִ������ С����
			left.fork();
			right.fork();
			//�� ����С���� �ۼӵĽ�� �ϲ�����
			return left.join()+right.join();			
		}	
	}
	
}
