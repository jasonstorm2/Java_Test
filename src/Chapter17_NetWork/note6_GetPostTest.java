package Chapter17_NetWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


/**
 * 不管是发送GET请求，还是发送POST请求，获取URLConnection相应的方式一样
 * 如果可以确定远程响应是字符流，则可以使用字符流读取
 * 如果不能确定，则使用字节流读取即可。。。
 */
public class note6_GetPostTest {
	
	public static String sendGet(String url,String param){
		String res = "";
		String urlName = url;
		
		try {
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();
			//设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
			//建立实际连接
			conn.connect();
			//获取所有的响应头字段
			Map<String,List<String>> map = conn.getHeaderFields();
			System.out.println("************打印所有响应头字段--开始*************");
			for (String key : map.keySet()) {
				System.out.println("key:"+key+"---->"+" value:"+map.get(key));
				
			}
			System.out.println("************打印所有响应头字段--结束*************");
			
			try(//定义BufferedReader 输入流来读取URL的响应
					BufferedReader in = new BufferedReader(	new InputStreamReader(conn.getInputStream(),"utf-8"))
				){
				String line;
				while ((line = in.readLine())!= null) {
					res += "\n" +line;
				}
				
			}
					
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("GET请求出现异常！"+e);
			e.printStackTrace();
		}		
		return res;
	}
	
	
	public static String senPost(String url,String param){
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			//设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
			//发送 POST 请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			try(
					//获取 URLConnection对象对应的输出流
					PrintWriter out = new PrintWriter(conn.getOutputStream())){
				//发送请求参数
				out.print(param);
				//flush 输出流的缓冲
				out.flush();
			}
			
			try(//定义BufferedReader 输入流来读取URL的响应
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"))
					){
				String line;
				while((line = in.readLine()) != null){
					result += "\n" + line;
					
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("发POST请求出现异常"+e);
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public static void main(String[] args) {
		String s = note6_GetPostTest.sendGet("http://10.163.46.202:8080/taiyi/survey.txt", null);
		System.out.println("打印结果：");		
		System.out.println(s);
		System.out.println("***************************************post test*********************************");

		String s1 = note6_GetPostTest.senPost("http://10.163.46.202:8080/taiyi/survey.txt", null);
		System.out.println(s1);
		
	}
}
