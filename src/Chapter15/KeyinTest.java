package Chapter15;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KeyinTest {
	/*
	 * �ֽ��� ת���� �ַ���
	 */
	public static void main(String[] args) {
		try(
				//��system.in����ת����Reader����,
				InputStreamReader ir = new InputStreamReader(System.in);
				//����ͨ��reader�����װ��BufferedReader
				BufferedReader br = new BufferedReader(ir);
				){
			String line =null;
			while ((line = br.readLine()) != null) {
				if(line.equals("exit")){
					System.exit(1);
				}
				System.out.println("��������Ϊ��"+line);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
