package Chapter18_ClassLoadAndReflection;

public class Hello {
	public String hh = "���";
	public void sayHello(){
		System.out.println("���õķ����������");
	}
	static{
		System.out.println("��̬����飺�����㡣����������");
	}
	
	public static void main(String[] args) {
		for(String arg : args){
			System.out.println("Helloworld������i:"+arg);
		}
	}

}
