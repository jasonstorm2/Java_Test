import java.util.Properties;


class Annoyance extends Exception {}
class Sneeze extends Annoyance {}
 
/**
 * �д��쳣�Ĳ������쳣����̳��ߵĹ�ϵ
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) throws Exception {
		
		Properties p = new Properties();
		
		try {
			try {
				throw new Sneeze();
			} catch (Annoyance a) {//�׳�����ʵ��Sneeze����
				System.out.println("Caught Annoyance");
				throw a;
			}
		} catch (Sneeze s) { //�д�����ʵ��Sneeze����
			System.out.println("Caught Sneeze");
			return;
		} finally {
			System.out.println("Hello World!");
		}
	}
}