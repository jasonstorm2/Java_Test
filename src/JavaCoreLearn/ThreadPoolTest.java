package JavaCoreLearn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executors�ṩ�����̳߳ز���
 * @author Administrator
 * 
 * public ThreadPoolExecutor(int corePoolSize,  �����߳�����������е��߳�����corePoolSize���򴴽����߳���ִ�������񣬼�ʹ�̳߳��е������߳��ǿ��е�
                          int maximumPoolSize,  ����߳����������������߳�����corePoolSize��maximumPoolSize���õı߽��Զ������ش�С��
                          long keepAliveTime,   ����߳�������corePoolSize,����Щ������̵߳Ŀ���ʱ�䳬��keepAliveTimeʱ������ֹ
                          TimeUnit unit,         keepAliveTime������ʱ�䵥λ
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,   ʹ��ThreadFactory�������̣߳�Ĭ��ʹ��defaultThreadFactory�����߳�
                          RejectedExecutionHandler        handler ���崦���ܾ�����Ĳ��ԣ�Ĭ��ʹ��ThreadPoolExecutor.AbortPolicy,���񱻾ܾ�ʱ���׳�RejectExecutorException
                          ) //����������Ϊ��ѡ���� 
                          
                          corePoolSize <���е��߳���< maximumPoolSize:����������ʱ�Ŵ������߳�
                          corePoolSize=���е��߳���= maximumPoolSize�������̶���С���̳߳�
                          
                          workQueue:����������������У����̳߳صĴ�С�йأ�
  �����е��߳�������corePoolSizeʱ������������ʱֱ�Ӵ������߳���ִ������������ٽ�����
  �����е��߳������ڻ����corePoolSize���������������ʱ��ѡ������У���ֱ�Ӵ����߳�
  ��������ʱ������������ʱ�ʹ������߳�
 *
 */
public class ThreadPoolTest {
	public static void main(String[] args) throws Exception {
		ThreadPoolTest.submitTest();
//		ThreadPoolTest.excutorFixedThreadPool1();
//		ThreadPoolTest.excutorFixedThreadPool2();	
//		ThreadPoolTest.excutorSingleThreadExecutor();
//		ThreadPoolTest.excutorScheduledThreadPool2();
//		ThreadPoolTest.CompletionServiceTest();
	}
	
	/**
	 * submit���� ��execute�������� 
	 * ���յĲ�����һ��: 
	 *   submit�������Խ���runnable��callable  �з���ֵFuturn<>
	 *   execute��������runnable �޷���ֵ
	 * 
	 */
	private static void submitTest(){
		ThreadPoolExecutor tt ;
		ExecutorService executorService = Executors.newCachedThreadPool();  
        List<Future<String>> resultList = new ArrayList<Future<String>>();  

        // ����10������ִ��  
        for (int i = 0; i < 10; i++) {  
            // ʹ��ExecutorServiceִ��Callable���͵����񣬲������������future������  
            Future<String> future = executorService.submit(new TaskWithResult(i));  
            // ������ִ�н���洢��List��  
            resultList.add(future);  
        }  
        executorService.shutdown(); 
        System.out.println("������Ĵ�С��"+resultList.size());
        // ��������Ľ��  
        for (Future<String> fs : resultList) {
            try {  
                System.out.println("ִ�н����"+fs.get()); // ��ӡ�����̣߳�����ִ�еĽ��  
            } catch (InterruptedException e) {  
            	System.out.println("�쳣��ӡ1��");
                e.printStackTrace();  
            } catch (ExecutionException  e) {
            	System.out.println("�쳣��ӡ2��");
                executorService.shutdownNow();  
                e.printStackTrace();  
//                return;  
            }  
        }
	}
	
	// Callable �ӿڷ��ص��� Future<>����
	static class TaskWithResult implements Callable<String> {
	    private int id;  

	    public TaskWithResult(int id) {  
	        this.id = id;  
	    }  

