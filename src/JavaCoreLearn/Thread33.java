package JavaCoreLearn;

/**
 * ʵ��Runnable�ӿڵ��� ����ʹ�� Thread���ʵ�� ���ܴ����̡߳� Runnable�����������û��start()����
 * 
 * ͨ��Runnable�ӿڴ����̷߳�Ϊ������
 *  1. ��ʵ��Runnable�ӿڵ���ʵ������
 *  2. ����һ��Thread���󣬲�����һ��ʵ������Ķ�����Ϊ��������Thread��Ĺ��췽����
 * 
 * �����̣߳����ն���ҪThread��Ķ���
 * ʵ���� Thread ʵ���� runnable�ӿ�
 * 
 * @author Administrator
 *
 */
class Thread33 implements Runnable { // ʵ����Runnable�ӿڣ�jdk��֪���������һ���߳�  

	int t;
	Thread a, b;

	Thread33() {
		t = 300;
		a = new Thread(this);//Thread(Runnable)
		a.setName("Accountant");
		b = new Thread(this);
		b.setName("Cashier");
	}

	//runnable �ӿڵĳ��󷽷�run(),���� ʵ��
	public void run() {
		System.out.println("this is run");
		while (true) {
			t = t - 50;
			if (Thread.currentThread() == a) {
				System.out.println(a.getName() + " need " + t + " Yuan.");
				if (t <= 150) {
					System.out.println(a.getName() + " is dead...");
					return;
				}
			} else if (Thread.currentThread() == b) {
				System.out.println(b.getName() + " need " + t + " Yuan.");
				if (t <= 0) {
					System.out.println(a.getName() + " is dead...");
					return;
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

			}
		}
	}
}
