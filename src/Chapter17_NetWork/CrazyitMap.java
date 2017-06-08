package Chapter17_NetWork;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CrazyitMap<K,V> {
	
	public Map<K,V> map = Collections.synchronizedMap(new HashMap<K, V>());
	
	//ͨ��ֵ ɾ����mapָ����ֵ��
	public synchronized void removeByValue(Object value){
		for (Object key : map.keySet()) {
			if(map.get(key) ==  value){
				map.remove(key);
				break;
			}			
		}
	}
	
	// ��ȡ����value ��ɵļ���set
	public synchronized Set<V> valueSet(){
		Set<V> result = new HashSet<V>();
		for (V value : map.values()) {
			result.add(value);
		}
		return result;
	}
	
	// ͨ��ֵ ��ȡ��Ӧ�Ŀ���
	public synchronized K getKeyByValue(V value){
		for (K key : map.keySet()) {
			if(map.get(key) == value || map.get(key).equals(value)){
				return key;
			}
		}
		return null;		
	}
	
	// ʵ�� put�������÷��� ������value�ظ�
	public synchronized V put(K key , V value){
		for(V val:valueSet()){
			if(val.equals(value) && val.hashCode() == value.hashCode()){
				throw new RuntimeException("mapʵ���У����������ظ���value!");
			}
		}
		
		return map.put(key, value);
	}

}
