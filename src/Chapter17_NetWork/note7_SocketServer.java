package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ������ͨ��ʵ��û�н�������֮ǰ��������һ��������������̬��������������һ��ͨ��ʵ�������
 * java���������������������ServerSocket,ServerSocket�������ڼ������Կͻ��˵�SOCKET���ӣ�
 * ���û�����ӣ��������ڵȴ�״̬����
 * 
 * ������
 * accept(): ����һ��socket���󣬷���һ����ͻ���socket��Ӧ��socket������÷���һֱ���ڵȴ�״̬���߳�Ҳ��������
 * ServerSocket(int port):
 * ServerSocket(int port,int backlog)
 * ServerSocket(int port,int backlog,InetAddress localAddr)����������ĵ���ָ��һ��ip��socket
 * close()���ر�ServerSocket
 * 
 *  ��ServerSocketʹ����Ϻ�Ӧ��ʹ��close()�����رո�ServerSocket����ͨ��������£�����˲���ֻ����һ���ͻ��˵����󣬶��ǲ��Ͻ���
 *  ���ԣ�java����ͨ����ѭ������accept����
 *  
 *  ��ʹ�ô�ͳBufferedReader �� readLine()������ȡ����ʱ���ڸ÷����ɹ�����֮ǰ���̱߳������������޷�ִ��
 *  ���ԣ������Ӧ��Ϊÿ��socket��������һ���̣߳�ר����ÿ���ͻ���ͨ��
 *  
 *  ͬ���ͻ���Ҳ����ˣ���Ҫ��һ���߳�
 * 
 * @author Administrator
 *
 */
public class note7_SocketServer {
	//�̰߳�ȫlist
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());	
	
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(3383);
		System.out.println("����˿�������������");
		while(true){
			// ���յ�socket��Ϣ������һ����ͻ���socket��Ӧ��socket
			// ���д������������һֱ�ȴ����˵�����
			Socket s = ss.accept();
			socketList.add(s);
			// ÿ���ͻ������Ӻ�����һ���̣߳�Ϊ�ÿͻ��˷���
			new Thread(new ServerThread(s)).start();

		}
//		ServerSocket ss = new ServerSocket(1022);
//		Socket s = ss.accept();
//		socketList.add(s);
	}	
}

		

class ServerThread implements Runnable{
	Socket s = null;
	BufferedReader br = null;
	public ServerThread(Socket s) throws IOException{
		this.s = s;
		// ��ʼ��socket��Ӧ��������
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("�пͻ��˳ɹ�����");
		PrintStream p;
		try {
			p = new PrintStream(s.getOutputStream());
			p.println("��ӭ����������ҫ");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try{			
			String content = null;
			// ����ÿͻ���socket�б䶯�������е��û�����һ����Ϣ
			while((content = readFromClient()) != null){
				for(Socket s : note7_SocketServer.socketList){
					PrintStream ps = new PrintStream(s.getOutputStream());
					ps.println(content);
				}
				
			}
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	// �����ȡ�ͻ������ݵķ���
	private String readFromClient(){
		
		try {
			return br.readLine();
			// ��������쳣��������socket��Ӧ�Ŀͻ����Ѿ��ر�
		} catch (IOException e) {
			// ɾ���� socket
			note7_SocketServer.socketList.remove(s);
		}
		return null;
		
	}
	
}
