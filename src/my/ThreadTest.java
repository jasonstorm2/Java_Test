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
//		System.out.println("·����"+Thread.currentThread().getContextClassLoader().getClass().getPackage());
//		
//		for(int i = 0; i<5;i++){
//			new ThreadTest().start();
//			System.out.println("ALL THREADS STARTED");
//		}
		
		getThreadStace();
	}
	
	public static void ThreadStace(){
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		int i=0;
		for (StackTraceElement ss : stackTrace) {
			System.out.println("i��ֵ��"+i);
			System.out.println(ss.getClassName()+""+ss.getFileName()+""+ss.getLineNumber()+""+ss.getMethodName());	
			i++;
			
		}
		System.out.println(stackTrace[2]);		
	}
	
	
	public static void getThreadStace(){
		ThreadStace();
	}

}
