package awtSwing;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame3 extends JFrame{
    public static void main(String[] args) {
        MyFrame3 f = new MyFrame3();
    }

    JLabel label1;
    JButton jb1;
    JButton jb2;


    public MyFrame3() {
        this.setVisible(true);
        this.setSize(250, 220);
        this.setVisible(true);
        this.setLocation(400, 200);
        
        // ���������������
        JPanel panel = new JPanel();
        // �������鲼�֣�����������
        GroupLayout layout = new GroupLayout(panel);
        // ���������Ĳ���
        panel.setLayout(layout);


        label1 = new JLabel("TRAILING");  
        jb1 = new JButton("��ť1");
        jb1.setSize(20, 20);
        jb2 = new JButton("��ť2");
        jb2.setSize(20, 20);
        
        
        label1.setSize(20, 20);
        //����GroupLayout��ˮƽ�����飬��Խ�ȼ����ParallelGroup�����ȼ�����Խ�ߡ�
        GroupLayout.SequentialGroup hGroup1 = layout.createSequentialGroup().addComponent(jb1).addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(label1)).addComponent(jb2);

        layout.setHorizontalGroup(hGroup1);
        
        
        //����GroupLayout�Ĵ�ֱ�����飬��Խ�ȼ����ParallelGroup�����ȼ�����Խ�ߡ�
        GroupLayout.ParallelGroup vGroup = layout.createParallelGroup(Alignment.CENTER).addComponent(jb1).addComponent(label1).addComponent(jb2);

        layout.setVerticalGroup(vGroup);
        
        this.setContentPane(panel);

    }

}
