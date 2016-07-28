package JavaCoreLearn;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

class Test{
	public void replace(String str,List<String> list,Integer...i){	//�ɱ��������βΣ�ʵ���������飡��������÷���ʱ����Կ���
		
	}
}
public class MethodParameterTest {
	public static void main(String[] args) throws Exception {
		//��ȡ String ����
//		Class<Test> clazz = Test.class;
		Class<?> clazz = Class.forName("JavaCoreLearn.Test");

		System.out.println(clazz.getName());
		//��ȡ String ��Ĵ����������� replace()����
		Method re = clazz.getMethod("replace", String.class,List.class,Integer[].class);
		System.out.println("�����ڣ��Ƿ��пɱ��βΣ�"+re.isVarArgs());
		
		//��ȡ ָ�������� ��������
		System.out.println("replace��������������"+re.getParameterCount()+"\n");
		//��ȡ ָ�������� ���η�
		System.out.println("replace�������η���"+re.getModifiers()+"\n");
		//��ȡ re�����в�����Ϣ
		Parameter[] parameters = re.getParameters();
		int index = 1;
		for (Parameter parameter : parameters) {
			if(parameter.isNamePresent()){    //����Ĭ�����ɵ�.class�ļ����������� �β�������Ϣ
				System.out.println("��"+index+"��������Ϣ��");
				System.out.println("��������"+parameter.getName());
				System.out.println("�������ͣ�"+parameter.getType());
				System.out.println("�������ͣ�"+parameter.getParameterizedType());				
			}
			System.out.println("�Ƿ��ǿɱ��βΣ�"+parameter.isVarArgs());
			System.out.println("��"+index+"��������Ϣ��");
			System.out.println("��������"+parameter.getName());
			System.out.println("�������ͣ�"+parameter.getType());
			System.out.println("�������ͣ�"+parameter.getParameterizedType());		
			
		}		
	}
}
