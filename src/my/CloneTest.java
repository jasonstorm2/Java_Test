package my;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ��
 *  1.Ϊ�˻�ȡ�����һ�ݿ��������ǿ�������Object���clone()������

����2.���������и��ǻ����clone()������������Ϊpublic��

������Object���е�clone()������protected�ģ���

������������д��ʱ�򣬿�������������η��ķ�Χ��

����3.���������clone()�����У�����super.clone()��

������Ϊ������ʱ�̣�Object���е�clone()ʶ�����Ҫ���Ƶ�����һ������Ȼ��Ϊ�˶������ռ䣬�����ж���ĸ��ƣ�
    ��ԭʼ���������һһ���Ƶ��¶���Ĵ洢�ռ��С�

����4.����������ʵ��Cloneable�ӿڡ�

��������ӿ���û��ʲô������ֻ��˵�����á�

����ע�⣺�̳���java.lang.Object���clone()������ǳ���ơ�

    java��clone()����::
    
����clone()����������Object���С�

����clone()��������������һ�ݲ����ظ������ߡ���������ĺ��彫ȡ���ڶ������ڵ��ࡣ

����һ����ԣ�clone()�������㣺

����1. ��¡������ԭ������ͬһ�����󡣼����κεĶ���x��

����x.clone() != x

����2.��¡������ԭ���������һ���������κεĶ���x��

����x.clone().getClass() == x.getClass()

����3.�������x��equals()��������ǡ������ô��ʽӦ�ó�����

����x.clone().equals(x)

������Ϊһ���������õ�equals()������Ӧ���������Ƚ������Ƿ���ȵġ�

    �������л�ʵ�����
�������������еķ���ʵ����ƱȽ��鷳��

�����������һ��ȫ�µķ������������л�������ơ�

�����Ѷ���д������Ĺ��������л����̣�Serialization�������Ѷ�������ж������Ĺ�������������л����̣�Deserialization����

����Ӧ��ָ�����ǣ�д��������Ƕ����һ����������ԭ������Ȼ������JVM���档

������Java���������һ�����󣬳���������ʹ����ʵ��Serializable�ӿڣ�Ȼ��Ѷ���ʵ����ֻ�Ƕ����һ��������д��һ������ٴ������������������ؽ�����

������������ǰ���Ƕ����Լ������ڲ��������õ��Ķ����ǿɴ��л��ģ����򣬾���Ҫ��ϸ������Щ���ɴ��л��Ķ���ɷ����transient���Ӷ������ų��ڸ��ƹ���֮�⡣

����ע��Cloneable��Serializable�ӿڶ���marker Interface��Ҳ����˵����ֻ�Ǳ�ʶ�ӿڣ�û�ж����κη���
 * @author LiZhenhua
 *
 */
public class CloneTest {
	public static void main(String[] args) throws Exception {
//		CloneTest.clone1();//��ͨ����--��������
//		CloneTest.clone2();//ǳ����--ֻ����ͨ���������ö��󲻿���
//		CloneTest.clone3();//���--����clone������¡���õĶ���--�Ƚ��鷳
		CloneTest.clone4();//���--�������л�����--�ȽϷ���
	}
	
	/**
	 * ˵����������student1��student2ָ����ǲ�ͬ�Ķ���
	 * @throws CloneNotSupportedException
	 */
	public static void clone1() throws CloneNotSupportedException{
		Student1 student1 = new Student1();
        student1.setName("ZhangSan");
        student1.setAge(20);

        Student1 student2 = new Student1();
        student2 = (Student1) student1.clone();

        System.out.println("�����õ�����Ϣ");
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println("-------------");

        // �޸ĵڶ����������Ϣ
        student2.setName("LiSi");
        student2.setAge(25);

        System.out.println("�޸ĵڶ������������Ϊlisi,25��");
        System.out.println("��һ������");
        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println("�ڶ�������");
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println("-------------");
        
        // ˵����������student1��student2ָ����ǲ�ͬ�Ķ���
	}
	
