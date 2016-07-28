package Chapter15;

import java.io.StringReader;
import java.io.StringWriter;

public class StringNodeTest {
	public static void main(String[] args) {
		String src = "����������һ���Ҹ�����\nι��,����,��������\n";
		char[] buffer = new char[32];
		int hasRead = 0;
		try(StringReader sr = new StringReader(src)){// �� �ַ��� Ϊ����ڵ�
			while((hasRead = sr.read(buffer))>0){
				System.out.println(new String(buffer,0,hasRead));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			}
		
		//����StringWriterʱ��ʵ��������һ��StringBuffer��Ϊ����ڵ��
		//����ָ����20����StringBuffer�ĳ�ʼ����
		try(StringWriter sw = new StringWriter(20)){
			sw.write("����������-���\n��һ��������������\n����Զ������\n����������ĺ���\n���й���ľ���\n");
			System.out.println("----------������sw�ַ����ڵ��������");
			System.out.println(sw.toString());
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
