package my;

public class JavaBean {
	public String name;
	public int age;
	public String[] wtf;
	
	//����������û��������getter �� setter
	public String one ;
	public int two ;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String[] getWtf() {
		return wtf;
	}
	public void setWtf(String[] wtf) {
		this.wtf = wtf;
	}
	
	public void setOneTwo(String one,int two){
		this.one = one;
		this.two = two;
	}

}
