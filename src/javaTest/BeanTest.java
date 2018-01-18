package javaTest;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.hdgf.streams.Stream;

import my.JavaBean;


/**
 * ��ʡ(Introspector) ��Java ���Զ� JavaBean �����ԡ��¼���һ��ȱʡ��������
 * Java JDK���ṩ��һ�� API ��������ĳ�����Ե� getter/setter �������������ʡ��
 * ��ЩAPI����ڰ�java.beans �С�
 * @author Administrator
 * 
 * һ���������ͨ����Introspector��getBeanInfo������ȡĳ�������BeanInfo ��Ϣ,
 * Ȼ��ͨ��BeanInfo����ȡ���Ե�������(PropertyDescriptor),
 * ͨ����������������Ϳ��Ի�ȡĳ�����Զ�Ӧ��getter/setter����,Ȼ�����ǾͿ���ͨ�����������������Щ������
 */

/*
*��	Introspector��:
	*	��JavaBean�е����Է�װ�������в������ڳ����һ���൱��JavaBean���������ǵ���Introspector.getBeanInfo()������
*	�õ���BeanInfo�����װ�˰�����൱��JavaBean���Ľ����Ϣ�������Ե���Ϣ��
	*	getPropertyDescriptors()��������Ե����������Բ��ñ���BeanInfo�ķ����������ҡ������������
*/

/*��PropertyDescriptor��:

	PropertyDescriptor���ʾJavaBean��ͨ���洢������һ�����ԡ���Ҫ������
	1. getPropertyType()��������Ե�Class����;
	2. getReadMethod()��������ڶ�ȡ����ֵ�ķ�����getWriteMethod()���������д������ֵ�ķ���;
	3. hashCode()����ȡ����Ĺ�ϣֵ;
	4. setReadMethod(Method readMethod)���������ڶ�ȡ����ֵ�ķ���;
	5. setWriteMethod(Method writeMethod)����������д������ֵ�ķ�����
* 
*/
public class BeanTest {
	public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		ABean ab = new ABean();
		ab.setCock("����");
		ab.setFrequence(new int[]{1,3,4});
		ab.setHowbig(20);
		ab.setNotget("û��get����");
		ab.notset = "û��set����";
		ab.field = "û��setget����";
		/**
		 * Introspector.getBeanInfo()��ʡ���õ�bean��������Ϣ
		 * BeanInfo.getMethodDescriptors(); ֻ�ܵõ�public���͵ķ�����protected��private�޷���ȡ���ڳ����һ���൱��JavaBean������beanû��˽���ࣩ
		 */
//		BeanInfo bi = Introspector.getBeanInfo(ab.getClass(),Object.class);//�õ�javabean����Ϣ���õ�javabean �����з�������ͳ�Ƹ���Object.class����13��
		BeanInfo bi = Introspector.getBeanInfo(ab.getClass(),JavaBean.class);//�õ�javabean �����з�������ͳ�Ƹ���javaBean.class����6��
//		BeanInfo bi = Introspector.getBeanInfo(ab.getClass());//�õ�javabean �����з�������������ķ������ܹ�22��
//		BeanInfo bi = Introspector.getBeanInfo(Object.class);
		
		//see how many methods the class "ab"have
		MethodDescriptor[] md = bi.getMethodDescriptors();//�õ�ABean��ķ���������������ABean�ķ����м��������п��Ի��Method����
		PropertyDescriptor[] pd = bi.getPropertyDescriptors();//���ֻ꣬Ҫ��һ��getter����setter��������һ������ֵ�ˡ�������������߶�û�У���ô��û������ֵ
		System.out.println("****PropertyDescriptor�ĸ�����"+pd.length);		
		System.out.println("class 'ab' has "+md.length+" Methods");
		//������������������������
		for(MethodDescriptor m : md){
			System.out.println("��������"+m.getName());				//��������getClass
			Method method = m.getMethod();							//ȡ��Method����Ϳ������÷�������������
			Class<?>[] paramtypes = method.getParameterTypes();//���ط����Ĳ������������
			
			if(paramtypes.length>0){
				System.out.println("�����в���");
				for(Class<?> p : paramtypes){
					System.out.println("������������ʾ�����͵�������֣�"+p.getSimpleName());
				}
			}else{
				System.out.println("�����޲���");
			}
			System.out.println("������������������������������������");
		}
		
		System.out.println("******************����PropertyDescriptor******************");

		for(PropertyDescriptor p : pd){
			System.out.println("Ԫ�ص����֣�"+p.getName());				//Ԫ�ص����֣�howbig
			System.out.println("Ԫ�ص����ͣ�"+p.getPropertyType());		//Ԫ�ص����ͣ�int
			if(p.getPropertyType().isArray()){
				if(p.getName().equals("frequence")){
					int[] ss = (int[]) p.getReadMethod().invoke(ab);	//�������Ԫ�ص�getter����
					System.out.print("Ԫ�ص�ֵ��");						//Ԫ�ص�ֵ    ��20
					for(int s :ss){
						System.out.print(s+",");
					}
					System.out.println();
				}
			}else{
				//notget����û��get�������˴��ᱨ��
				System.out.println("Ԫ�ص�ֵ--    ��"+p.getReadMethod().invoke(ab));
			}			
			System.out.println("&&&&&&&&&&&&&&");
		}
		
		//use the setter method
		for(PropertyDescriptor p : pd){
			if(p.getName().equals("cock")){
				p.getWriteMethod().invoke(ab,"dick");					//�������Ԫ�ص�setter����
			}else if(p.getName().equals("frequence")){
				p.getWriteMethod().invoke(ab,new int[]{11,22,33});
			}else if(p.getName().equals("howbig")){
				p.getWriteMethod().invoke(ab,50);
			}else if(p.getName().equals("notset")){
				p.getWriteMethod().invoke(ab,"ll"); //����û��set�������˴��ᱨ��
			}
			
			System.out.println(p.getName()+" �ı���ֵ��"+p.getReadMethod().invoke(ab));
		}
		//�����һ��СӦ��
		System.out.println("��setget�����������"+pd.length);
		Field[] fields = ab.getClass().getFields();
		System.out.println("bean�����������"+fields.length);			  //��ñ���͸��������е�field������
		System.out.println("*****�����������****");
		for(Field f : fields){
			System.out.println(f.getName());
		}
		System.out.println("*****�����������****");
		ab.isAlive = true;
		System.out.println("``````````````````");
		for(Field f : fields){			
			if(f.getName().equals("isAlive")){
				System.out.println("isAlive is exist!!");
				System.out.println("isAlive��ֵ�ǣ�"+f.get(ab));
			}
			if(f.getName().equals("cock")){
				System.out.println("cockjiji��ֵ�ǣ�"+f.get(ab));
			}
		}
		
	
	}
}

class ABean extends JavaBean{
	/***��setget����***/
	public int howbig;
	public String cock;
	public int[] frequence;
	/***û��setget����***/
	public String field;
	public boolean isAlive;
	
	public String notset;
	public String notget;
	
	public String getNotset(){
		return notset;
	}
	public void setNotget(String str) {
		this.notget = str;
	}
	
	
	public int getHowbig() {
		return howbig;
	}
	public void setHowbig(int howbig) {
		this.howbig = howbig;
	}
	public String getCock() {
		return cock;
	}
	public void setCock(String cock) {
		this.cock = cock;
	}
	public int[] getFrequence() {
		return frequence;
	}
	public void setFrequence(int[] frequence) {
		this.frequence = frequence;
	}	
	
	public void non(){
		
	}

}
