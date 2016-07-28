package my;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import Chapter15.FileTest;

/**
 * ��Ե�ַ��������ַ�ǣ�System.getProperty("user.dir")������Ŀ�ĵ�ַ������Ŀ��ַ�ǣ�D:\WorkSpace1\Java_Test
 * @author Administrator
 *
 */
public class FileAddressTest {
	public static void main(String[] args) {
		try {
			//·����******��Ŀ¼�£���("./FileInputStreamTest.txt")���һ��
			FileInputStream fis = new FileInputStream("FileInputStreamTest.txt");
			//·����******.class�ĵ�ǰĿ¼����("./FileInputStreamTest.txt")���һ��
			InputStream inputFile2 = FileTest.class.getResourceAsStream("FileInputStreamTest.txt");
			File file = new File("");  //-----------------------------------�������󲢷Ǵ�����һ���ļ����ļ��У�--�ܵ�����
			System.out.println(file.getAbsolutePath());//·����D:\WorkSpace1\Java_Test
			File file1 = new File(".");  //-----------------------------------�������󲢷Ǵ�����һ���ļ����ļ��У�
			System.out.println(file1.getAbsolutePath());//·����D:\WorkSpace1\Java_Test\.
			File file2 = new File("file2.txt");  //-----------------------------------�������󲢷Ǵ�����һ���ļ����ļ��У�
			System.out.println(file2.getAbsolutePath());//·����D:\WorkSpace1\Java_Test\file2.txt
			/***�����ļ�***/
//			file.createNewFile(); //����Ϊ�գ��޷�����
			file1.createNewFile(); //һ��.Ҳû�а취����
			file2.createNewFile();  //---------------------------------------------ͨ�����󴴽�һ���ļ�����
			
		} catch (Exception e) {
			e.printStackTrace();
		}//��Ե�ַ����������ַ�ǣ�System.getProperty("user.dir")
	}

}
