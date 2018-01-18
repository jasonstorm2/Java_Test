package my;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 
 * UNC(Universal Naming Convention) / ͨ����������Ҳ��ͨ�������淶��ͨ������Լ����

 	���磨��Ҫָ������������Դ������ Windows 2000 ���ơ������� \\servername\sharename ��ʽ��
   ���� servername �Ƿ���������sharename �ǹ�����Դ�����ơ�
   Ŀ¼���ļ��� UNC ���ƿ��԰������������µ�Ŀ¼·������ʽΪ��

   \\servername\sharename\directory\filename��

   ��������������ϵ�Ŀ���ļ�����ʹ�á�ͨ������Լ�� (UNC)�� 
   �� UNC����ͳһ����Լ������ַ������ȷ������������������ϵ��ļ�λ�á�
      ��Щ��ַ��������б�� (\\) ��ͷ�����ṩ�������������������������ļ�·������
      
   ��ַ����Щ��ַ�ԡ�file:\\����ʼ���ṩ�������������������ļ�������·����
   ���磬��file:\\server\share\path\project file.mpp���Ǿ��� UNC ��ַ��
   
 * ������Ҫʹ�������System.getProperty("user.dir")��ǰ�û�Ŀ¼�����·��������һ�Ŷ�ʱը������ʱ����Ҫ�������
 * 
 * ����ʹ��URI��ʽ�ľ���·����Դ�������Ժ����׵�ת��ΪURI,URL��File����
 * 
 * ����ʹ�����classpath�����·������Ҫʹ�þ���·����
 * 
 * ���Բ�Ҫʹ��Ӳ����ľ���·������Ϊ��������ȫ����ʹ��ClassLoader���getResource("")�����õ���ǰclasspath�ľ���·����
 * 
 * @author Administrator 
 * 		   URL��һ���ʽΪ��
 * 
 *         scheme://host:port/path?query#fragment
 * 
 *         ���磺
 * 
 *         http://www.imailtone.com:80/WebApplication1/WebForm1.aspx?name=tom&;
 *         amp;age=20#resume
 * 
 *         scheme��ͨ��Э�鷽�����±��г� Scheme ���Ե���Ч�������ơ�
 * 
 *         file ��Դ�Ǳ��ؼ�����ϵ��ļ�����ʽfile:///(ע��������б��)
 * 
 *         ftp ͨ�� FTP������Դ����ʽ FTP://
 * 
 *         gopher ͨ�� Gopher Э����ʸ���Դ��
 * 
 *         http ͨ�� HTTP ���ʸ���Դ�� ��ʽ http:// https ͨ����ȫ�� HTTPS ���ʸ���Դ�� ��ʽ
 *         target=_blank>https://
 * 
 *         mailto ��ԴΪ�����ʼ���ַ��ͨ�� SMTP ���ʡ� ��ʽ mailto:
 * 
 *         MMS ͨ�� ֧��MMS����ý�壩Э��Ĳ��Ÿ���Դ�������������Windows Media Player����ʽ MMS://
 * 
 *         ed2k ͨ�� ֧��ed2k��ר���������ӣ�Э���P2P������ʸ���Դ���������������¿�� ��ʽ ed2k://
 * 
 *         Flashget ͨ�� ֧��Flashget:��ר���������ӣ�Э���P2P������ʸ���Դ��������������쳵�� ��ʽ Flashget://
 * 
 *         thunder ͨ�� ֧��thunder��ר���������ӣ�Э���P2P������ʸ���Դ�������������Ѹ�ף� ��ʽ thunder://
 * 
 *         news ͨ�� NNTP ���ʸ���Դ��
 * 
 *         host��������ָ���ķ�����������ϵͳ (DNS) �������� IP ��ַ��
 * 
 *         port���˿ںţ���������ѡ��ʡ��ʱʹ�÷�����Ĭ�϶˿ڣ���http��Ĭ�϶˿�Ϊ80��
 * 
 *         path��·�������������/�����Ÿ������ַ�����һ��������ʾ�����ϵ�һ��Ŀ¼���ļ���ַ��
 * 
 *         query����ѯ����ѡ�����ڸ���̬��ҳ����ʹ��CGI��ISAPI��PHP/JSP/ASP/ASP.NET�ȼ�����������ҳ�����ݲ�����
 *         ���ж���������á�&�����Ÿ�����ÿ������������ֵ�á�=�����Ÿ�����
 * 
 *         fragment����ϢƬ�ϣ��ַ���������ָ��������Դ�е�Ƭ�ϡ�����һ����ҳ���ж�����ʽ��ͣ���ʹ��fragmentֱ�Ӷ�λ��ĳһ���ʽ��͡�
 * 
 *         ע�⣬Windows ���������� URL ��Сд�����ǣ�Unix/Linux �������ִ�Сд��
 */
