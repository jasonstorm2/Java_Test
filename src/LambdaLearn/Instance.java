package LambdaLearn;
/**
 * ʵ������˳����ԣ�
 * ʵ����һ�������ٵ��øö���ķ������÷�������һ��ʵ����ͬ�����˳���ǣ����÷����е�ʵ�����ٺ��档
 * @author Administrator
 *
 */
public class Instance {
	public static void main(String[] args) {
		Ins ins = new Ins("��main����ʵ����");
		ins.run();		
		System.out.println("-------------------------");
		ins.work();
	}
}

class Ins {
	
	int age;
	int sex;
	static int eye;
	public Ins(String s) {
		System.out.println(s);
	}

	public void run() {
		Ins ins = new Ins("��run����ʵ����");
		ins.jump();
		System.out.println("run����");
	}

	public void jump() {
		System.out.println("jump����");
	}
	public void work() {
		this.jump();
		System.out.println("work����");
	}
	public void work2() {
		jump();   //ʡ����this��ʵ����thisֻ�������ˣ������ڼ�������Ӱ��
		System.out.println("work2����");
	}
	
	public static void staticMethod() {
//		int ages = age;  static ��Ա ���ܷ��� ��static��Ա
		int e  = eye;  //static ������ֻ�ܷ��� static ����
		System.out.println("staticMethod����");
	}
}
