package my;

import java.util.Random;

/**
 * Random().nextBoolean()���������ص���һ��α�������ֵ
 * @author LiZhenhua
 *
 */
public class RandomBoolean {
	public static void main(String[] args) {
		for(int i=0;i< 100;i++){
			System.out.println("����Ĳ���ֵ:"+i+new Random().nextBoolean());
		}		
	}
}
