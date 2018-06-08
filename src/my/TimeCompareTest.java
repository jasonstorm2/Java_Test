package my;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * calendar����ĸ����÷�
 * �Լ�java8��ʱ���ȡ����
 * 
 * 
 * java8������һ��ȫ�µ�ʱ������API����ƪ��ʽ�˵��ѧϰjava8������API��

   java��time���е������ǲ��ɱ����̰߳�ȫ�ġ��µ�ʱ�估����APIλ��java.time�У�������һЩ�ؼ���

��Instant�������������ʱ���

��LocalDate��������������ʱ������ڣ�����2014-01-14�������������洢���գ���������գ���ְ���ڵȡ�

��LocalTime������������ǲ������ڵ�ʱ��

��LocalDateTime���������������ڼ�ʱ�䣬��������û��ƫ����Ϣ����˵ʱ����

��ZonedDateTime��������һ������ʱ��������������ʱ�䣬ƫ��������UTC/��������ʱ��Ϊ��׼��
 * @author Administrator
 *
 */
public class TimeCompareTest {

	
	public static void main(String[] args) {
		
		//**********��ʽת��:ע�� yyyy/MM/dd �ȣ�����ĸ�Ĵ�Сд�й�ϵ*************
		//��ǰʱ��
		long cutime = System.currentTimeMillis();
		//ʱ���ʽ����
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd HH:mm");//ʱ��ת������yyyyMMdd is the FORMAT to transform
		//ʱ���ʽ���� ����format(time) ת�� ��ǰʱ�� Ϊ String����
		String timeNow=sim.format(cutime);				
		System.out.println(cutime);		
		System.out.println(timeNow);		
		
		//**********Calendar���� ��һЩ��������*************
		// ���ʵ������ʵ������ʵ�����Ի�ȡ��ǰ�������գ�ʱ��ȣ�������������Щʱ��
		Calendar ca = Calendar.getInstance();
		Date date = ca.getTime(); //���ʱ��
		long time = ca.getTimeInMillis();
		//set(field��value)����ĳ����ָ��ֵ
		ca.set(Calendar.DAY_OF_MONTH, 1); 		 
		System.out.println("��ǰ�գ�"+ca.get(Calendar.DAY_OF_MONTH));
		System.out.println("��ǰ�£�"+ca.get(Calendar.MONTH));
		//add(field��value) ���ӻ��߼�ȥָ����ֵ
		ca.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println("����һ�գ�"+ca.get(Calendar.DAY_OF_MONTH));
		
		System.out.println("��ǰʱ��gettime��"+ca.getTime());
		
		System.out.println("***********************************");
		//**********java8 LocalDateTime���� ��һЩ��������*************
		LocalDateTime localTime = LocalDateTime.now();
		System.out.println("�꣺"+localTime.getYear());		
		System.out.println("�£�"+localTime.getMonthValue());
		System.out.println("�գ�"+localTime.getDayOfMonth());
		System.out.println("ʱ"+localTime.getHour());		
		System.out.println("��"+localTime.getMinute());
		System.out.println("��"+localTime.getSecond());
		System.out.println("�µ�Ӣ�ģ�"+localTime.getMonth());		
		System.out.println("һ���еĵڼ��죺"+localTime.getDayOfYear());
		System.out.println("�ܵĵڼ���Ӣ�ģ�"+localTime.getDayOfWeek());
		
		System.out.println(localTime.getNano());
		System.out.println("getlong:"+localTime.getLong(ChronoField.MILLI_OF_DAY));
		System.out.println("��ǰʱ�䣺"+Clock.systemDefaultZone().millis()); //java8
		
		DateTimeFormatter formate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String mate = localDate.format(formate);
		System.out.println("��ǰʱ��java8��"+mate);
		LocalDateTime lo = localTime.minusDays(1);
		
		System.out.println("�������ڣ�"+lo.getDayOfMonth());
		System.out.println("����ĵ�ǰ���ڣ�"+lo.toLocalDate());
		System.out.println("����ĵ�ǰʱ�̣�"+lo.toLocalTime());
		System.out.println("����ĵ�ǰʱ�䣺"+lo);
		
		
		
		System.out.println("***********************************");
		
		//��õ�������
		getTotalDaysOfThisMonth(Calendar.getInstance());
		getTotalDaysOfThisMonth2(Calendar.getInstance());
	    //�ж�����ʱ��
		int aDayBefore=getDayBefore(cutime);
		System.out.println("һ��ǰ�Ǽ���:"+aDayBefore);
		//��ȡָ��calendar��5ʱʱ��
		long time2=getTimeHourOfToday(cutime, 5);
		String later=sim.format(time2);
		System.out.println("5Сʱ��"+later);
		//��ȡָ��calendar���Ƴ�5ʱ��ʱ��
		long time5=getTimeHourPastFive(cutime, 5);
		String later5=sim.format(time5);
		System.out.println("5Сʱ��:"+later5);
	}
	
	
	/**
	 * ��ȡǰһ�������
	 * @param time ȡ�����賿�Ļ����� System.currentTimeMillis() ����
	 * @return
	 */
	public static int getDayBefore(long time) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time);
		ca.add(Calendar.DATE, -1);
        return ca.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * ��ȡ�����ָ��Сʱ��ʱ��
	 * @param time ȡ�����賿�Ļ����� System.currentTimeMillis() ����
	 * @return hour ָ��Сʱ
	 * @return
	 */
	public static long getTimeHourOfToday(long time, int hour) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time);
		ca.set(Calendar.HOUR_OF_DAY, hour);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		
		return ca.getTimeInMillis();
	}
	
	/**
	 * ��ȡ�����ָ��Сʱ��ʱ��
	 * @param time ȡ�����賿�Ļ����� System.currentTimeMillis() ����
	 * @return hour ָ��Сʱ
	 * @return
	 */
	public static long getTimeHourPastFive(long time, int hour) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time);
		ca.add(Calendar.HOUR_OF_DAY, hour);		
		return ca.getTimeInMillis();
	}
	
	/**
	 * ��ñ��µ����������roll�ع���ֻ������»ع�
	 * @param a
	 */
	public static void getTotalDaysOfThisMonth(Calendar a){
		System.out.println("��ǰʱ��Date��"+a.getTime());
		System.out.println("��ǰʱ�䣺"+a.getTimeInMillis());
		//set(��ֵ),set����Calendar������ֵ
	    a.set(Calendar.DATE, 1);//����������Ϊ���µ�һ��  
	    a.roll(Calendar.DATE, -1);//���ڻع�һ�죬Ҳ�������һ��  
	    int maxDate = a.get(Calendar.DATE);  
	    System.out.println("�����������:"+maxDate);
	}
	
	
	/**
	 * ��ñ��µ��������
	 * @param a
	 */
	public static void getTotalDaysOfThisMonth2(Calendar a){
	    System.out.println("�����������:"+a.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
	

}
