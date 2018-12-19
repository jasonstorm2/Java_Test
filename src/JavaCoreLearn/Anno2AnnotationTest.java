package JavaCoreLearn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * �������� ͨ��ע���࣬�󶨰�ť�ͼ�����
 * @author LiZhenhua
 *
 */
public class Anno2AnnotationTest {
	private JFrame mainWin = new JFrame("ʹ��ע����¼�������");
	
	@Anno2Mine(listener = OkListener.class,test = "ȷ��")//�������� annotation Ϊ����Ĭ��ֵ�ı���
	private JButton ok = new JButton("ȷ��");
	
	@Anno2Mine(listener = CancelListener.class,test = "ȡ��")
	private JButton cancel = new JButton("ȡ��");
	
	public void init(){
		JPanel jp = new JPanel();
		jp.add(ok);
		jp.add(cancel);
		mainWin.add(jp);
		
		Anno2ActionListenerInstaller.processAnnotations(this);//����ť���ü���	
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.pack();
		mainWin.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Anno2AnnotationTest().init();
	}
}

class OkListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "������ȷ�ϰ�ť");
	}
	
}

class CancelListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "������ȡ����ť");
	}
	
}
