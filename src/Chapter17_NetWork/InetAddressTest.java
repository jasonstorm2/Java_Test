package Chapter17_NetWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * java �ṩ�� InetAddress���� ����IP��ַ
 * InetAddress �౾��û���ṩ̫��Ĺ��ܣ�������һ��IP��ַ����
 * InetAddress ���������� Inet4Address Inet6Address
 * InetAddress û�й������������ṩ��������̬���� ����ȡ InetAddressʵ����getByName  getByAddress
 * InetAddress�� ���ṩ��һ����ȡ������ InetAddress����ķ��� getLocalHost();
 * 
 * isReachable()���������ڲ��� �Ƿ��ܹ��ﵽ�õ�ַ���÷�����������Ŭ�� ��ͼ�ﵽ������������ǽ�ͷ��������� ������������
 * ʹ������ ����ĳЩ�ض��Ķ˿�ʱ�����ڲ��ɴﵽ״̬��������Ի��Ȩ�ޣ����͵�ʵ�ֽ�ʹ�� ICMP ECHO REQUEST;����������ͼ��Ŀ������
 * �Ķ˿�7��ECHO���Ͻ���TCP���ӡ�
 * 
 * InetAddress�� ����û��̫��Ĺ��ܣ�������һ��IP��ַ����������ͨ�ŵĻ���������Ľ����н�����ʹ�ø���
 * @author Administrator
 *
 */
public class InetAddressTest {
	public static void main(String[] args) throws Exception {
		InetAddress ip = InetAddress.getByName("www.baidu1.com");
//		InetAddress ip = InetAddress.getByAddress(new byte[]{115,(byte) 239,(byte) 210,27});
		System.out.println("�Ƿ���Ե��"+ip.isReachable(5000));
		System.out.println("��ȡ��InetAddress�����ȫ�޶�������"+ip.getCanonicalHostName());
		System.out.println("������ַ��"+ip.getHostAddress());
		System.out.println("��������"+ip.getHostName());

		
		System.out.println("------------------------------------------");
//		InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
		InetAddress local = InetAddress.getByAddress(new byte[]{10,(byte) 163,86,113});

		
		InetAddress getLocal = InetAddress.getLocalHost();
		System.out.println("�����Ƿ���Դﵽ:"+getLocal.isReachable(5000));
		System.out.println("��ȡ��InetAddress�����ȫ�޶�������"+getLocal.getCanonicalHostName());//windows10.microdone.cn
		System.out.println("������ַ��"+getLocal.getHostAddress());//10.163.86.113
		System.out.println("��������"+getLocal.getHostName());//LiZhenHua

		
	}

}
