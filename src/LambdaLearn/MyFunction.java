package LambdaLearn;

/**
 * 
 * ͨ��@FunctionalInterfaceע������ʽָ��һ���ӿ��Ǻ���ʽ�ӿڣ��Ա�������������һ�����Ϻ���ʽ��׼�Ľӿڣ�
 * FI�Ķ�����ʵ�ܼ򵥣��κνӿڣ����ֻ���� Ψһ һ�����󷽷�����ô������һ��FI��
 * 
 * �ӿ���ÿһ������Ҳ����ʽ�����,�ӿ��еķ����ᱻ��ʽ��ָ��Ϊ public abstract��ֻ���� public abstract���������η����ᱨ����
 * ע��java1.8�İ汾�ӿ��п��Դ��ھ�̬����static��Ҳ��Ĭ�Ϸ���default�ĳ���
 * @author Administrator
 *
 * @param <T>
 */
@FunctionalInterface  
interface MyFunction<T> {
	abstract T oneMethod(T t);
	static void testMethod(){System.out.println("�ӿ���ľ�̬����testMethod");};
	
	default void defaultTest1(){ //Ĭ�Ϸ������Ա��̳е�����д��Ҳ����ֱ��������
		System.out.println("This is a default method1");
	};
	default void defaultTest2(){
		System.out.println("This is a default method2");
	};
	default void defaultTest3(){
		System.out.println("This is a default method3");
	};	
}
