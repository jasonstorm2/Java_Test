package JavaCoreLearn;

/**
 * 
 * ��Java���У��߳�ͨ����������״̬�����������������С�������������
 * 
 * ��һ�Ǵ���״̬�� �������̶߳��󣬲�û�е��øö����start�����������̴߳��ڴ���״̬��
 * 
 * �ڶ��Ǿ���״̬�� ���������̶߳����start����֮�󣬸��߳̾ͽ����˾���״̬�� ���Ǵ�ʱ�̵߳��ȳ���û�аѸ��߳�����Ϊ��ǰ�̣߳���ʱ���ھ���״̬��
 * ���߳�����֮�󣬴ӵȴ�����˯���л���֮��Ҳ�ᴦ�ھ���״̬��
 * 
 * ����������״̬�� �̵߳��ȳ��򽫴��ھ���״̬���߳�����Ϊ��ǰ�̣߳���ʱ�߳̾ͽ���������״̬����ʼ����run�������еĴ��롣
 * 
 * ����������״̬�� �߳��������е�ʱ�򣬱���ͣ��ͨ����Ϊ�˵ȴ�ĳ��ʱ��ķ���(����˵ĳ����Դ����)֮���ټ������С�sleep,suspend��
 * wait�ȷ��������Ե����߳�������
 * 
 * ����������״̬�� ���һ���̵߳�run����ִ�н������ߵ���stop�����󣬸��߳̾ͻ������������Ѿ��������̣߳��޷���ʹ��start����������������
 * 
 * start() �� run()������
 * 
 * 1.start���������������̣߳�����ʵ���˶��߳����С�
 * ͨ������Thread���start()����������һ���̣߳� ��ʱ���߳��Ǵ��ھ���״̬�� ��û�����С�
 * Ȼ��ͨ����Thread����÷���run()����������в����ģ� ���﷽��run()��Ϊ�߳��壬��������Ҫִ�е�����̵߳����ݣ� Run�������н�����
 * ���߳���ֹ��Ȼ��CPU�ٵ��������߳�
 * ��
 * 2.run��������������ͨ�����ķ�ʽ���á�
 * ������Ҫ˳��ִ�У�Ҫ�ȴ�run������ִ����Ϻ󣬲ſɼ���ִ������Ĵ��룻 
 * �������ڵ�ǰ�߳������У�������main�̣߳�����û�п����µ��̡߳�����һ���̣߳�
 * �����ִ��·������ֻ��һ���� ������û�дﵽд�̵߳�Ŀ�ġ� 
 * 
 * ��ס�����߳̾��Ƿ�ʱ����CPU��������������߳�һ��ִ�� ��Ҳ�в���
 * 
 * @author LiZhenhua
 *
 */
public class ThreadSecond implements Runnable{
	private int i;

	@Override
	//ͬ�� λ�ò�һ����Ч���᲻һ��Ŷ������
//	public synchronized void run() {
	public void run() {
		for (;i < 100; i++) {
			synchronized(this){  //ͬ������飬��ֹ�ظ��������֣���Ʊ�ظ���Ʊ������
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("�߳����֣�"+Thread.currentThread().getName()+" "+i);

			}
		}
	}
	
	
	
	public static void main(String[] args) {
		
		//runnable�ӿڿ��Դﵽ����Ч����������� Runnable he Thread ������				
		ThreadSecond st = new ThreadSecond();
		new Thread(st, "���߳�1 ").start();//target��st �����̹߳���  ----����ʱ����ȫ���⣡��
		new Thread(st, "���߳�2 ").start();//target��st �����̹߳���
		//�����ʵ����������ͬ��Thread����ô���ܴﵽ�����Ч��
		
		Thread tt = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("��ǰ�̵߳����֣�"+Thread.currentThread().getName());
				// TODO Auto-generated method stub
				System.out.println("ִ��run��ִ��start������");
			}
		}, "���߳�3 ");//target��st �����̹߳���
		
		tt.run();   //�̻߳������̣߳���û�п������߳�
		tt.start(); //���������߳�
	}

}
