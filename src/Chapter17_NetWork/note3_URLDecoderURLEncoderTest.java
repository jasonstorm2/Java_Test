package Chapter17_NetWork;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLDecoder URLEncoder���������ͨ�ַ��� �� application/x-www-form-urlencoded MIME �ַ���֮����໥ת��
 * 
 * ��������ŷ�ַ�����ͨ�ַ�����application/x-www-form-urlencoded MIME�ַ�������ת��
 * 
 * ��URL��ַ�� ��������ŷ�ַ����ַ���ʱ��ϵͳ�Ὣ��Щ����ŷ�ַ���ת���������ַ�������Ҫ�õ���Щ�ַ���������ҪURLDecoder URLEncoderת����
 * 
 * URLDecoder�����룬���� decode��̬���������Խ� ����ȥ������ ���ַ���ת���� ��ͨ�ַ���
 * URLEncoder :���룬encode��̬���������Խ� ��ͨ�ַ��� ת���� ��ν������--application/x-www-form-urlencoded MIME�ַ���
 * 
 * utf-8�ַ����У�ÿ������ռ �����ֽڣ�1�ֽ�=8λ��4λ������1��ʮ�����Ʊ�ʾ����ÿ���ֽڿ���ת��������ʮ�����Ƶ����֣��磺%E4%BD%A0
 * ��ͬ�ַ�����������ռ�ֽ�����һ��
 * @author Administrator
 *
 */
public class note3_URLDecoderURLEncoderTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		//����--��������ַ� ת��Ϊ ��ͨ�ַ�
		String decode = URLDecoder.decode("%E4%BD%A0%E5%A5%BD%E5%90%97", "utf-8");
		System.out.println(decode);
		
		//����--����ͨ�ַ� ת���� �����ַ�
		String encode = URLEncoder.encode("�����", "utf-8");
		System.out.println(encode);
	}

}
