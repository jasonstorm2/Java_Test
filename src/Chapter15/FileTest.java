package Chapter15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/*
 * windows·���ָ����Ƿ�б��\,java��·���ָ�����б��/��Ҳ������˫��б��\\
 * ��Ե�ַ��������ַ�ǣ�System.getProperty("user.dir")������Ŀ�ĵ�ַ������Ŀ��ַ�ǣ�D:\WorkSpace1\Java_Test
 */
public class FileTest {
	public static void main(String[] args) throws IOException {
		//�Ե�ǰ·��������һ��File����--��Ŀ��·��
		File file = new File(".");  //-----------------------------------�������󲢷Ǵ�����һ���ļ����ļ��У�
		/****ע����Ե�ַ������****/
		FileInputStream inputFile = new FileInputStream("./FileInputStreamTest.txt");			//******��Ŀ¼�£���("FileInputStreamTest.txt")���һ��
		InputStream inputFile2 = FileTest.class.getResourceAsStream("./FileInputStreamTest.txt");//******.class�ĵ�ǰĿ¼
		InputStream inputFile3 = FileTest.class.getResourceAsStream("FileInputStreamTest.txt");//******.class�ĵ�ǰĿ¼
		System.out.println(inputFile);
		System.out.println(inputFile2);
		System.out.println(inputFile3);
		byte[] b = new byte[1024];
		int hasRead = 0;
		if((hasRead = inputFile3.read(b))>0){
			System.out.println("���ļ��ж�ȡ������:"+new String(b,0,hasRead));  
		}
		
//		�ܽ�һ�£�����ֻ������д��
//		��һ��ǰ���� ��   / ��
//		�� / �������˹��̵ĸ�Ŀ¼�����繤��������myproject���� / ��������myproject
//		me.class.getResourceAsStream("/com/x/file/myfile.xml");
//		�ڶ���ǰ��û�� ��   / ��
//		����ǰ���Ŀ¼
//		me.class.getResourceAsStream("myfile.xml");
//		me.class.getResourceAsStream("file/myfile.xml");
		
		//ֱ�ӻ�ȡ�ļ��������һ��
		System.out.println("�ļ���"+file.getName());
		//��ȡ���·���ĸ� ·�����ܳ�������������null
		System.out.println("���ļ�����"+file.getParent());
		//��ȡ����·��
		System.out.println("����·����"+file.getAbsoluteFile());
		//��ȡ��һ������·��
		System.out.println("��һ���ľ���·����"+file.getAbsoluteFile().getParent());
		
		//�ڵ�ǰ·���´���һ����ʱ�ļ�
		File tmpFile = File.createTempFile("aaaaaaaaaaaaaa", ".txt",file);
		//��JVM�˳�ʱ��ɾ�����ļ�
//		tmpFile.deleteOnExit();
		
		//��ϵͳ��ǰʱ�� ��Ϊ���ļ��������� ���ļ�
		File newFile = new File(System.currentTimeMillis() + "time2.txt");
		System.out.println("newFile�Ƿ���ڣ�"+newFile.exists());
		//��ָ�� newFile����������һ���ļ�
		newFile.createNewFile();  //---------------------------------------------ͨ�����󴴽�һ���ļ�����
		System.out.println("newFile�Ƿ���ڣ�"+newFile.exists());
		
		//�� newFile ����������һ��Ŀ¼(���ļ���)����ΪnewFile�Ѿ�����
		//�������淽������false�����޷�������Ŀ¼		
		newFile.mkdir();          //---------------------------------------------ͨ�����󴴽�Ŀ¼�����ļ��У����������ļ�ͬ��
		
		//ʹ��list()�����г� ��ǰ·���� �������ļ���·��
		String[] fileList = file.list();
		System.out.println("**********��ǰ·���µ������ļ���·��***********");
		for(String f : fileList){
			System.out.println(f);
		}
		
		
		//listRoots()��̬�����г����еĴ��̸�·��
		File[] roots = File.listRoots();
		System.out.println("========ϵͳ���и�·������=========");
		for (File f : roots) {
			System.out.println(f);
		}
	}
}
