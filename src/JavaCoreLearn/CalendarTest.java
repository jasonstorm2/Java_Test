package JavaCoreLearn;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTest {
	public  String test = "test";
	
	public void setTest(CalendarTest ca,String str){
		ca.test=str;
	}
	
	
	public static void main(String[] args) {
//		int s = Integer.valueOf("s");
		int d = Integer.parseInt("s");
		
		GregorianCalendar ca = new GregorianCalendar();
		

		
		int today = ca.get(Calendar.DAY_OF_MONTH);//��¼���պ���
		int month = ca.get(Calendar.MONTH);//��¼�����·�
		

		ca.set(Calendar.DAY_OF_MONTH, 1);//����������Ϊ���µ�1��
		
		int weekday = ca.get(Calendar.DAY_OF_WEEK);
		int firstdayofweek = ca.getFirstDayOfWeek();

		int indent = 0;
		while(weekday!=firstdayofweek){
			indent++;
			ca.add(Calendar.DAY_OF_MONTH, -1);			
			weekday = ca.get(Calendar.DAY_OF_WEEK);
		}
		System.out.println("weekday:"+weekday);
		System.out.println("indent**:"+indent);
		
		String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
		System.out.println("ca����һ�죺"+ca.get(Calendar.DAY_OF_MONTH));
		do {
			System.out.printf("%4s",weekdayNames[weekday]);
			ca.add(Calendar.DAY_OF_MONTH, 1);
			weekday = ca.get(Calendar.DAY_OF_WEEK);
		} while (weekday!=firstdayofweek);
		System.out.println();
		
		for(int i=1;i<=indent;i++){//һ��ǰ��Ŀհ�
			System.out.print("       ");
		}
		
		ca.set(Calendar.DAY_OF_MONTH, 1);
		
		do {
			int day = ca.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d",day);
			//������ͨ�����Ӻ���������
			if(day == today){
				System.out.print("*   ");
			}else System.out.print("    ");
			
			ca.add(Calendar.DAY_OF_MONTH, 1);//����ǰ��һ��
			weekday = ca.get(Calendar.DAY_OF_WEEK);
			
			if(weekday==firstdayofweek)System.out.println();//����������ܵĵ�һ��������һ��
			
		} while (ca.get(Calendar.MONTH)==month);//���ǵ�������������
		
		if(weekday!=firstdayofweek){
			System.out.println();
		}
		
		
		
//		int cnt = 1;
//		do{			
//			if(month==ca.get(Calendar.MONTH)){
//				System.out.print(ca.get(Calendar.DAY_OF_MONTH)+"^");	
//				System.out.print(ca.get(Calendar.DAY_OF_WEEK)+"   ");
//			}
//			ca.add(Calendar.DAY_OF_MONTH, 1);
//			cnt++;
//			if(cnt>=7){
//				System.out.println();
//				cnt=0;
//			}
//		}while(month==ca.get(Calendar.MONTH));
		
		CalendarTest cad = new CalendarTest();
		cad.setTest(cad,"metoo");
		System.out.println(cad.test);
	}
		
}
