package my;

public class changingArguments {
	public static void printMsg(boolean set,String ...msg){
		
		
		int i=0;
		if(set){
			System.out.println("�ɱ�����ĳ��ȣ�"+msg.length);
			for(String s:msg){
				i++;
				System.out.println("i="+i+",����ֵ��"+s);
			}
		}else{
			System.out.println("you have input false");
		}	
	}
	public static void main(String[] args) {
		printMsg(false, "rini","mabi");
		
		printMsg(true, "rini","mabi");
		
	}

}