public class FileAndUrlAddressTest {
	public static void main(String[] args) throws Exception {
		File file = new File("D:/�ҵ�������/2.bat");
		System.out.println("File�ļ�·����"+file.getAbsolutePath());//File�ļ�·����D:\�ҵ�������\2.bat
		URL url0 = file.toURL();//����������������ķ�ʽ
		URL url = file.toURI().toURL();
		System.out.println("url.getPath(): "+url.getPath());	//URL�ļ�·��: /D:/�ҵ�������/2.bat---����һ��б�ܣ�
		System.out.println("url.getContent():"+url.getContent());  //sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@1d44bcfa
		System.out.println("url.getDefaultPort():"+url.getDefaultPort());
		System.out.println("url.getHost():"+url.getHost());
		System.out.println("url.getPort():"+url.getPort());
		System.out.println("url.getProtocol():"+url.getProtocol());
		System.out.println("url.getQuery():"+url.getQuery());
		System.out.println("url.getRef():"+url.getRef());
		System.out.println("url.getUserInfo():"+url.getUserInfo());
		System.out.println("url.getClass():"+url.getClass());
//		System.out.println("url.getPath():"+url.getContent(classes));
		
		
		FileAndUrlAddressTest ft = new FileAndUrlAddressTest();
		URL url2 = ft.getURL();
		System.out.println("URL2�ļ�·��: "+url2.getPath());	
		
		URL w3 = new URL("http://www.baidu.com");		
		System.out.println("w3.getContent():"+w3.getContent());  //sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@1d44bcfa
		System.out.println("w3.getPath():"+w3.getPath());
		System.out.println("w3.getDefaultPort():"+w3.getDefaultPort());
		System.out.println("w3.getHost():"+w3.getHost());
		System.out.println("w3.getPort():"+w3.getPort());
		System.out.println("w3.getProtocol():"+w3.getProtocol());
		System.out.println("w3.getQuery():"+w3.getQuery());
		System.out.println("w3.getRef():"+w3.getRef());
		System.out.println("w3.getUserInfo():"+w3.getUserInfo());
		System.out.println("w3.getClass():"+w3.getClass());
//		System.out.println("w3.getPath():"+w3.getContent(classes));
		
		
	}
	
	/**
	 * ��ø���� class�ļ���·������ǰ��FileAndUrlAddressTest.class�ļ���URIĿ¼
	 * /D:/WorkSpace1/Java_Test/bin/my/
	 * /D:/java_test/bin/my/
	 * @return
	 * 
	 */
	public URL getURL(){
		URL base = this.getClass().getResource("");//д������ļ����Ϳ��Է��ظ��ļ��ĵ�ַ
		return base;
	}
	
	/**
	 * ��ø���� class�ļ�����һ��·������ǰ��classpath�ľ���URI·��
	 * /D:/WorkSpace1/Java_Test/bin/
	 * /D:/java_test/bin/
	 * @return
	 */
	public URL getURL2(){
		URL base = this.getClass().getResource("/");//д������ļ����Ϳ��Է��ظ��ļ��ĵ�ַ
		return base;
	}
	
	/**
	 * ��ø���� class�ļ�����һ��·������ǰ��classpath�ľ���URI·��
	 * /D:/WorkSpace1/Java_Test/bin/
	 * /D:/java_test/bin/
	 * 
	 * �Ƽ��������������ȡclasspath�ľ���·����URI
	 * @return
	 */
	public URL getURL3(){
		URL base = Thread.currentThread().getContextClassLoader().getResource("");
		return base;
	}
	
	/**
	 * ��ø���� class�ļ�����һ��·������ǰ��classpath�ľ���URI·��
	 * /D:/WorkSpace1/Java_Test/bin/
	 * @return
	 */
	public URL getURL4(){
		URL base = FileAndUrlAddressTest.class.getClassLoader().getResource("");
		return base;
	}
	
	/**
	 * ��ø���� class�ļ�����һ��·������ǰ��classpath�ľ���URI·��
	 * /D:/WorkSpace1/Java_Test/bin/
	 * @return
	 */
	public URL getURL5(){
		URL base = ClassLoader.getSystemResource("");
		return base;
	}
}
