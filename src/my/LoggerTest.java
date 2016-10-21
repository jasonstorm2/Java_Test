package my;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
   
   log4j2�������ļ��������¶�λ��
   
   ���û������"log4j.configurationFile" system property�Ļ���application����classpath�а������²���˳�����������ļ���
����log4j2-test.json ��log4j2-test.jsn�ļ�
����log4j2-test.xml�ļ�
����log4j2.json ��log4j2.jsn�ļ�
����log4j2.xml�ļ�
   
   1���������ϵķ���������System property��
   2������������.classpath�ļ��������䣺<classpathentry kind="lib" path="../config"/>---��������classpath��ȡ��·��
   
   
 * @author Jason
 *
 *
 * ������� slf4j-api-1.7.21.jar  ������İ汾
 * 
 *  log4j-api.jar , log4j-core.jar ��һ�Զ�Ӧ��
 *  ʹ�� log4j-slf4j-imp ����slf4j-log4j12-1.7.21  ����Ϊʲô�أ���
 */
public class LoggerTest {
	static Logger logger = LogManager.getLogger(Logger.class.getName());

	
	public LoggerTest(){
//		logger.entry();
		logger.error("����error��Ϣ");
		logger.debug("����debug��Ϣ");
		logger.warn("����warn��Ϣ");		
		logger.fatal("����fatal��Ϣ");
		logger.log(Level.DEBUG,"����debug��Ϣ");//������Ƕ���level���͵ĵ���
//		logger.exit();//��entry��Ӧ�Ľ�������
	}
	public static void main(String[] args) {
		
		System.out.println("ϵͳ�������ԣ�jin");
		System.out.println(System.getProperties());
		
		
		LoggerTest logt= new LoggerTest();
		logger.info("this is a message");
//		
		org.slf4j.Logger  logger2 =  LoggerFactory.getLogger("GAME");
		String d = "******";
		logger2.info("yes :{} and wahrt",d);
	}

}
