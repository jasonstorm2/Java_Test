package my;



/**
 * ����eclipse java �� �ϵ㹦����
 * @author LiZhenhua
 * 
 * 1.ǿ���˳���ǰ�ϵ����ڵķ����� alt+shift+F���˳��󣬷����ڶϵ����Ĳ��ֲ�ִ��
 * 
 * 2.�����ϵ㣺�Ҽ��ϵ㣬���ò���
 * 
 * 3.variables ��ͼ�ı����ֵ
 * 
 * 4.�쳣�ϵ�
 * 
 * 5.���ʽ��ͼ��ʹ�� --  ��ǰ������ʽ��ֵ�����߸ı������ֵ
 * 
 * �ڳ����жϵ㣬�ϵ�ʱ��
 * 		1.�����ڱ��ʽ��ͼ������ر����ı��ʽ��������ʽ��ֵ��
 * 		2.Ҳ�����ڳ����У�ѡ��Ҫ����ı��ʽ���һ�ѡ�� inspect ѡ����� ctrl+shift+i
 * ע�⣺ ���ʽ��ִ��ʱ��Ч���������Ҫ��ʱɾ��
 *
 */
public class DebugTest {
	/**
	 * �׳�	
	 * @param value
	 * @return
	 */
	public static int factorial(int value){	
			if(value==1){
				return value;
			}else {	
				return value*factorial(value-1);
			}		
	}	
	
	/**
	 * �����ϵ㣺
	 * ��i==500ʱ���ϵ���Ч
	 * 
	 * 1.�Ҽ��ϵ㣬ѡ�� breakpoint.properties
	 * ѡ�� conditional
	 * �����������ָ���Ĳ��� i==500.
	 * ��i==500ʱ���ϵ�Ż���Ч
	 * 
	 * 2.�� variables ��ͼ�������Ҽ��ı������ֵ ͬ�����������Ч��
	 */
	public static void forDebug(){
		int value = 0;
		for (int i = 0; i < 1000; i++) {			
			value += i;
		}
		forDebug2();
		System.out.println("�ۼӣ�"+value);
	}
	
	public static void forDebug2(){
		int i=0;
		int s=1;
		int t=5;
		System.out.println("forDebug2:"+(s+t+i));
		System.out.println("forDebug2:"+s+t+i);
	}
	
	/**
	 * �����׳��쳣����
	 * 
	 * ����:���׳�ָ���쳣�ĵط��Զ��ϵ�
	 * �ϵ���ͼ�� ����쳣�ϵ� �У����ָ�����쳣����
	 * 
	 * 1.�������������쳣�ϵ㡣Ч������һ������������쳣���ϵ����
	 * @throws MyExceptoin 
	 */
    public static void exceptionDebug(boolean alter) throws MyExceptoin{
    	if(alter){
    		throw new NullPointerException();
    	}else{
    		throw new MyExceptoin();
    	}
    }
	
	public static void main(String[] args) throws MyExceptoin {
		forDebug();
		System.out.println("�׳ˣ�"+factorial(10));
		
		utils.utils.PrintLine("�׳��쳣����");
		exceptionDebug(true);
	}
}


/**
 * ����Զ���һ���쳣�� 
 * 
 * 1.�Զ�����쳣��̳����е��쳣�� 
 * 2.�ṩһ�����кţ��ṩ�������صĹ����� * 
 * @author LiZhenhua
 *
 */

class MyExceptoin extends Exception{

	/**
	 * �Զ����ɵ����к�
	 */
	static final long serialVersionUID = -5028318153622440729L;
	
	public MyExceptoin() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public MyExceptoin(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	
}
