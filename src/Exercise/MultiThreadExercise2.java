package Exercise;

import java.util.ArrayList;
import java.util.Random;

public class MultiThreadExercise2 {
	public static void main(String[] args) {
		Garage g = new Garage();
		new portThread(g).start();
		new outThread(g).start();

	}
	
}

class Garage{
	boolean[] sit = new boolean[3];
	boolean have = false;
	boolean lock = false;
	
	//��ӡ����λ�����
	public  void booleanToString(boolean[] sit){
		String str = null;
		str = "[";
		for(boolean b: sit){
			str+=b+",";
		}
		str = str.substring(0, str.length()-1);
		str += "]";		
		System.out.println(str);
	}
	
	//�Ƿ��г�λ
	public boolean haveSite(boolean[] sit){
		boolean havesite = true;
		for(boolean b : sit){
			havesite = b&havesite;
		}
		return !havesite;
	}
	
	//�Ƿ��г�
	public boolean haveCar(boolean[] sit){
		boolean havecar = true;
		for(boolean b : sit){
			havecar = !b&havecar;
		}
		return !havecar;
	}
	//ͣ����λ��
	public int getIn(boolean[] sit){
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i=0;i<sit.length;i++){
			if(sit[i]==false){
				index.add(i);
			}
		}
		int ran = new Random().nextInt(index.size());
		return index.get(ran);
	}	
	//�����ĳ���λ��
	public int getOut(boolean[] sit){
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i=0;i<sit.length;i++){
			if(sit[i]==true){
				index.add(i);
			}
		}
		int ran = new Random().nextInt(index.size());
		return index.get(ran);
	}
	

	// ������
	public synchronized void port() {
		have = haveSite(sit);
		if (have) {
			int index = getIn(sit);
			sit[index] = true;
			booleanToString(sit);
			have = haveSite(sit);			
		}
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	// �������
	int i=1;
	public synchronized void out() {		

		have = haveCar(sit);
		if (have) {
			int index = getOut(sit);
			sit[index] = false;
			booleanToString(sit);
			have = haveCar(sit);
//			System.out.println(i);
			i++;
		}
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}

class portThread extends Thread{
	private Garage g;
	public portThread(Garage g){
		this.g = g;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 20; i++) {			
			g.port();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.yield();
		}
		
	}
}

class outThread extends Thread{
	private Garage g;
	public outThread(Garage g){
		this.g = g;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 20; i++) {			
			g.out();	
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.yield();
		}
	}
}
