package my;


/**
 * ���า�Ǹ���ķ������� ������� ���ø÷�������ʵ�����ǵ������า�ǵķ���
 * 
 * ���ǵķ��� ������� super()����������ȵ��ø��౻���ǵķ���
 * 
 * super() ���ø���ķ���
 * @author LiZhenhua
 *
 */
public class FatherChildOverrideTest extends fatherClass1{
	@Override
	public int c() {	
		int s = super.c();
		System.out.println("���෽��c����");
		System.out.println("����ķ���ֵs:"+s);
		return 100000;
	}
	
	public static void main(String[] args) {
		FatherChildOverrideTest ff = new FatherChildOverrideTest();
		int zhi = ff.a();
		System.out.println("����ķ���ֵzhi:"+zhi);
	}
}

class fatherClass1 {
	public void dd(){
		
	}
	

	public int a() {
		return b();

	}

	public int b() {
		return c();
	}

	public int c() {
		System.out.println("���෽��c����");
		return 11111;
	}

}
