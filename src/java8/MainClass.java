package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** 
* ���eclipse�Ƿ�֧��java8С���� 
* MainClass
* @author An Hui 
* */
public class MainClass {
	
	
	
  public static void main(String[] args) {
	// ��ȡԴ�ļ����µ�������
        /*
         * ��������һ���򵥵�lambda���ʽ�������Ƿ���Ա��������java8�Ĵ���
         */
        new Thread(() -> System.out.println("����һ��java8��С����,����ʹ��lambda���ʽ")).start();
        
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        Collections.sort(names, (a, b) -> b.compareTo(a));
    }
}
