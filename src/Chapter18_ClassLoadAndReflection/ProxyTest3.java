package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface Dog{
	void info(String name);
	void run();
}

class GunDog implements Dog{

	@Override
	public void info(String name) {
		// TODO Auto-generated method stub
		System.out.println("(�����һ)����һֻ��,�ҵ������ǣ�"+name);//ϣ������� ִ�� ͨ�÷���
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("(����ζ�)���ڿ��ٱ���");				 //ϣ������� ִ�� ͨ�÷���
	}	
	
	public void self(){
		System.out.println("����һ�����Լ��ķ������ǽӿڼ̳�");
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
		System.out.println("(�����һ)����һֻ��,�ҵ������ǣ�"+name);//ϣ������� ִ�� ͨ�÷���
		du.method2();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		du.method1();
		System.out.println("(����ζ�)���ڿ��ٱ���");				 //ϣ������� ִ�� ͨ�÷���
		du.method2();
	}	
	public void self(){
		System.out.println("����һ�����Լ��ķ������ǽӿڼ̳�");
	}
}

/*
 * ͨ�÷���
 */
class DogUtil{
	//��һ������������
	public void method1(){
		System.out.println("======ģ���һ��ͨ�÷���======");
	}
	//�ڶ�������������
	public void method2(){
		System.out.println("======ģ��ڶ���ͨ�÷���======");
	}
	
}

class MyInvokationHandler2 implements InvocationHandler{
	//��Ҫ������Ķ���
	private Object target;
	public void setTarget(Object target){
		this.target = target;
	}
	
	/*
	 *ͨ�÷��� ���� �����handler��invoke�����ڣ������� 
	 */
	//ִ�ж�̬������� �����з���ʱ�����ᱻ�滻��ִ�����µ�invoke����
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (proxy instanceof GunDog) {			
			System.out.println("������GUNDOGʵ��");
		}
		if(proxy instanceof Dog){
			System.out.println("������DOGʵ��");
		}
		System.out.println("Proxy���ࣺ"+proxy.getClass());
		System.out.println("��������"+method.getName());
		DogUtil du = new DogUtil();//ͨ�÷���
		du.method1();
		//��target��Ϊ ������ ��ִ�� method����
		Object result = method.invoke(target,args);//������ʵ��ԭ���ķ�������ԭ���ķ�����ͨ�÷��� �����ˡ���
		du.method2();
		return result;
	}
	
}
class MyProxyFactory{
	//Ϊָ���� target���ɶ�̬�������
	public static Object getProxy(Object target) throws Exception{
		MyInvokationHandler2 handler = new MyInvokationHandler2();
		
		handler.setTarget(target);//���� �˶���� Ŀ���ǣ��ô����ܹ�ͨ�����䣬��ɴ˶���� ָ�������ĵ���
		
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
	}
}
public class ProxyTest3 {
	public static void main(String[] args) throws Exception {
		
		System.out.println("-------------------------����ʹ�÷���--------------------------");
		//����һ�� ԭʼ��GunDog������Ϊ target
		Dog target = new GunDog();
		//��ָ����target ��������̬�������
		Dog dog = (Dog)MyProxyFactory.getProxy(target);
		
		dog.info("����");//�ڴ����invoke�����У���ͨ��Method�ķ��䣬��ʵ��info����
		dog.run();
		//dog.self()�������ܵ��á������������ܵ��ýӿڷ���������
		
		
		System.out.println("-------------------------������ʹ�÷���--------------------------");
		GunDog2 target2 = new GunDog2();
		target2.info("����");
		target2.run();
		target2.self();
		
		if (target instanceof GunDog) {			
			System.out.println("1111������GUNDOGʵ��");
		}
		if(target instanceof Dog){
			System.out.println("1111������DOGʵ��");
		}
		System.out.println(target.getClass());
		
	}

}
