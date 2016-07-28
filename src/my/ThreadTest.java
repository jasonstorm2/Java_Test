package my;

public class ThreadTest extends Thread{

	private int countDown = 5;
	private int threadNumber;//�����������ʵ��������ʱ���ܷ���ռ䣬ʵ�����ٸ����󣬾��ж��ٸ�ʵ������
	private static int threadCount = 0;//��������м�����ʱ��������ռ䣬���ڴ���ֵ����һ����̬����----life cycleȡ���������������
	public ThreadTest(){
		threadNumber = ++threadCount;
		System.out.println("MAKING��"+threadNumber);
		System.out.println("countDown��"+countDown);
		System.out.println("threadCount��"+threadCount);
		
	}
	public void run(){
		while(true){
			System.out.println("Thread"+threadNumber+"("+countDown+")");
			if(--countDown==0){
				return;
			}
		}
	}
	
	public static void main(String[] args){
		for(int i = 0; i<5;i++){
			new ThreadTest().start();
			System.out.println("ALL THREADS STARTED");
		}
	}

}
