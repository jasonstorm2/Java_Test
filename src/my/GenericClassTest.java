package my;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * ����֧�� �ںܴ�̶��϶���Ϊ���ü����ܼ�ס��Ԫ�ص��������͡�
 * �����ڱ���ʱ��鼯���е�Ԫ�����ͣ������ͼ��Ӳ���������ͣ�����������ʾ����
 * 
 * �����࣬���ܴ���������͵Ĳ�������JAVA��˵ ������Ȼ������ͬһ���ദ�����ڴ���Ҳֻռ��һ���ڴ�ռ�
 * ���ԣ��ھ�̬��������̬��������̬��ʼ�����У������������Ͳ���
 * 
 * ����ϵͳ��������Ĳ��������࣬���� �� instanceof ���ж� �������Ǵ��
 * @author LiZhenhua
 *
 */
public class GenericClassTest {
	
	public static void getDate(myGeneric<?> s){
		System.out.println(s.get());
	}
	
	//�����������ã���������String ��������
	public static void getLimitDate(myGeneric<? extends String> ss){
		System.out.println("������������Ŷ��"+ss.get());
		
	}
	
	public static void main(String[] args) {
		new GeMethod().doMethod();	
		
		
//		List<String>[] lsa = new List<String>[10]; // Not really allowed.
//		Object o = lsa;
//		Object[] oa = (Object[]) o;
//		List<Integer> li = new ArrayList<Integer>();
//		li.add(new Integer(3));
//		oa[1] = li; // Unsound, but passes run time store check
//		String s = lsa[1].get(0); // Run-time error: ClassCastException.
		
		List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
		Object o = lsa;
		Object[] oa = (Object[]) o;
		List<Integer> li = new ArrayList<Integer>();
		li.add(new Integer(3));
		oa[1] = li; // Correct.
		String s = (String) lsa[1].get(0); // Run time error, but cast is explicit.  java.lang.Integer cannot be cast to java.lang.String
		System.out.println("�����������:s:"+lsa[1].get(0));


		
		
		myGeneric<String> ss = new myGeneric<String>("what");
		myGeneric<String> ss2 = new myGeneric<String>("what the fuck");
		myGeneric<Integer> integer = new myGeneric<Integer>(123);		
		
		System.out.println("ss:"+ss.get());
		System.out.println("ss2:"+ss2.get());
		System.out.println("integer:"+integer.get());
	
		getDate(ss);
		getDate(ss2);
		getDate(integer);
		getLimitDate(ss2);
		
	}

}

/**
 * �Զ���ķ�����---�ڶ���ӿڣ���ʱ�������Ͳ��������Ͳ����������ӿڣ������ڿ��Ե�������ʹ��K�������кܶ��֣���String��Integer��
 * �������п�ʹ�� ��ͨ���� �ĵط�������ʹ������ ���Ͳ���
 * @author LiZhenhua
 *
 * @param <K>
 */
//����Ϊ�κ��࣬�ӿ����ӷ�������
// class ����<��������>
class myGeneric<K> {
	
//	static K s;��̬�ı���������ʹ�����Ͳ�������
//	public static void bar(K m){} ��̬�ķ���Ҳ������ʹ�����Ͳ���----����Ϊ���ܴ�������ʵ�Σ�����java��˵���Ƕ���ͬһ���ֻ࣬ռͬһ���ڴ�ռ䣿

	private K date;   
	
	private List<K> s;// List<K> ��һ���µ�  �������� ,������Ϊ��List���͵�������
	//������������������ �����ڶ����������������ʱ����һ������ʵ�Σ��Ӷ����Զ�̬��������������߼��ϵ����࣬�����������������ϲ�������

	public myGeneric(K date){
		set(date);
	}
	
	//������������ԭ������������Ҫ���ӷ������������� myGeneric ���� myGeneric<K>
	public myGeneric(){
		
	}
	public K get() {
		return date;
	}

	public void set(K date) {
		this.date = date;
	}
}
/****�����࣬�������Ͳ���ʱ Ҳ�����������ޣ��Ƚ�ͨ������ޣ�*****/
class myG<T extends Object>{
	private T k;
	public T getT(){
		return k;
	}
}
/**
 * ���ö�����ޣ�����һ���������ޣ������ж���ӿ����ޣ��� �����ޱ����ڵ�һ��λ��
 * @author LiZhenhua
 *
 * @param <T>
 */
class myGe<T extends Object & Serializable>{
	private T k;
	public T getT(){
		return k;
	}
}

/**
 * �ӷ���������������  �����������ӿڲ��ܴ����Ͳ���
 * class mg extends myGeneric<K>�Ǵ�ģ��������ʵ�ʵ����ͣ���ʹ�÷�������class mg extends myGeneric<String>
 * ����class mg extends myGeneric Ҳ����ȷ�ģ����Բ��ô���ʵ�ʵ����Ͳ���
 * @author LiZhenhua
 *
 * ������������� �Ѿ����Ƿ�����
 */
