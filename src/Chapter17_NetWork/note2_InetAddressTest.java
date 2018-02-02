package Chapter17_NetWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * java �ṩ�� InetAddress���� ����IP��ַ InetAddress �౾��û���ṩ̫��Ĺ��ܣ�������һ��IP��ַ����
 * InetAddress ���������� Inet4Address Inet6Address InetAddress û�й������������ṩ��������̬���� ����ȡ
 * InetAddressʵ����getByName getByAddress InetAddress�� ���ṩ��һ����ȡ������
 * InetAddress����ķ��� getLocalHost();
 * 
 * isReachable()���������ڲ��� �Ƿ��ܹ��ﵽ�õ�ַ���÷�����������Ŭ�� ��ͼ�ﵽ������������ǽ�ͷ��������� ������������ ʹ������
 * ����ĳЩ�ض��Ķ˿�ʱ�����ڲ��ɴﵽ״̬��������Ի��Ȩ�ޣ����͵�ʵ�ֽ�ʹ�� ICMP ECHO REQUEST;����������ͼ��Ŀ������
 * �Ķ˿�7��ECHO���Ͻ���TCP���ӡ�
 * 
 * HTTP��Э�� ������������ĵ�ַ�����������վ��ַ����URL (Uniform Resource Locator��ͳһ��Դ��λ��)��
 * ����ÿ��ÿ������һ�����Ƶ�ַһ����ÿ����ҳҲ����һ��Internet��ַ����
 * ����������ĵ�ַ��������һ��URL���ǵ���һ����������ʱ��URL��ȷ����Ҫ����ĵ�ַ��
 * �����ͨ�����ı�����Э��(HTTP)����Web��������վ�����ҳ������ȡ�������������Ư������ҳ��
 * ��ˣ���������ʶHTTP֮ǰ���б�Ҫ��Ū���URL�����,���磺 http://www.baidu.com/china/index.htm ��
 * ���ĺ������£� 1. http://�������ı�����Э�飬֪ͨbaidu.com��������ʾWebҳ��ͨ���������룻 2.
 * www������һ��Web����ά������������ 3. baidu.com/������װ����ҳ�ķ���������������վ������������ƣ� 4.
 * China/��Ϊ�÷������ϵ���Ŀ¼���ͺ������ǵ��ļ��У� 5. Index.htm��index.htm���ļ����е�һ��HTML�ļ�����ҳ����
 * 
 * 
 * InetAddress�� ����û��̫��Ĺ��ܣ�������һ��IP��ַ����������ͨ�ŵĻ���������Ľ����н�����ʹ�ø���
 * 
 * InetAddress.getByName():�����������ֻ�ȡInetAddress����
 * InetAddress.getByAddress():ͨ��ԭʼ��IP��ַ����ȡInetAddress����
 * getCanonicalHostName()����ȡ��InetAddress�����ȫ�޶�����
 * getHostAddress()����ȡ��InetAddress����Ķ�Ӧ��IP��ַ���ַ���
 * getLocal.getHostName():��ȡ��InetAddress�����������
 * isReachable(2000)�������Ƿ���Ե���õ�ַ
 * @author Administrator
 *
 */
public class note2_InetAddressTest {
	public static void main(String[] args) throws Exception {
		InetAddress ip = InetAddress.getByName("www.baidu.com");
//		InetAddress ip = InetAddress.getByAddress(new byte[]{115,(byte) 239,(byte) 210,27});
		System.out.println("�Ƿ���Ե��"+ip.isReachable(2000));
		System.out.println("��ȡ��InetAddress�����ȫ�޶�������"+ip.getCanonicalHostName());
		System.out.println("������ַ��"+ip.getHostAddress());
		System.out.println("��������"+ip.getHostName());

		
		System.out.println("------------------------------------------");
//		InetAddress getLocal = InetAddress.getByAddress(new byte[]{127,0,0,1});
//		InetAddress getLocal = InetAddress.getByAddress(new byte[]{10,(byte) 163,86,113});

		
		InetAddress getLocal = InetAddress.getLocalHost();
		System.out.println("�����Ƿ���Դﵽ:"+getLocal.isReachable(2000));
		System.out.println("��ȡ��InetAddress�����ȫ�޶�������"+getLocal.getCanonicalHostName());//windows10.microdone.cn
		System.out.println("������ַ��"+getLocal.getHostAddress());//10.163.86.113
		System.out.println("��������"+getLocal.getHostName());//LiZhenHua
		InetAddress in =InetAddress.getByAddress(new byte[]{(byte)103,(byte)44,(byte)145,(byte)245});

		
	}

}
