package my;

/**
 * ���� String���contain����������String�����Ƿ����ĳ��StringƬ��
 * �������������Ƭ�Σ�����true
 * @author LiZhenhua
 *
 */
public class StringContainTest {
	public static void main(String[] args) {
		String str = "123,235���Ĵ���";
		int a = 5;
		String as = a+"";
		
		boolean inContain = StringContainTest.isStrContain(str, as);
		System.out.println("�Ƿ����ĳ�ֶΣ�"+inContain);
		System.out.println("�Ƿ����ĳ�ֶΣ�"+str.contains("35��"));
		
		int percent = (int)(((3*1.0)/6)*100);
		System.out.println("�ٷֱȣ�"+percent);
		
	}
	
	public static boolean isStrContain(String a,String b){
		return a.contains(b);
	}

}
