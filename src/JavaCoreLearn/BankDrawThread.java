package JavaCoreLearn;

public class BankDrawThread extends Thread{
	private BankAccount account;
	private double drawAmount;
	//ȡǮ�߳�
	public BankDrawThread(String name,BankAccount account,double drawAmount){
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;		
	}
	
	public BankDrawThread(BankAccount account,double drawAmount){
		this.account = account;
		this.drawAmount = drawAmount;		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
//		account.draw(drawAmount);
//		account.drawlock(drawAmount);
		
		for (int i = 0; i < 20; i++) {
			account.draw3(drawAmount);		
		}

	
		/*
		 * ͬ�������
		 */
//		synchronized(account){
//			if(account.getBalance() >= drawAmount){
//				System.out.println(getName()+" ȡǮ�ɹ���ȡ����"+drawAmount);
//				//�޸����
//				account.setBalance(account.getBalance() - drawAmount);
//				System.out.println("\t ���Ϊ��"+account.getBalance());
//			}else{
//				System.out.println(getName() + " ȡǮʧ�ܣ����㣡");
//			}
//		}
		
	}

}
