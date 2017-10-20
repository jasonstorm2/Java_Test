package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SocketServer {
	//�̰߳�ȫlist
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());	
	
	
	public static void main(String[] args) throws IOException {
//		ServerSocket ss = new ServerSocket(1022);
//		while(true){
//			// ���յ�socket��Ϣ������һ��socket
//			// ���д������������һֱ�ȴ����˵�����
//			Socket s = ss.accept();
//			socketList.add(s);
//			// ÿ���ͻ������Ӻ�����һ���̣߳�Ϊ�ÿͻ��˷���
//			new Thread(new ServerThread(s)).start();
//
//		}
		ServerSocket ss = new ServerSocket(1022);
		Socket s = ss.accept();
		socketList.add(s);
	}
}

class ServerThread implements Runnable{
	Socket s = null;
	BufferedReader br = null;
	public ServerThread(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("33");
		
		try{			
			String content = null;
			// ����ÿͻ���socket�б䶯�������е��û�����һ����Ϣ
			while((content = readFromClient()) != null){
				for(Socket s : SocketServer.socketList){
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
			SocketServer.socketList.remove(s);
		}
		return null;
		
	}
	
}
