package my;

/**
 * ��̬����������ָ jvm���ظ��������Class�Ķ�Ӧ��������synchronizeTest.class����
 * @author Administrator
 *
 */
public class synchronizeTest extends Thread {

    private int threadNo;
    //private String lock;

    public synchronizeTest(int threadNo) {
        this.threadNo = threadNo;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 20; i++) {
            new synchronizeTest(i).start();
            Thread.sleep(1);
        }
    }
    // ����ֻ��һ�������Զ��߳��»ᰴ˳���ӡ
    public static synchronized void abc(int threadNo) {
        for (int i = 1; i < 10; i++) {
            System.out.println("No." + threadNo + ":" + i);
        }
    }

    public void run() {
        abc(threadNo);
    }
}