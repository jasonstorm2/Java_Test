package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer1 {
	private static final int SERVER_PORT = 30000;

	// ��������¼ÿ��user�����Ӧ������� 
	public static CrazyitMap<String, PrintStream> clients = new CrazyitMap<String, PrintStream>();

	public void init() {
		try (ServerSocket ss = new ServerSocket(SERVER_PORT)) {
			while (true) {
				// ���յ�socket��Ϣ������һ��socket
				// ���д������������һֱ�ȴ����˵�����
				Socket socket = ss.accept();
				// ÿ���ͻ������Ӻ�����һ���̣߳�Ϊ�ÿͻ��˷���
				new ServerThread(socket).start();
			}
		} catch (Exception e) {
			System.out.println("����������ʧ�ܣ��Ƿ�˿�" + SERVER_PORT + "�ѱ�ռ�ã�");
		}
	}

	public static void main(String[] args) {
		SocketServer1 server = new SocketServer1();
		server.init();
	}

	class ServerThread extends Thread {
		private Socket socket;
		BufferedReader br = null;
		PrintStream ps = null;

		public ServerThread(Socket socket) {
			this.socket = socket;
		}
		
		// ������������ȥ��ǰ���Э���ַ����ָ�����ʵ����
		private String getRealMsg(String line) {
			return line.substring(CrazyitProtocol.PROTOCOL_LEN, line.length()
					- CrazyitProtocol.PROTOCOL_LEN);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			
			try {
				// �ͻ�����Ϣ
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// ����˷��ؿͻ�����Ϣ
				ps = new PrintStream(socket.getOutputStream());
				String line = null;
				while((line = br.readLine())!=null){
					//����ͻ��˴�������ע����Ϣ
					if(line.startsWith(CrazyitProtocol.USER_ROUND) && line.endsWith(CrazyitProtocol.USER_ROUND)){
						// �õ���ʵ��Ϣ
						String userName = getRealMsg(line);	
						if(SocketServer1.clients.map.containsKey(userName)){
							System.out.println("�û����ظ�");
							ps.println(CrazyitProtocol.NAME_REP);
						}else{
							ps.println(CrazyitProtocol.LOGIN_SUCCESS);
							// ���� �û���-�����
							SocketServer1.clients.put(userName, ps);
							// ��½�ɹ� -- ���û�������ϲ��Ϣ���������û�������½��Ϣ
							ps.println("��ϲ����½�ɹ�����ǰ�û�������"+SocketServer1.clients.map.size());
							System.out.println("�û�������"+SocketServer1.clients.map.size());	
							for(PrintStream clientPs : SocketServer1.clients.valueSet()){
								if(ps != clientPs){
									clientPs.println(SocketServer1.clients.getKeyByValue(ps)+"������");
								}								
							}
							
						}
						//����˽����Ϣ
					}else if(line.startsWith(CrazyitProtocol.PRIVATE_ROUND )&& line.endsWith(CrazyitProtocol.PRIVATE_ROUND)){
						String userAndMsg = getRealMsg(line);
						
						String user = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[0];
						String msg = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[1];
						//�û������ڲŷ��ͣ���Ȼ����
						if(SocketServer1.clients.map.containsKey(user)){
							SocketServer1.clients.map.get(user).println(SocketServer1.clients.getKeyByValue(ps) + "���ĵض���˵" + msg);
						}
						
					}else{
						// ����Ⱥ����Ϣ
						String msg = getRealMsg(line);
						// ���� clients �е�ÿ�������
						for(PrintStream clientPs : SocketServer1.clients.valueSet()){
							clientPs.println(SocketServer1.clients.getKeyByValue(ps)+"˵��"+msg);
						}						
					}					
				}				
			} catch (IOException e) {
				// �������û����͸��û��뿪����Ϣ
				for(PrintStream clientPs : SocketServer1.clients.valueSet()){
					if(ps != clientPs){
						clientPs.println(SocketServer1.clients.getKeyByValue(ps)+"�뿪��");
					}								
				}				
				SocketServer1.clients.removeByValue(ps);
				System.out.println("�û�������"+SocketServer1.clients.map.size());	
				try{
					if(br != null){
						br.close();
					}
					if(ps != null){
						ps.close();
					}
					if(socket != null){
						socket.close();
					}
					
				}catch(IOException ex){
					ex.printStackTrace();
				}	
			}			
		}
	}

}
