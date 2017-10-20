package Chapter17_NetWork;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NServer {
	private Selector selector = null;
	static final int PORT = 30000;
	// ����ʵ�ֱ������� �ַ�������
	private Charset charset = Charset.forName("UTF-8");
	
	public void init() throws IOException{
		// ���һ��ѡ����ʵ�� open����
		selector = Selector.open();
		
		// ͨ����̬���� open���һ��serverSocketChannel����
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1",PORT);
		// serverSocketChannel����һ����ַ�Ͷ˿�
		serverSocketChannel.bind(isa);
		// serverSocketChannel����Ϊ������
		serverSocketChannel.configureBlocking(false);
		// serverSocketChannel��selectorע��
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(selector.select() > 0){
			// ���δ���selector�ϵ�ÿ���Ѿ�ѡ���selectionkey
			for(SelectionKey sk : selector.selectedKeys()){
				// ɾ�����ڴ����selectionkey
				selector.selectedKeys().remove(sk);
				// ��� sk ��Ӧ��channel�����ͻ��˵� ��������
				if(sk.isAcceptable()){
					// ����Ŷ��������accept��������������˵� socketchannel
					SocketChannel sc = serverSocketChannel.accept();
					// ҲҪ����Ϊ������
					sc.configureBlocking(false);
					// ҲҪע����selector��
					sc.register(selector, SelectionKey.OP_READ);
					// ��sk ��Ӧ��Channel���ó�׼��������������
					sk.interestOps(SelectionKey.OP_ACCEPT);					
				}
				// ��Ӧ�� channel��������Ҫ��
				if(sk.isReadable()){
					// ��ȡ��Ӧ�� channel����channel���пɶ�����
					SocketChannel sc = (SocketChannel)sk.channel();
					ByteBuffer buff = ByteBuffer.allocate(1024);
					
					String content = "";
					
					try{
						// ���� buff��
						while(sc.read(buff)>0){
							buff.flip();
							content += charset.decode(buff);
						}
						
						System.out.println("��ȡ�����ݣ�"+content);
						// ��sk��Ӧ��channel ���ó�׼����һ�ζ�ȡ
						sk.interestOps(SelectionKey.OP_READ);					
						//���񵽸� sk ��Ӧ ��Channel �������쳣��˵����channel��Ӧ��client����������
						// ���Դ� selector��ȡ�� sk ��ע��
					}catch(IOException ex){
						// �� selector ��ɾ��ָ���� key
						sk.cancel();
						if(sk.channel() != null){
							sk.channel().close();
						}
					}
					// ������Ϣ��Ϊ�գ������пͻ��˷�����Ϣ
					if(content.length()>0){
						for(SelectionKey key : selector.keys()){
							Channel targetChannel = key.channel();
							// ���channel �� SocketChannel����
							if(targetChannel instanceof SocketChannel){
								SocketChannel dest = (SocketChannel)targetChannel;
								dest.write(charset.encode(content));
							}
							
						}
						
					}					
				}
				
			}			
		}		
	}
	
	public static void main(String[] args) throws IOException{
		new NServer().init();
	}

}
