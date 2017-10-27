package awtSwing;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame2 extends JFrame{
    public static void main(String[] args) {
        MyFrame2 f = new MyFrame2();
    }

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;

    public MyFrame2() {
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


        label1 = new JLabel("label1");
        label2 = new JLabel("label2");
        label3 = new JLabel("label3");
        label4 = new JLabel("label4");
        label5 = new JLabel("label5");
        label6 = new JLabel("label6");

       
        GroupLayout.ParallelGroup all = layout.createParallelGroup(Alignment.LEADING);
        GroupLayout.ParallelGroup all2 = layout.createParallelGroup(Alignment.LEADING);
        
        //����GroupLayout��ˮƽ�����飬��Խ�ȼ����ParallelGroup�����ȼ�����Խ�ߡ�
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        GroupLayout.ParallelGroup hGroup1 = layout.createParallelGroup();
        GroupLayout.ParallelGroup hGroup2 = layout.createParallelGroup();
        hGroup1.addComponent(label1)
                .addComponent(label3).addComponent(label5);
        hGroup2.addComponent(label2)
                .addComponent(label4).addComponent(label6);
       
        
        hGroup.addGap(75).addGroup(hGroup1).addGap(5).addGroup(hGroup2);
        all.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,hGroup);
        layout.setHorizontalGroup(all);
        
        
        //����GroupLayout�Ĵ�ֱ�����飬��Խ�ȼ����ParallelGroup�����ȼ�����Խ�ߡ�
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        GroupLayout.ParallelGroup vGroup1 = layout.createParallelGroup();
        GroupLayout.ParallelGroup vGroup2 = layout.createParallelGroup();
        GroupLayout.ParallelGroup vGroup3 = layout.createParallelGroup();
        
        vGroup1.addComponent(label1).addComponent(label2);
        vGroup2.addComponent(label3).addComponent(label4);
        vGroup3.addComponent(label5).addComponent(label6);       
        vGroup.addGap(54).addGroup(vGroup1).addGap(10).addGroup(vGroup2).addGap(10).addGroup(vGroup3);
        //���ô�ֱ��
        all2.addGroup(vGroup);
        layout.setVerticalGroup(all2);
        
        this.setContentPane(panel);

    }

}
