package my;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import Chapter15.FileTest;

/**
 * 相对地址，即跟地址是：System.getProperty("user.dir")，即项目的地址，此项目地址是：D:\WorkSpace1\Java_Test
 * @author LiZhenhua
 *
 */
public class FileAddressTest {
	public static void main(String[] args) {
		String pathStr = System.getProperty("java.class.path");
		String pathUser = System.getProperty("user.dir");
		System.out.println("Java 类路径 :"+pathStr); //  Java 类路径 
		System.out.println("用户的当前工作目录:"+pathUser);//  用户的当前工作目录
		
		try {
			//路径：******根目录下，与("./FileInputStreamTest.txt")结果一样
			FileInputStream fis = new FileInputStream("FileInputStreamTest.txt");// 调用了File()方法
			//路径：******.class的当前目录，与("./FileInputStreamTest.txt")结果一样
			InputStream inputFile2 = FileTest.class.getResourceAsStream("FileInputStreamTest.txt");
			File file = new File("");  //-----------------------------------创建对象并非创建了一个文件或文件夹？--管道左右
			System.out.println("new File(''):"+file.getAbsolutePath());//路径：D:\WorkSpace1\Java_Test
			File file1 = new File(".");  //-----------------------------------创建对象并非创建了一个文件或文件夹？
			System.out.println("new File('.'):"+file1.getAbsolutePath());//路径：D:\WorkSpace1\Java_Test\.
			File file2 = new File("file2.txt");  //-----------------------------------创建对象并非创建了一个文件或文件夹？
			System.out.println("new File('file2.txt'):"+file2.getAbsolutePath());//路径：D:\WorkSpace1\Java_Test\file2.txt
			/***创建文件***/
//			file.createNewFile(); //名字为空，无法创建
			file1.createNewFile(); //一个.也没有办法创建
			file2.createNewFile();  //---------------------------------------------通过对象创建一个文件！！
			
		} catch (Exception e) {
			e.printStackTrace();
		}//相对地址，即，根地址是：System.getProperty("user.dir")
	}

}
