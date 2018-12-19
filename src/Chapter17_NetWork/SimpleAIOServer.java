package Chapter17_NetWork;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * �첽IOѧϰ
 * AsynchronousServerSocketChannel ���������channel
 * ��open�����ַ��� һ��Ĭ�ϣ�һ��ָ��ʹ�� AsynchronousChannelGroup����������
 * 
 * AsynchronousChannelGroup �첽channel���������������ʵ����Դ����
 * 
 * accept����Ҳ��������һ���������̣߳�һ������
 * 
 *  Future �����ͨ��������ʵ�ʲ����Ľ�������磬��ȡ��д���������Ϊ�������ض���д���ֽ�����������һ�� Future<Integer>��
 * @author LiZhenhua
 *
 */
public class SimpleAIOServer {
	static final int PORT = 30000;
	public static void main(String[] args)throws Exception{
		System.out.println("����������");
		
		try(AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open()){			
			serverChannel.bind(new InetSocketAddress(PORT));
			
			while(true){
				// ���ܿͻ��˵�����
				Future<AsynchronousSocketChannel> future = serverChannel.accept();	
				System.out.println("get()����");
				// ����Future��get()��������ȡ���Ӻ󷵻ص�AsynchronousSocketChannel���˷�����������ǰ����,
				// ���� Future ���󣬵�ǰ�߳̿��������ȴ����
				AsynchronousSocketChannel socketChannel = future.get();
//				AsynchronousSocketChannel socketChannel = future.get(10,TimeUnit.SECONDS); // ��������10��
				// ��ѯ�����ĵ�ǰ״̬������ȡ������
//				if (!future.isDone()) {
//				    future.cancel(true);
//				}
				
				System.out.println("�пͻ������ӵ�");
				//AsynchronousSocketChannel ����ִ��IO����
				socketChannel.write(ByteBuffer.wrap("��ӭ�������첽IO������".getBytes("utf-8"))).get();
			}
		}	
	}
}
