package Chapter15;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {
	public static void main(String[] args) {
		/*
		 * �رմ��������������Դ�󣬴����� ����� �ڵ��������ر�
		 */
		try(
				FileOutputStream fos = new FileOutputStream("test.txt");//�ڵ�����ֱ��������IO�ڵ���Ϊ����������
				PrintStream ps = new PrintStream(fos)){                 //����������һ���Դ��ڵ�����Ϊ����������
			
			//ʹ��PrintStreamִ�����
			ps.println("��ͨ���ַ���");
			//ֱ��ʹ�� PrintStream�������
			ps.println(new PrintStreamTest());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
