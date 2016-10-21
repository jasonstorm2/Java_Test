package Chapter17_NetWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress �౾��û���ṩ̫��Ĺ��ܣ�������һ��IP��ַ����
 * InetAddress ���������� Inet4Address Inet6Address
 * InetAddress û�й�����
 * @author Administrator
 *
 */
public class InetAddressTest {
	public static void main(String[] args) throws Exception {
		InetAddress ip = InetAddress.getByName("www.baidu.com");
//		InetAddress ip = InetAddress.getByAddress(new byte[]{115,(byte) 239,(byte) 210,27});
		System.out.println("�Ƿ���Ե��"+ip.isReachable(1000));
		System.out.println("InetAddress����Ļ�ȡip�ַ���"+ip.getHostAddress());
		
		InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
		System.out.println("�����Ƿ���Դﵽ:"+local.isReachable(5000));
		System.out.println("��ȡ��InetAddress�����ȫ�޶�������"+local.getCanonicalHostName());
		
		InetAddress getLocal = InetAddress.getLocalHost();
		System.out.println("�����Ƿ���Դﵽ:"+getLocal.isReachable(5000));
		System.out.println("��ȡ��InetAddress�����ȫ�޶�������"+getLocal.getCanonicalHostName());//windows10.microdone.cn
		System.out.println("������ַ��"+getLocal.getHostAddress());//10.163.86.113
		System.out.println("��������"+getLocal.getHostName());//LiZhenHua

		
	}

}
