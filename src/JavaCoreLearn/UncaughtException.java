package JavaCoreLearn;

public class UncaughtException {
	public static void main(String[] args) {
		Thread.currentThread().setName("���߳�");
		//���� ���߳� �� �쳣������:
		Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler());//ָ���̵߳�ʵ�� ���� �쳣������
//		Thread.setDefaultUncaughtExceptionHandler(new MyExHandler());//���̵߳�����ʵ�� ���� Ĭ���쳣������
		

		try {
			int a = 5/0;

		} catch (Exception e) {      //try catch �����״��󣬳��������������
			// TODO: handle exception
			System.out.println("�������");
		}
		int a = 5/0;                 // �쳣������ �����쳣������ֹͣ���С�
		System.out.println("�߳���������");
		
	}

}

//�����Լ����쳣������

class MyExHandler implements Thread.UncaughtExceptionHandler{

	//ʵ��uncaughtException�������÷����������̵߳� δ������쳣
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println(t+"�̳߳������쳣��"+e);		
	}	
}