class mg extends myGeneric<String>{
	
	
	//����Ѿ�ȷ�� �������������Ͳ�������ô��д�����ͱ���ʹ�ø����Ͳ���String
	@Override
	public String get() {
		// TODO Auto-generated method stub
		return super.get();
	}
	
	@Override
	public void set(String date) {
		// TODO Auto-generated method stub
		super.set(date);
	}
	
	/**
	 * ����ͨ�������һ���ʺţ�
	 * ��һ���ʺ���Ϊ����ʵ�Σ�����myGeneric���ϣ���ʾ���ַ���myGeneric�ĸ���
	 * @param my
	 */
	public void test(myGeneric<?> my){
		List<?> c = new ArrayList<String>();
//		c.add("s");//c ��ͨ������͵�list ,�޷�֪�� Ԫ�ص����ͣ����Բ�������κ����͵�Ԫ��
		c.add(null);//null ���⣬null���������ø����͵� ʵ��
	}
	
}

/**
 * ������������� �Ѿ����Ƿ�����
 * @author LiZhenhua
 *
 */
class mg2 extends myGeneric{
	//û��ȷ�� �������������Ͳ�������д�������ص���Object
	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return super.get();
	}
}




/*
 * ������ͨ���������
 */

abstract class Shape{
	public abstract void draw(Canvas c);
}

class Circle extends Shape{

	@Override
	public void draw(Canvas c) {
		// TODO Auto-generated method stub
		System.out.println("����һ��Բ");
		
	}	
}

class Rectangle extends Shape{

	@Override
	public void draw(Canvas c) {
		// TODO Auto-generated method stub
		System.out.println("����һ������");
		
	}	
}

/**
 * ���Ƶķ���ͨ��� �����壬Ϊ��Ҫ���ƣ�(���⣬Ҳ�����ڶ������Ͳ�������������)
 * @author LiZhenhua
 *
 */
class Canvas{
	/**
	 * �˻�����ͼ�����ͼ�Σ�Բ�;���
	 * ����List<Shape> ���� List<Circle> �� List<Rectangle> �ĸ���
	 * List<Circle>������뽫�ᱨ��
	 * @param shapes
	 */
	public void drawAll(List<Shape> shapes){
		for(Shape s:shapes){
			s.draw(this);
		}		
	}
	
	/**
	 * ������ͨ�������ʾ�κ����͵ĸ����ͣ������õ��˷��ͣ��ֽ�����ǿ��ת��
	 * ӷ�׶�����
	 * @param shapes
	 */
	public void drawAll2(List<?> shapes){
		for(Object o:shapes){
			Shape s = (Shape)o;
			s.draw(this);
		}		
	}
	
	/**
	 * �����Ƶķ���ͨ�����<? extends Shape>����ʾ����Shape����List�ĸ��࣬�����Ա�ʾCirle Rectangle�ĸ���
	 * @param shapes
	 */
	public void drawAll3(List<? extends Shape> shapes){
		for(Shape s:shapes){
			s.draw(this);
		}		
		
//		shapes.add(new Circle());//�����޷�֪��List�����ͣ���Ȼ�������Ƶ�ͨ��������˴���Ӿ�������Ҳ�Ǵ��
	}
}

/****���ͷ���***/

class GeMethod{	//�಻�Ƿ���Ŷ�����Ե������巽��Ϊ����
	
	
	public void doMethod(){
		Object[] obs = new Object[10];
		obs[0] = "1";
		obs[1] = 2;
		Collection<Object> c = new ArrayList<Object>();
		addItem(obs, c);
		String[] str = {"a","b"};
		List<String> l = new ArrayList<String>();
//		addItem(str, l); ����
	}
	
	/**
	 * �������ޣ�ֻ�ܽ�Object���͵�Ԫ�ط���Collection<Object>��
	 * @param objects
	 * @param c
	 */
	static void addItem(Object[] objects,Collection<Object> c){
		for(Object o : objects){
			c.add(o);
		}
	}
	
	static void addItem2(Object[] objects,Collection<?> c){
		for(Object o : objects){
//			c.add(o); ������Ӿ������ͽ���δ֪���͵ķ���
		}
	}
	
	/**
	 * ���ͷ����� ����ͨ��������һ�������β������������β������� ���η��ͷ���ֵ ֮�䣬�����ж�������βΣ��ö��Ÿ���
	 * �� ������Ȳ�ͬ���ǣ����÷��ͷ���ʱ��������ʾ����ʵ�����Ͳ���������������ݲ����ƶϳ����Ͳ��������͡�
	 * @param t
	 * @param c
	 */
	static <T> void addItem3(T[] t,Collection<T> c){
		for(T o : c){
			c.add(o);
		}
	}
	
	/**
	 * ���ͷ��� ʹ�� ����ͨ���
	 * @param f
	 * @param t
	 */
	static <T> void addItem4(Collection<? extends T> f,Collection<T> t){
		for(T o : f){
			t.add(o);
		}
	}
}

