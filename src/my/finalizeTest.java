package my;

public class finalizeTest {
	public String str = "sss";	
	public static void main(String[] args) {
		System.out.println("����ִ����");
		finalizeTest ff = new finalizeTest();
		System.gc();

	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		
		System.out.println("��������ִ��finalize");
	}

}
