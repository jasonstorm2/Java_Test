package my;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileInOutStremTest{
	   public static void main(String[] args) throws Exception {
		   /*
		    * "/"���ŵȼ���"\\"
		    * �������� FileOutputStream��·����·��ָʾ���ļ������ڣ���ô������������ļ�
		    */
	      FileOutputStream out = new FileOutputStream("C:/Users/Administrator/Documents/JavaTest/hellowddd.txt");
	      out.write("how to set yourself cool down".getBytes()); //���ַ��� ת��Ϊ �ֽ����� ��д�뵽����
	      out.close();//�ر������
	 
	      byte[] buf = new byte[1024];
	      File f = new File("C:/Users/Administrator\\Documents\\JavaTest/hellowddd.txt");
	      FileInputStream in = new FileInputStream(f);
	      int len = in.read(buf); //��ȡ���ݵ��ֽ������У����ض�����ֽ���
	      System.out.println(new String(buf,0,len)); //String���캯�����ֽ�����ת��Ϊ�ַ���
	      in.close();//�ر�������
	      
	   }
	}