package Chapter17_NetWork;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

public class SimpleAIOClient {
	static final int PORT = 30000;
	public static void main(String[] args) throws Exception {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		Charset utf = Charset.forName("utf-8");
		
		// ����get������ã�����ȷ���첽�����Ѿ����
		try(AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open()){
			Future<Void> fu = clientChannel.connect(new InetSocketAddress("127.0.0.1",PORT));
			fu.get();
			buff.clear();
			
			Future<Integer> future = clientChannel.read(buff);
			future.get();
			buff.flip();
			
			String content = utf.decode(buff).toString();
			System.out.println("��������������Ϣ��"+content);
			
		}
		
	}

}
