package javassistTest;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

/**
 * Javassist��һ����̬��⣬����������顢����̬���޸��Լ����� Java�ࡣ�书����jdk�Դ��ķ��书�����ƣ����ȷ��书�ܸ�ǿ��
 * 
 * @author LiZhenhua
 * 
 *         ��Ҫ���� ClassPool��javassist����أ�ʹ��ClassPool ����Ը��ٺͿ�������������,���Ĺ�����ʽ�� JVM
 *         ��װ�����ǳ����ƣ� CtClass��
 *         CtClass�ṩ�˼�������ݣ����ֶκͷ������Լ�������������ֶΡ������͹��캯�����Լ��ı��ࡢ����ͽӿڵķ�����
 *         ������Javassist ��δ�ṩɾ�������ֶΡ��������߹��캯�����κη����� CtField������������ CtMethod ���������ʷ���
 *         CtConstructor���������ʹ�����
 * 
 * 
 *         �����������
 *         1.��Ҫע����ǣ��ڵ���ctClass.toClass()ʱ������ش��࣬���������֮ǰ�Ѿ������ع�����ᱨһ��duplicate
 *         load�Ĵ��󣬱�ʾ�����ظ�����һ���ࡣ���ԣ��޸ķ�����ʵ�ֱ������޸ĵ������֮ǰ���С�
 *         2.���ܷ��ʿ�֮��ľֲ������������һ�������Ŀ�ʼ�ͽ�β�������˴����
 *         ����ô�ڷ����Ľ�β�����޷����ʷ�����ʼ�еĴ�����еı���
 *         ����̫�����Ľ�������ǽ�ԭ����������Ȼ����������ԭ����ͬ���ķ�������
 *
 */
public class classOne {
	
	public  void method1(){
		System.out.println("this is method1");
		
	}

	public static void main(String[] args) throws Exception {	
		replaceMethodBody("javassistTest.classTwo", "method1", "System.out.println(\"this method is changed dynamically!\");");
		new classTwo().method1();		
		createNewClass("classThree", "method1");
	}
	
	/**
	 * ��̬�޸���ķ�����
	 * @param clazzName
	 * @param methodName
	 * @param newMethodBody
	 * @throws IOException 
	 */
	public static void replaceMethodBody(String clazzName, String methodName, String newMethodBody) throws IOException {
	    try {
	    	//���classpath�в�ѯ����
	        CtClass clazz = ClassPool.getDefault().get(clazzName);
	        CtMethod method = clazz.getDeclaredMethod(methodName);
	        method.setBody(newMethodBody);
	        //���������class �࣬Ĭ�ϼ��ص���ǰ�̵߳�ClassLoader�У�Ҳ����ѡ�������ClassLoader
	        clazz.toClass();
	        //���clazzName.class�ļ�����Ŀ¼��
	        clazz.writeFile("d://javassist");
	        //����ɶ����Ƹ�ʽ
	        //byte[] b=clazz.toBytecode();
	    } catch (NotFoundException | CannotCompileException e) {
	       throw new RuntimeException(e);
	    }
	}
	
	public static void createNewClass(String className,String methodName) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass(className);
		// ��������
		CtMethod m = CtNewMethod.make("public int xmove(int dx) { x += dx; }",cc);
		cc.addMethod(m);
		// ����Field
//		cc.addField(f);

	}
	
	
	
	
	
	
	
	
	

}
