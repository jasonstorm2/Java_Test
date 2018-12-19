package JavaCoreLearn;
/**
 * String compareTo�����ıȽϹ��� 
 * ����ַ��Ƚϴ�С�������򷵻����������С�򷵻ظ����������Ϊ0.
 * 
 * String1.compareTo(String2) > 0 ˵��String1��String2��
 * 
 * @author LiZhenhua
 *
 */
public class PairTest1 {
	public static void main(String[] args) {
		String[] words = {"bary","had","adfdfdfdf","little","lamb","oliva"};
		Pair<String,Integer> mm = ArrayAlg.minmax(words);
		System.out.println("min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());
		System.out.println("length:"+mm.getUFirst());		
	}

}

class ArrayAlg{
	// ���;��廯������һ��Pair����
	public static Pair<String,Integer> minmax(String[] a){
		if(a == null||a.length==0)return null;
		String min = a[0];
		String max = a[0];
		for (int i = 0; i < a.length; i++) {
			if(min.compareTo(a[i])>0) min = a[i];
			if(max.compareTo(a[i])<0) max = a[i];			
		}
		return new Pair<>(min,max,a.length);
	}
}
