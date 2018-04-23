package JavaCoreLearn;

/**
 * ���������ת�ַ����ķ���
 * @author Administrator
 *
 */
public class StringReverse {
	
	public static void main(String[] args) {
		System.out.println(reverse1("long"));		
		System.out.println(reverse2("long"));	
		System.out.println(reverse3("long"));	
		System.out.println(reverse4("long"));	
		System.out.println(reverse5("long"));	
		System.out.println(reverse6("long"));	
		
		
	}
	
	/**
	 * ��򵥵ķ���1
	 * ����StringBuffer�ķ�ת���� reverse
	 * @param str
	 * @return
	 */
	public static String reverse1(String str){
		return new StringBuffer(str).reverse().toString();
	}
	/**
	 * ��򵥵ķ���2
	 * ����StringBuilder�ķ�ת���� reverse
	 * @param str
	 * @return
	 */
	public static String reverse2(String str){
		return new StringBuilder(str).reverse().toString();
	}
	
	/**
	 * ��String�ַ���ת����char[]���飬���±������С��ֵ���µ�String��
	 * @param s
	 * @return
	 */
	public static String reverse4(String s)
	 { 
	   char[] array = s.toCharArray(); //��string���Ͳ���ת��Ϊchar[]���Ͳ���
	   String reverse = "";  //ע�����ǿմ�������null
	   for (int i = array.length - 1; i >= 0; i--) 
	   reverse += array[i]; 
	   return reverse; 
	  }

	/**
	 * C�����г��õķ�����
	 * @param orig
	 * @return
	 */
	 public static String reverse5(String orig)
	 { 
	   char[] s = orig.toCharArray(); 
	   int n = s.length - 1; 
	   int halfLength = n / 2; 
	   for (int i = 0; i <= halfLength; i++) { 
	     char temp = s[i]; 
	     s[i] = s[n - i]; 
	     s[n - i] = temp; 
	   } 
	   return new String(s);  //֪��  char�����String�໥ת��
	 } 
	 
	 /**
	  * ���õݹ鷽��
	  * @param orig
	  * @return
	  */
	 public static String reverse6(String orig)
	 { 
		 if(orig == null || orig.length() <=1){
			 return orig;
		 }
		 int i = Integer.parseInt(orig);
		 Integer it = Integer.valueOf(orig);
		 String dd = String.valueOf(1);
		 return reverse6(orig.substring(1)) + orig.charAt(0);				 
	 } 
	 
	/**
	 * String�ַ������÷���charAt(i)������������µ�String
	 * 
	 * @param s
	 * @return
	 */
	public static String reverse3(String s) {
		int length = s.length();
		String reverse = ""; // ע�����ǿմ�������null
		for (int i = 0; i < length; i++) {
			reverse = s.charAt(i) + reverse;// ���ַ���ǰ�����ӣ� ���ǳ����ĺ���
		}

		return reverse;
	}
}
