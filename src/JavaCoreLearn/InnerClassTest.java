package JavaCoreLearn;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;
/**
 * һ��javaԴ�ļ�������ж��class����ֻ����һ����public
 * @author LiZhenhua
 *
 */
public class InnerClassTest {

	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		
		//ͨ���ⲿ����� ���� �ڲ������
		TalkingClock clock2 = new TalkingClock(1000, true);
		TalkingClock.TimePrinter listener = clock2.new TimePrinter();//�ⲿ��.�ڲ���
		clock2.init(new ArrayList<String>(){
			{
				add("jx");add("bd");
			}
		});
		
		clock.start();
		JOptionPane.showMessageDialog(null, "Quit?");
		System.exit(0);
		
	
		}
}

	class TalkingClock {
		private int interval;
		private boolean beep;
		private ArrayList<String> namelist;

		public TalkingClock(int interval, boolean beep) {
			this.interval = interval;
			this.beep = beep;
		}
		
		public void init(ArrayList<String> ar){
			this.namelist = ar;			
		}
		public void start() {
			//�ֲ��ڲ���
//			class TimePrinter implements ActionListener {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					Date now = new Date();
//					System.out.println("pick up:" + now);
//					if (beep)//��Χ�����õ���ʽ��ʽoutter.this.beep
//						Toolkit.getDefaultToolkit().beep();
//				}
//
//		}
			ActionListener listener = new TimePrinter();
//			ActionListener listener = new TimePrinter(this);�ڲ��࣬������������
//			ActionListener listener2 = this.new TimePrinter();//����Χ�������this����Ϊ�����ڲ������ �ķ����е�this����
			Timer t = new Timer(interval, listener);
			t.start();
		}
		
		/************************************************************************************/
		//���ⲿ��������final����
		public void start(int interval,final boolean beep) {
			//�ֲ��ڲ���
			class TimePrinter2 implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					Date now = new Date();
					System.out.println("pick up:" + now);
					if (beep)//��Χ�����õ���ʽ��ʽoutter.this.beep
						Toolkit.getDefaultToolkit().beep();
				}

		}
			ActionListener listener = new TimePrinter2();
			Timer t = new Timer(interval, listener);
			t.start();
		}
		/************************************************************************************/
		//�����ڲ���
		public void start1(int interval,final boolean beep){
			ActionListener listener = new ActionListener() { //��д�ӿڵķ���
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			};
			
			Timer t = new Timer(interval, listener);
			t.start();
		}
		
		
		public class TimePrinter implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date now = new Date();
				System.out.println("pick up:" + now);
				if (beep)//��Χ�����õ���ʽ��ʽoutter.this.beep
					Toolkit.getDefaultToolkit().beep();
			}

	}
}

