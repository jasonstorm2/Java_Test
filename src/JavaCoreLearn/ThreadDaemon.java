package JavaCoreLearn;

/**
 * �ػ����̣���̨���̣�
 * @author LiZhenhua
 *
 */
public class ThreadDaemon extends Thread{
	
	public  ThreadDaemon(String name){
		super(name);
	}
	
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 1000; i++) {
			System.out.println(getName()+""+i);
		}
	}
	
	public static void main(String[] args) {
		ThreadDaemon td = new ThreadDaemon("�Զ����̨����");
		td.setDaemon(true);//����Ϊ��̨����
		
		td.start();
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+i);
		}
		
		
	}

}
