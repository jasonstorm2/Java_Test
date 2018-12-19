package JavaCoreLearn;
import java.lang.reflect.Array;

/**
 * �����е� �����࣬Array
 * Array������Դ������е�����
 * 
 * ��̬���������� ����
 * @author LiZhenhua
 *
 */
public class ArrayTest1 {
	public static void main(String[] args) {
		try{
			//����һ��Ԫ������ΪString������10������
			Object arr = Array.newInstance(String.class, 10);
			//setXXX(Object obj,int index,xxx value)
			Array.set(arr, 5, "����");
			Array.set(arr, 6, "ˮ���");
			
			//getXXX(Object ob,int index) ����array�����index��Ԫ�أ�
			Object ob1 = Array.get(arr, 5);
			Object ob2 = Array.get(arr, 6);
			Object ob3 = Array.get(arr, 0);
			
			System.out.println("ob1:"+ob1);
			System.out.println("ob2:"+ob2); 
			System.out.println("ob3:"+ob3); 
			System.out.println(Array.get(arr,9));
			
		}catch(Throwable e){
			System.err.println(e);
		}
	}

}
