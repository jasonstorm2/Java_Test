package LambdaLearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.hssf.record.formula.functions.T;


public class implementsMyFunction implements MyFunction<String>{
	
    static Map<Integer, Integer> cache = new ConcurrentHashMap<>();      


	public static void main(String[] args) {
		implementsMyFunction thisClass =  new implementsMyFunction();
		
		

        cache.put(0, 0);  
        cache.put(1, 1);  
        implementsMyFunction.fibonacciJava8(8);
        System.out.println("��û�����8��ֵ��"+cache.get(8));
        
        MyFunction<Integer> myFuc = (Integer a)->a+1;
        int rest = myFuc.oneMethod(4);
        System.out.println("ʹ���Զ���ĺ���ʽ�ӿڣ�"+rest);
        
		// ��ʼһ���̣߳�ʹ��lambda����
		new Thread(()->{System.out.println("��ʼһ���̣߳�ʹ��lambda����");}, "lambda�߳�").start();
		
		// ����Ķ�����һ��myFunction���󣩵�������ķ������÷�����Ҫһ��myFunction�ӿڵ�ʵ�ֶ���
		//--�˴����������ڲ����ʵ��
		thisClass.useFunc(a->{
			System.out.println("���ö����к��к���ʽ�ӿڲ����ķ���");
			return "long";
		});	
		thisClass.useFunc(thisClass::oneMethod);
//
		thisClass.useFunc(thisClass);
		thisClass.useFunc(a->"mess");
		thisClass.useFunc(a->{return "mess";});
		
		useFunc2(thisClass);
		System.out.println(thisClass.oneMethod("��㷵��"));
		thisClass.oneMethod("suibian");
		
		List<String> list = Arrays.asList("ab","lady","amine","count");
		List<String> list2 =  Stream.of("ab","lady","amine","count").collect(Collectors.toList());
		
		List<String> collected = new ArrayList<>();
		for (String string : Arrays.asList("a", "b", "hello")) {
		String uppercaseString = string.toUpperCase();
		collected.add(uppercaseString);
		}
		
		
		List<String> collected2 = Stream.of("a", "b", "hello")
				.map(string -> string.toUpperCase()).collect(Collectors.toList());
		
		List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
				.filter(value -> true).collect(Collectors.toList());
		
		long res = list.stream().filter((a)->true).count();
		
//		// forEach��������Ҫһ����������Consumer<? super String> action��Consumer��һ������ʽ�ӿڣ��и����󷽷�accept(T t)
//		list.forEach((l)->{
//			System.out.println(l);
//		});
		// �˴��е���죬System.out::println��һ��Consumer������
		list.forEach(System.out::println);
//		thisClass.useFunc({implementsMyFunction::testMethod});
//		implementsMyFunction ss = implementsMyFunction::thisMethod;
		
	}
	
	public void useFunc(MyFunction<String> mm){
		
	}
	public static void useFunc2(MyFunction<String> mm){
		mm.oneMethod("����ʹ�ò����ķ���");
	}

	@Override
	public String oneMethod(String t) {
		// TODO Auto-generated method stub
		String str = "override oneMethod";
		System.out.println("��д����ʽ�ӿڵĳ��󷽷�oneMethod,������" + t);
		return str;
	}
	
	public  static void filter(List<String> str,Predicate pr){
		for(String s : str){
//			if(){
//				
//			}
		}
		
	}
	
	public static implementsMyFunction thisMethod(String str){
		implementsMyFunction ss = new implementsMyFunction();
		return ss;
		
	}
	
    /**  
     * ����java8�ı��ػ��淽ʽ �������MAP�в�����ָ��key��ֵ�����Զ�����mappingFunction(key)����key��value  
     * Ȼ��key = value���뵽����Map,java8��ʹ��thread-safe�ķ�ʽ��cache�д�ȡ��¼  
     *   
     * @param n  
     * @return  
     */ 
	static int fibonacciJava8(int n) {
		return cache.computeIfAbsent(n, (key) -> {
			System.out.println("calculating FibonacciJava8 " + n);
			return fibonacciJava8(n - 2) + fibonacciJava8(n - 1);
		});
	}
    /**  
     * ��java7�е�ʵ�ַ�ʽ  
     * ��java7�У�ͨ��synchronized�����߳�ͬ������黺���Ƿ����key��Ӧ��ֵ����������ڲŽ��м��㲢���뻺����  
     * Ϊ�˸��õ����ܣ���Ҫʹ�� double-checked locking����������������  
     *   
     * @param n  
     * @return  
     */ 
    static int fibonacciJava7(int n) {  
        if (n == 0 || n == 1)  
            return n;  
 
        Integer result = cache.get(n);  
 
        if (result == null) {  
            synchronized (cache) {  
                result = cache.get(n);  
 
                if (result == null) {  
                    System.out.println("calculating FibonacciJava7(" + n + ")");  
                    result = fibonacciJava7(n - 2) + fibonacciJava7(n - 1);  
                    cache.put(n, result);  
                }  
            }  
        }  
        return result;  
    }
    
    /**  
     * ��ͨ��ʵ�ַ�ʽ ��ͨ��ʽʹ�ô����ļ��㣬������������. ���Ҽ���������n�����ӳ�ָ�������ӣ���Ҫ�õ�һЩ������ԣ��������̰߳�ȫ��.  
     *   
     * @param n  
     * @return  
     */ 
    static int fibonacci(int n) {  
        if (n == 0 || n == 1)  
            return n;  
 
        System.out.println("calculating Fibonacci(" + n + ")");  
        return fibonacci(n - 2) + fibonacci(n - 1);  
    }  
    
    public <R> implementsMyFunction change(Function<? super String, ? extends String> mapper){
		return null;    	
    }
    
    

}


class  chan<R> implements Function<T,R>{
	@Override
	public R apply(T t) {
		R r = null;
		// TODO Auto-generated method stub
		return r;
	}
	
}
