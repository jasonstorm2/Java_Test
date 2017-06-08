package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SocketClient {
	public static void main(String[] args) throws IOException {
		Socket ss = new Socket("127.0.0.1",1022);
		
		// �����ͻ����߳�����ȡ������������
		new Thread(new ClientThread(ss)).start();
		
		//��ȡsocket��������������������Ϣ
		PrintStream ps = new PrintStream(ss.getOutputStream());
		String line = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while((line = br.readLine())!=null){ 
			ps.println(line);			
		}
		

//		BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
//		String line = br.readLine();
//		System.out.println("���Է����������ݣ�"+line);
//		br.close();
//		ss.close();
//		
//		// socket���� ���ó�ʱʱ��--socketû�й������������ó�ʱ�ģ�����������һ��ʵ�����ڵ���connect
//		Socket ss2 = new Socket();
//		ss2.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1022), 10000);
	}

}

class ClientThread implements Runnable{
	
	private Socket s;
	BufferedReader br = null;
	
	public ClientThread(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		
		try {
			String content = null;
			while((content = br.readLine()) != null){
				System.out.println(content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
