package Chapter18;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;


@Repeatable(Annos.class)
@interface Anno{}
@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos{
	Anno[] value();
}

//ʹ���˸�ע�����θ���
@SuppressWarnings(value="unchecked")
@Deprecated
//ʹ���ظ�ע�����θ���
@Anno
@Anno
public class ClassForNameTest {
	static{
		System.out.println("////�౻��ʼ������");
	}
	//����һ��˽�й�����
	private ClassForNameTest(){
		
	}
	//����һ���в����Ĺ�����
	public ClassForNameTest(String name){
		System.out.println("ִ���в����Ĺ�����");
	}
	
	public void info(){
		System.out.println("ִ���޲ε�info����");
	}
	
	public void info(String str){
		System.out.println("ִ���в�����info����"+",��Str����ֵ��"+str);
	}
	
	//����һ�������õ��ڲ���
	class Inner{

	}
    class Toto{
		
	}
	public static void main(String[] args) throws Exception {
		//����Ĵ�����Ի�ȡClassTest��Ӧ��Class
		Class<ClassForNameTest> clazz = ClassForNameTest.class;
		//��ȡ ȫ��������
		Constructor[] ctors = clazz.getDeclaredConstructors();
		
		System.out.println("ClassForNameTest��ȫ�����������£�");
		for (Constructor c : ctors) {
			System.out.println(c);
		}
		//��ȡ ȫ�� public������
		Constructor[] publicCtors = clazz.getConstructors();
		System.out.println("ClassForNameTest��ȫ�� PUBLIC���������£�");
		for (Constructor c : publicCtors) {
			System.out.println(c);
		}
		//��ȡ ȫ��public����
		Method[] mds = clazz.getMethods();
		System.out.println("ȫ����public������");
		for (Method method : mds) {
			System.out.println(method);
		}
		//��ȡ�� Class��������Ӧ���� ��ָ������
		System.out.println("�����ַ���������info������"+clazz.getMethod("info", String.class));
		//��ȡ ȫ��ע��
		Annotation[] anns = clazz.getAnnotations();
		System.out.println("Class��������Ӧ����� ȫ��ע�����£�");
		for (Annotation annotation : anns) {
			System.out.println(annotation);
		}
		System.out.println("��ClassԪ���ϵ�@SuppressWarningsע��Ϊ��"+Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));
		
		System.out.println("��ClassԪ���ϵ�@Annoע��Ϊ��"+Arrays.toString(clazz.getAnnotationsByType(Anno.class)));
		//��ȡ ȫ���ڲ���
		Class<?>[] inners = clazz.getDeclaredClasses();
		System.out.println("ȫ���ڲ������£�");
		for (Class<?> class1 : inners) {
			System.out.println(class1);
		}
		
		//ʹ��Class.forName����ClassForNameTest�� Inner �ڲ���
		Class<?> inClazz = Class.forName("JavaCoreLearn.ClassForNameTest$Inner");
		//�����ڲ��� ���ⲿ��
		System.out.println("inClazz��Ӧ��� �ⲿ�� Ϊ:"+inClazz.getDeclaringClass());
		
		System.out.println("�������ڵİ���"+clazz.getPackage());
		System.out.println("�ڲ��������ڵİ���"+inClazz.getPackage()+"\n");
		System.out.println("ClassForNameTest�ĸ��ࣺ"+clazz.getSuperclass());
	}
}
