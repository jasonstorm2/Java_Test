package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class GetPostTest {
	
	public static String sendGet(String url,String param){
		String res = "";
		String urlName = url + "?" + param;
		
		try {
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();
			//����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
			//����ʵ������
			conn.connect();
			//��ȡ���е���Ӧͷ�ֶ�
			Map<String,List<String>> map = conn.getHeaderFields();
			System.out.println("************��ӡ������Ӧͷ�ֶ�--��ʼ*************");
			for (String key : map.keySet()) {
				System.out.println("key:"+key+"---->"+" value:"+map.get(key));
				
			}
			System.out.println("************��ӡ������Ӧͷ�ֶ�--����*************");
			
			try(//����BufferedReader ����������ȡURL����Ӧ
					BufferedReader in = new BufferedReader(	new InputStreamReader(conn.getInputStream(),"utf-8"))
				){
				String line;
				while ((line = in.readLine())!= null) {
					res += "\n" +line;
				}
				
			}
					
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("GET��������쳣��"+e);
			e.printStackTrace();
		}		
		return res;
	}
	
	
	public static String senPost(String url,String param){
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			//����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
			//���� POST �������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			try(
					//��ȡ URLConnection�����Ӧ�������
					PrintWriter out = new PrintWriter(conn.getOutputStream())){
				//�����������
				out.print(param);
				//flush ������Ļ���
				out.flush();
			}
			
			try(
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"))
					){
				String line;
				while((line = in.readLine()) != null){
					result += "\n" + line;
					
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("��POST��������쳣"+e);
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public static void main(String[] args) {
		String s = GetPostTest.sendGet("https://www.baidu.com/", null);
		System.out.println("��ӡ�����");		
		System.out.println(s);
		System.out.println("***************************************post test*********************************");

		String s1 = GetPostTest.senPost("https://www.baidu.com/", null);
		System.out.println(s1);
		
	}
}
