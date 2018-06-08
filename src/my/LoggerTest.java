package my;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.slf4j.LoggerFactory;

/**
 * apache log4j��ʹ��
 * 
 * slf4j ��ʹ�ã�SLF4J����˵��Java�ļ򵥼�¼��־���û��������ʵ����־��¼��
 * �෴��ֻ��һ��������ʹ���κδ��ں�˵���־��¼��ĳ���� 
 * 
 * slf4j���ŵ㣺
 * 1.����ĳ���������κ��ض�����־��¼��
 * 2.ռλ�����ܣ��ڴ�������{}��ʾ������������ʱ�̲���ȡ���ṩ���������ַ�����
 *   �ⲻ�������˴����е�����ַ������ӣ����Ҽ����˴���String��������Ҫ����Դ
 *   
 * ������ϵͳ����slf4j-api��Ϊ��־����Ľӿڡ�
 *   compileʱslf4j-api��public final class LoggerFactor����private final static void bind()������Ѱ�Ҿ������־ʵ����󶨣�
 *   ��Ҫͨ��StaticLoggerBinder.getSingleton()�������á�
 *   
   ��slf4j-log4j12������slf4j-api��log4j�м����������
   ��ʵ����slf4j-apiz��StaticLoggerBinder�ӿڣ��Ӷ�ʹ���ڱ���ʱ�󶨵���slf4j-log4j12��getSingleton()������
   
   ��log4j�Ǿ������־ϵͳ��ͨ��slf4j-log4j12��ʼ��Log4j���ﵽ������־�������
   ԭ����ַ��http://blog.csdn.net/tengdazhang770960436/article/details/18006127
   
 * log4j2��log4j1�ǲ�һ���ģ����������ļ��ж��֡�Log4j��������д��log4j.properties�ļ����棬����Log4j2�Ϳ���д��XML��JSON�ļ����ˡ�
   
 * log4j2�������ļ��������¶�λ��
   ���ý��Զ����ء�automatically reload its configuration upon modification!!
   ���û������"log4j.configurationFile" system property�Ļ���application����classpath�а������²���˳�����������ļ���
����log4j2-test.json ��log4j2-test.jsn�ļ�
����log4j2-test.xml�ļ�
����log4j2.json ��log4j2.jsn�ļ�
����log4j2.xml�ļ�
   
   1���������ϵķ���������System property��
   2������������.classpath�ļ��������䣺<classpathentry kind="lib" path="../config"/>
      ---��������classpath��ȡ��·�����������¿���Eclipse����Ȼû�а취������·��
   
   
 * lo4j2 �������ݣ�
   ��LOG4J�������ļ���,log4j.appender.CATNAME.layout.ConversionPattern���п�������־�����ʽ.���и�ʽ���������������. 

  %n - ����   
  %m - ��־����      
  %p - ��־����(FATAL,   ERROR,   WARN,   INFO,   DEBUG   or   custom)       
  %r - �������������ڵĺ�����    
  %% - percent   sign   in   output   
  %t - ��ǰ�߳���   
  %d   -  ���ں�ʱ��,    
  
  ���õĸ�ʽ�� %d{DATE}, %d{ABSOLUTE}, %d{HH:mm:ss,SSS}, %d{ddMMyyyy HH:mm:ss,SSS}������ 
    
  %l - ͬ %F%L%C%M   
  %F - javaԴ�ļ���   
  %L - javaԴ������   
  %C - java����,%C{1} ������һ��Ԫ��   ��ָ������Logger������name
  %M-java������   
  %n - ���� 
  %m - ��־����    
  %p - ��־����(FATAL,   ERROR,   WARN,   INFO,   DEBUG   or   custom)     
  %r - �������������ڵĺ�����  
  %% - percent   sign   in   output 
  %t - ��ǰ�߳��� 
  %d   -  ���ں�ʱ��,  



  ʾ���� 
  [%d{HH\:mm\:ss\:SSS}][%p] (%c\:%L) - %m%n   
  [%d{HH\:mm\:ss\:SSS}][%p] (%c\:%L) - %m%n 
  �����ʽΪ��[08:58:59:412][INFO] (com.soon.action:35) - ���������� 
   
   
 * @author Jason
 *
 *
 * ������� slf4j-api-1.7.12.jar  ������İ汾
 * 
 *  log4j-api-2.7.jar , log4j-core-2.7.jar log4j-slf4j-imp-2.7 ��һ��ģ���ͬһ������ѹ������
 *  ʹ�� log4j-slf4j-imp ����slf4j-log4j12-1.7.21  ����Ϊʲô�أ���
 */
public class LoggerTest {
	static Logger logger = LogManager.getLogger(Logger.class.getName());

	
	public LoggerTest(){
//		logger.entry();
		logger.fatal("����fatal��Ϣ");
		logger.error("����error��Ϣ");
		logger.warn("����warn��Ϣ");
		logger.info("����info��Ϣ");
		logger.debug("����debug��Ϣ");
		logger.trace("����trace��Ϣ");
		

		
		logger.log(Level.DEBUG,"����debug��Ϣ");//������Ƕ���level���͵ĵ���
//		logger.exit();//��entry��Ӧ�Ľ�������
	}
	public static void main(String[] args) {		
		LoggerTest logt= new LoggerTest();
		logger.info("this is a message");
//		
		org.slf4j.Logger  logger2 =  LoggerFactory.getLogger("CORE");
		String d = "******";
		logger2.error("error");
		logger2.warn("warn");		
		logger2.info("info{}",d);
		logger2.debug("debug");
		logger2.trace("trace");
		
		/********************************/
		String str = "{}����ɵ��";		
		System.out.println(LoggerTest.StringMax(str, 1));
	}
	
	/**
	 * log4j���ַ�����װ����
	 * @param str
	 * @param objs
	 */
	public static String StringMax(String str,Object... objs){
		return ParameterizedMessage.format(str, objs);
	}

}
