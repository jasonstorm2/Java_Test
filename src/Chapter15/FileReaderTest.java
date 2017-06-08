package Chapter15;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class FileReaderTest {
	/*
	 * try-with-resources���:java 7���¹��ܡ����е�IO��Դ�඼ʵ���� AutoCloseable�ӿڡ���try������Զ��ر���
	 * 
	 * ��ʽ��
	 * 
	 * try(Ҫ�رյ���,���Զ��){//try-with-resources�������Դ�Ĺر��ͷ�˳������Ǵ�����˳���෴��
	 *    �������
	 * }
	 * 
	 * try(){
	 * 
	 * }catch(){//��Ȼ��try������˳�㲶�����쳣
	 *  
	 * }
	 * 
	 */
	public static void main(String[] args) {
		//�ļ�Ĭ�ϵı����ʽ�� gbk
		try(FileReader fis = new FileReader("FileInputStreamTest.txt");
				/*
				 * �������FileReader��ȡ�ļ��Ĺ����У�FileReader�̳���InputStreamReader��
				 * ����û��ʵ�ָ����д��ַ��������Ĺ��캯��������FileReaderֻ�ܰ�ϵͳĬ�ϵ��ַ��������룬
				 * Ȼ����UTF-8 -> GBK -> UTF-8�Ĺ����б��������ʧ����ɽ�����ܻ�ԭ������ַ�
				 * 
				 * Windows�¼�������Ĭ��ʹ��GBK�ַ�������Linux����Ĭ��ʹ��UTF-8�ַ���������������
				 */
		    //�˷������Խ�� ��������
		    InputStreamReader isr = new InputStreamReader(new FileInputStream("FileInputStreamTest.txt"),"GBK");
				
				
			FileInputStream fisss = new FileInputStream("FileInputStreamTest.txt")){
			//��������Ϊ1024����Ͳ
			char[] bbuf = new char[1024];
			//���ڱ���ʵ�ʶ�ȡ���ֽ���
			int hasRead = 0;
			//ѭ��ȡˮ�Ĺ���
			while((hasRead = fis.read(bbuf))>0){
//				System.out.println("�Ƿ�֧��mark��"+fis.markSupported());
//				fis.skip(2);
				String print = new String(bbuf,0,hasRead);
				System.out.println(new String(print.getBytes(),"GBK"));
				System.out.println(new String(print.getBytes(),"UTF-8"));//���벻һ�£����ĳ���

				System.out.println("@@@@@-------------------------------------------------------------------");

				System.out.println(print); //���ļ��ı��� Ҳ��GBK
				System.out.println("#####-------------------------------------------------------------------");

			}
			
			while((hasRead = isr.read(bbuf))>0){
//				System.out.println("�Ƿ�֧��mark��"+fis.markSupported());
//				fis.skip(2);
				System.out.println(new String(bbuf,0,hasRead));
				System.out.println("$$$$$-------------------------------------------------------------------");

			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
