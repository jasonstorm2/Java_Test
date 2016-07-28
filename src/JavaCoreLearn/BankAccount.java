package JavaCoreLearn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	private String accountNo;
	private double balance;
	
	private final ReentrantLock lock = new ReentrantLock();//�߳���
	private final Condition cond = lock.newCondition();	 //�߳����� ��������
	
	public BankAccount(String accountNo,double balance){
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	//��Ϊ�˻������������޸ģ�����ֻΪbalance�ṩgetter����
	public double getBalance() {
		return balance;
	}

//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
	
	/*
	 * ͬ���������˷�����ͬ����������this��Ҳ���Ǳ����ʵ��
	 */
	public synchronized void draw(double drawAmount){
		if(balance >= drawAmount){
			System.out.println(Thread.currentThread().getName()+" ȡǮ�ɹ���ȡ����"+drawAmount);
			//�޸����
			balance -= drawAmount;
			System.out.println("\t ���Ϊ��"+balance);
		}else{
			System.out.println(Thread.currentThread().getName() + " ȡǮʧ�ܣ����㣡");
		}
		
	}
	
	public  void drawlock(double drawAmount){
		lock.lock();
		try{
			if(balance >= drawAmount){
				System.out.println(Thread.currentThread().getName()+" ȡǮ�ɹ���ȡ����"+drawAmount);
				//�޸����
				balance -= drawAmount;
				System.out.println("\t ���Ϊ��"+balance);
			}else{
				System.out.println(Thread.currentThread().getName() + " ȡǮʧ�ܣ����㣡");
			}
		}finally{
			lock.unlock();
		}	
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return accountNo.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this ==obj)
			return true;
		if(obj!=null&& obj.getClass() == BankAccount.class){
			BankAccount target = (BankAccount)obj;
			return target.getAccountNo().equals(accountNo);
		}
		return false;
	}
	
	public boolean haveMoney;
	
	public synchronized void draw2(double money) {//ȡ��
//		System.out.println("ȡ���ǰ�߳�����"+Thread.currentThread().getName());

			if(haveMoney==false){
//				System.out.println("ȡ��ȴ�����ʼ*******************");
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
//				System.out.println("ȡ��ȴ�������*******************");
			}else{
				balance -= money;
				System.out.println("ȡ��"+money+"Ԫ");
//				System.out.println("��"+balance);
//				System.out.println();	
				haveMoney = false;
				notifyAll();
				}
	}
	
	public synchronized void deposit2(double money){//���
//		System.out.println("���,��ǰ�߳�����"+Thread.currentThread().getName());

		if(haveMoney == true){
			try {
//				System.out.println("���ȴ�����ʼ*******************");
				wait();
//				System.out.println("���ȴ�������*******************");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			balance += money;
			System.out.println("���"+money+"Ԫ");
//			System.out.println("��"+balance);
//			System.out.println();
			haveMoney = true;
			notifyAll();			
		}
		
	}
	
	public  void draw3(double money) {//ȡ��
		lock.lock();
		try{
			if(!haveMoney){
				cond.await(); //�ȴ�	
			}else{
				balance -= money;
				System.out.println("ȡ��"+money+"Ԫ");
				haveMoney = false;
				cond.signalAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}

	}
	
	public  void deposit3(double money){//���
		lock.lock();
		try{
			if(haveMoney){
			  cond.await();	
			}else{
				balance += money;
				System.out.println("���"+money+"Ԫ");
				haveMoney = true;
				cond.signalAll();		
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			
		}
		
	}
	


}
