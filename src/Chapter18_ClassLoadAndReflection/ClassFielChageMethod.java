package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.Field;

public class ClassFielChageMethod {
	/**
	 * ���䶯̬�ı���field
	 */
	public static void resetField(Object obj) throws IllegalArgumentException, IllegalAccessException{		

	    Class<? extends Object> cl = obj.getClass();
			
			for (Field f : cl.getDeclaredFields()) {
				//���ó�Ա�������óɿ����ɷ���			
				f.setAccessible(true);
				if(f.getType() == String.class ){//�޸��ƶ����͵�����
					f.set(obj, "Jessica");
				}			
				
				f.setAccessible(false);
		    }		
		}

}
