package java8;

public class impleLikeExtends2 implements interfacePeople,interfacePerson{

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("�̳�");
		
	}
	
//	public void sayHello(){
//		System.out.println("��д��Ĭ�ϵķ���");
//		
//	}
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		interfacePerson.super.sayHello();
		System.out.println("��д��Ĭ�ϵķ���");
	}
	public static void main(String[] args) {
		impleLikeExtends2 im = new impleLikeExtends2();
		im.action();
		im.get();
		im.sayHello();
		
		
	}
}
