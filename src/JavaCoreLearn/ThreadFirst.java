package JavaCoreLearn;

public class ThreadFirst extends Thread{
	private int i ;
	
	@Override
	public void run() {
		super.run();
		for(;i<100;i++){
			System.out.println(this.getName()+"�Զ����߳�"+i);

		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
//			System.out.println("��ǰ�߳�����"+Thread.currentThread().getName()+"i="+i);
			if(i == 20){
				ThreadFirst one = new ThreadFirst();//������һ���߳�
				ThreadFirst two = new ThreadFirst();//�����ڶ����߳�
				one.setName("ONE");
				two.setName("TWO");
				one.start();
				two.start();
			}
		}
	}

}
