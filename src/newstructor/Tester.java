package newstructor;

/**
 * �� bean���� �����ݿ���Ŀ һһ��Ӧ
 * 
 * ʹ�� beanObject �������ݿ�
 * @author LiZhenhua
 *
 */
public class Tester {
	
	public static final int TESTNUMBER = 0;
	public String str;
	
	public void ss(String sd){
		this.str=sd;
		
		
	}
	
    public static void main(String args[]) {
        //���NetJavaSession����
    	NetJavaSession session = new NetJavaSession();
        //����һ��UserInfo����
        UserInfo user = new UserInfo();
        //���ö��������
        user.setId(15);
        user.setAge(45);
        user.setPwd("123456");
        user.setName("jacika");
        
        //�����󱣴浽���ݿ���        
        int val=session.saveObject(user);
        System.out.println("�Ƿ�д�����ݿ⣺"+val);
        //�õ�SQL���
        String sql = session.getSaveObjectSql(user);               
        System.out.println("��������sql��䣺" + sql);
        //���Ҷ���
        UserInfo userInfo = (UserInfo) session.getObject(
                "newstructor.UserInfo", 123456);
        System.out.println("��ȡ������Ϣ��" + userInfo);
 
    }
}