package my;

/**
 * object[] ������ԣ������������κ����͵�����
 * @author Administrator
 *
 */
public class ObjectArrayTest {
	
	public static void main(String[] args) {
		Object[] ob = new Object[2]; 
		ob[0]=true;
		ob[1]=1;
		System.out.println("ob[0]:"+ob[0]+","+"ob[1]:"+ob[1]);
		boolean re = (boolean) ob[0];
		int value = (int) ob[1];
		if(re){
			System.out.println("��ӡ��ֵ��"+value);
		}
		
	}

}
