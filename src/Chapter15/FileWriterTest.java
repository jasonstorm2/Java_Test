package Chapter15;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
	/*
	 * Java��\n��\r���������£�
	 * 1.\r �лس� Carriage Return
	 * 2.\n ������ New Line
	 * ���Ƕ�����ɻ���,ʹ��System.getProperty("line.separator")����ȡ��ǰOS�Ļ��з�
	 * ��ϵͳӦ����\r Mac ,\n Unix ,Linux\r\n Windows
	 * 
	 * ��ͬ����ƽ̨����ͬ�����һ���£����߽��ʹ��\r\n
	 */
	public static void main(String[] args) {
		try(FileWriter fw = new FileWriter("poem.txt")){
			fw.write("��ɪ-������\r\n");
			fw.write("��ɪ�޶���ʮ�ң�һ��һ��˼����\r\r");
			fw.write("ׯ�������Ժ��������۴����жž�\r\n");			
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
