package javaTest;


/**
 * int[] blankArray = new int[]{} 	������һ������Ϊ0���������
 * iint[] blankArray = new int[0]; Ҳ�Ƕ�����һ������Ϊ0���������
 * 
 * int[] res���鳤��Ϊ0�ģ�����û��Ԫ�أ�res[0]���գ���Ϊû���±�Ϊ0��Ԫ�ش���
 * @author Administrator
 *
 */
public class intArrayEmptyLengthTest {
	public static void main(String[] args) {
		//ʵ����ʹ���������ڲ���������blankArray�ˣ������ǲ�Ϊnull�����ǳ���Ϊ��
		int[] blankArray = new int[]{};
//		int[] blankArray = new int[12];
		if (blankArray != null) {
			System.out.println("�ǿ�");
		}
		if(blankArray!=null&&blankArray.length>0){
			System.out.println("��Ϊ��,��Ԫ��");
		}else{
			System.out.println("��Ϊ�գ�û��Ԫ��");
		}
		System.out.println("blankArray���ȣ�"+blankArray.length);
		for(int i=0;i<blankArray.length;i++){
			System.out.println("��"+i+"��Ԫ��:"+blankArray[i]);
		}
		
		int[] result = new int[0];
		System.out.println("result�ĳ��ȣ�"+result.length);		
//		System.out.println("resultֵ��"+result[0]); //����
		
	}
}
