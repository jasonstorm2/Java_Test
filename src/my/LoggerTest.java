package my;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerTest {
	//1,get logger
	//ͨ��ָ�������ֻ�ü�¼�����������Ҫ�Ļ�����Ϊ������ִ���һ���µļ�¼����Nameһ��ȡ���������
	static Logger logger = Logger.getLogger(LoggerTest.class);
	static{
		 PropertyConfigurator.configure("log4j.properties");
	}
	
	public LoggerTest(){
		logger.debug("wtf");
		logger.warn("destny");		
	}
	public static void main(String[] args) {
    //2.��ȡ�����ļ�
        //BasicConfigurator.configure ()�� �Զ����ٵ�ʹ��ȱʡLog4j������
		//DOMConfigurator.configure ( String filename ) ����ȡXML��ʽ�������ļ���
		
		
		LoggerTest logt= new LoggerTest();
		logger.info("this is a message");
	}

}
