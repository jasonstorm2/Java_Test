package JavaCoreLearn;

/**
 * һ���࣬�ڷ����ϼ����Զ����ע��
 * @author LiZhenhua
 *
 */
public class AnnoMyTest {
	
	@AnnoTestable	
	public static void m1(){
		System.out.println("m1����������");		
	}
	
	public static void m2(){
		
	}
	@AnnoTestable	
	public static void m3(){
		throw new IllegalArgumentException("����������");
		
	}
	public static void m4(){
		
	}
	@AnnoTestable	
	public static void m5(){
		System.out.println("m5����������");		
	}
	public static void m6(){
		
	}
	@AnnoTestable	
	public static void m7(){
		throw new RuntimeException("����ҵ������쳣");
		
	}
	public static void m8(){
		
	}



}
