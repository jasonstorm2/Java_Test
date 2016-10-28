package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.Array;
import java.util.Date;

public class GenericTypeFactory {
	
	//ԭʼ����
	/*
	 * ͨ������������õ�������ڷ���õ���ʵ��
	 */
	public static Object getInstance(String clsName){
		try{
			Class cls = Class.forName(clsName);
			return cls.newInstance();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//ԭʼ����
	/*
	 * ͨ������������õ�������ڷ���õ���ʵ��
	 */
	public static Object getInstance2(String clsName){
		try{
			Class<?> cls = Class.forName(clsName);
			return cls.newInstance();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//���뷺��
	/*
	 * ֱ��ͨ�� ����󣬷���õ���ʵ��
	 */
	public static <F> F getInstance3(Class<F> cls){
		try{			
			return cls.newInstance();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//�� Array �� newInstance�������а�װ
	@SuppressWarnings("unchecked")
	public static <T> T[] newInstance(Class<T> componentType,int length){
		
		return(T[])Array.newInstance(componentType, length);
		
	}
	public static void main(String[] args) {
		Date d = (Date) GenericTypeFactory.getInstance("java.util.Date");
		Date d2 = GenericTypeFactory.getInstance3(Date.class);
		
		//ʹ��newInstance��̬���� ���� һά����
		String[] arr = GenericTypeFactory.newInstance(String.class, 10);
		//ʹ��newInstance��̬���� ���� ��ά����
		int[][] intArr = GenericTypeFactory.newInstance(int[].class, 5);
		
		arr[5] = "���Բ���";
		
		intArr[1] = new int[]{23,12	};//��ά�����Ԫ�� ������ һά����
		
		System.out.println(arr[5]);
		System.out.println(intArr[1][1]);
		System.out.println(arr[4]);
		
	}

}
