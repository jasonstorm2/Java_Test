package my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * JSON�����и��ְ�����Ҫ�����֣�
 *  
 * 1.json-lib 
 * 
 * json-lib�ʼ��Ҳ��Ӧ����㷺��json�������ߣ�json-lib
 * ���õĵط�ȷʵ�������ںܶ����������
 * ����commons-beanutils.jar��commons-collections-3.2.jar��commons
 * -lang-2.6.jar��commons-logging-1.1.1.jar��ezmorph-1.0.6.jar��
 * ���ڸ������͵�ת����json-lib����jsonת����bean����ȱ��
 * ������һ��������������һ�����list����map���ϣ�json-lib��json��bean��ת���ͻ�������⡣
 * json-lib�ڹ��ܺ��������涼�����������ڻ������������� 
 * 
 * 2.��Դ��Jackson
 * 
 * ���json-lib��ܣ�Jackson��������jar�����٣������ò�������ҲҪ��Ը�Щ�� ����Jackson������ԱȽϻ�Ծ�������ٶ�Ҳ�ȽϿ졣
 * Jackson���ڸ������͵�jsonת��bean��������⣬һЩ����Map��List��ת���������⡣
 * Jackson���ڸ������͵�beanת��Json��ת����json��ʽ���Ǳ�׼��Json��ʽ 
 * 
 * 3.Google��Gson
 * 
 * Gson��Ŀǰ������ȫ��Json����������Gson������Ϊ��ӦGoogle��˾�ڲ��������Google�����з�������
 * ���Դ���2008�����¹���������һ����ѱ���๫˾���û�Ӧ�á�
 * Gson��Ӧ����ҪΪtoJson��fromJson����ת��������������������Ҫ��������jar���ܹ�ֱ������JDK�ϡ�
 * ����ʹ�����ֶ���ת��֮ǰ���ȴ����ö���������Լ����Ա���ܳɹ��Ľ�JSON�ַ����ɹ�ת�������Ӧ�Ķ���
 * ������ֻҪ��get��set������Gson��ȫ���Խ��������͵�json��bean��bean��json��ת������JSON������������
 * Gson�ڹ��������޿����ޣ��������������FastJson������ࡣ 
 * 
 * 4.����Ͱ͵�FastJson
 * 
 * Fastjson��һ��Java���Ա�д�ĸ����ܵ�JSON������,�ɰ���Ͱ͹�˾������ ������������Ҫ��������jar���ܹ�ֱ������JDK�ϡ�
 * FastJson�ڸ������͵�Beanת��Json�ϻ����һЩ���⣬���ܻ�������õ����ͣ�����Jsonת��������Ҫ�ƶ����á�
 * FastJson���ö������㷨����parse���ٶ����������£���������json�⡣
 * 
 * @author Administrator
 *
 */
public class JSONToBeanTest {
	public static void main(String[] args) {
		String json = "{id:'1001',name:'����',age:'22'}";
		Student stu = JSONToBeanTest.ToBeanTest(json);
		System.out.println(stu.getAge());
		
	}	
	
	public static Student ToBeanTest(String json){
		Student stu = new Student();
		return stu = (Student) JSON.parseObject(json, new TypeReference<Student>() {});	      
	}
	public static class Student{
		private int id ;
	    private String name;
	    private int age;
	    
	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public int getAge() {
	        return age;
	    }
	    public void setAge(int age) {
	        this.age = age;
	    }
	    
	    public String toString(){
	        return this.id + ", " + this.name + ", " + this.age;
	    }
		
	}

}