	    /** 
	     * ����ľ�����̣�һ�����񴫸�ExecutorService��submit��������÷����Զ���һ���߳���ִ�С� 
	     *  
	     * @return 
	     * @throws Exception 
	     */  
	    public String call() throws Exception {
	        System.out.println("id="+id+"  call()�������Զ�����,�ɻ����             " + Thread.currentThread().getName());  
	        if (new Random().nextBoolean())  
	            throw new TaskException("###Meet error in task." + Thread.currentThread().getName()+"   id="+id);  
	        // һ��ģ���ʱ�Ĳ���  
	        for (int i = 999999999; i > 0; i--)  
	            ;  
	        return "call()�������Զ����ã�����Ľ���ǣ�" + id + "    " + Thread.currentThread().getName();  
	    }  
	}
	
	static class TaskException extends Exception {
	    public TaskException(String message) {  
	        super(message);  
	    }  
	} 
	
	
	private static void excutorFixedThreadPool1(){
		//������6���߳������̳߳�
				ExecutorService pool = Executors.newFixedThreadPool(6);
				
				Runnable target = ()->{
					for (int i = 0; i <200; i++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()+" �� i ��ֵ��"+i);
					}
				};
				//���̳߳����ύ �����߳�
				pool.submit(target);
				pool.submit(target);
				//�ر��̳߳�  ��Ȼ�ر��ˣ��ύ���̻߳�ֱ��������
				pool.shutdown();	
				new Random().nextBoolean();
	}
	
	private static void excutorFixedThreadPool2(){
		ExecutorService pool = Executors.newFixedThreadPool(6);
		for (int i = 0; i < 10; i++) {
		    final int index = i;
		    pool.execute(new Runnable() {		 
		        @Override
		        public void run() {
		            try {
		                System.out.println("index:"+index+" �߳����֣�"+Thread.currentThread().getName());		                
		                Thread.sleep(2000);
		            } catch (InterruptedException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		        }
		    });
		}
		// ������ص��������߳�ִ����ϣ������ǲ����˳�
		pool.shutdown();
	}
	
	// ��ʱ�̳߳أ��ӳ�3��ִ��
	private static void excutorScheduledThreadPool1(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
		 
		    @Override
		    public void run() {
		        System.out.println("delay 3 seconds");
		    }
		}, 3, TimeUnit.SECONDS);
	}
	
	// ��ʱ�̳߳� �ӳ�1��ִ�У�ÿ3��ִ��1��
	// ScheduledExecutorService��Timer����ȫ�����ܸ�ǿ��
	private static void excutorScheduledThreadPool2(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			 
		    @Override
		    public void run() {
		    	 System.out.println("�߳����֣�"+Thread.currentThread().getName());		
		        System.out.println("ģ����������");
		    }
		}, 1, 1, TimeUnit.SECONDS);
	}
	
	/**
	 * ����һ�����̻߳����̳߳أ���ֻ����Ψһ�Ĺ����߳���ִ�����񣬱�֤����������ָ��˳��(FIFO, LIFO, ���ȼ�)ִ��
	 */
	private static void excutorSingleThreadExecutor(){
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
		    final int index = i;
		    singleThreadExecutor.execute(new Runnable() {
		 
		        @Override
		        public void run() {
		            try {
		                System.out.println("index:"+index);
		                Thread.sleep(2000);
		            } catch (InterruptedException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		        }
		    });
		}
		singleThreadExecutor.shutdown();
	}
	
	private static void CompletionServiceTest() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(10); // ������10.���̵߳��̳߳�
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
				executor);
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		for (int i = 1; i <= 10; i++) {
			final int result = i;
			Future<Integer> fu = completionService.submit(new Callable<Integer>() {
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000)); // �õ�ǰ�߳��������һ��ʱ��
					return result;
				}
			});
			list.add(fu);
		}
		System.out.println(completionService.take().get()); // ��ȡִ�н��
		executor.shutdown();

	}

}
