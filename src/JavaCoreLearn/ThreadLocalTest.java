package JavaCoreLearn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Account{
	//����һ��ThreadLocal���͵ı������ñ������� һ���ֲ߳̾�����
	private ThreadLocal<String> name = new ThreadLocal<String>();//�̲߳����ǹ���˱���������ÿ���̶߳����������
	private int count = 0;
	
	public Account(String name){
		this.name.set(name);
		//���ڷ��� ��ǰ�̵߳� name������ֵ
		System.out.println("----"+this.name.get());
	}
	
	
	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);;
	}
}

class myTest extends Thread{
	
	private Account account;
	
	public myTest(Account account,String name){
		super(name);
		this.account = account;
	}
	
	@Override
	public void run() {
		super.run();
		
		for (int i = 0; i < 10; i++) {
			if(i==6){
				account.setName(getName());
			}
			account.setCount(account.getCount()+1);
			System.out.println(account.getName() + "�˻���i ֵ "+ i);
			System.out.println("COUNT��ֵ"+ account.getCount());

		}		
	}
}

public class ThreadLocalTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		//ʹ��Collections �� synchronizedMap��̬���� �� һ����ͨ��HashMap ��װ���̰߳�ȫ����
		Map<String, String> map1 = Collections.synchronizedMap(map);
		Map<String, String> map2 = Collections.synchronizedMap(new HashMap<String, String>());
		
		
		Account ac = new Account("��ʼ��");
		System.out.println("���߳�ac����"+ac.getName());

		new myTest(ac, "�߳�1").start();
		new myTest(ac, "�߳�2222").start();
		
		System.out.println("���߳�ac��2��"+ac.getName());

		
	}

}
