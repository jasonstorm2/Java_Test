package javaTest;

public class intArrayEmptyLengthTest {
	public static void main(String[] args) {
		//ʵ����ʹ���������ڲ���������blankArray�ˣ������ǲ�Ϊnull�����ǳ���Ϊ��
		int[] blankArray = new int[]{};
//		int[] blankArray = new int[12];
		if(blankArray==null){
			System.out.println("kong");
		}else{
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

		
	}
}
