package my;

public class ForceConversionTest {
	public static void main(String[] args) {
		BClass1 b = new BClass1(1,2);//����ػ��ƣ��ȸ���ʵ������������û��super���๹�죬����ø�����޲ι��캯�����������super�Ĺ��췽��
		AClass1 a = (AClass1)b;
		
//		BClass1 b2 = new BClass1();//����ػ��ƣ��ȸ���ʵ���������ø�����޲ι��캯��
		
//		AClass1 a1 = new AClass1(1,2);
//		BClass1 b1 = (BClass1)a1;//�������಻��ǿ��ת��������
	}

}


class AClass1{
	public int a;
	public int b;
	public AClass1(){
		System.out.println("A����޲ι�����");
	}
	
	public AClass1(int a,int b){
		this.a = a;
		this.b = b;
		System.out.println("A�� ��������������");
	}
}

class BClass1 extends AClass1{
	public BClass1(){
//		super();
		System.out.println("B����޲ι�����");
	}
	public BClass1(int a,int b){
		super(a, b);
		System.out.println("B�� ��������������");
	}
	
}
