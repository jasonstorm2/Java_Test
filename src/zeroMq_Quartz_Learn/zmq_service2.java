package zeroMq_Quartz_Learn;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * �����zmq
 * @author LiZhenhua
 *
 */
public class zmq_service2 {
	public static void main(String[] args) {
		ZContext context = new ZContext();//��ʾ��������һ��I/O�̵߳� context
		context.setIoThreads(1);
		
		ZMQ.Socket socket = context.createSocket(ZMQ.PULL);
		socket.setRcvHWM(0);
		socket.setLinger(3000);		
		socket.bind("tcp://127.0.0.1:5555");  //�󶨶˿ڣ�
		int i=0;
		System.out.println("zmq����˿���............");
		while(true){
			System.out.println("true");
			byte[] req = new byte[1204];
			System.out.println("waiting.............");
			int recvLen = socket.recv(req, 0, req.length, ZMQ.DONTWAIT);
			System.out.println("receive.!!!!!!!!!!!!");
			if(recvLen != -1){
				System.out.println("������յ���Ϣ��"+ new String(req));
				String res = "���Ƿ���ˣ��Ѿ��յ���Ϣ";
//				socket.send(res);//��ͻ��� ������Ϣ
			}
		}
	}
}
