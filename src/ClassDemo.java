import java.util.HashMap;

import myl.earth;
import myl.moon;



class AFather
{
	static{
		System.out.println("A����ľ�̬�����");
	}
    void aa()
    {
        System.out.println ("A���");    
        
    }    
    
}
class B extends AFather
{
    void aa()
    {
        System.out.println ("B���");    
        
    }    
    
}
class C extends AFather
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
    	ClassDemo t1=new ClassDemo();
        t1.show("C");//��������
        System.out.println("-----------��̬����--------------");
        C showc = new C();
        showc.aa();  //����̳��˸��࣬��ʵ�ָ���
        System.out.println("-------------------------");
        
        ClassDemo t=new ClassDemo();
        t.show("C");//��������
        t.show("AFather");
        t.show("B");
        
        System.out.println("-------------------------");
        AFather show2 = new AFather();
        show2.aa();
        AFather sh = new AFather();
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
             AFather show=(AFather)Class.forName(name).newInstance();
           
        show.aa();
    }
    catch(Exception e)
    {
        System.out.println (e);
        }
            
        
    }
    
    
}