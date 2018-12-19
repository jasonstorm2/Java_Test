package JavaCoreLearn;

/**
 * ��̬�ڲ��ࣨʵ������ȷ��˵���� ��̬Ƕ���ࣩ�ͷǾ�̬�ڲ���֮�䵽����ʲô��ͬ�أ����������߼���Ҫ�Ĳ�ͬ�� 
 * ��1���ڲ���̬�಻��Ҫ��ָ���ⲿ������á����Ǿ�̬�ڲ�����Ҫ���ж��ⲿ������á�
 * 
 * ��2���Ǿ�̬�ڲ��� �ܹ����� �ⲿ��ľ�̬�ͷǾ�̬��Ա����̬�� ���ܷ����ⲿ��� �Ǿ�̬��Ա��
 * 		��ֻ�ܷ����ⲿ��ľ�̬��Ա��
 * ��3��һ���Ǿ�̬�ڲ��಻�������ⲿ��ʵ�屻������һ���Ǿ�̬�ڲ�����Է����ⲿ������ݺͷ�����
 * 		��Ϊ�������ⲿ�����档
 * 
 * 
 * ��̬�ڲ���������� ArrayAlg2.Pair p �� �ⲿ��.��̬�ڲ���
 * 
 * ��̬�ڲ�����ô�����װ���ݽṹ���������½�һ��class����װ
 * 		���ڳ�����Ե�ʱ��Ϊ�˱����ڸ���JavaԴ�ļ�����д�������� ���룬
		���Խ�������д�뵽��̬�ڲ����У��Լ��ٴ������д�����ô�����ӵļ��
		
  �ڲ���;�̬Ƕ����Ĳ��죺
  �ڲ��಻��������̬�ĳ�Ա�����;�̬����������̬Ƕ�������
  �ڲ��������Χ������ã�����̬Ƕ����û��
 * @author LiZhenhua
 *
 */
public class StaticInnerClassTest {
	public static void main(String[] args) {
		// һ�����double����
		double [] d = new double[20];
		for(int i=0;i<d.length;i++){
			d[i]=100*Math.random();
		}
		
	   /** ��ô������̬�ڲ���ͷǾ�̬�ڲ����ʵ�� **/
	   ArrayAlg2.Pair p1 = new ArrayAlg2.Pair(1, 2);
	   // Ϊ�˴����Ǿ�̬�ڲ��࣬������Ҫ�ⲿ���ʵ��
	   ArrayAlg2.Inner inner = new ArrayAlg2().new Inner();
		
	   // ���þ�̬����������һ����̬�ڲ������
	   ArrayAlg2.Pair p = ArrayAlg2.minmax(d);
	   System.out.println("min="+p.getFirst());
	   System.out.println("max="+p.getSecond());
	   
	   // ���Ծ�̬�����Ƿ��ܸı侲̬������ֵ  --  ���Ըı�
	   System.out.println("ArrayAlg2.country��ֵ��"+ArrayAlg2.country);
	   ArrayAlg2.changeName();
	   System.out.println("ArrayAlg2.country��ֵ��"+ArrayAlg2.country);
		  
	   
	}

}

class ArrayAlg2 {
	
	public String name = "jason";
	
	public static String country = "china";
	
	// �Ǿ�̬�ڲ���
	public class Inner{
		// �ڲ����в�����̬��������
//		public static int age = 1; // ����
//		public static void main(String[] args) { //����
//			// �Ǿ�̬�ڲ��� ������ ��̬�ķ����ͱ���
//		}
		
		public String accessName(){
			return name;
		}
		
		public String accessCountry(){
			return country;
		}
		
	}
	
	// ��̬�ڲ���
	public  static class Pair {
		private double first;
		private double second;

		public static void main(String[] args) {
			// �Ǿ�̬�ڲ��� ������ ��̬�ķ����ͱ���
		}

		public Pair(double f, double s) {
			first = f;
			second = s;
		}

		public double getFirst() {
			return first;
		}

		public double getSecond() {
			return second;
		}
		// ��̬�ڲ��� �޷��ı��ⲿ�ڵ� �Ǿ�̬��Ա����Ϊ�޷����ʸó�Ա
//		public void changeName(){
//			this.name =  "Jessica";
//		}
		// ��̬�ڲ��� ���Ըı��ⲿ�ڵ� ��̬��Ա
		public static void changeName() {
			country = "Jessica";
		}

	}
	// ��̬����
	public static Pair minmax(double[] values) {
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (double v : values) {
			if (min > v)
				min = v;
			if (max < v)
				max = v;
		}
		return new Pair(min, max);
	}
	// ��̬����
	public static void changeName(){
		country = "America";
	}
}
