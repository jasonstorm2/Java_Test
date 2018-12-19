package javaTest;


/**
 * �ɱ������βεĲ��ԣ�...
 * �����뷽���������Ƿ��޸��˶����ֵ��
 * @author LiZhenhua
 *
 */
public class MyClassTest {
	private String name;	
	public static void main(String args[]){
		MyClassTest m1 = new MyClassTest();
		MyClassTest m2 = new MyClassTest();
		m1.name = "m1";
		m2.name = "m2";
		callMe(m1,m2);
		System.out.println("m1.name��"+m1.name+" m2.name��"+m2.name);	
		System.out.println("--------------------------------------");
		callMe2(m1,m2);
		System.out.println("m1.name��"+m1.name+" m2.name��"+m2.name);	
	}	
	
	private static void callMe(MyClassTest...m){
		// �ڷ�����ָ��ͬһ���������ã��޸ĺ��ڷ���������Ϊû���޸ģ�����
		m[0]=m[1];
		m[1].name = "new name";
		System.out.println("m[0].name:"+m[0].name);
		System.out.println("m[1].name:"+m[1].name);
	}
	
	private static void callMe2(MyClassTest...m){
		m[0].name = "new name1";
		m[1].name = "new name2";
		System.out.println("m[0].name:"+m[0].name);
		System.out.println("m[1].name:"+m[1].name);		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
