package my;

/**
 * ���� final���εı��� �� ��ͨ����������
 * final�ı�����ֵ���޷��ı�
 * final�ı����ڳ�ʼ��ʱ���븳ֵ������������ʱ��ֵ
 * 
 * @author Administrator
 *
 */
public class FinalVariableTest {
	public final int a;
	public final int[] b;
	public int aa;
	public int[] bb;
	
	public FinalVariableTest(int a,int[] b,int aa,int[] bb){
		this.a = a;
		this.b = b;		
		this.aa = aa;
		this.bb = bb;
	}
	
	public static void main(String[] args) {
		FinalVariableTest fv = new FinalVariableTest(1, new int[]{1,1},4, new int[]{4,4});
//		fv.a = 1;  //����final�ı����޷��ı�
		System.out.println("fv.aa��ֵ:"+fv.aa);		
		fv.aa = 5;
//		fv.b = new int[]{5,5}; //����final�ı����޷��ı�
		System.out.println("fv.bb��ֵ:"+fv.bb[0]+","+fv.bb[1]);
		fv.bb = new int[]{6,6} ; //�����޸���
		System.out.println("fv.bb��ֵ:"+fv.bb[0]+","+fv.bb[1]);
		
		System.out.println("fv.aa��ֵ:"+fv.aa);		
		fv.aa = 666;  //������ֵ������
		System.out.println("fv.aa��ֵ:"+fv.aa);
		
		
	}
}
