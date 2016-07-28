import java.util.HashMap;

import myl.earth;
import myl.moon;



class A
{
	static{
		System.out.println("A里面的静态代码块");
	}
    void aa()
    {
        System.out.println ("A里的");    
        
    }    
    
}
class B extends A
{
    void aa()
    {
        System.out.println ("B里的");    
        
    }    
    
}
class C extends A
{
    void aa()
    {
        System.out.println ("C里的");    
        
    }    
    
}
//newInstance: 弱类型。低效率。只能调用无参构造。 
//new: 强类型。相对高效。能调用任何public构造。

//下面给一个例子：Class的最大作用就是实现了动态加载类，为多态提供了很好的帮助。
//动态加载和创建Class 对象，比如想根据用户输入的字符串来创建对象
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
//        t.show("C");//程序类名
//        t.show("A");
//        t.show("B");
        
        
        A show2 = new A();
        show2.aa();
        A sh = new A();
        sh.aa();
        
        moon ww = new moon();
        earth.descripe("导入的包只能在默认的包中使用吗？？？？");
        
    }
    void show(String name)
    {
        try
        {
        	//Class.forName(xxx.xx.xx) 返回的是一个类, .newInstance() 后才创建一个对象 
        	//Class.forName(xxx.xx.xx)的作用是要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段
             A show=(A)Class.forName(name).newInstance();
           
        show.aa();
    }
    catch(Exception e)
    {
        System.out.println (e);
        }
            
        
    }
    
    
}