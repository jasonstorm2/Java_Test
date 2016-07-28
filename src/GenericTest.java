import java.util.*;

public class GenericTest {

	public static void main(String[] args) {

		Box<String> name = new Box<String>("corn");
		Box<Integer> age = new Box<Integer>(712);
		Box<Number> number = new Box<Number>(314);

		getData(name);
		getData(age);
		getData(number);
		// getUpperNumberData(name); // 1
		getUpperNumberData(age); // 2
		getUpperNumberData(number); // 3
	}

	// ��������ͨ����������á���������������ʵ�Σ���ע�⣬��ʵ�ζ����βΡ�����Box<?>������Box<��������ʵ��>���߼��ϵĸ���
	public static void getData(Box<?> data) {
		System.out.println("data :" + data.getData());
	}

	// ����ͨ������ޡ��������������޵����ã�
	/**
	 * ����ͨ�������ͨ������Box<? extends Number>��ʽ���壬���Ӧ�ģ�����ͨ�������ΪBox<? super
	 * Number>��ʽ���京��������ͨ������������෴���ڴ˲�����������ˡ�
	 */
	public static void getUpperNumberData(Box<? extends Number> data) {
		System.out.println("data :" + data.getData());
	}
}

class Box<T> {

	private T data;

	public Box() {

	}

	public Box(T data) {
		setData(data);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}