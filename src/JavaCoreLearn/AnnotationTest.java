package JavaCoreLearn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited()
public @interface AnnotationTest {
	//�����������Ա������annotation
	//annotation �еĳ�Ա�����Է�������ʽ������
	String name() default "jason";//���βεķ���
	int age() default 20;//�������ͷ���ֵ ������ �ó�Ա���������ֺ�����
}
