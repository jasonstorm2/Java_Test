package my;

public class FieldScopeTest {
	int x;
	public void ss(){
		int y=1;
		System.out.println("��������Y��"+y);
	}
	public static void main(String[] args) {
		FieldScopeTest fs = new FieldScopeTest();
		System.out.println("���ݳ�Աx��"+fs.x);
		fs.ss();
	}

}
