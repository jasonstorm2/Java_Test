package conchapter2;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/**
 * ÿ������ִ�д���ͳ��
 * 
 * @author Xie le
 * @date 2016/7/9
 */
public class TPSTest {

    /** �߳����� */
    public static final int N_THRESHOLDS = 2;

    /** 30 ����ʱ�� */
    public static final int TIME_THRESHOLDS = 30;

    /** ��ԭ�ӱ�����ͳ��ִ��ʱ�䣬������ԭ�ӵݼ� */
    private static AtomicInteger totalTime = new AtomicInteger(TIME_THRESHOLDS);

    /** ����ͳ��ִ�е�������������ԭ�ӷ�ʽ�ۼӼ�¼ */
    private static AtomicLong totalExecCount = new AtomicLong(0L);

    /** ��Ҫ���ȵ������̶߳���ͬһ�����ߣ��ſ�ʼͳ�Ƽ����������ڷ���ǹ */
    private static CyclicBarrier barrier = new CyclicBarrier(N_THRESHOLDS);

    /** ִ��ʱ�䵽��ʱ�����е��߳���Ҫ�����˳������̲߳ſ�ʼͳ��ִ���������� */
    private static CountDownLatch countDownLatch = new CountDownLatch(N_THRESHOLDS);

    /** �߳�ִ�б�� , ��volatile���Σ�ʹ�����޸ľ����߳̿ɼ��� */
    private static volatile boolean runnning = true;

    /** ���̳߳���ִ��ͳ�� */
    private static ExecutorService executorService;

    /**
     * �ýӿ�����ģ��ͳ��
     */
    interface Job {
        void execute() throws Exception;
    }

    /**
     * ����Job��ģ�����һ��Http���� BTW:�ڲ�����static����
     */
    static class JobDetail implements Job {

        public void execute() throws Exception {
//            String run = OkHttpUtil.run("http://publicobject.com/helloworld.txt");
        	for(int i=0;i<500000/20;i++){
				//�����һ��ҵ���߼�
				String str = new String();
				Math.random();					
			}
        }
    }

    /**
     * Workerִ��Job
     */
    static class Worker implements Runnable {

        private Job job;

        Worker(Job job) {
            this.job = job;
        }

        // ÿ���߳�ִ�е�����ͳ����
        int innerCount = 0;

        public void run() {
            try {
                barrier.await(); // �ȵ������̶߳���������
                while (runnning) {
                    this.job.execute();
                    innerCount++;
                }
            } catch (Exception e) {
                System.out.println("�߳�Id��" + Thread.currentThread().getId() + " " + e.getMessage());
            } finally {
                // �ۼӵ��ܼ�¼ͳ����
                System.out.println("�߳�Id��" + Thread.currentThread().getId() + " ִ���������Ϊ��" + innerCount);
                totalExecCount.getAndAdd(innerCount);
                // �߳̽��������μ���, �������̼߳���ִ��
                countDownLatch.countDown();
            }
        }
    }

    public static void run() throws Exception {
    	long beginTime = System.currentTimeMillis();
        Job job = new JobDetail(); // ����Job
        executorService = Executors.newFixedThreadPool(N_THRESHOLDS); // �½��̶���С�̵߳ĳ���
        for (int i = 0; i < N_THRESHOLDS; i++) {
            executorService.submit(new Worker(job)); // �ύ�̵߳�������
        }
        // ����Ҫһ���̣߳��������ڼ��ִ��ʱ���Ƿ񵽴�
        final ScheduledExecutorService scheduledExcutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExcutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (totalTime.decrementAndGet() == 0) { // ִ��ʱ��ݼ���0
                    runnning = false; // �����̣߳�ʱ�䵽�ˣ������̲߳���ִ��
                    scheduledExcutor.shutdownNow();
                }
            }
        }, 1L, 1L, TimeUnit.SECONDS);

        // ���̵߳ȵ����е��̶߳��˳�����ʼͳ��
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("��������ʱ�䣺"+(endTime - beginTime));

        long totalExeCount = totalExecCount.get();
        System.out.println(N_THRESHOLDS + " ���̣߳�" + TIME_THRESHOLDS + " �����ܹ�ִ�е�����������" + totalExeCount);

//        long tps = LongMath.divide(totalExeCount, TIME_THRESHOLDS, RoundingMode.HALF_EVEN);

//        System.out.println("OKHttpִ�е�TPS: " + tps);

        executorService.shutdownNow(); // �ر��̳߳�


    }

    public static void main(String[] args) throws Exception {
        run();
    }

}