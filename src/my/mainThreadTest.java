package my;

/**
 * ����main�߳��Ƿ������һ���˳����߳�
 * 
 * @author LiZhenhua
 * 
 *         JVM�������еķ��ػ��̣߳��û��̣߳�ִ����Ϻ��˳��� 
 *         main�߳����û��̣߳�
 *         ����main�߳�һ���û��߳�ִ����ϣ����ܾ���JVM�Ƿ��˳���Ҳ����˵main�̲߳���һ�������һ���˳����̡߳�
 *
 */
public class mainThreadTest {

	public static void main(String[] args) {
		System.out.println("��ʼ����");
		// �ڲ�����ʵ��һ������̬��������ֱ�ӵ��÷Ǿ�̬��
		// �ڲ����Ƕ�̬�ģ���static�ؼ������Σ���
		// ��main�����Ǿ�̬�ģ���ͨ���ڲ�����������ر�����һ�����ã�
		// ָ�򴴽�������Χ���������Ҫ��static�����������ʱ�Ѿ���ʼ���������ڲ���ı����ȴ����ⲿ�ࡣ
		// ��һ����ľ�̬��Ա��ȥ���ʷǾ�̬��Ա֮���Ի��������Ϊ����ķǾ�̬��Ա�����ڵ�ʱ��̬��Ա���Ѿ������ˣ�
		// ����һ���ڴ��в����ڵĶ�����Ȼ�����
		new mainThreadTest().new testThread().start();        
		System.out.println("��������");		
	}	
	
	
	class testThread extends Thread{
		public testThread() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			super.run();
			int i=10;
			System.out.println("��ǰ�߳����֣�"+Thread.currentThread().getName());			
			Thread.currentThread().setName("myDefineThread");
			while(true){
				
//				try {
//					System.out.println(i);
//					Thread.sleep(1000);					
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				i--;
//				if(i == 0){
//					System.out.println("��������˳�");
//					break;
//				}	
			}
			
		}
		
	}

}
