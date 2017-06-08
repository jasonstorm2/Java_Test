package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class SocketClient1 {
	private static final int SERVER_PORT = 30000;
	private Socket socket;
	private PrintStream ps;
	private BufferedReader brServer;
	private BufferedReader keyIn;
	
	public void init(){
		try{
			// ��������
			keyIn = new BufferedReader(new InputStreamReader(System.in));
			socket = new Socket("127.0.0.1",SERVER_PORT);
			// ���������
			ps = new PrintStream(socket.getOutputStream());
			// ����˷���
			brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip = "";
			
			// ������ע��
			while(true){
				String userName = JOptionPane.showInputDialog(tip+"�����û���");
				ps.println(CrazyitProtocol.USER_ROUND+userName+CrazyitProtocol.USER_ROUND);
				
				String result = brServer.readLine();
				if(result.equals(CrazyitProtocol.NAME_REP)){
					tip = "�û����ظ���";
					continue;
				}
				if(result.equals(CrazyitProtocol.LOGIN_SUCCESS)){
					System.out.println("��¼�ɹ�");
					break;
				}
				
			}			
		}catch(UnknownHostException ex){
			System.out.println("�Ҳ���Զ�̷���������ȷ���������Ѿ�����");
			closeRs();
			System.exit(1);
			
		}catch (IOException e) {
			System.out.println("�����쳣�������µ�¼");
			closeRs();
			System.exit(1);			
		}
		
		//�Ѹ�socket�������������ͻ����߳�
		new ClientThread(brServer).start();
	}
	
	//�ر�socket ��������������ķ���
	private void closeRs(){
		try{
			if(keyIn != null){
				ps.close();
			}
			if(brServer != null){
				ps.close();
			}
			if(ps != null){
				ps.close();
			}
			if(socket != null){
				keyIn.close();
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	//����һ����ȡ����������������緢�͵ķ���
	private void readAndSend(){
		try{
			String line = null;
			while((line = keyIn.readLine())!=null){
				if(line.indexOf(":")>0 && line.startsWith("//")){
					line = line.substring(2);
					ps.println(CrazyitProtocol.PRIVATE_ROUND + line.split(":")[0] + CrazyitProtocol.SPLIT_SIGN 
							+ line.split(":")[1] + CrazyitProtocol.PRIVATE_ROUND);
				}else{
					ps.println(CrazyitProtocol.MST_ROUND + line + CrazyitProtocol.MST_ROUND);
				}
				
			}
			
		}catch(IOException e){
			System.out.println("�����쳣�������µ�¼");
			closeRs();
			System.exit(1);
		}
	}

	public static void main(String[] args) throws IOException {
		SocketClient1 client = new SocketClient1();
		client.init();
		client.readAndSend();		
	}
	
	
	
	
	
	
	class ClientThread extends Thread{
		BufferedReader br = null;
		
		public ClientThread(BufferedReader br){
			this.br = br;
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
			
			finally{
				try{
					if(br != null){
						br.close();
					}
				}catch(IOException ex){
					ex.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	
	
	
}


