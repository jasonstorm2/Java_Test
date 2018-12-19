package my;

/**
 * 可变长度的参数测试
 * 方法的参数可以任意长度
 * @author LiZhenhua
 *
 */
public class changingLenthArguments {
	public static void printMsg(boolean set,String ...msg){
		
		
		int i=0;
		if(set){
			System.out.println("可变参数的长度："+msg.length);
			for(String s:msg){
				i++;
				System.out.println("i="+i+",具体值："+s);
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
