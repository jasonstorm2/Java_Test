package JavaCoreLearn;

import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;

/**
 * ����������ǣ��� ��Ա�������� �� ��Ա������ע����Ĳ�������
 * 
 * ͨ�����䣬��ȡ��Ա������ע�����������Ĳ����������Ĳ�����ActionListener��Class����
 * ͨ�����䣬��ȡ��Ա�����Ķ��󣬣�ǿ��ת��(AbstractButton)fObj��
 * 
 * 
 * @author Administrator
 *
 */
public class Anno2ActionListenerInstaller {
	int a = 2;
	int b = 3;
	public static void processAnnotations(Object obj) {

		try {
			Class<? extends Object> cl = obj.getClass();
			for (Field f : cl.getDeclaredFields()) {//�������ǳ�Ա����
				//���ó�Ա�������óɿ����ɷ���
				f.setAccessible(true);
				Anno2Mine a = f.getAnnotation(Anno2Mine.class);//��� ��Ա����ע�ͣ�����û��ע�Ͷ���
				Annotation as = f.getAnnotation(Anno2Mine.class);
				Object fObj = f.get(obj);//��� ��Ա���������Ա��ж϶������ͣ�
				
				if(a !=null && fObj !=null && fObj instanceof AbstractButton){
					Class<? extends ActionListener> listenerClazz = a.listener();//��ȡԪ���� Class����					
					ActionListener al = listenerClazz.newInstance();//ʵ����
					AbstractButton ab = (AbstractButton)fObj;
					
					ab.addActionListener(al);			//����ť���ü���					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
