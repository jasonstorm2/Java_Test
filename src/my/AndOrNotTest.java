package my;

/**
 * 
 * @author Administrator
 * 
 * java �Ķ����Ʊ�ʾ 0b,���� 6�Ķ����Ʊ�ʾ��0b110
 * 
 * ������xת�ɶ������������ַ�����ʾ��������ַ���:Integer.toBinaryString()
 * 
 * ��λ�������ڻ������ͣ����ܱ�ʾ�߼�����
 */
public class AndOrNotTest {
	
	public boolean isSame(int channelNum,int channel){
//		return channelNum==channel;
		return (channelNum & channel) == channelNum;
	}
	//������ ��ͬtrue����true �� 1&1 == 1,1&0=0 0&0=0;
	public int methodAnd(int channelNum,int channel){
		return (channelNum & channel);
	}
	
	public int methodOr(int channelNum,int channel){
		return (channelNum | channel);
	}
	public static void main(String[] args) {
		AndOrNotTest ii = new AndOrNotTest();
		//1000000000000000100000000
		//1000000000000001100000000
		boolean bo=ii.isSame(0b1000000000000000100000000 ,0b1000000000000001100000000); // 110 10
		System.out.println("����������"+bo);
		
		int and=ii.methodAnd(0b110,0b10);
		System.out.println("methodAnd:"+Integer.toBinaryString(and));
		
	}

}
