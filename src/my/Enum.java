package my;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;

public class Enum {

    // 1.����ö������--�ڲ���

    public enum Light {
        // ���ù��캯������
        RED(1,"��ɫ"), GREEN(5,"��ɫ"), YELLOW(7,"��ɫ");
        // ����˽�б���
        public int nCode;
        public String ss;
        // ���캯����ö������ֻ��Ϊ˽��
        private Light(int _nCode,String ss) {
            this.nCode = _nCode;
            this.ss = ss;
        }
        @Override
        public String toString() {
            return String.valueOf(this.nCode+"_"+this.ss);
        }
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 1.����ö������
        System.out.println("��ʾö�����͵ı��� ......");
        testTraversalEnum();
        // 2.��ʾEnumMap�����ʹ��
        System.out.println("��ʾEnmuMap�����ʹ�úͱ���.....");
        testEnumMap();
        // 3.��ʾEnmuSet��ʹ��
        System.out.println("��ʾEnmuSet�����ʹ�úͱ���.....");
        testEnumSet();
    }

    /**
     * 
     * ��ʾö�����͵ı���
     */

    private static void testTraversalEnum() {
    	//values:Returns an array containing the constants of this enum type, in the order they are declared.
        Light[] allLight = Light.values();//��ø�ö�������е�ö�ٶ���

        for (Light aLight : allLight) {		//��������
        	/****ģ����⣺ö�����ڣ�ÿ��ö�ٶ���һ������һ��ö���࣬�൱��һ�������ܶ�����arraylist***/
            System.out.println("����ö�ٵ����֣�" + aLight.name());//�õ�ö�ٵ�����
            System.out.println("����ö�ٵ�λ�����кţ�" + aLight.ordinal());//�õ�ö�ٵ�λ�����к�

            System.out.println("��ӡö�٣�" + aLight);//ö�ٵ�toString������û�и���toString�����Ļ���Ĭ���Ƿ���ö�ٵ�����
            System.out.println("��ӡö���Ա�����" + aLight.nCode);//�õ�ö������Ĳ���
            System.out.println("��ӡö���Ա�����" + aLight.ss);//�õ�ö������Ĳ���                       
            System.out.println("�Զ����toString��" + aLight.toString());//���Ը���toString����
            System.out.println("��������ö�ٶ���" + aLight.GREEN);//����ĳһ��aLight
            System.out.println("��������ö�ٶ���ı�����" + aLight.GREEN.ss);
        }
        ArrayList< Light> EnumArray = new ArrayList<Light>();
        for (Light aLight : allLight) {		//��������
        	EnumArray.add(aLight);
        	System.out.println("###ö�ٶ���"+aLight);
        }
    }

    /**
     * 
     * ��ʾEnumMap��ʹ�ã�EnumMap��HashMap��ʹ�ò�ֻ࣬����keyҪ��ö������
     */

    private static void testEnumMap() {

        // 1.��ʾ����EnumMap����EnumMap����Ĺ��캯����Ҫ��������,Ĭ����key���������

        EnumMap<Light, String> currEnumMap = new EnumMap<Light, String>(Light.class);

        currEnumMap.put(Light.RED, "���");

        currEnumMap.put(Light.GREEN, "�̵�");

        currEnumMap.put(Light.YELLOW, "�Ƶ�");

        // 2.��������

        for (Light aLight : Light.values()) {

            System.out.println("[key=" + aLight.name() + ",value="

            + currEnumMap.get(aLight) + "]");

        }

    }

    /**
     * 
     * ��ʾEnumSet���ʹ�ã�EnumSet��һ�������࣬��ȡһ�����͵�ö����������<BR/>
     * 
     * ����ʹ��allOf����
     */

    private static void testEnumSet() {

        EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);

        for (Light aLightSetElement : currEnumSet) {

            System.out.println("��ǰEnumSet������Ϊ��" + aLightSetElement);

        }

    }

}
