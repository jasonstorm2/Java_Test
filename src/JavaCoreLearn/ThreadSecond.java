package JavaCoreLearn;

public class ThreadSecond implements Runnable{
	private int i;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (;i < 100; i++) {
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
	
	
	
	public static void main(String[] args) {
		

		for (int i = 0; i < 100; i++) {
			System.out.println("���߳�"+Thread.currentThread().getName()+" "+i);
			if(i==20){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ThreadSecond st = new ThreadSecond();
				new Thread(st, "���߳�1 ").start();//target��st �����̹߳���
				new Thread(st, "���߳�2 ").start();//target��st �����̹߳���
				}
		}
		
		Thread tt = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("ִ��run��ִ��start������");
			}
		}, "���߳�3 ");//target��st �����̹߳���
		
		tt.run();
		tt.start();
	}

}
