package my;

public class byteNumTest {
	public static void main(String[] args) {
		String num = "123456789";
		String chinese = "�����˸�����sd";
		System.out.println("num����"+num.length());
		System.out.println("Chinese����"+chinese.length());
		System.out.println(num.substring(0,4));
		System.out.println(chinese.substring(0,4));
		
	}

}
