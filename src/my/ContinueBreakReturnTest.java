package my;

public class ContinueBreakReturnTest {
	public static void main(String[] args) {
		int a=1;
		int b=2;
		int c=3;
		int[] d={0,1,2,3,4,5,1};
		
		
		if(a==1){
//			System.out.println("a=1");
			for(int i=0;i<7;i++){
				if(d[i]==4){
					System.out.println("i��ֵ��"+i);
					break;//��ֹѭ����ѭ�����Ժ����䶼��ִ�С�����������ѭ��
//					return;//�������أ��Ժ��������䶼��ִ�С��������ȽϰԵ�
//					continue;
				}
//				else if(d[i]==2){
//					System.out.println("b����2");
////					break;
////					return;//�������أ��Ժ��������䶼��ִ�С��������ȽϰԵ�
//
//				}
				System.out.println("i=="+i);
			}
			System.out.println("��forѭ��֮��");

//		}else if(b==2){
//			System.out.println("b=2");
//		}else if(c==3){
//			System.out.println("c=3");
//		}
		
//		if(b==2){
//			System.out.println("�жϣ�b==2");
		}
		
		System.out.println("ѭ���壬�ж���֮�������Ƿ�ִ��");
		
	}

}
