package my;

public class StringLenthSubTest {
	public static void main(String[] args) {
		String content ="�����й����ذ�����������Ҫ�һ�Ҫ";
		
		int strlen=strLength(content);
		if(strlen>10){
			 content = content.substring(0,10);
		}
		System.out.println(content);
		String str = "100,100";
		System.out.println("str�ĳ��ȣ�"+str.length());
		int[] i = new int[]{1,4};
		System.out.println(i[0]+","+i[1]);
		i[0]=i[1];
		System.out.println(i[0]+","+i[1]);
	}
	
    /**
     * �������ж�һ���ַ����Ƿ�Ϊnull���ֵ.
     *
     * @param str ָ�����ַ���
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
    /**
     * ��������ȡ�ַ����ĳ���.
     *
     * @param str ָ�����ַ���
     * @return  �ַ����ĳ��ȣ������ַ���2����
     */
     public static int strLength(String str) {
         int valueLength = 0;
         String chinese = "[\u0391-\uFFE5]";
         if(!isEmpty(str)){
	         //��ȡ�ֶ�ֵ�ĳ��ȣ�����������ַ�����ÿ�������ַ�����Ϊ2������Ϊ1
	         for (int i = 0; i < str.length(); i++) {
	             //��ȡһ���ַ�
	             String temp = str.substring(i, i + 1);
	             //�ж��Ƿ�Ϊ�����ַ�
	             if (temp.matches(chinese)) {
	                 //�����ַ�����Ϊ2
	                 valueLength += 2;
	             } else {
	                 //�����ַ�����Ϊ1
	                 valueLength += 1;
	             }
	         }
         }
         return valueLength;
     }

}
