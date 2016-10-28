package Chapter18_ClassLoadAndReflection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * �ο�����
 * http://blog.csdn.net/is_zhoufeng/article/details/26602689
 * 
 * http://blog.csdn.net/zhangdaiscott/article/details/23378023   �Ȳ���
 */
class ComClassLoader extends ClassLoader{
	@SuppressWarnings("deprecation")
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("��д��findClass����");
		
		String classPath = ComClassLoader.class.getResource("/").getPath(); //�õ�classpath  D:/WorkSpace1/Java_Test/bin/
        String fileName = name.replace(".", "/") + ".class" ;  //Chapter18/Hello.class
        File classFile = new File(classPath , fileName); //������·������������γ�һ����·��
        if(!classFile.exists()){                                //D:\WorkSpace1\Java_Test\bin\Chapter18\Hello.class
            throw new ClassNotFoundException(classFile.getPath() + " ������") ;  
        } 
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;  
        ByteBuffer bf = ByteBuffer.allocate(1024) ;  
        FileInputStream fis = null ;  
        FileChannel fc = null ;  
        try {  
            fis = new FileInputStream(classFile) ;  
            fc = fis.getChannel() ;  
            while(fc.read(bf) > 0){  
                bf.flip() ;  
                bos.write(bf.array(),0 , bf.limit()) ;  
                bf.clear() ;  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                fis.close() ;  
                fc.close() ;  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return defineClass(bos.toByteArray() , 0 , bos.toByteArray().length) ;  
	}
}
public class CompileClassLoader extends ClassLoader{
	
	
//	//��ȡһ���ļ� �� ����
//	private byte[] getBytes(String filename) throws IOException {
//		File file = new File(filename+"q");
//		long len = file.length();
//		byte[] raw = new byte[(int)len];
//		
//		try (FileInputStream fin = new FileInputStream(file)){
//			//һ�ζ�ȡClass�ļ���ȫ������������
//				int r = fin.read(raw);
//				if(r!=len) throw new IOException("�޷���ȡȫ���ļ���"+r+"!="+len);
//				return raw;
//			}		
//	}
//	
//	
//	//�������ָ��JAVA�ļ��ķ���
//	private boolean compile(String javaFile) throws IOException{
//		System.out.println("myClassLoader:���ڱ���"+javaFile+"..........");
//		//����ϵͳ��javac����
//		Process p = Runtime.getRuntime().exec("javac"+javaFile+"jiba");
//		try {
//			//�����̶߳��ȴ�����߳����
//			p.waitFor();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e);
//		}
//		//��ȡjavac�̵߳��˳�ֵ
//		int ret = p.exitValue();
//		//���ر����Ƿ�ɹ�
//		return ret == 0;
//		
//	}
//	
//	//��дClassLoader��findClass����
//	protected Class<?> findClass(String name) throws ClassNotFoundException{
//		Class clazz = null;
//		//����·���еĵ�(.)�滻��б�ߣ�/��
//		String fileStub = name.replace(".","/");
//		String javaFilename = fileStub+".java";
//		String classFilename = fileStub + ".class";
//		
//		File javaFile = new File(javaFilename);
//		File classFile = new File(classFilename);
//		//��ָ��JavaԴ�ļ����ڣ��ң�Class�ļ������ڣ�����JavaԴ�ļ����޸�ʱ���class�ļ����޸�ʱ�����ʱ�����±���
//		if(javaFile.exists() && (!classFile.exists()||javaFile.lastModified()>classFile.lastModified())){
//			try {
//				//�������ʧ�ܣ����߸�class�ļ�������
//				if(!compile(javaFilename)||!classFile.exists()){
//					throw new ClassNotFoundException("ClassNotFoundException:"+javaFilename);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//		//���class�ļ����ڣ�ϵͳ���𽫸��ļ�ת����class����
//		if(classFile.exists()){
//			try {
//				//��class�ļ��Ķ��������ݶ�������				
//				byte[] raw = getBytes(classFilename);
//				//����ClassLoader��defineClass ����������������ת����class����
//				clazz = defineClass(name,raw,0,raw.length);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//		
//		//���clazzΪnull����������ʧ�ܣ����׳��쳣
//		if(clazz == null){
//			throw new ClassNotFoundException(name);
//		}
//		
//		return clazz;	
//	}

	
	//����һ��������
	public static void main(String[] a) throws Exception{
		//������иĳ���ʱû�в�������û��Ŀ����
//		if(a.length<1){
//			System.out.println("ȱ��Ŀ���࣬�밴�����¸�ʽ����JavaԴ�ļ���");
//			System.out.println("java myClassLoader ClassName");
//			return;
//		}
//
//		//��һ����������Ҫ���е���
//		String progClass = a[0];
//		//ʣ�µĲ�������Ϊ����Ŀ����ʱ�Ĳ���
//		//����Щ������ֵ��һ����������
//		String[] progArgs = new String[a.length-1];
//		//����array���飬�� a ���Ƶ� progArgs;��a ���±�1��ʼ���ƣ���progArgs���±�0.������progArgs.length
//		//����Ԫ�ص������ǣ� a �±�index����Ӧ��Ԫ�� �� a �±� index+length-1����Ӧ��Ԫ�� ֮�������
//		System.arraycopy(a, 1, progArgs,0,progArgs.length);
		
		String ss = "Chapter18.Hello";
		int i = 0 ;  
        
        while(true){  
        	ComClassLoader mcl = new ComClassLoader() ;  
            System.out.println(mcl.getParent());  
            Class<?> personClass =  mcl.findClass(ss);  
              
            try {  
                Object person =  personClass.newInstance() ;  
                Method sayHelloMethod = personClass.getMethod("sayHello") ;  
                sayHelloMethod.invoke(person) ;  
                System.out.println(++i);  
            } catch (InstantiationException e1) {  
                e1.printStackTrace();  
            } catch (IllegalAccessException e1) {  
                e1.printStackTrace();  
            } catch (SecurityException e) {  
                e.printStackTrace();  
            } catch (NoSuchMethodException e) {  
                e.printStackTrace();  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (InvocationTargetException e) {  
                e.printStackTrace();  
            }  
              
            try {  
                Thread.sleep(1000) ;  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } 
		
//		ClassLoader cl = ClassLoader.getSystemClassLoader();
//		cl.loadClass("Chapter18.Hello").newInstance();  //�õ��� Tester��   ����һ���࣬�����ᵼ��һ����� ��ʼ��
		
		
		
//		CompileClassLoader mc = new CompileClassLoader();
		
//		//������Ҫ���е���
//		Class<?> clazz = mc.loadClass("Chapter18.Tester");
//		clazz.newInstance();
//		System.out.println(value);
//		//��ȡ��Ҫ���е����������
//		Method main = clazz.getMethod("main", (new String[0].getClass()));
//		Object argsArray[] = {progArgs};
//		main.invoke(null, argsArray);	
		
	}
}
	
}
