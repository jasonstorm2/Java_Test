package JavaCoreLearn;

public class LambdaRunnable {

	public static void main(String[] args) {
		Runnable runn;
		Runnable run = new Runnable(){
			@Override
			public void run() {
				System.out.println("һ���߳̽ӿ�");
			}			
		};
		
		Runnable ru=()->{System.out.println("lamba");};
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("�߳̽ӿ�");
			}
			
		}).start();
		
		new Thread(runn=()->{System.out.println("lamba,����");}).start();
		
		new Thread(ru).start();
		
		
	}
	
	

}
