package zeroMq_Quartz_Learn;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * �ͻ���zmq
 * @author LiZhenhua
 *
 */
public class zmq_client2 {
	public static void main(String[] args) {
		System.out.println("�򿪿ͻ��ˡ�����������������");
		
		for(int i=0;i<5;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ZContext context = new ZContext();//��ʾ��������һ��I/O�̵߳� context
					ZMQ.Socket socket = context.createSocket(ZMQ.PUSH); //zmqģʽ���ͻ�������
					socket.connect("tcp://127.0.0.1:5555");  //�����˽�������
			
					String req = "hello";
					socket.send(req);
//					byte[] res = socket.recv();
//					System.out.println("�ͻ����յ�����˷���:"+new String(res));
				}
			}).start();
			
		}	
		
	}
}