	/**
	 * ǳ���������������еĶ������ò�û�иı䡣����Ϳ������� �еĶ������ã���ͬһ������
	 * @throws CloneNotSupportedException
	 */
	public static void clone2() throws CloneNotSupportedException{
		Teacher teacher = new Teacher();
        teacher.setName("Teacher Zhang");
        teacher.setAge(40);

        Student3 student3 = new Student3();
        student3.setName("ZhangSan");
        student3.setAge(20);
        student3.setTeacher(teacher);

        Student3 student4 = (Student3) student3.clone();
        System.out.println("�����õ�����Ϣ");
        System.out.println(student4.getName());
        System.out.println(student4.getAge());
        System.out.println(student4.getTeacher().getName());
        System.out.println(student4.getTeacher().getAge());
        System.out.println("-------------");

        // �޸���ʦ����Ϣ
        teacher.setName("Teacher Zhang has changed");
        System.out.println(student3.getTeacher().getName());
        System.out.println(student4.getTeacher().getName());
        
        System.out.println(student3==student4);
        System.out.println(student3.equals(student4));

        // ��������student1��student2ָ��ͬ����������
        // ������������student1��student2�е�����teacher����ָ�����ͬһ������
        // ����˵����ǳ����--Object���clone()�������е���ǳ������
	}
	
	/**
	 * �������дclone����������Щ��������ÿ���һ�飬Ȼ��ֵ
	 * @throws CloneNotSupportedException
	 */
	public static void clone3() throws CloneNotSupportedException{
        Teacher1 teacher = new Teacher1();
        teacher.setName("Teacher Zhang");
        teacher.setAge(40);

        Student4 student4 = new Student4();
        student4.setName("ZhangSan");
        student4.setAge(20);
        student4.setTeacher(teacher);

        Student4 student2 = (Student4) student4.clone();
        System.out.println("�����õ�����Ϣ");
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getTeacher().getName());
        System.out.println(student2.getTeacher().getAge());
        System.out.println("-------------");

        // �޸���ʦ����Ϣ
        teacher.setName("Teacher Zhang has changed");
        System.out.println(student4.getTeacher().getName());
        System.out.println(student2.getTeacher().getName());

        // ��������student1��student2ָ��ͬ����������
        // ������������student1��student2�е�����teacher����ָ�����ͬһ������
        // ����˵����ǳ����

        // ��Ϊ���֮�󣬶�teacher������޸�ֻ��Ӱ���һ������
	}
	
	/**
	 * �������л� ���������
	 * ���󣬺Ͷ����е���������ʵ����Serializable�ӿ�
	 * @throws Exception
	 */
	public static void clone4() throws Exception  {
		    Teacher2 t = new Teacher2();
	        t.setName("Teacher Wang");
	        t.setAge(50);

	        Student5 s1 = new Student5();
	        s1.setAge(20);
	        s1.setName("ZhangSan");
	        s1.setTeacher(t);

	        Student5 s2 = (Student5) s1.deepClone();

	        System.out.println("�����õ�����Ϣ:");
	        System.out.println(s2.getName());
	        System.out.println(s2.getAge());
	        System.out.println(s2.getTeacher().getName());
	        System.out.println(s2.getTeacher().getAge());
	        System.out.println("---------------------------");

	        // �����ƺ�Ķ������ʦ��Ϣ�޸�һ�£�
	        System.out.println("�޸Ŀ���������������");

	        s2.getTeacher().setName("New Teacher Wang");
	        s2.getTeacher().setAge(28);

	        System.out.println("��������Ľ�ʦ��");
	        System.out.println(s2.getTeacher().getName());
	        System.out.println(s2.getTeacher().getAge());
	        System.out.println("ԭ������Ľ�ʦ��");
	        System.out.println(s1.getTeacher().getName());
	        System.out.println(s1.getTeacher().getAge());

	        // �ɴ�֤�����л��ķ�ʽʵ���˶�������
		
	}

}

class Student1 implements Cloneable
{
    private String name;
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        // ע��˴�Ҫ��protected��Ϊpublic

        Object object = super.clone();//Object ��clone()������protected��

        return object;
    }
}

class Student3 implements Cloneable
{
    private String name;
    private int age;
    private Teacher teacher;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
    
    public Teacher getTeacher()
    {
        return teacher;
    }

