package my;

import java.text.NumberFormat;

public class PercentTest {
	public static void main(String[] args) {
		//�����������ӡ�D���Ǳ�������Double���ͣ���������Ļ�ȡ�����޷�����ʹ��
		   double percent = 50.5D / 150D;
		   //���һ�£�ȷ�����С������
		   System.out.println("С����" + percent);
		   //��ȡ��ʽ������
		   NumberFormat nt = NumberFormat.getPercentInstance();
		   //���ðٷ�����ȷ��2��������λС��
		   nt.setMinimumFractionDigits(2);
		   //����ʽ�������
		   System.out.println("�ٷ�����" + nt.format(percent));
		   
		   
		   System.out.println(percent * 100);
		   System.out.println((int)(percent * 100));
		   
		   long a = 100l;
		   long b = 300l;
		   System.out.println((int)((double)a/b));
		   System.out.println(      (int)(((double)a/b)*100)    );
			
			
	}

}
