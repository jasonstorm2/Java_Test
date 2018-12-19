package my;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * double������λС����
 * 
 * float��double���͵���Ҫ���Ŀ����Ϊ�˿�ѧ����͹��̼��㡣
 * �ڴ��������ҵ�����У�һ�����java.math.BigDecimal�������о�ȷ���㡣
 * 
 * BigDecimal���췽�� 1.public BigDecimal(double val) ��double��ʾ��ʽת��ΪBigDecimal
 * *������ʹ�� -- ����Ԥ֪��0.1--0.1000000000000000055511151231257827021181583404541015625
 * 
 * 2.public BigDecimal(int val)������int��ʾ��ʽת����BigDecimal
 * 
 * 3.public BigDecimal(String val)������String��ʾ��ʽת����BigDecimal
 * 
 * ��double��������BigDecimal��Դʱ����ʹ��Double.toString(double)ת��String��
 * Ȼ��ʹ��String���췽������ʹ��BigDecimal�ľ�̬����valueOf
 * 
 * 
 * �Ӽ��˳� public BigDecimal add(BigDecimal value); //�ӷ�
 * 
 * public BigDecimal subtract(BigDecimal value); //����
 * 
 * public BigDecimal multiply(BigDecimal value); //�˷�
 * 
 * public BigDecimal divide(BigDecimal value); //����
 * 
 * BigDecimal a = new BigDecimal("4.5"); BigDecimal b = new BigDecimal("1.5");
 * 
 * System.out.println("a + b =" + a.add(b)); System.out.println("a - b =" +
 * a.subtract(b)); System.out.println("a * b =" + a.multiply(b));
 * System.out.println("a / b =" + a.divide(b));
 * 
 * ��һ����Ҫע����ǳ�������divide.
 * 
 * BigDecimal�������ܳ��ֲ������������������ 4.5/1.3�� ��ʱ�ᱨ��java.lang.ArithmeticException:
 * Non-terminating decimal expansion; no exact representable decimal result.
 * 
 * ��ʵdivide�����п��Դ���������
 * 
 * public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
 * ��һ������ʾ������ �ڶ���������ʾС�������λ���� ������������ʾ����ģʽ��ֻ�����������������������ʱ���õ�����ģʽ
 * 
 * ROUND_CEILING //�������������
 * 
 * ROUND_DOWN //���㷽������
 * 
 * ROUND_FLOOR //�����������
 * 
 * ROUND_HALF_DOWN //�򣨾��룩�����һ�����룬�������ߣ��ľ��룩�����,�������������������, ����1.55 ����һλС�����Ϊ1.5
 * 
 * ROUND_HALF_EVEN
 * //�򣨾��룩�����һ�����룬�������ߣ��ľ��룩�����,������������������λ����������ʹ��ROUND_HALF_UP�������ż��
 * ��ʹ��ROUND_HALF_DOWN
 * 
 * ROUND_HALF_UP //�򣨾��룩�����һ�����룬�������ߣ��ľ��룩�����,�������������������,(����������� ) 1.55����һλС�����Ϊ1.6
 * 
 * ROUND_UNNECESSARY //�������Ǿ�ȷ�ģ�����Ҫ����ģʽ
 * 
 * ROUND_UP //��Զ��0�ķ�������
 * 
 * 
 * @author LiZhenhua
 *
 */
public class doubleTest {
	public static void main(String[] args) {
		// ��ͨ�� ���㣬�ó���ֵ����ȷ
		/**
		 * ԭ���������ǵļ�����Ƕ����Ƶġ�
		 * ������û�а취���ö����ƽ��о�ȷ��ʾ��
		 * ���ǵ�CPU��ʾ������������������ɣ�ָ����β���������ı�ʾ����һ�㶼��ʧȥһ���ľ�ȷ�ȣ���Щ����������Ҳ�����һ������
		 * �磺2.4�Ķ����Ʊ�ʾ���Ǿ��Ǿ�ȷ��2.4��
		 * ������Ϊ�ӽ��Ķ����Ʊ�ʾ�� 2.3999999999999999����������ֵʵ��������һ���ض�����ѧ��ʽ����õ��ġ�
		 */
		System.out.println(0.2+0.1);// 0.30000000000000004
		double f = 1/(3*1.0);
		System.out.println("f:"+f);
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(20, BigDecimal.ROUND_HALF_UP).doubleValue(); 
		System.out.println("f1:"+f1);
		
		double r = 1/(3*1.0);				
		BigDecimal bb = new BigDecimal(r);
		double rate = bb.setScale(15, BigDecimal.ROUND_DOWN).doubleValue(); 
		System.out.println("rate:"+rate);
		
		char[] cha = new char[3200];
		for(int i = 0;i<cha.length;i++){
			cha[i]= 'a';
		}
		System.out.println(cha);
		 
	}

}
