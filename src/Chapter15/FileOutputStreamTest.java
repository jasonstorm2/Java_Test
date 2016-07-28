package Chapter15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
	public static void main(String[] args) {
		try (
		// �����ֽ�������
		FileInputStream fis = new FileInputStream("FileInputStreamTest.txt");
				// �����ֽ������
				FileOutputStream fos = new FileOutputStream("OutputStream.txt");) {
			byte[] bt = new byte[32];
			int hasRead = 0;
			while((hasRead = fis.read(bt))>0){
				//ÿ��ȡһ�Σ���д���ļ������������˶��٣���д����
				fos.write(bt, 2, hasRead-2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
