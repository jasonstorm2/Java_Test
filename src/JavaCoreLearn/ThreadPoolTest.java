package JavaCoreLearn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
	public static void main(String[] args) {
		//������6���߳������̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(6);
		
		Runnable target = ()->{
			for (int i = 0; i <200; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" �� i ��ֵ��"+i);
			}
		};
		//���̳߳����ύ �����߳�
		pool.submit(target);
		pool.submit(target);
		//�ر��̳߳�  ��Ȼ�ر��ˣ��ύ���̻߳�ֱ��������
		pool.shutdown();			
		
	}

}
