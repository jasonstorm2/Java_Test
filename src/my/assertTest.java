package my;

/**
 * 
 * ���������Ĵ��붼�Ƕ�����Ч�ģ������������Ĵ����ж�����䶼����ִ�еģ��������õģ�
 * 
 * assert �����ԣ��ж���ȷ����� ������default vm
 * arguments������-eaʹ������Ч��Ҳ����ʹ��-daѡ��ʹ������Ч��Ĭ��Ϊ��Ч������Ҳ��ʾ��Ч
 * 
 * ͬ����Ҳ����ͨ����-ea��-da����ָ��������ʹһ�����Ķ�����Ч����Ч�� ���磬Ҫʹһ��com.test���еĶ��� ��Ч������ʹ�ã�
 * 
 * -da:com.test Ҫʹһ�����е������Ӱ��еĶ����ܹ���Ч����Ч���ڰ�������������㡣
 * 
 * ���磺 -ea:com.test... ����ʹcom.test�������Ӱ��еĶ�����Ч��
 * 
 * 
 * 
 * Java2��1.4��������һ���ؼ��֣�assert���ڳ��򿪷�������ʹ��������һ������(assertion)������ �﷨��ʽ��������ʾ��������ʽ��
 * 
 * 1��assert <boolean���ʽ>
 * 
 * 2��assert <boolean���ʽ> : <������Ϣ���ʽ>
 * 
 * ������ʽΪfalse�������ʧ�ܣ�����׳�һ��AssertionError�������AssertionError�̳���Error����
 * ��Error�̳���Throwable��Error�Ǻ�Exception���е�һ���������ͨ�����ڱ��ϵͳ�����д���
 * 
 * @author LiZhenhua
 *
 */
public class assertTest {
	public static void main(String[] args) {
		assertTest.assertMethod(true);
		assertTest.assertMethod(false);		
	}
	
	public static void assertMethod(boolean b){
//		assert(b);
		System.out.println("�ɹ�ִ����");	
		assert b:"����һ������";
	}

}
