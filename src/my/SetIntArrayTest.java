package my;

public class SetIntArrayTest {
	
	public static String getIntArrayToString(int[] array) {
		String str="";
		for(int i=0;i<array.length;i++){
			str+=array[i]+",";
		}
		return str.substring(0,str.length()-1);		 
	}
	
	
	public static void main(String[] args) {
		int get[] = {1,2,3,4,5,6,7,8,9};
		System.out.println(getIntArrayToString(get));
		
		for(int a:get){
			int i=0;
			i++;
			if(a==5){
				a=10;				
				System.out.println("the value is 5=="+i);
			}
			System.out.println("�±꣺"+(i++)+"  a��ֵ��"+a);
		}
		System.out.println(getIntArrayToString(get));
		for(int i=0;i<8;i++){
			System.out.println("����get��ֵ:"+get[i]);

			if(get[i]==5){
				get[i]=10;
				System.out.println("get��ֵ:"+get[i]);
				break;
//				continue;
//				return;
			}	

	}
		System.out.println("��������"+getIntArrayToString(get));
		
  }
}
