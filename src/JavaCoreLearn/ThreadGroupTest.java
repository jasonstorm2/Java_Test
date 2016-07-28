package JavaCoreLearn;

public class ThreadGroupTest {
	public static void main(String[] args) {
		//��ȡ���̵߳� �߳���
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();//��һ�� Ĭ�ϵ��߳���
		System.out.println("���߳��� �����֣�"+mainGroup.getName());
		System.out.println("���߳��� �Ƿ��Ǻ�̨�߳��飺"+mainGroup.isDaemon());
		new MyThread("Dont you cry tonight").start();
		
		ThreadGroup tg = new ThreadGroup("���߳���");
		//����Ϊ��̨�߳�
		tg.setDaemon(true);
		
		System.out.println("tg�߳����Ƿ��Ǻ�̨�߳��飺"+tg.isDaemon());
		
		MyThread tt = new MyThread(tg, "tg����̼߳�");
		tt.start();
		
		new MyThread(tg, "tg����߳���").start();
		
		
	}
	
}

class MyThread extends Thread{
	public MyThread(String name){
		super(name);
	}
	
	public MyThread(ThreadGroup group,String name){//�̹߳��캯�� ָ�����߳������� �߳��� �� �߳�����
		super(group, name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 20; i++) {
			System.out.println(getName() + "�̵߳�i����"+i);
		}
	}
	
}
