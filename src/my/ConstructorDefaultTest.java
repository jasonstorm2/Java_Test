package my;

/**
 * ����Զ����˹���������ô������ʹ��Ĭ�ϵĹ������ˡ��������Զ���Ĭ�Ϲ�����
 * @author Administrator
 *
 */
public class ConstructorDefaultTest {
	int s;
	
	public ConstructorDefaultTest(int s){
		this.s = s;
	}
	public static void main(String[] args) {
		ConstructorDefaultTest con = new ConstructorDefaultTest(3);
		
		System.out.println("ʵ��������ֵs:"+con.s);
	}

}
