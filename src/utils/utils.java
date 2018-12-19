package utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class utils {
	
	/**
	 * ����ָ��λ�ã�ָ�����ļ�
	 */
	public static void cleanFile(String address,String suffix){
		File f  =  new File(address);
		if(f.isDirectory()){
			File[] fileList = f.listFiles();
			for (File file :fileList) {
				String fileName = file.getName();
				if(fileName.endsWith(suffix)){
					file.delete();
				}
			}
		}
	}
	
	public static String getProperty(String key){
		URL url;
		String serPath = null;
		try {
			url = new URL("file:///E:/properties.properties");
			InputStream inStream = url.openStream(); 
			Properties pp = new Properties();
			pp.load(inStream);
			serPath = pp.getProperty(key);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		return serPath;
	}
	
	/**
	 * �� Map<String, Integer> ��value���������ɴ�С��
	 * @param oldMap
	 * @return
	 */
    public static Map<String, Integer> sortMap(Map<String, Integer> oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());  
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
  
            @Override  
            public int compare(Entry<java.lang.String, Integer> arg0,  
                    Entry<java.lang.String, Integer> arg1) {  
//                return arg0.getValue() - arg1.getValue();  
            	return arg1.getValue() - arg0.getValue();
            }  
        });  
        Map<String, Integer> newMap = new LinkedHashMap<String, Integer>();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    }
    
    /**
     * ��ӡmap
     * @param map
     */
    public static void printMap(Map map){  
        System.out.println("===================mapStart==================");  
        Iterator it = map.entrySet().iterator();  
        while(it.hasNext()){  
            Map.Entry entry = (Map.Entry) it.next();  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
        System.out.println("===================mapEnd==================");  
    }
    
	public static JSONObject toJSONObject(String str) {
		if (StringUtils.isEmpty(str) || !isJSONString(str)) {
			str = "{}";
		}
		return JSON.parseObject(str);
	}
	
	/**
	 * �Ƿ�ΪJSON�ַ���
	 * @param str
	 */
	public static boolean isJSONString(String str) {
		boolean ret = false;
		if (str != null && str.length() >= 2 && str.startsWith("{") && str.endsWith("}")) {
			ret = true;
		}
		return ret;
	}
	
	public static JSONArray toJSONArray(String str) {
		if (StringUtils.isEmpty(str)) {
			str = "[]";
		}
		return JSON.parseArray(str);
	}
	
	/**
	 * ��ӡ�ָ���
	 */
	public static void PrintLine(String str){
		System.out.println("-------------------------------------------------"+str+"-------------------------------------------------");
	}
	
	/**
	 * ��ӡ��ǰʱ��
	 * @param str
	 */
	public static void PrintTimeNow(){
		Date date = new Date();		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = format.format(date);
		System.out.println(time);		
	}

}
