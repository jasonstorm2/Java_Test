package my;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLAddDeleTest {

	/**
	 * ʹ��dom������xml���н���
	 * 
	 * @param args
	 *            �������ҷ��֣� Node �� Element, document�ĸ��࣬ Element����Ҫ�� ���ӣ�ɾ�����޸ģ�����
	 *            �ȡ�document ���� xml�еĶ��� ����document.getElementById();������
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// ����һ��documentBuilderFactoryʵ��
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// ����һ��documentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		// ָ�����Ǹ�xml�ļ�
		Document document = db.parse("family.xml");
		// list(document);
		// red(document);
//		createXML();
//		delete(document,"family.xml");
//		add(document,"family.xml");
		modify(document);
//		modify(document);
//		update(document);
	}

	// �޸�
	public static void modify(Document doc) throws Exception {
		
//		//ͨ�����֣���xml��˳�� ֱ���ҵ���һ��Ԫ�أ��޸�Ԫ������
//		Element ele = (Element)doc.getElementsByTagName("name3").item(0);
//		ele.setTextContent("xiaohai");
//
//		//�޸�����
//		Element element = (Element)doc.getElementsByTagName("name3").item(0);
//		element.setAttribute("sex", "nv");
		
        Element per =(Element) selectSingleNode("/family/girl[@id='005']", doc.getDocumentElement());
        per.getElementsByTagName("name").item(0).setTextContent("rose");
        update(doc, "family.xml");
       
		
		
	}

	// ɾ��
	public static void delete(Document doc,String path) throws Exception {		
		// �Ӹ��ڵ�ɾ��ָ�����ӽڵ�
		Element root = doc.getDocumentElement();        
        Element son =(Element) selectSingleNode("/family/son[@id='1']", root);
        if(son != null){
        	 root.removeChild(son);
             update(doc, path);
        }
       
		
	}
	
	// ����java��xml�ļ��в��������ݣ���Դ�ļ����и���
	public static void update(Document doc,String path) throws Exception {
		// ����һ��TransformerFactoryʵ��
		TransformerFactory tff = TransformerFactory.newInstance();
		// ͨ��TransformerFactory �õ�һ��ת����
		Transformer tf = tff.newTransformer();
		// ͨ��Transformer��ķ��� transform(Source xmlSource, Result outputTarget)
		// �� XML Source ת��Ϊ Result��
		tf.transform(new DOMSource(doc), new StreamResult(path));
	}

	// ����xml�ļ���Ԫ��
	public static void list(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE)
			System.out.println(node.getNodeName());
		// �õ��ý����ӽ��
		NodeList nodelist = node.getChildNodes();

		for (int i = 0; i < nodelist.getLength(); i++) {
			Node n = (Node) nodelist.item(i);
			list(n);
		}
	}

	// ��ȡdocument����� Ԫ�ص� �ı�
	public static void red(Document docu) {
		NodeList nodelist = docu.getElementsByTagName("xuesheng");
		Element element = (Element) nodelist.item(0);
		System.out.println(element.getAttribute("sex"));
		System.out.println(element.getTextContent());
	}
	
	
	public static void add(Document docu,String path) throws Exception{
		//��ø��ڵ�
		Element doElement = docu.getDocumentElement();	
		
		 //�����ӽڵ�
        Element girl =docu.createElement("girl");
        girl.setAttribute("id", "005");   
        //�ӽڵ���ӽڵ�
        Element name = docu.createElement("name");
        name.setTextContent("����");
        girl.appendChild(name);
        Element age = docu.createElement("age");
        age.setTextContent("5");        
        girl.appendChild(age);
        // ���ڵ�����ӽڵ�
        doElement.appendChild(girl);
        
		update(docu, "family.xml");
		
	}
	
	public static void createXML() throws Exception{   
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// ����һ��documentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		// ָ�����Ǹ�xml�ļ�
		Document docu = db.newDocument();
	    // ����XML������standaloneΪyes����û��dtd��schema��Ϊ��XML��˵���ĵ����Ҳ���ʾ������
         docu.setXmlStandalone(true);
		
		// ���ڵ�
        Element family =docu.createElement("family"); 
        
        // �ӽڵ�
        Element son = docu.createElement("son");
        son.setAttribute("id", "1");
        son.setTextContent("����"); 
        // ���ڵ�����ӽڵ�
        family.appendChild(son);
        
        docu.appendChild(family);
        
		// ����һ��TransformerFactoryʵ��
		TransformerFactory tff = TransformerFactory.newInstance();
		// ͨ��TransformerFactory �õ�һ��ת����
		Transformer tf = tff.newTransformer();
		// ͨ��Transformer��ķ��� transform(Source xmlSource, Result outputTarget)
		// �� XML Source ת��Ϊ Result��
		tf.transform(new DOMSource(docu), new StreamResult("family.xml"));

	}
	
	//ͨ��XPath����ȡĿ��ڵ�
	public static Node selectSingleNode(String express, Element source) {
        Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        
        return result;
    }
	
}
