package Chapter17_NetWork;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLDecoder URLEncoder���������ͨ�ַ��� �� application/x-www-form-urlencoded MIME �ַ���֮����໥ת��
 * 
 * ��������ŷ�ַ�����ͨ�ַ�����application/x-www-form-urlencoded MIME�ַ�������ת��
 * @author Administrator
 *
 */
public class URLDecoderURLEncoderTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		//����--��������ַ� ת��Ϊ ��ͨ�ַ�
		String decode = URLDecoder.decode("%E4%BD%A0%E5%A5%BD%E5%90%97", "utf-8");
		System.out.println(decode);
		
		//����--����ͨ�ַ� ת���� �����ַ�
		String encode = URLEncoder.encode("�����", "utf-8");
		System.out.println(encode);
	}

}
