package my;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class AtomicIntegerTest {

    static long randomTime() {
        return (long) (Math.random() * 1000);
    }

    public static void main(String[] args) {
        // �������У�������100���ļ�
        final BlockingQueue<File> queue = new LinkedBlockingQueue<File>(100);
        // �̳߳�
        final ExecutorService exec = Executors.newFixedThreadPool(5);
        final File root = new File("D:\\SVN_XINGXIU\\2016220newserver\\server\\gof");
        // ��ɱ�־
        final File exitFile = new File("");
        // ԭ�����ͣ�������
        // AtomicInteger�����ڲ�������´ﵽԭ�ӻ����£�����ʹ����synchronized���������ܷǳ��ߡ�
        final AtomicInteger rc = new AtomicInteger();
        // ԭ�����ͣ�д����
        final AtomicInteger wc = new AtomicInteger();
        Logger log = Logger.getLogger("AtomicIntegerTest.class");
        
		exec.submit(() -> {
			try {
				System.out.println("***************");
				System.out.println(1 / 0);// û���쳣�׳�������ͨ�߳� �� �̳߳ص�����֮������������Ҫtrycatch����
			} catch (Exception e) {
				e.printStackTrace();
//				log.

				System.out.println("���쳣�׳�");
			}
		});
		
        
        
        // ���߳�
        Runnable read = new Runnable() {
            public void run() {
                scanFile(root);
                scanFile(exitFile);
            }

            public void scanFile(File file) {
                if (file.isDirectory()) {
                    File[] files = file.listFiles(new FileFilter() {
                        public boolean accept(File pathname) {
                            return pathname.isDirectory() || pathname.getPath().endsWith(".bat");
                        }
                    });
                    for (File one : files)
                        scanFile(one);
                } else {
                    try {
                        // ԭ�����͵�incrementAndGet��������ԭ�ӷ�ʽ����ǰֵ�� 1�����ظ��µ�ֵ
                        int index = rc.incrementAndGet();
                        System.out.println("Read0: " + index + " " + file.getPath());
                        // ��ӵ�����������--ÿ��һ����������������+1����д����������
                        queue.put(file);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        // submit�����ύһ�� Runnable ��������ִ�У�������һ����ʾ������� Future��
        exec.submit(read);

        // �ĸ�д�߳�
        for (int index = 0; index < 4; index++) {
            // write thread
            final int num = index;
            Runnable write = new Runnable() {
                String threadName = "Write" + num;

                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(randomTime());
                            // ԭ�����͵�incrementAndGet��������ԭ�ӷ�ʽ����ǰֵ�� 1�����ظ��µ�ֵ
                            int index = wc.incrementAndGet();
                            // ��ȡ���Ƴ��˶��е�ͷ������Ԫ�ر�ÿ���֮ǰһֱ�ȴ�������б�Ҫ����
                            //Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.

                            File file = queue.take();
                            // �����Ѿ��޶���
                            if (file == exitFile) {
                                // �ٴ����"��־"�����������߳������˳�
                                queue.put(exitFile);
                                break;
                            }
                            System.out.println(threadName + ": " + index + " " + file.getPath());
                        } catch (InterruptedException e) {
                        	System.out.println("�쳣����������������������");
                        }
//                        System.out.println("ִ���С���������������");
                    }
                }

            };
            exec.submit(write);
        }//forѭ������
        exec.shutdown();
    }
}
