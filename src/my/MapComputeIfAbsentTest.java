package my;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MapComputeIfAbsentTest {
	 static Map<Integer, Integer> cache = new ConcurrentHashMap<>();

	    public static void main(String[] args) throws InterruptedException {
	        cache.put(0, 0);
	        cache.put(1, 1);
	        // ��ͨ��ʽ
//	        System.out.println("Fibonacci(7) = " + fibonacci(7));
	        // ����java7��ͬ���̷߳�ʽ��java8�ı��ػ���ķ�ʽ
//	        System.out.println("FibonacciJava8(7) = " + fibonacciJava8(7));
	        System.out.println("FibonacciJava7(7) = " + fibonacciJava7(7));
//
//	        // ������ֵMap��������
//	        Map<String, HashSet<String>> map1 = new HashMap<>();
//	        map1.computeIfAbsent("fruits", k -> genValue(k)).add("apple");
//	        map1.computeIfAbsent("fruits", k -> genValue(k)).add("orange");
//	        map1.computeIfAbsent("fruits", k -> genValue(k)).add("pear");
//	        map1.computeIfAbsent("fruits", k -> genValue(k)).add("banana");
//	        map1.computeIfAbsent("fruits", k -> genValue(k)).add("water");
//	        System.out.println(map1);
//
//	        //���Զ��̲߳��������Ƿ�ͬ������
//	        Map<String, String> map2 = new ConcurrentHashMap<>();
//	        ExecutorService exec = Executors.newCachedThreadPool();
//	        for (int i = 0; i < 5; i++) {
//	            exec.execute(() -> {
//	                map2.computeIfAbsent("name", k -> genValue2(k));
//	                map2.computeIfAbsent("addr", k -> genValue2(k));
//	                map2.computeIfAbsent("email", k -> genValue2(k));
//	                map2.computeIfAbsent("mobile", k -> genValue2(k));
//	            });
//	        }
//	        exec.shutdown();
//	        exec.awaitTermination(1, TimeUnit.SECONDS);
//	        System.out.println(map2);
	    }
	    
	    
	    
	    

	    static HashSet<String> genValue(String str) {
	        return new HashSet<String>();
	    }

	    static String genValue2(String str) {
	        System.out.println("===");
	        return str + "2";
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

}
