package Chapter15;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class RedirectOut {
	public static void main(String[] args) {
		try(PrintStream ps = new PrintStream(new FileOutputStream("outlog.txt"));
		    PrintStream out = System.out){
			//�� ��׼����� ���¶��� ps�����
			System.setOut(ps);
			System.out.println("��ӡ���");
			System.out.println(new RedirectOut());
			
			//�ڶ���Ϊ���---Ϊʲôֱ����System.out�����ء���Ҫ����һ��PrintStream
			System.setOut(out);
			System.out.println("�ٴβ���");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
