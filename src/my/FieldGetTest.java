package my;

import java.lang.reflect.Field;

/**
 * ���䣬������ ʵ�������������
 * getDeclaredField �õ�ָ������ �������
 * getField �õ� public ���ε� ָ�����ֵ� �����
 * 
 * �������һ����������Ҫ�������������ͽ����жϣ����ܽ�����Ӧ���߼�
 * @author Administrator
 *
 */
public class FieldGetTest {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		BeCall be = new BeCall();
		Field f = BeCall.class.getDeclaredField("a");
		
		/**��� ʵ����������ָ������**/
		int a1 = (int)f.get(be);  //��static��fieldҪ������صĶ���ʵ��
		System.out.println(a1);	
		System.out.println("-----------------");		
		
		/**��� �����������ָ������**/
		Field fb = BeCall.class.getDeclaredField("b");
		int b1 = fb.getInt(null);  //static �� field ��������������������null
		int b2 = fb.getInt(be);
		int b3 = fb.getInt(12);
		int b4 = fb.getInt("");
		
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		System.out.println("-----------------");		
		
		Field s = BeCall.class.getField("s");  //public ���򣬿����� getField������ã���Ȼ�ᱨ��
		Field s1 = BeCall.class.getDeclaredField("s");  //����������ֵ���
		System.out.println("String ʵ����������getField��õ�ֵ��"+s1.get(be));//û���ж����ͣ�ֱ�����
		
		/*�ж�������Class����,���Դ˽�����Ӧ�߼�����*/
		Class<?> type = s.getType();
		System.out.println("s Field�������ǣ�"+type);
		Object obj = s.get(be);
		
		if(type.isAssignableFrom(String.class)){
			if(obj==null){
				System.out.println("��Ҫ��ѯ��ֵ�ǿյ�");
			}else{
				System.out.println("��Ҫ��ѯ��ֵ:"+obj);
			}
		}else{
			System.out.println(type);
		}
		
	}
}

class BeCall{
	int a = 1;
	static int b = 2;
	public String s = "fuck";
}
