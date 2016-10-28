package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.Field;

public class ClassFielChageMethod {
	public static void resetField(Object obj) throws IllegalArgumentException, IllegalAccessException{
		
		/**
		 * ���䶯̬�ı���field
		 */
	    Class<? extends Object> cl = obj.getClass();
			
			for (Field f : cl.getDeclaredFields()) {
				//���ó�Ա�������óɿ����ɷ���			
				f.setAccessible(true);
				if(f.getType() == String.class ){
					f.set(obj, "Jessica");
				}			
				
				f.setAccessible(false);
		    }		
		}

}
