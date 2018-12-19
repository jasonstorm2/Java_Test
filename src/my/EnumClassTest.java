package my;

import java.lang.Enum;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

/**
 * ö������ԣ�
 * ͨ�����䣬���ĳ�������ö�١�
 * Class���� getEnumConstants()���Ի�ø��������ö�٣�Ȼ����Ա���ö��������
 * @author LiZhenhua
 *
 */
public class EnumClassTest {
	public static void main(String[] args) {
		ArrayList<Enum<?>> result = new ArrayList<Enum<?>>();//ö����--ת��Ϊ--����
		try {
			Class<?> myEnum = Class.forName("my.myEnum");
			Enum<?>[] enums = (Enum[])myEnum.getEnumConstants();
			System.out.println("ö�ٳ��ȣ�"+enums.length);
			for(Enum<?> e : enums){
				System.out.println("e:"+e);
				System.out.println("e.name:"+e.name());
				
				System.out.println("ö������������֣�"+e.getClass().getName());
				result.add(e);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/**
 * һ����ͨ��
 * @author LiZhenhua
 * ö�ٵ� switchӦ��
 */
class aClass{
	
	aEnum e = aEnum.a;
	public void change(){
		switch(e){
		case a:
			e = aEnum.b;
			break;
		case b:
			break;
		case c:
			break;
		case d:
			break;		
		}
		
	}
}

/**
 * һ����ͨ��ļ̳�
 * @author LiZhenhua
 *
 */
class bClass extends aClass{
	
}

interface Food {
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
/**
 * һ��ö����
 * @author LiZhenhua
 *
 */
enum aEnum {
	a,
	b,
	c,
	d
}
