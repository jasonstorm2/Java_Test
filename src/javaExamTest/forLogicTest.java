package javaExamTest;

/**
 * for����ѭ�����߼�
 * @author Administrator
 *
 */

/*
 * for()���߼���:
 *      for(��ʼ����䣻�жϱ��ʽ���������){������}
 *      ��ʼ����䣺ִ��һ�Σ�
 *      �жϱ��ʽ���ɹ���ִ�з����壬ִ�е�����䣻ʧ�ܣ�����ѭ����
 *      �������  ������
 */
public class forLogicTest {
    static boolean foo(char c) {
       System.out.print(c);
       return true;
    }
    public static void main(String[] argv) {
       int i = 0;
       //for(65;88&&(i<2);67)
       for (foo('A'); foo('B') && (i < 2); foo('C')) {
           i++;
           foo('D');
       }
    }
}