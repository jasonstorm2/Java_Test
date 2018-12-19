package my;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * �ö�ά����ģ��·��ѡ��
 * @author LiZhenhua
 *
 */
public class pathFinding {
	
	//���ֶ�ά����ĸ�ֵ��ʽ
	int[][] block = new int[4][2];
	int[][] zone = {{1,2,3},{1,2,3}};
	
	
	/**
	 * �����ֶ�ά����ĸ�ֵ��ʽ
	 * @param a
	 */
	public static int[][] setValue(int[][] a) {
		int va = 1;
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = va;
				va++;
			}

		}
		for (int x = 0; x < a.length; x++) { // ��λ��
			for (int y = 0; y < a[x].length; y++) { // ��λÿ�е�Ԫ�ظ���
				System.out.print(a[x][y] + " ");
			}
			System.out.println("\n");
		}
		return a;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		int[][] block = new int[10][10];
		block = setValue(block);		
		List<elementIndex> blockElements = new ArrayList<elementIndex>();// �ϰ�Ԫ��
		int[] blockValue = {22,24,34,44,23,45,46,36,37};
		
		for(int i=0;i<blockValue.length;i++){
			blockElements.add(getElementIndex(block, blockValue[i]));
		}
		int begin = 33;
		int end = 35;
		List<elementIndex> pathElements = new ArrayList<elementIndex>(); // ·��Ԫ��		
		List<elementIndex> pathElement = getPath(block, begin, end, blockElements,pathElements);
		System.out.println("��ʼ��"+begin+"������"+end);
		System.out.println("�ϰ�Ԫ�أ�");
		for (int i = 0; i < blockValue.length; i++) {
			System.out.print(blockValue[i]+" ");
		}
		System.out.println();
		for (elementIndex elementIndex : pathElement) {
			System.out.println("·����"+elementIndex.value);
		}		
	}
	
	public static List<elementIndex> getPath(int[][] block,int begin,int target,List<elementIndex> blockElements,List<elementIndex> pathElements) throws CloneNotSupportedException{
		
		List<elementIndex> elements = getRoundElement(block, begin,blockElements); //��ΧԪ��
		elementIndex beginElement = getElementIndex(block, begin);  //��ʼԪ��
		blockElements.add(beginElement); //�ϰ�Ԫ�����
		elementIndex closedElement = getCloseElements(block, elements,beginElement, target,blockElements);  //�����Ԫ��
		if(closedElement == null){
			return pathElements;
		}else{
			pathElements.add(closedElement);
			return getPath(block, closedElement.value, target, blockElements,pathElements);
		}		
	}
	
	
	
	
	/**
	 * ��֪�����㣬һ���ϰ���������������·��
	 */
	public static List<Integer> findPath(int[][] ����,int[] �ϰ�,int ���,int �յ�){
		List<Integer> result = new ArrayList<Integer>();
		System.out.println("4,2��ֵ��"+����[4][2]);
		return result;
	}
	
	/**
	 * �õ�һ��Ԫ����Χ��Ԫ��
	 * @throws CloneNotSupportedException 
	 */
	public static List<elementIndex> getRoundElement(int[][] ����,int element,List<elementIndex> blockElement) throws CloneNotSupportedException{
		List<elementIndex> elements = new ArrayList<elementIndex>();
		elementIndex e = getElementIndex(����, element);
		
		//23
		e.x = e.x -1;
		if(ifElementExit(����, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}			
		}
		//22
		e.y = e.y - 1;
		if(ifElementExit(����, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		//24
		e.y = e.y + 2;
		if(ifElementExit(����, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		//34
		e.x = e.x+1;
		if(ifElementExit(����, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		//32
		e.y = e.y - 2;
		if(ifElementExit(����, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		//42
		e.x = e.x+1;
		if(ifElementExit(����, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		
		//43
		e.y = e.y + 1;
		if(ifElementExit(����, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		
		//44
		e.y = e.y + 1;
		if(ifElementExit(����, e)){
			if(!isContain(blockElement, e)){
				elements.add(e.clone());
			}
		}
		
		return elements;
		
	}
	
	/**
	 * �õ�Ԫ���±��б��У����ֵ��Ԫ���±���
	 * @param elements
	 * @param element
	 * @return
	 */
	public static elementIndex getCloseElements(int[][] ����,List<elementIndex> elements,elementIndex beginElement,int target,List<elementIndex> blockElements){
		elementIndex index = getElementIndex(����, target); //Ŀ��Ԫ��
		
		Collections.sort(elements, new Comparator<elementIndex>(){
			@Override
			public int compare(elementIndex element1, elementIndex element2) {
				int x = Math.abs(element1.x - index.x);
				int y = Math.abs(element1.y - index.y);
				double v1 = Math.sqrt(x * x + y * y);
				
				int x2 = Math.abs(element2.x - index.x);
				int y2 = Math.abs(element2.y - index.y);
				double v2 = Math.sqrt(x2 * x2 + y2 * y2);				
				// TODO Auto-generated method stub
	              if(v1 < v2){
	                    return -1;  
	                }  
	                if(v1 == v2){
	                    return 0;  
	                }  
	                return 1;  
			}			
		});
		
		List<elementIndex> randomIndex = new ArrayList<pathFinding.elementIndex>();
		boolean canRandom = false;
		for (int i = 0; i < elements.size(); i++) {
			elementIndex element1 = elements.get(i);
			int x = Math.abs(element1.x - beginElement.x);
			int y = Math.abs(element1.y - beginElement.y);
			double v1 = Math.sqrt(x * x + y * y);
			if(v1 == 1){
				if(elements.get(i).value == index.value){
					return null;
				}else{
					canRandom = true;
					randomIndex.add(elements.get(i));
				}				
			}
			
		}
		if(canRandom){
			Random ran = new Random();
			elementIndex element = randomIndex.remove(ran.nextInt(randomIndex.size()));
			for (int i = 0; i < randomIndex.size(); i++) {
				blockElements.add(randomIndex.get(i));
			}
			
			return element;
		}
		return null;		
	}
	
	/**
	 * �õ�Ԫ���±�
	 * @param ����
	 * @param element
	 */
	public static elementIndex getElementIndex(int[][] ����,int element){
		elementIndex res = null;
		   for(int x = 0; x<����.length; x++){  //��λ��  
	            for(int y = 0; y<����[x].length; y++){  //��λÿ�е�Ԫ�ظ���  
	            	if(����[x][y] == element){
	            		res = new elementIndex(x, y);
	            		res.setValue(element);
	            	}
	            }  
	        }
		   return res;
	}
	
	/**
	 * �ж�ĳ���±��Ƿ����
	 * @param ����
	 * @param index
	 * @return
	 */
	public static boolean ifElementExit(int[][] ����,elementIndex elementIndex){
		boolean isExit = false;
		
		try{
			int value = ����[elementIndex.x][elementIndex.y];
			elementIndex.value = value;
			isExit = true;
		} catch (Exception e) {
		}
		return isExit;
	}
	
	private static class elementIndex implements Cloneable{
		int x = 0;
		int y = 0;
		int value = -1;
		public elementIndex(int x,int y) {
			this.x = x;
			this.y = y;
		}
		
		public void setValue(int value){
			this.value  = value;
		}
		
		@Override
		protected elementIndex clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return (elementIndex)super.clone();
		}
		
	}
	
	public static boolean isContain(List<elementIndex> list,elementIndex e){
		for (elementIndex elementIndex : list) {
			if(elementIndex.value == e.value){
				return true;
			}
		}
		return false;
		
	}
	

}
