package my;

/**
 * �����ȡ�����Լ�double�����Ľ��
 * 
 *  a/b б�ܣ�	���a��b������������ʾ���࣬
 *  		  	�����һ�������Ǵ�С���ĵ����������doubleС��
 *  
 *  
 *  a%b �ٷֺţ���ʾȡ��
 * 
 */
public class DivisionAndRemainderTest {
	public static void main(String[] args) {
		System.out.println("0/2.0ȡ��"+0/2);//0
		System.out.println("1/2.0ȡ��"+1/2);//0
		System.out.println("2/2.0ȡ��"+2/2);//1
		System.out.println("3/2.0ȡ��"+3/2);//1
		System.out.println("4/2.0ȡ��"+4/2);//2
		System.out.println("5/2.0ȡ��"+5/2);//2
		utils.utils.PrintLine("ȡ��");
		System.out.println("6/2ȡ��"+6/2);//3
		System.out.println("7/2ȡ��"+7/2);//3		
		System.out.println("0%2����"+0%2);//0
		System.out.println("1%2����"+1%2);//1
		System.out.println("2%2����"+2%2);//0
		System.out.println("3%2����"+3%2);//1
		System.out.println("4%2����"+4%2);//0
		System.out.println("5%2����"+5%2);//1
		utils.utils.PrintLine("double����");
		System.out.println("0/2.0ȡ��"+0/2);//0
		System.out.println("1/2.0ȡ��"+1.0/2);//0.5
		System.out.println("2/2.0ȡ��"+2/2.0);//1.0
		System.out.println("3/2.0ȡ��"+3/2.0);//1.5
		System.out.println("4/2.0ȡ��"+4/2.0);//2.0
		System.out.println("4/2.0ȡ��"+1/3.0);//0.3333333333333333
		
	}

}
