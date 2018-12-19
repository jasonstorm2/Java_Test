package my;

import java.util.concurrent.LinkedBlockingQueue;

import utils.utils;

/**
 * LinkedBlockingQueue ����
 * @author LiZhenhua
 * 
 * java.util.concurrent���µ����ࡣ
 * LinkedBlockingQueue��������֮һ������˼������һ���������̰߳�ȫ�Ķ��У��ײ�Ӧ�ò�������ʵ��
 * 
 * LinkedBlockingQueue�����ʱ����û��ָ����С����Ĭ�ϴ�СΪInteger.MAX_VALUE����ȻҲ�����ڹ��캯���Ĳ�����ָ����С��
 * LinkedBlockingQueue������null
 * 
 * put�������ڶ�β��ӣ������β���Ԫ�ص�ʱ���ֶ����Ѿ����˻ᷢ������һֱ�ȴ��ռ䣬�Լ���Ԫ��
 * add�����������Ԫ�ص�ʱ���������˶��еĳ��Ȼ�ֱ���׳��쳣��
 * offer�������ڶ�β��ӣ������Ԫ��ʱ��������ֶ��������޷���ӵĻ�����ֱ�ӷ���false��
 * 
 * poll ȡ����ͷ�ĵ�һ��Ԫ�أ�û���򷵻�null
 *
 */
public class LinkedBlockingQueueTest {
	public static void main(String[] args) throws Exception {
		 LinkedBlockingQueue<String> queue= new LinkedBlockingQueue<String>(2);        
		 new Thread(){
			 @Override
			public void run() {
				// TODO Auto-generated method stub
				 System.out.println("���߳��� queue �� ��С��"+queue.size());
				while(true){
					if(queue.size()!=0){
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						utils.PrintLine("pollǰ"+queue.toString());
						String str = queue.poll();
						utils.PrintLine("poll��"+queue.toString());
					}else{
						break;
					}
				}
			 }
		 }.start();
		 queue.add("hi");  
		 queue.add("haa");  
         queue.put("hello");  
         System.out.println("��ӡ���У�"+queue);
         queue.put("world");  
         System.out.println("��ӡ����2��"+queue);
         queue.put("yes");     
         // ���߳�������ֱ�����߳�ȡ�����еĵ�һ��Ԫ��
         System.out.println("yes"); 
         boolean bol3=queue.offer("����ֵ");  
         System.out.println("offer����ֵ:"+bol3);
        
	}

}