    public void setTeacher(Teacher teacher)
    {
        this.teacher = teacher;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        // ע��˴�Ҫ��protected��Ϊpublic

        Object object = super.clone();//Object ��clone()������protected��

        return object;
    }
}

class Teacher implements Cloneable
{
    private String name;
    private int age;  
   

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

}

class Teacher1 implements Cloneable , Externalizable
{
    private String name;
    private int age;
    public Teacher1(String name,int age){
    	this.name = name;
    	this.age = age;
    	
    } 
    public Teacher1(){
    	
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeObject(name);
		out.writeObject(age);
		
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		name = (String)in.readObject();
		age = (int)in.readObject();
		
	}

}

class Teacher2 implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

}

class Student4 implements Cloneable
{
    private String name;
    private int age;
    private Teacher1 teacher;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Teacher1 getTeacher()
    {
        return teacher;
    }

    public void setTeacher(Teacher1 teacher)
    {
        this.teacher = teacher;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        // ǳ����ʱ��
        // Object object = super.clone();
        // return object;

        // ��Ϊ��ƣ�
        Student4 student = (Student4) super.clone();
        // ������ǳ���ƣ����ڽ�Teacher������һ�ݲ�����set����
        student.setTeacher((Teacher1) student.getTeacher().clone());
        return student;
    }
}


/**
 * ������дclone������дһ���µķ���deepClone()�Ϳ���
 * @author LiZhenhua
 * 
 * serialVersionUID����
������һ����ʵ����Serializable�ӿ�ʱ����������ɱ����л������ʱ��Eclipse�����һ�����棬Ҫ����Ϊ���ඨ��һ���ֶΣ����ֶ�����ΪserialVersionUID������Ϊlong����ʾ��Ϣ���£�

����The serializable class Teacher3 does not declare a static final serialVersionUID field of type long�� 

������Eclipse�����������ɷ�ʽ��

����һ����Ĭ�ϵ�1L��

����private static final long serialVersionUID = 1L;

����һ���Ǹ����������ӿ�������Ա���������Ե�������һ��64λ�Ĺ�ϣ�ֶΣ����磺

����private static final long serialVersionUID = -932183802511122207L;

���������û�п��ǵ������Ե����⣬�Ͱ����ص�����������������Ǻõģ�ֻҪ�κ����ʵ����Serializable�ӿڣ����û�м���serialVersionUID��Eclipse���������ʾ�����serialVersionUIDΪ���ø����Serializable�����ݡ�

���������Ķ������л���浽Ӳ���������ȴ���������field�����ӻ���ٻ�����������㷴���л�ʱ���ͻ�����쳣�������ͻ���ɲ������Ե����⡣

��������serialVersionUID��ͬʱ�����ͻὫ��һ����field��type��ȱʡֵDeserialize��������Աܿ��������Ե�����
 *
 */
class Student5 implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6257547865699167078L;
	private String name;
    private int age;
    private Teacher2 teacher;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Teacher2 getTeacher()
    {
        return teacher;
    }

    public void setTeacher(Teacher2 teacher)
    {
        this.teacher = teacher;
    }

    /**
     * ������������� �� �ֽ�������������� �Ľ������
     * 
     * ����д���ڴ棬���൱�����һ����
     * @return
     * @throws Exception
     */
    public Object deepClone() throws Exception
    {
    	
    	/**
    	 * ע����FileOutputStream(String path) �� FileInputStream(String path)������
    	 * FileOutputStream��Ҫһ����ַ�� ��źͶ�ȡ
    	 * ��ByteArrayOutputStream ��ֱ��д���ڴ棿ϵͳ�Զ�������
    	 */
    	
        // ���л�
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);//��װ��ObjectOutputStream,�������Object����

        oos.writeObject(this);//д�������󣨶�����ֽ���Ŷ�������ڴ�
        System.out.println("�ֽ�������������ݣ�"+bos.toString());

        // �����л�
        //ע������Ҫ�ṩһ��byte������Ϊ��������
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());//���ڴ��ж�ȡ�����ֽ����飬���ֽ������ȡ
        ObjectInputStream ois = new ObjectInputStream(bis);//���ֽ���ת���ɣ���װ�ɣ�����

        return ois.readObject();
    }

}
