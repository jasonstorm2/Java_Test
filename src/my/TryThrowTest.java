package my;

public class TryThrowTest {
	public static void main(String[] args) throws Exception {
//		test();
		testCatch();
	}
	
	public static void test() throws myException{
		int i=0;
		int s=5;
		int re=0;
		try {
			re = s/i;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("���ݳ���");
//			e.printStackTrace();//��Ҫ�׳���trycatch֮��ĳ������ִ��
//			throw new Exception("�׳����������ִ��");
			throw new myException();
		}
		System.out.println("ִ�н���");
	}
	
	public static void testCatch(){
		try {
			
			test();
		} catch (myException e) {
			// TODO: handle exception
			System.out.println("������һ���쳣");
		}
	}
}

 class myException extends Exception{
	 private static final long serialVersionUID = 1L;
	
}
