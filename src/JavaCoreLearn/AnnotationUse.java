package JavaCoreLearn;

import java.lang.annotation.Annotation;

/**
 * �ж�һ�����Ƿ���ĳ��ע�ͣ�**.class.isAnnotationPresent(**.class��
 * ͨ��������һ�����ϵ�����ע����Ķ��� Class.forName("JavaCoreLearn.second").getAnnotations()
 * ���ע��������һЩ��Ϣ������ǿ��ת��Ϊĳע���ࣺ((AnnotationTest) a).name())
 * 
 * �������ע��ʱ�����Ը���ע�͵ı�������ֵ��@AnnotationTest(name="ji")
 * 
 * @author Administrator
 *
 */
public class AnnotationUse {
	public static void main(String args[]){
		System.out.println(AnnotationUse.class.isAnnotationPresent(AnnotationTest.class));
		System.out.println(first.class.isAnnotationPresent(AnnotationTest.class));
		System.out.println(second.class.isAnnotationPresent(AnnotationTest.class));
		
		try {
			Annotation[] arr = Class.forName("JavaCoreLearn.second").getAnnotations();
			for(Annotation a : arr){
				System.out.println("annotation:"+a);
				if(a instanceof AnnotationTest){
					System.out.println("a is:"+a);
					System.out.println("ע�����϶������Ϣname��"+((AnnotationTest) a).name());
					int age = ((AnnotationTest)a).age();
					System.out.println("ע�����϶������Ϣage��"+age);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

@AnnotationTest(name="ji",age = 222)
class first{
	
}

class second extends first{
	
	
}
