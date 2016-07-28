package Chapter15;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterTest {
	public static void main(String[] args) {
		File file = new File(".");
		System.out.println(file.getAbsolutePath());
		//lamda������ʹ��lamda���ʽ��Ŀ������ΪFilenameFilter,ʵ���ļ�������
		String[] nameList = file.list((dir,name) -> name.endsWith(".java")||new File(name).isDirectory());
		
		for (String string : nameList) {
			System.out.println(string);
		}
		
		//��ͨ������
		String[] nameList2 = file.list(new FilenameFilter(){//�����ڲ���  ʵ���˶�����д�˶���ķ������Ƿ������ڸ� ����ı�����ֵ����
			@Override
			public boolean accept(File dir, String name) {//��д�˷���
				// TODO Auto-generated method stub				
				return name.endsWith(".java")||new File(name).isDirectory();
			}			
		});
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		for (String string : nameList2) {
			System.out.println(string);
		}
		
		//�Լ�д�� ����ʽ�ӿ�
		new FilenameFilterTest().bool(stringla->stringla.endsWith("la"));//��д�˽ӿڵķ�������ʵ����һ���ӿڶ���һ�ζ�����Ϊbool�Ĳ���
		

	}
	
	public void bool(testLamdaInteface in){
		int i=0;
		if(in.judge("tonglad")){
			System.out.println("��ȷ��");
		}else{
			System.out.println("�����");
		}
	}

}
