package Chapter17_NetWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * java �ṩ�� InetAddress���� ����IP��ַ
 * InetAddress �౾��û���ṩ̫��Ĺ��ܣ�������һ��IP��ַ����
 * InetAddress ���������� Inet4Address Inet6Address
 * InetAddress û�й������������ṩ��������̬���� ����ȡ InetAddressʵ����getByName  getByAddress
 * @author Administrator
 *
 */
public class InetAddressTest {
	public static void main(String[] args) throws Exception {
		InetAddress ip = InetAddress.getByName("www.baidu.com");
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
