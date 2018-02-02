package Chapter17_NetWork;

/**
 * ���߳����ز���
 * @author Administrator
 *
 */
public class note5_MultiThreadDown {
	public static void main(String[] args) throws Exception {
		final note4_DownUtil downUtil = new note4_DownUtil("file:///D:/Test/source/xxx.rmvb", "D:\\Test\\destination\\xxx.rmvb", 4);
		System.out.println("main��ǰ�̣߳�"+Thread.currentThread().getName());
		downUtil.download();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				System.out.println("��ʼʱ�䣺"+startTime);
				// TODO Auto-generated method stub
				while (downUtil.getCompleteRate()<1) {
					//ÿ��0.1���ѯһ���������ɽ���
					System.out.println("����ɣ�"+downUtil.getCompleteRate());
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				System.out.println("����ʱ�䣺"+System.currentTimeMillis());
				System.out.println("���ʱ�䣺"+(System.currentTimeMillis()-startTime));
				
				
			}
		}).start();
	}

}
