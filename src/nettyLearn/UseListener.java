package nettyLearn;

public class UseListener {
	
	private MyListener listener;
	
	public int value = 100;

	public static void main(String[] args) {
		UseListener thisClass = new UseListener();
		thisClass.addListener(new MyListener() {
			@Override
			public void operate(UseListener invoke) {
				// TODO Auto-generated method stub
				//super.operate(invoke);//�����˸���ķ�����ע�͵��󣬾Ͳ�����ã�������д
				if(invoke.value>100){
					System.out.println("value��ֵ>100");
				}else{
					System.out.println("value��<=100");
				}
			}
		});

	}

	public void addListener(MyListener method) {
		listener = method;
		method.operate(this);

	}

	public boolean isSucess() {
		return false;
	}

}

/**
 * ��������ֻ�жԼ�������Ĵ���
 * @author LiZhenhua
 *
 */
class MyListener {
	/**
	 * �Լ�������Ĵ���
	 * @param invoke
	 */
	public void operate(UseListener invoke) {	
		System.out.println("��ӡֵ��"+invoke.value);
	}

}
