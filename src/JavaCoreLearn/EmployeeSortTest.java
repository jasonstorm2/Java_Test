package JavaCoreLearn;

import java.util.Arrays;

/**
 * Arrays.sort �������������
 * @author LiZhenhua
 *
 */
public class EmployeeSortTest {
	public static void main(String[] args) {
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("jason", 2000);
		staff[1] = new Employee("mars", 1500);
		staff[2] = new Employee("jack", 5000);
		
		Arrays.sort(staff);//�������������
		
		for(Employee e : staff){
			System.out.println("name:"+e.getName()+" salary:"+e.getSalary());
		}
		if(Employee.testCast(0)){
			System.out.println("��ȷ");
		}else{
			System.out.println("����");

		}
		
	}

}
