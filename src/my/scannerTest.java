package my;

import java.util.Scanner;

public class scannerTest {
	public static void main (String[] args) 
        {
	       double radius;
           double volume;
           double high;
           Scanner input = new Scanner(System.in);
           System.out.print("������뾶�͸�,�Կո�Ϊ�ָ���:");
           radius = input.nextDouble(); 
           high = input.nextDouble();
           
           input.close();//scannerҪ�ر�

           volume = Math.PI * radius * radius * high;
           System.out.println("Բ��������:" + volume);	
				
	}
}