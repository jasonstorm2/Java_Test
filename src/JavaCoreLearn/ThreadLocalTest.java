package JavaCoreLearn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal���͵ı�������
 * 
 * ����̶߳�ͬһ������Ĵ���ʱ���ö����� ThreadLocal���͵ı�������ͨ������ ����
 * ThreadLocal���͵ı��������߳�ʱ��ÿ���̶߳��иñ�����һ�����������û����д��ʼ����initialValue����Ĭ��ֵΪnull
 * ��ͨ����:���߳�ʱ���ñ���Ϊ���б��������Ǽ���������ñ����̲߳���ȫ��
 * 
 * 
 * Thread ���ڲ���ͨ��ThreadLocalMap��ά��ThreadLocal������ ��Thread������һ��threadLocals
 * ��������ThreadLocalMap���͵ģ�������Ϊÿһ���߳����洢�����ThreadLocal�����ģ�
 * ThreadLocalMap��ThreadLocal���һ���ڲ��࣬���Map�������С�Ĵ洢��λ��һ��Entry��
 * ��ʹ��ThreadLocal��Ϊkey�� ������Ϊ value��������Ϊ��ÿһ���߳����棬���ܴ����Ŷ��ThreadLocal����
 * 
 * ��ʼʱ����Thread���棬threadLocalsΪ�գ���ͨ��ThreadLocal��������get()��������set()������
 * �ͻ��Thread���е�threadLocals���г�ʼ��
 * �������Ե�ǰThreadLocal����Ϊ��ֵ����ThreadLocalҪ����ĸ�������Ϊvalue���浽threadLocals��
 * Ȼ���ڵ�ǰ�߳����棬���Ҫʹ�ø����������Ϳ���ͨ��get������threadLocals�������
 * 
 * ThreadLocalMap������Ϊ�˽���̰߳�ȫ���⣬ �����ṩ��һ�ֽ�ʵ���󶨵���ǰ�̵߳Ļ��ƣ�
 * �����ڸ����Ч����ʵ�����Լ��ڷ�����new��������Ҳ�ܴﵽ���Ƶ�Ч����
 * ThreadLocalMap���̰߳�ȫ��������ߣ�����ȥ��ʵ��Ҳ���Ƕ��̹߳��õģ�
 * ����ÿ���߳�newһ�ݣ����ʵ���϶����ǹ��õģ���������ˣ��Ǿͻ������̰߳�ȫ���⡣
 * ThreadLocalMap�����ô�����������ʵ�����������ȫ�ֱ����� �ڳ�����κη����ж����Է��ʵ���ʵ���������ѡ�
 * ���Ϻܶ���˵ThreadLocalMap�ǽ�����̰߳�ȫ���⣬��ʵ���������壬���߲���ͬ������
 * 
 * @author LiZhenhua
 *
 */
class Account{
	//����һ��ThreadLocal���͵ı������ñ������� һ���ֲ߳̾�����
	private ThreadLocal<String> name = new ThreadLocal<String>(){
		@Override
		protected String initialValue() {
			// TODO Auto-generated method stub
			return "-Ĭ��ֵ-";
		}
	};
	
	//�̲߳����ǹ���˱���������ÿ���̶߳����������
	private ThreadLocal<Integer> value = new ThreadLocal<Integer>();//�̲߳����ǹ���˱���������ÿ���̶߳����������
	
	private int count = 0;
	
	public Account(String name,int value){
		this.name.set(name);
		//���ڷ��� ��ǰ�̵߳� name������ֵ
		this.value.set(value);		
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
		this.name.set(name);
	}
	
	public Integer getValue() {
		return value.get();
	}
	public void settValue(Integer value) {
		this.value.set(value);
	}
}

 class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override protected Integer initialValue() {
                return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
    
    public static int getId(){
    	return nextId.getAndIncrement();
    }
}

class myTestThread extends Thread{
	
	private int id = 0;
	
	private Account account;
	
	public myTestThread(Account account,String name,ThreadId threadId){
		super(name);
		this.account = account;
		this.id = ThreadId.get();
	}
	
	@Override
	public void run() {
		super.run();
		
		System.out.println("�̵߳ı�ţ�"+id);
		System.out.println("Account��localname:"+account.getName());
		
		for (int i = 0; i < 4; i++) {
			if(i==2){
				System.out.println("ThreadName:"+getName()+"  "+"i==2");
				account.setName(getName());
			}
			account.setCount(account.getCount()+1);
			System.out.println("ThreadName:"+getName()+"  "+account.getName() + "**�˺�**,�˻���i ֵ "+ i);
			System.out.println("ThreadName:"+getName()+"  "+"COUNT��ֵ"+ account.getCount());
			System.out.println("ThreadName:"+getName()+"  "+"value��ֵ,����ǰ��"+ account.getValue());
			account.settValue(i);
			System.out.println("ThreadName:"+getName()+"  "+"value��ֵ,���ú�"+ account.getValue());
			
		}		
	}
	
	public int getSelfId(){
		return id;
	}
}

public class ThreadLocalTest {
	
	public static void main(String[] args) {
		
		ThreadId threadId = new ThreadId();		
		Account ac = new Account("��ʼ��",100);
		System.out.println("���߳�ac����"+ac.getName());

		myTestThread t1 = new myTestThread(ac, "�߳�1",threadId);
		myTestThread t2 = new myTestThread(ac, "�߳�2222",threadId);
		t1.start();
		t2.start();
		
		
		System.out.println("���߳�ac��2��"+ac.getName());
		

		
	}

}
