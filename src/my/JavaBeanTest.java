package my;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaBeanTest {
	public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JavaBean jb = new JavaBean();
		jb.setAge(18);
		jb.setName("�ž��");
		String[] childs=new String[]{"�յ�","�ζ���"};
		jb.setWtf(childs);
		
		BeanInfo bi=Introspector.getBeanInfo(jb.getClass(), Object.class);
//		BeanInfo bi=Introspector.getBeanInfo(jb.getClass(), jb.getClass().getSuperclass());
		//��ʼ��ʡ        
        
	    /*     
	    * BeanInfo.getMethodDescriptors()     
	    * ���ڻ�ȡ��Bean�е������������ĳ�Ա��������MethodDescriptor�������ʽ����     
	    *     
	    * MethodDescriptor��     
	    * ���ڼ���һ����Ա������������Ϣ     
	    * MethodDescriptor.getName()     
	    * ��ø÷����ķ�������     
	    * MethodDescriptor.getMethod()     
	    * ��ø÷����ķ�������Method�ࣩ     
	    *     
	    * Method��     
	    * ����һ������ĵķ�����������Ϣ     
	    * Method.getParameterTypes()     
	    * ��ø÷������õ������в�������Class�������ʽ����     
	    *     
	    * Class..getName()     
	    * ��ø����͵�����     
	    */		
		MethodDescriptor[] methodDescriptor = bi.getMethodDescriptors();
		System.out.println("bean�෽���ĸ�����"+methodDescriptor.length);
		for(MethodDescriptor m : methodDescriptor){
			//���һ����Ա����������������ķ���������
			String methodName = m.getName();
			//�@��ԓ�����Č���methodġ�����������
			Method methodObj = m.getMethod();			
			
			//ͨ�����������ø÷��������в�������Class�������ʽ����   
			Class[] paramters = methodObj.getParameterTypes();
			if(paramters.length>0){
				//��ò��������͵�����
				System.out.println("����=0�Ĳ���Class����:"+paramters[0].getName());
				for(int j=1;j<paramters.length;j++){
					System.out.println("����������>=1�Ĳ���Class���ͣ�"+paramters[j].getName());
				}
			}
			System.out.println("������methodName��"+methodName);
			System.out.println("��������������"+paramters.length);				
			System.out.println("***********************");
		}
		
		  /*     
		    * BeanInfo.getPropertyDescriptors()     ---����set��get�ķ�������Ȼ�����ڳ�Ա����
		    * ���ڻ�ȡ��Bean�е������������ĳ�Ա���ԣ���PropertyDescriptor�������ʽ����     
		    *     
		    * PropertyDescriptor��     
		    * ��������һ����Ա����     
		    *     
		    * PropertyDescriptor.getName()     
		    * ��ø����Ե�����     
		    *     
		    * PropertyDescriptor.getPropertyType()     
		    * ��ø����Ե��������ͣ���Class����ʽ����     
		    *     
		    */ 
		
	      /*     
		    * BeanInfo.getEventSetDescriptors()     
		    * ���ڻ�ȡ��Bean�е������������ĳ�Ա�¼�����EventSetDescriptor�������ʽ����     
		    *     
		    * EventSetDescriptor��     
		    * ��������һ����Ա�¼�     
		    *     
		    * EventSetDescriptor.getName()     
		    * ��ø��¼�������     
		    *     
		    * EventSetDescriptor.getListenerType()     
		    * ��ø��¼����������¼�����������Class����ʽ����     
		    *     
		    */ 
		PropertyDescriptor[] pd=bi.getPropertyDescriptors();
		
		 System.out.println("pd.length="+pd.length);
		  //��ʾ���get
	      for (int i = 0; i < pd.length; i++) {
	    	  
	         if(pd[i].getPropertyType().isArray())  //getPropertyType�õ��������͡�
	         {
	            //getReadMethod()�õ������Ե�get����----Method����Ȼ����invoke�����������
	            String[] result=(String[]) pd[i].getReadMethod().invoke(jb);
	            System.out.println("String[]�������Ե����֣�"+pd[i].getName()+":");//getName�õ���������
	            for (int j = 0; j < result.length; j++) {
	               System.out.println("String[]�����Ԫ�أ�"+result[j]);
	            }
	         }
	         else
	         {
	            System.out.println(pd[i].getName()+":"+pd[i].getReadMethod().invoke(jb));
	            System.out.println("3");
	         }
	         System.out.println("������������������������������������������������������������");
	      }
	      for(PropertyDescriptor p :pd){
	    	  System.out.println("���Ե�����.getName():"+p.getName());
	    	  System.out.println("���Ե�����.getPropertyType():"+p.getPropertyType());
	    	  System.out.println("���Ե�ֵ������invoke:"+p.getReadMethod().invoke(jb));
	    	  
	    	  System.out.println("---------------------------");
	    	   
	      }
	      
	      //set�đ���
	      JavaBean pb0=new JavaBean();
	      //ģ��һ�����ݣ��������ֺ�javabean��������һ�£�
	      String name="luonan";
	      int age = 19;
	      String[] childname=new String[]{"xing","xian"};
	 
	      BeanInfo bi0=Introspector.getBeanInfo(pb0.getClass(), Object.class);
	      PropertyDescriptor[] pd0=bi0.getPropertyDescriptors();
	 
	      for (int i = 0; i < pd0.length; i++) {
	         if(pd0[i].getPropertyType().isArray()){
	            if(pd0[i].getName().equals("wtf")){ 
	            if(pd0[i].getPropertyType().getComponentType().equals(String.class))
	               {//getComponentType()���Եõ��������͵�Ԫ������
	                  //getWriteMethod()�õ������Ե�set����---Method����Ȼ����invoke�����������
	                  pd0[i].getWriteMethod().invoke(pb0,new Object[]{childname});
	               }
	            }
	         }
	         else{
	            if(pd0[i].getName().equals("name"))
	            {
	               pd0[i].getWriteMethod().invoke(pb0,name);
	            }
	            if(pd0[i].getName().equals("age")){
	            	 pd0[i].getWriteMethod().invoke(pb0,age);
	            }
	         }
	      }
	 
	      System.out.println("the name:"+pb0.getName());
	      String[] array=pb0.getWtf();
	      for (int i = 0; i < array.length; i++) {
	         System.out.println(array[i]);
	      }
	      System.out.println("the age:"+pb0.getAge());

	}
}
