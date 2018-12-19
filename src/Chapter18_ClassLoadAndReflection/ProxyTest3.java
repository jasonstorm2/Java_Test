package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import utils.utils;


/**
 * JAVA ���ִ���
 * 1.��̬����
 * 2.��̬����
 * 3.Cglib��̬����
 * 
 * https://blog.csdn.net/yangsnow_rain_wind/article/details/79291256
 * 
 * @author LiZhenhua
 * 
 * JDK��̬��������� 
 * ͨ��������Proxy��InvocationHandler�ص��ӿ�ʵ�ֵ�jdk��̬����Ҫ�� ί���� ����ʵ��һ���ӿڣ�
 * ����ʵ�ϲ����������඼�нӿڣ�����û��ʵ�ֽӿڵ��࣬���޷�ʹ�ø÷���ʽʵ�ֶ�̬����
 * 
 * ��̬�����У������ಢ������Java������ʵ�֣�����������ʱ�����ɣ���Ⱦ�̬������̬������Ժܷ���Ķ�ί����ķ�������ͳһ����
 * ����ӷ������ô����������־���ܵȵȣ���̬�����Ϊjdk��̬�����cglib��̬����
 *
 */


/**
 * ������Ķ���ʵ�ֵĽӿ�
 * @author LiZhenhua
 *
 */
interface Dog{
	void info(String name);
	void run();
}

interface Cat{
	void miao(String str);
}

/**
 * ������Ķ���
 * @author LiZhenhua
 *
 */
class GunDog implements Dog{

	@Override
	public void info(String name) {
		// TODO Auto-generated method stub
		System.out.println("GunDog.info (�����һ)����һֻ��,�ҵ������ǣ�"+name);//ϣ������� ִ�� ͨ�÷���
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("GunDog.run (����ζ�)���ڿ��ٱ���");				 //ϣ������� ִ�� ͨ�÷���
	}	
	
	public void self(){
		System.out.println("GunDog.self ����һ�����Լ��ķ������ǽӿڼ̳�");
	}
}

class MyCat implements Cat,Dog{

	@Override
	public void miao(String str) {
		// TODO Auto-generated method stub
		System.out.println("è�У�"+str);
	}

	@Override
	public void info(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("èѧ������");
	}
	
}

/*
 * Ҫִ��ͨ�÷�������ͨ��ʽ�������˴���� ����ԣ���
 */
class GunDog2 implements Dog{
	DogUtil du = new DogUtil();
	@Override
	public void info(String name) {
		// TODO Auto-generated method stub
		du.method1();
		System.out.println("GunDog2.info (�����һ)����һֻ��,�ҵ������ǣ�"+name);//ϣ������� ִ�� ͨ�÷���
		du.method2();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		du.method1();
		System.out.println("GunDog2.run (����ζ�)���ڿ��ٱ���");				 //ϣ������� ִ�� ͨ�÷���
		du.method2();
	}	
	public void self(){
		System.out.println("GunDog2.self ����һ�����Լ��ķ������ǽӿڼ̳�");
	}
}

/**
 * ͨ�÷�����Ϊ�˷���ʵ�ִ�ӡ
 * @author LiZhenhua
 *
 */
class DogUtil{
	//��һ������������
	public void method1(){
		System.out.println("DogUtil.method1 ======ģ���һ��ͨ�÷���======");
	}
	//�ڶ�������������
	public void method2(){
		System.out.println("DogUtil.method2 ======ģ��ڶ���ͨ�÷���======");
	}
	
}

/**
 * ���ô����࣬�̳���InvocationHandler����д�� invoke����
 * ÿ�δ��������ô�����ʱ���������invoke����������дһЩ���˵��߼�
 * @author LiZhenhua
 *
 */
/**
 * ��������Ϊ��������һ�� �������
 * 
 * ����������ɵ��������裺
 * 1.�ѱ�����Ķ�����һ��InvokationHandler�������д invoke����
 * 2.����Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler)
 * @author LiZhenhua
 *
 */
class ProxyFactory implements InvocationHandler{
	//������Ķ���
	private Object target;
	public ProxyFactory(Object target){
		this.target = target;
	}	
	/*
	 *ͨ�÷��� ���� �����handler��invoke�����ڣ������� 
	 */
	//ִ�ж�̬������� �����з���ʱ�����ᱻ�滻��ִ�����µ�invoke����
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (proxy instanceof GunDog) {			
			System.out.println("MyInvokationHandler2.invoke ������GUNDOGʵ��");
		}
		if(proxy instanceof Dog){
			System.out.println("MyInvokationHandler2.invoke ������DOGʵ��");
		}
		System.out.println("MyInvokationHandler2.invoke Proxy���ࣺ"+proxy.getClass());
		System.out.println("MyInvokationHandler2.invoke ��������"+method.getName());
		DogUtil du = new DogUtil();//ͨ�÷���
		du.method1();
		//������÷���
		Object result = method.invoke(target,args);//������ʵ��ԭ���ķ�������ԭ���ķ�����ͨ�÷��� �����ˡ���
		du.method2();
		return result;
	}	
	
	public static Object getProxy(Object target) throws Exception{	
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new ProxyFactory(target));
	}
	
}



public class ProxyTest3 {
	public static void main(String[] args) throws Exception {
		utils.PrintLine("����ʹ�÷���");
		//�����������Ϊ target
		Dog target = new GunDog();
		//���ɵĶ�̬�������--ע��,ֻ�ܷ��ؽӿڶ����Ǿ���ʵ����,���򱨴���
		Dog dog = (Dog)ProxyFactory.getProxy(target);
		//ʹ�ô��������÷���
		dog.info("dog.info ����");//�ڴ����invoke�����У���ͨ��Method�ķ��䣬��ʵ��info����
		dog.run();
		//dog.self()�������ܵ��á������������ܵ��ýӿڷ���������
		utils.PrintLine("è�Ĵ�����");
		Cat cat = (Cat)ProxyFactory.getProxy(new MyCat());
		cat.miao("miao~miao!");
		utils.PrintLine("è�Ĵ�����,ʵ���˹��ӿ�");
		Dog catDog = (Dog)ProxyFactory.getProxy(new MyCat());
		catDog.run();
		utils.PrintLine("������ʹ�÷���");
		GunDog2 target2 = new GunDog2();
		target2.info(" target2.info ����");
		target2.run();
		target2.self();
		
		if (target instanceof GunDog) {			
			System.out.println("ProxyTest3.main  1111������GUNDOGʵ��");
		}
		if(target instanceof Dog){
			System.out.println("ProxyTest3.main 1111������DOGʵ��");
		}
		System.out.println(target.getClass());		
	}

}
