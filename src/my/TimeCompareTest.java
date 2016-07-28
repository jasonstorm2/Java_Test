package my;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeCompareTest {
	
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
	
	public static void main(String[] args) {
		//Current Time
		long cutime = System.currentTimeMillis();
		String nana ="1421154699843";
		//Time format
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd HH:mm");//ʱ��ת������yyyyMMdd is the FORMAT to transform
		String timeNow=sim.format(cutime);
				
		System.out.println(cutime);		
		System.out.println(timeNow);		
		
		
		//��������
		Calendar a = Calendar.getInstance();  
	    a.set(Calendar.DATE, 1);//����������Ϊ���µ�һ��  
	    a.roll(Calendar.DATE, -1);//���ڻع�һ�죬Ҳ�������һ��  
	    int maxDate = a.get(Calendar.DATE);  
	    System.out.println(maxDate);
	    
	    //�յ�����
	    int moonlen[] =new int[maxDate];
//	    for(int i=0;i<maxDate;i++){
//	    	moonlen[i]=0;
//	    	System.out.println("i:="+i+"����ֵ��"+moonlen[i]);
//	    }
	    //����ת���ַ���
	    String str="0";
//	    for(int i=0;i<maxDate;i++){
//	    	str+=String.valueOf(moonlen[i])+",";
//	    }
	    for(int i=1;i<maxDate;i++){
	    	str+=",0";
	    }
	    System.out.println(str);
	    
	    //�ж�����ʱ��
		int aDayBefore=getDayBefore(cutime);
		System.out.println("һ��ǰ����:"+aDayBefore);
		//��ȡ5Сʱ
		long time2=getTimeHourOfToday(cutime, 5);
		String later=sim.format(time2);
		System.out.println("5Сʱ"+later);
		//�ƺ�5Сʱ
		long time5=getTimeHourPastFive(cutime, 25);
		String later5=sim.format(time5);
		System.out.println("5Сʱ��:"+later5);
		
//		Calendar ca = Calendar.getInstance();		
//		ca.setTimeInMillis(time2);
		
		int [] getMoon={1,1,1,1,1,1,1,1,1,1,1};
		
		String str2="";
		for(int i=0;i<getMoon.length;i++){
			str2+=getMoon[i]+",";
		}
		
		System.out.println(str2);
		
		System.out.println(str2.substring(0,str2.length()-1));
		

		
	}

}
