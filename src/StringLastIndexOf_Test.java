
public class StringLastIndexOf_Test {
	//substring(index)����ʾ ��ȡ ���ַ�����index�±꿪ʼ��ֱ���ַ������� ���ַ������γ��µ��ַ���
	public static void main(String[] args) {
//		String s = "C:\\myfile\\jspfile\\example.jsp";
		String s =".0.1.2.3������";
		//���һ�����š�.�����ֵ�λ�� ���±�
		int index = s.lastIndexOf(".");
		String s1=s.substring(index);
		String s2=s.substring(index,index+1);

		
		System.out.println(index);
		System.out.println(s1);
		System.out.println(s2);
		
	}

}
