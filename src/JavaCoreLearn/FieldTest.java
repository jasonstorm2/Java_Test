package JavaCoreLearn;

import java.lang.reflect.Field;

class Person{
	private String name;
	private int age;
	public String toString(){
		return "Person[name:"+name+",age:"+age+"]";
	}
}
public class FieldTest {
	public static void main(String[] args) throws Exception {
		Person p = new Person();
		Class<Person> personClazz = Person.class;
		
		Field nameField = personClazz.getDeclaredField("name");
		
		System.out.println("nameField������"+nameField.getName());
		//���� ͨ��������ʸñ���ʱ ȡ������Ȩ�޼��
		nameField.setAccessible(true);
		//field.getXXX(Obj) ��ȡobj����� �ó�Ա������ xxx��Ӧ8�ֻ������ͣ�������������ͣ�XXX��ȡ��
		System.out.println("nameField��ֵ��"+nameField.get(p));
		nameField.set(p, "Yeeku");
		
		Field ageField = personClazz.getDeclaredField("age");
		ageField.setAccessible(true);
		//field.setXXX(Obj) ����obj����� �ó�Ա������ xxx��Ӧ8�ֻ������ͣ�������������ͣ�XXX��ȡ��
		ageField.set(p, 30);
		System.out.println(p);
		
		
	}

}
