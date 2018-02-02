package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * �ͻ���ͨ������ʹ��socket�Ĺ����������ӵ�ָ���ķ���� �������� Socket(IntAddress/String addr,int port)
 * Socket(IntAddress/String addr,int port,IntAddress/String localAddr,int
 * localPort) :ָ�����ص�ip�Ͷ˿�
 * 
 * ��ִ��Socket ss = new Socket("127.0.0.1",1022)����ǣ��������ӵ�ָ���ķ���������������accept��������ִ��
 * ���ǣ�����˿ͻ��˾Ͳ���һ���໥���ӵ�socket��
 * 
 * Socket�ķ���
 * 
 * InputStream getInputStream(): ���ظ�socket��Ӧ����������ͨ��������socketȡ������ ��ȡ
 * 
 * OutputStream getOutputStream():���ظ�socket��Ӧ�������ͨ��������socket������� д��
 * 
 * �ͻ���socket �������ó�ʱʱ�䣬�����ָ��ʱ���ڷ�����û����Ӧ������Ϊ�ǳ�ʱ ������һ��û�����ӵ�socket�������ó��в��� Socket ss2
 * = new Socket(); 
 * ss2.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1022), 10000);
 * 
 * @author Administrator
 *
 */
public class note8_SocketClient {
	public static void main(String[] args) throws IOException {
		Socket ss = new Socket("139.199.159.226",3033);
		
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
		Socket ss2 = new Socket();
		ss2.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1022), 10000);
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
