package my;

public class LongAndDouble {

	/**
	 * @param args
	 * double �� long ���໥ת��
	 * 
	 * long ǿ��ת��Ϊint 
	 */
	public static void main(String[] args) {
		double d = 88.88;
		long l = Math.round(d);
		System.out.println(l);
		
		long ll = 100L;
		double dd = (double) ll;
		System.out.println(dd);
		
		//����ǿ��ת�����
		long lll = 123456789123467894l;
		int inte = (int)lll;
		System.out.println("longǿ��ת��Ϊint:"+inte);
	}

}

