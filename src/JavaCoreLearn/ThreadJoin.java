package JavaCoreLearn;
/**
 * �̼߳���
 * @author LiZhenhua
 *
 */
public class ThreadJoin extends Thread{
	//���ø��๹����
	public ThreadJoin(String name){
		super(name);
	}
	public ThreadJoin(Runnable run,String name){
		super(run,name);
	}
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 100; i++) {
			System.out.println(getName() + " "+ i);
		}
	}

	
	public static void main(String[] args) throws InterruptedException {
		new ThreadJoin("��һ�߳�").start();
		for (int i = 0; i < 100; i++) {
			System.out.println("���߳�"+Thread.currentThread().getName()+""+i);
			if(i == 20){
				ThreadJoin jo = new ThreadJoin("��������߳�");
				jo.start();
				//����join,��ǰ�߳̽���������ֱ��jo�߳�ִ����
				jo.join();
//				jo.join(2);
//				jo.join(2,2);
			}
		}
		
		System.out.println("���߳�000000000000");
	}
}
