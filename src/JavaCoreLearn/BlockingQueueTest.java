package JavaCoreLearn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) throws InterruptedException {
		//����һ������Ϊ2����������
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(2);
		bq.put("java");//bq.add(),bq.offer()��ͬ
		bq.put("jaja");
		System.out.println("1");
//		bq.put("vava");//�����߳�
//		bq.add("vava");//�׳��쳣����������
		bq.offer("vava");//����false,Ԫ�ز������
		System.out.println("2");

	}

}
