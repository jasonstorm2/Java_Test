package JavaCoreLearn;

public class BankDrawTest {
	public static void main(String[] args) throws InterruptedException {
		BankAccount acct = new BankAccount("11223344", 0);
//		new BankDrawThread("֣�غ�", acct, 800).start();
//		new BankDrawThread("��γ�", acct, 800).start();		
		new BankDrawThread(acct, 800).start();
		new BankDepositThread(acct, 800).start();		
	}
}
