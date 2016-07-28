package JavaCoreLearn;

import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Զ���һ�� annotationע��
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)   //Ԫ annotation
public @interface Anno2Mine {
	//����һ����Ա��������������Ԫ����
	//��listener ��Ա�������ڱ��������ʵ����
	Class<? extends ActionListener> listener();  // ����������ֵ + ������()---ʹ�ô�ע�͵� ��򷽷��������listener��ֵ
	String test() default "";					   // ������---ʹ�ô�ע�͵���򷽷������Բ��ø�test��ֵ����Ϊ����һ��Ĭ��ֵ����

}