import java.util.HashMap;

import myl.earth;
import myl.moon;



class A
{
	static{
		System.out.println("A����ľ�̬�����");
	}
    void aa()
    {
        System.out.println ("A���");    
        
    }    
    
}
class B extends A
{
    void aa()
    {
        System.out.println ("B���");    
        
    }    
    
}
class C extends A
{
    void aa()
    {
        System.out.println ("C���");    
        
    }    
    
}
//newInstance: �����͡���Ч�ʡ�ֻ�ܵ����޲ι��졣 
//new: ǿ���͡���Ը�Ч���ܵ����κ�public���졣

//�����һ�����ӣ�Class��������þ���ʵ���˶�̬�����࣬Ϊ��̬�ṩ�˺ܺõİ�����
//��̬���غʹ���Class ���󣬱���������û�������ַ�������������
public class ClassDemo
{
    
    public static void main(String[] args)
    {
    	
    	 HashMap<String, String> hashMap = new HashMap<String, String>();
 	    System.out.println("hashMap: " + hashMap);

    	    hashMap.put("1", "mqboss");
    	    hashMap.put("2", "Jboss");
    	    System.out.println("hashMap: " + hashMap);
    	    hashMap.clear();
    	    System.out.println("hashMap: " + hashMap);
        
//        C showc = new C();
//        showc.aa();
//        
//        ClassDemo t=new ClassDemo();
//        t.show("C");//��������
//        t.show("A");
//        t.show("B");
        
        
        A show2 = new A();
        show2.aa();
        A sh = new A();
        sh.aa();
        
        moon ww = new moon();
        earth.descripe("����İ�ֻ����Ĭ�ϵİ���ʹ���𣿣�����");
        
    }
    void show(String name)
    {
        try
        {
        	//Class.forName(xxx.xx.xx) ���ص���һ����, .newInstance() ��Ŵ���һ������ 
        	//Class.forName(xxx.xx.xx)��������Ҫ��JVM���Ҳ�����ָ�����࣬Ҳ����˵JVM��ִ�и���ľ�̬�����
             A show=(A)Class.forName(name).newInstance();
           
        show.aa();
    }
    catch(Exception e)
    {
        System.out.println (e);
        }
            
        
    }
    
    
}