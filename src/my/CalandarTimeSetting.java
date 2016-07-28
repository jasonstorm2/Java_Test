package my;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalandarTimeSetting {
	static Calendar calendar = Calendar.getInstance(); 
	
	/*
     * ���������ʱ���ȡ���������
     */
    public static void getTime(long ll){
    	Calendar calendar = Calendar.getInstance(); 
        calendar.setTimeInMillis(ll);
        String str;
	    str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());  
	    System.out.println("���������ʱ���õ������ǣ�"+str);

    }
    
    public static void getTime2(long ll){
    	Calendar calendar = Calendar.getInstance(); 
        calendar.setTimeInMillis(ll);
        String str;
	    str = (new SimpleDateFormat("HH:mm:ss")).format(calendar.getTime());  
	    System.out.println("���������ʱ���õ������ǣ�"+str);

    }
    
    public static void getTimeByCalendarInstance(){
    	Calendar ca = Calendar.getInstance();
    	SimpleDateFormat simdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
    	Date date = ca.getTime();
    	String str = simdate.format(date);
    	System.out.println("��ӡһ��calendarʵ����ʱ�䣺"+str);
    	
    	System.out.println("�ܵĵ�һ�죺"+ca.getFirstDayOfWeek());
    	System.out.println("���գ�"+Calendar.SUNDAY);
    	//SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, Constant Field Values

    	System.out.println("��һ��"+Calendar.MONDAY);
    	System.out.println(Calendar.TUESDAY);
    	System.out.println(Calendar.WEDNESDAY);
    	System.out.println(Calendar.THURSDAY);
    	
    }
	public static void main(String[] args)  
	  {  
	    // �ַ���ת�����ڸ�ʽ  
	    DateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    // �õ����ڸ�ʽ����  
//	    Date date = fmtDateTime.parse(strDateMake);  
	  
	    // ������ʾ����ʱ��--��һ��date����ת��Ϊ�ض��ĸ�ʽ  
	    String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(new Date());  //Formats a Date into a date/time string.
	    System.out.println("DateFormat.format(Date date):"+str);  
	  
	    // ���� Calendar ����  
	    Calendar calendar = Calendar.getInstance();  
	    // ��ʼ�� Calendar ���󣬵�������Ҫ��������Ҫ����ʱ��  
	    long ll = System.currentTimeMillis();
//	    calendar.setTime(new Date());  
	    calendar.setTimeInMillis(ll);
	    System.out.println("��ǰʱ��ĺ�������"+ll);
	  
	    // setTime ��������һ��  
	    // Date date = new Date();  
	    // calendar.setTime(date);  
	  
	    // ��ʾ���  
	    int year = calendar.get(Calendar.YEAR);  
	    System.out.println("YEAR is = " + String.valueOf(year));  
	  
	    // ��ʾ�·� (��0��ʼ, ʵ����ʾҪ��һ)  
	    int MONTH = calendar.get(Calendar.MONTH);  
	    System.out.println("MONTH is = " + (MONTH + 1));  
	  
	    // ����ĵ� N ��  
	    int DAY_OF_YEAR = calendar.get(Calendar.DAY_OF_YEAR);  
	    System.out.println("DAY_OF_YEAR is = " + DAY_OF_YEAR);  
	  
	    // ���µ� N ��  
	    int DAY_OF_MONTH = calendar.get(Calendar.DAY_OF_MONTH);  
	    System.out.println("DAY_OF_MONTH = " + String.valueOf(DAY_OF_MONTH));  
	  
	    // 3Сʱ�Ժ�  
	    calendar.add(Calendar.HOUR_OF_DAY, 3);  
	    int HOUR_OF_DAY = calendar.get(Calendar.HOUR_OF_DAY);  
	    System.out.println("HOUR_OF_DAY + 3 = " + HOUR_OF_DAY);  
	  
	    // ��ǰ������  
	    int MINUTE = calendar.get(Calendar.MINUTE);  
	    System.out.println("MINUTE = " + MINUTE);  
	  
	    // 15 �����Ժ�  
	    calendar.add(Calendar.MINUTE, 15);  
	    MINUTE = calendar.get(Calendar.MINUTE);  
	    System.out.println("MINUTE + 15 = " + MINUTE);  
	  
	    // 30����ǰ  
	    calendar.add(Calendar.MINUTE, -30);  
	    MINUTE = calendar.get(Calendar.MINUTE);  
	    System.out.println("MINUTE - 30 = " + MINUTE);  
	  
	    // ��ʽ����ʾ  
	    str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());  
	    System.out.println(str);  
	  
	    // ���� Calendar ��ʾ��ǰʱ��  
	    calendar.setTime(new Date());  
	    str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());  
	    System.out.println(str);  
	  
	    // ����һ�� Calendar ���ڱȽ�ʱ��  
	    Calendar calendarNew = Calendar.getInstance();  
	  
	    // �趨Ϊ 5 Сʱ��ǰ�����ߴ���ʾ -1  
	    calendarNew.add(Calendar.HOUR, -5);  
	    System.out.println("ʱ��Ƚ�-5��" + calendarNew.compareTo(calendar));  
	  
	    // �趨7Сʱ�Ժ�ǰ�ߴ���ʾ 1  
	    calendarNew.add(Calendar.HOUR, +7);  
	    System.out.println("ʱ��Ƚ�+7��" + calendarNew.compareTo(calendar));  
	  
	    // �˻� 2 Сʱ��ʱ����ͬ����ʾ 0  
	    calendarNew.add(Calendar.HOUR, -2);  
	    System.out.println("ʱ��Ƚ�-2:" + calendarNew.compareTo(calendar)); 
	    
	    
	    calendar.set(Calendar.HOUR, 4);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
        System.out.println("���ĺ�����������"+calendar.getTimeInMillis());
        System.out.println("�Ƿ���㣿"+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DAY_OF_MONTH)+calendar.get(Calendar.HOUR)+calendar.get(Calendar.MINUTE));
	    
	    //�õ���������
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.DATE, 1);//����������Ϊ���µ�һ��  
	    a.roll(Calendar.DATE, -1);//���ڻع�һ�죬Ҳ�������һ��  
	    int maxDate = a.get(Calendar.DATE); 
	    System.out.println("����������"+maxDate);
	    a.get(Calendar.MONTH);
	    System.out.println("���·ݣ�"+a.get(Calendar.MONTH));
	    
	    getTime(1452569051917l);
	    getTime(1452828251917L);
	    getTimeByCalendarInstance();
	    
	  }  

}
