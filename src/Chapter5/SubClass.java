package Chapter5;
/**
 * ��� ��̬
 * @author Administrator
 *
 */
class BaseClass{
	public int book = 6;
	public void base(){
		System.out.println("1");
	}
	public void test(){
		System.out.println("2");
	}
}
public class SubClass extends BaseClass{
	public String book = "sa";
	public String bo = "bobo";
	public void test(){
		System.out.println("cover 2");
	}
	
	public void sub(){
		System.out.println("a");
	}
	
	public static void main(String[] args) {
		BaseClass bc = new BaseClass();
		
		System.out.println(bc.book);
		bc.base();
		bc.test();
		System.out.println("*********");
		SubClass sc = new SubClass();
		System.out.println(sc.book);
		sc.base();
		sc.test();
		System.out.println("*********");
		BaseClass ploybc = new SubClass();
		//ʵ������ ���߱���̬������ �߱���̬
		System.out.println(ploybc.book);
		ploybc.base();
		ploybc.test();
		//ploybc����ʱ������ BaseClass,��BaseClass��û���ṩsub()�������������������ʱ�����
		//��Ȼploybc���ñ��� ʵ���ϰ���sub()�����������÷�����ִ�и÷�����,����Ϊ���ı���ʱ����ΪBaseClass���ԣ�����ʱ�޷�����sub()������
//		ploybc.sub(); 		
		
	}

}
