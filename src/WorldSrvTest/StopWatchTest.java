package WorldSrvTest;

import org.apache.commons.lang3.time.StopWatch;

//ʹ��StopWatch���뵼��Apache��jar��


//StopWatch��һ����ʱ���������ʱ���޹أ���startʱ��ʱ���0��ʼ�ߡ�����ֱ��stop���м��ʱ��������ͣ��Ȼ���ָֻ���ʱ����ͣ��ʱ��Σ��������ʱ����ʱ��

public class StopWatchTest {
	
	private static void test01() throws InterruptedException {  
	    StopWatch watch = new StopWatch();  
	    watch.start();
	    Thread.sleep(1000);  
	    watch.split();  
	    /* 
	     * This is the time between start and latest split. 
	     * ����start()���������һ�ε���split()�������õ�ʱ�� 
	     */  
	    Thread.sleep(2000);  
	    watch.split();  
	    Thread.sleep(500);  
	    /* 
	     * This is either the time between the start and the moment this method 
	     * is called, or the amount of time between start and stop 
	     * ����start()����������getTime()��stop()�������õ�ʱ�� 
	     */  
	    System.out.println("watch.getTime():"+watch.getTime());
	    
	    //���һ��ִ�е�ʱ����
	    System.out.println(watch.getTime()-watch.getSplitTime());

	}  
	
	
	private static void test02() throws InterruptedException {  
	    StopWatch watch = new StopWatch();  
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	    /* ��λ ���� */  
	    watch.reset();  
	    //�����Ҫ��һ������
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	} 
	
	private static void test03() throws InterruptedException{  
	    StopWatch watch = new StopWatch();  
	    watch.start();  
	    Thread.sleep(1000);  
	    System.out.println(watch.getTime());  
	    /* ��ͣ */  
	    watch.suspend();  
	    System.out.println("do something");  
	    Thread.sleep(500);  
	    /* �ָ� */  
	    watch.resume();  
	    Thread.sleep(2000);  
	    System.out.println(watch.getTime());  
	}
	
	
	public static void main(String[] args) {
		StopWatchTest st = new StopWatchTest();
		try {
			StopWatchTest.test01();
			System.out.println("-------------------------------------");
			StopWatchTest.test02();
			System.out.println("-------------------------------------");
			StopWatchTest.test03();


		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
