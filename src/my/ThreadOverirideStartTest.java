package my;

/**
 * ��д�̵߳� star����
 * �������start����ʱ����һ����ͨ������
 * ���� super.start ���ø����start��������Ϊ��ʱ�Ὺ��һ���µ��̣߳��������߳����run������
 * ����������д�ķ�������run������Ҳ�൱�ڵ���һ����ͨ������
 * @author LiZhenhua
 *
 */
public class ThreadOverirideStartTest {
	public static void main(String[] args) {
		System.out.println("ThreadOverirideStartTest main Thread.currentThread(),id �� ���֣�"+Thread.currentThread().getId() + "  " + Thread.currentThread().getName());
		new myThread().start();
		new myThread2().start();
		new myThread4().start();
//		new myThread4().run();		//��ͨ��������Ȼ��main�߳���
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("�����ڲ��� id:"+Thread.currentThread().getId()+" name:"+Thread.currentThread().getName());
				
			}
		}).start();
	}
}

/**
 * ֻ��дrun����
 * @author LiZhenhua
 *
 */
class myThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		System.out.println("myThread run() this.getId()��id �� ���֣�"+this.getId() + "  " + this.getName());
		System.out.println("myThread run() Thread.currentThread()id��"+Thread.currentThread().getId());
	}

}


/**
 * ��д��start��������û�е��� �����start����
 * ����û�п���һ���µ��̡߳�������ͨ���̡߳�
 * @author LiZhenhua
 *
 */
class myThread2 extends Thread{
	public void run() {		
		System.out.println("myThread2 run ������ this.getid ��id �� ���֣�"+this.getId() + "  " + this.getName());
		System.out.println("myThread2 run ������ Thread.currentThread()��id��"+Thread.currentThread().getId());
	}
	
	public  void start() {
		System.out.println("myThread2 start() this.getId()��id �� ���֣�"+this.getId() + "  " + this.getName());
		System.out.println("myThread2 start() Thread.currentThread()��id��"+Thread.currentThread().getId());
		run();
	}
}



/**
 * ��д��start������ ������super.start()
 * super.start ���Ὺ��һ���µ��̣߳����ҵ��� �̵߳�run����
 * 
 * �������д��start ������ ����run����������һ����ͨ��������Ȼ��main�߳���
 * 
 * @author LiZhenhua
 *
 */
class myThread4 extends Thread{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("myThread4 run ������ this.getid ��id �� ���֣�"+this.getId() + "  " + this.getName());
		System.out.println("myThread4 run ������ Thread.currentThread()��id��"+Thread.currentThread().getId());
		
	}
	
	public  void start() {
		super.start();
		System.out.println("myThread4 start() this.getId()��id �� ���֣�"+this.getId() + "  " + this.getName());
		System.out.println("myThread4 start() Thread.currentThread()��id��"+Thread.currentThread().getId());
		run();
	}
}

