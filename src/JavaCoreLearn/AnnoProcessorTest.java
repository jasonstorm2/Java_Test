package JavaCoreLearn;

import java.lang.reflect.Method;

/**
 * ͨ�����䣬��ѯһ�����м��Զ���ע�͵ķ���
 * ��ͨ������ִ�и÷���
 *  
 * Class.forName(clazz)��·���Ǳ���Ŀ�е����·��������.����
 * 
 * @author Administrator
 *
 */
public class AnnoProcessorTest {
	public static void process(String clazz)throws ClassNotFoundException{
		int passed = 0;
		int failed = 0;
		Class<?> cl = Class.forName(clazz);
		try {
			Object obj = cl.newInstance();
			for(Method m : Class.forName(clazz).getMethods()){
				if(m.isAnnotationPresent(AnnoTestable.class)){
					try {
//						m.invoke(null, null); //FIXME ����ʹ������ķ���
						m.invoke(obj);
						
						passed ++;
					} catch (Exception e) {
						failed ++;
						e.printStackTrace();
					} 
					
				}
				
			}
			
			
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}//ͨ�������ʵ����
		
		
		System.out.println("�������ˣ�"+(passed+failed)+"�����������У�\n"+"ʧ���ˣ�"+failed+"����\n"+"�ɹ���:"+passed+"����");
		
	}

}
