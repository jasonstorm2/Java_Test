package Chapter15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author LiZhenhua
 *
 */
public class FileInputStreamTest {
	/*
	 * һ�����з���ռ�����ֽ�
	 * һ��Ӣ����ĸ��ռһ���ֽ�
	 */
	public static void main(String[] args) throws IOException  {
		//�����ֽ�������
		FileInputStream fis = new FileInputStream("FileInputStreamTest.txt");//��Ե�ַ����������ַ�ǣ�System.getProperty("user.dir")
		//��������Ϊ1024����Ͳ
		byte[] bbuf = new byte[100];
		//���ڱ���ʵ�ʶ�ȡ���ֽ���
		int hasRead = 0;
		//ѭ��ȡˮ�Ĺ���
		while((hasRead = fis.read(bbuf))>0){
			System.out.println(new String(bbuf,0,hasRead));
		}
		fis.close();		
		
	}

}
