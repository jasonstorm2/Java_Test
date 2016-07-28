package Exercise;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

//Դ��  http://my.oschina.net/leejun2005/blog/81729
public class EvalExecise {

	public static Object eval(String str) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("public class Temp");
		sb.append("{");
		sb.append("    public Object getObject()");
		sb.append("    {");
		sb.append("        " + str + "return new Object();");
		sb.append("    }");
		sb.append("}");
		// ���� �Զ���������� ���� �������ڴ���class�ļ�
		// ˵�������ַ�ʽҲ��ҪЩ�������д���̵�
		// Ϊëһ��Ҫ����أ�ֱ�� �ڴ������ ����������
		// Ӧ��Ҳ�ǿ��Եģ����Ӵ��̶���Ҳ�ǽ��ڴ�
		// ֻ���� java������ֱ�Ӳ����ڴ�!
		// дjni�����ǿ���
		Class<?> clazz = new MyClassLoader().findClass(sb.toString());//ʵ�ֶ��󣬲����ö���ķ���
		Method method = clazz.getMethod("getObject");
		// ͨ��������÷���
		return method.invoke(clazz.newInstance());
	}

	public static void main(String[] args) throws Exception {
		 Object rval = eval("System.out.println(\"Hello World\");");
	        System.out.println(rval);
	}
}

class MyClassLoader extends ClassLoader {
	@Override
	public Class<?> findClass(String str) throws ClassNotFoundException {
		System.out.println("��дfindClass�Ƿ��ܴ�ӡ������");
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// �������Դ����������Ķ���
//		DiagnosticCollector diagnostics = new DiagnosticCollector();
		// �ڴ��е�Դ���뱣����һ����JavaFileObject�̳е�����
		JavaFileObject file = new JavaSourceFromString("Temp", str.toString());
		 System.out.println(file);
		Iterable<JavaFileObject> compilationUnits = Arrays.asList(file);
		// ���ڱ���Exception in thread "main" java.lang.ClassNotFoundException: Temp
		// �Ľ��������http://willam2004.iteye.com/blog/1026454
		// ��ҪΪcompiler.getTask����ָ������·����
		// ִ�й������£�
		// 1����������ַ�����ʾ��
		// 2��������
		// 3�����ر�������
		// 4��ʵ���������е��á�
		// ��eclipse��������������ķ�ʽ���е��ã����ڵ������м��ر����������׳���ClassNotFoundException����
		// ��ΪĬ�ϵ�Eclipse��java���̱������ļ��Ƿ��ڵ�ǰ�����µ�binĿ¼�¡����ڶ������������·���ǹ���Ŀ¼��,
		// ���Լ���ʱ���׳����Ҳ����Ĵ���
		String flag = "-d";
		String outDir = System.getProperty("user.dir") + "/" + "bin";
		Iterable<String> stringdir = Arrays.asList(flag, outDir); // ָ��-d dir ����
		// ����һ����������
		JavaCompiler.CompilationTask task = compiler.getTask(null, null, null, stringdir, null, compilationUnits);
		// ����Դ����
		boolean result = task.call();
		if (result) {
			return Class.forName("Temp");
		}
		return null;
		
	}

}

class JavaSourceFromString extends SimpleJavaFileObject {
	private String code;

	public JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}

	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
}
