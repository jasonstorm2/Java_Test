package Chapter17_NetWork;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


/**
 * URL: uniform resource locator ͳһ��Դ��λ��������ָ�������е���Դ���ж�λ���� URL��ʽ��
 * protocol://host:port/resourceName Э�������������˿ڣ���Դ�� URI uniform resource
 * identifiers ͳһ��Դ��ʶ�������ܶ�λ�κ���Դ��Ψһ���þ��ǽ��� URL
 * �������Խ��������Ұ���һ���ɴ򿪵�����Դ������������URL��������һ��URI��������
 * 
 * ����URL���ӷ�ʽ��
 * // ����һ 
 * URL url = new URL("http://www.sina.com.cn"); URLConnection urlcon =
 * url.openConnection(); InputStream is = urlcon.getInputStream();
 * 
 * // ������ 
 * URL url = new URL("http://www.yhfund.com.cn"); HttpURLConnection
 * urlcon = (HttpURLConnection)url.openConnection(); InputStream is =
 * urlcon.getInputStream();
 * 
 * //������ 
 * URL url = new URL("http://www.yhfund.com.cn"); InputStream is =
 * url.openStream();
 * 
 * URL���ṩ�ķ�����
 * 
 * String getFile���� ��ȡ��URL����Դ�� String getHost() ��ȡ������ String getPate()
 * ��ȡ��URL��·������ int getPort() ��ȡ��URL�Ķ˿ں� String getProtocol() ��ȡ��URL��Э���� String
 * getQuery() ��ȡ��URL�� ��ѯ�ַ������� URLConnection openConnection() ����һ��URLConnection
 * �������������� URL �����õ�Զ�̶�������ӡ� InputStream openStream() �����URL�����ӣ�������һ�����ڶ�ȡ��
 * URL��Դ��InputStream����--ͨ���÷������Ժܷ���Ķ�ȡԶ����Դ�� ����ʵ�ֶ��߳�����
 */
public class note4_DownUtil {
	private String path;
	
	private String targetFile;
	
	private int threadNum;
	
	private DownThread[] threads;
	
	private int fileSize;
	
	/** 
	 * @param path  url��Դ·��
	 * @param targetFile ���ش��·��
	 * @param threadNum  �����߳�����
	 */
	public note4_DownUtil(String path,String targetFile,int threadNum){
		this.path = path;
		this.targetFile = targetFile;
		this.threadNum = threadNum;
		
		threads = new DownThread[threadNum];
		
	}
	
	public void download() throws Exception {
		
		URL url = new URL(path);
		//����һ��URLConnection��������������URL�����õ�Զ�̶��������
		//Ϊʲô���� openStream()�����أ������URL�����ӣ�������һ�����ڶ�ȡ��URL��Դ��InputStream
		
		//URLConnection ��ʾ ������URL֮���ͨ�����ӣ�HttpURLConnection��ʾ ��URL ֮���HTTP����
//		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		URLConnection conn = url.openConnection();

//		conn.setConnectTimeout(5* 1000);
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Accept", 
//				"image/gif,image/jpeg,image/pjpeg,"
//				+"application/x-shockwave-flash,application/xaml+xml,*/*");
//		conn.setRequestProperty("Accept-Language", "zh-CN");
//		conn.setRequestProperty("Charset", "UTF-8");
//		conn.setRequestProperty("Connection", "Keep-Alive");
		//�õ� �ļ���С
		fileSize = conn.getContentLength();
		System.out.println("�ļ���С��"+fileSize);
		System.out.println("�̸߳�����"+threadNum);
//		conn.disconnect();
		
		//�˴�Ϊ��+1��ȡ������󣬿��ܻ��������������϶���0~1���ڣ����Լ�1����ֹ���
		int currentPartSize = fileSize / threadNum +1;
		System.out.println("ÿ�����ز��ֵĴ�С:"+currentPartSize);
		
		RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
		//���ñ����ļ��Ĵ�С
		file.setLength(fileSize);
		file.close();
		System.out.println("��ǰ�̣߳�"+Thread.currentThread().getName());
		System.out.println("���ؿ�ʼʱ�䣺"+new Date());
		for (int i = 0; i < threadNum; i++) {
			//����ÿ���߳����صĿ�ʼλ��
			int startPos = i * currentPartSize;
			//ÿ���߳�ʹ��һ��RandomAccessFile ��������
			RandomAccessFile currenPart = new RandomAccessFile(targetFile, "rw");
			//��λ���̵߳�����λ��
			currenPart.seek(startPos);
			//���������߳�
			threads[i] = new DownThread(startPos,currentPartSize,currenPart);
			threads[i].start();
			threads[i].join();
			
		}
		System.out.println("���ؽ���ʱ�䣺"+new Date());
		
		
		
	}
	
	// ��ȡ���ص���ɰٷֱ�
	public double getCompleteRate(){
		int sumSize = 0;
		for(int i=0;i<threadNum;i++){
			sumSize += threads[i].length;
		}
		return sumSize * 1.0/fileSize;
	}
	
	private class DownThread extends Thread{
		//��ǰ�̵߳�����λ��
		private int startPos;
		//���嵱ǰ�̸߳������ص��ļ���С
		private int currentPartSize;
		//��ǰ�߳���Ҫ���ص��ļ��顣����
		private RandomAccessFile currentPart;
		//������߳������ص��ֽ���
		public int length;
		
		public DownThread(int startPos,int currentPartSize,RandomAccessFile currentPart){
			this.startPos = startPos;
			this.currentPart = currentPart;
			this.currentPartSize = currentPartSize;
			
		}
		
		public void run(){
			try {
				URL url = new URL(path);
				//����һ��URLConnection��������������URL�����õ�Զ�̶��������
				//Ϊʲô���� openStream()�����أ������URL�����ӣ�������һ�����ڶ�ȡ��URL��Դ��InputStream
				//url.openStream();This method is a shorthand for:  openConnection().getInputStream()

//				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				URLConnection conn = url.openConnection();



//				conn.setConnectTimeout(5* 1000);
//				conn.setRequestMethod("GET");
//				conn.setRequestProperty("Accept", "image/gif,"
//						+"application/x-shockwave-flash,application/xaml+xml,*/*");
//				conn.setRequestProperty("Accept-Language", "zh-CN");
//				conn.setRequestProperty("Charset", "UTF-8");
				
				InputStream inStream = conn.getInputStream();
				//����startPos�ֽڣ��������߳�ֻ�����Լ�������ǲ����ļ�
				inStream.skip(this.startPos);
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				//��ȡ�������ݣ���д�뱾���ļ�
				while(length < currentPartSize && (hasRead = inStream.read(buffer))!=-1){
					currentPart.write(buffer,0,hasRead);
					// �ۼƸ��߳����ص��ܴ�С
					length += hasRead;
				}
				currentPart.close();
				inStream.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
