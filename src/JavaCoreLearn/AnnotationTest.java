package JavaCoreLearn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * �Զ���ע���࣬
 * ������������������������Ĭ��ֵ����Ա�����Է�������ʽ������
 * 		��ʹ�ø�ע��ʱ����������������������ʽ��@AnnotationTest(name="ji",age = 222)
 * 
 * @author Administrator
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited()
public @interface AnnotationTest {
	//�����������Ա������annotation
	//annotation �еĳ�Ա�����Է�������ʽ������
	String name() default "jason";//���βεķ���
	int age() default 20;//�������ͷ���ֵ ������ �ó�Ա���������ֺ�����
}
