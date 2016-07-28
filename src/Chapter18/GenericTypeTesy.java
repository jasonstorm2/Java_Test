package Chapter18;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTypeTesy {
	
	private Map<String,Integer> score;  //���Map�Ƿ��ͣ�Map<K,V>
	private String str;
	
	public static void main(String[] args) throws Exception {
		Class<GenericTypeTesy> clazz = GenericTypeTesy.class;
		
		Field f = clazz.getDeclaredField("score");		
		Class<?> a = f.getType();		//ֻ�ܻ�ȡ ��ͨ���ͳ�Ա������ ��������	
		System.out.println("score�������ǣ�"+a);
		
		Type gType = f.getGenericType();//���ͳ�Ա���� ���������ȡ
		System.out.println("gType:"+gType);
		if(gType instanceof ParameterizedType){
			ParameterizedType pType = (ParameterizedType)gType; //��gType����ǿ��ת��ΪParameterizedType����
			//��ȡԭʼ����
			Type rType = pType.getRawType();
			System.out.println("ԭʼ�����ǣ�"+rType);
			//ȡ�÷������͵� ���Ͳ���
			Type[] tArgs = pType.getActualTypeArguments();//---һ������
			System.out.println("������Ϣ�ǣ�");
			
			for (int i = 0; i < tArgs.length; i++) {
				System.out.println("��"+i+"�����������ǣ�"+tArgs[i]);
			}			
		}else{
			System.out.println("��ȡ�������ͳ���");
		}
					
	}
}
