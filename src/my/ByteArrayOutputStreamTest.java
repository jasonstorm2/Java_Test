package my;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * �ֽ�������������ڴ��д���һ���ֽ����黺���������з��͵�����������ݱ����ڸ��ֽ����黺�����С� 
 * �����ֽ�������������������¼��ַ�ʽ��
 * ����Ĺ��췽������һ��32�ֽڣ�Ĭ�ϴ�С���Ļ����� OutputStream bOut = new ByteArrayOutputStream();
 * 
 * ��һ�ַ�ʽ��ָ����Сn�ֽڵĻ�������OutputStream bOut = new ByteArrayOutputStream(int n)
 * 
 * @author Administrator
 *
 */
public class ByteArrayOutputStreamTest {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
		while (bOutput.size() != 10) {
			// ��ȡ�û�����
			bOutput.write(System.in.read());
		}
		byte b[] = bOutput.toByteArray();//����һ���·�����ֽ����顣����Ĵ�С�͵�ǰ������Ĵ�С�������ǵ�ǰ������Ŀ��������ڴ��е����ݶ����ֽ�������
		System.out.println("Print the content");
		for (int x = 0; x < b.length; x++) {
			// ��ӡ�ַ�
			System.out.print((char) b[x] + "   ");
		}
		System.out.println("   ");
		int c;
		ByteArrayInputStream bInput = new ByteArrayInputStream(b);//�ְ��ֽ������е��ֽ���������ʽ����
		System.out.println("Converting characters to Upper case ");
		for (int y = 0; y < 1; y++) {
			while ((c = bInput.read()) != -1) {
				System.out.println(Character.toUpperCase((char) c));
			}
			bInput.reset();//�����ֽ������������ count �ֶ�����Ϊ�㣬�Ӷ������������Ŀǰ���ۻ����������������
		}
	}
}
