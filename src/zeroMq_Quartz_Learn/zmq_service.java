package zeroMq_Quartz_Learn;

import org.zeromq.ZMQ;

/**
 * �����zmq
 * @author Administrator
 *
 */
public class zmq_service {
	public static void main(String[] args) {
		ZMQ.Context context = ZMQ.context(1);//��ʾ��������һ��I/O�̵߳� context
		ZMQ.Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5555");  //�󶨶˿ڣ�
		int i=0;
		while(true){
			
			byte[] req = socket.recv(); // ��ȡrequest���͹���������
			System.out.println("������յ���Ϣ��"+ new String(req));
			
			
			String res = "���Ƿ���ˣ��Ѿ��յ���Ϣ";
			socket.send(res);//��ͻ��� ������Ϣ
			i++;
			if(i>=5){
				break;
			}
		}		
		socket.close(); //�ر�socket
		context.close();//�ر� ������
		
		
		
	}
}
