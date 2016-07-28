package Annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Administrator
 * �Զ����һ��annotationע�ͣ�
 * ǰ������ʹ�õ���ϵͳ��ע�͡�
 * ��һ��˵�������ɵ�ע�ͻ���javadoc�ĵ���
 * �ڶ���˵�������ɵ�ע�� Ӧ������Ҳ���ǳ�Ա����������data member��
 * ������˵������Դ�롢����õ�.class�ļ��б�����Ϣ����ִ�е�ʱ������һЩ��Ϣ���ص�JVM��ȥ��
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldUse {
	String  value();
	String  theOther();

}
