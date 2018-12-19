package my;

public class OuterClass {
	public int value = 10;
	
	/**
	 * �ڲ���
	 * @author LiZhenhua
	 *
	 */
	public class InnerClass{
		public void getOuterClassArgument(){
			System.out.println(value);
			System.out.println(OuterClass.this.value);			
		}
		
	}
	
	/**
	 * ��̬Ƕ����
	 * @author LiZhenhua
	 *
	 */
	static class StaticNestedClass{
		public void getOuterClassArgument(){
			System.out.println("��̬Ƕ�����޷�������Χ��Ĳ���");
		}
	}
	
	/**
	 * �����ڲ����ֻ࣬����abstract or final ���η�����
	 */
	public void InnerMethodClass(){
		class testss{
			int age = 18;
			public void getAge(){
				System.out.println("���䣺"+age);
			}
		}
		testss t = new testss();
		t.getAge();
	}
	
	
	
	public static void main(String[] args) {
		OuterClass out = new OuterClass();
		out.InnerMethodClass();// �����ڲ����ֻ࣬����abstract or final ���η�����
		InnerClass in = out.new InnerClass();
		in.getOuterClassArgument();
//		InnerClass in2 = new InnerClass();//�ڲ���ʵ����������ʵ������Χ��
		
		
		StaticNestedClass staticN = new StaticNestedClass(); //��̬Ƕ�����ʵ����1
		OuterClass.StaticNestedClass staticN2 = new OuterClass.StaticNestedClass();//��̬Ƕ�����ʵ����2
		staticN.getOuterClassArgument();
		staticN2.getOuterClassArgument();
	}

}
