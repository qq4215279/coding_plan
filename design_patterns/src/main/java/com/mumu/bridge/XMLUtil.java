//designpatterns.bridge.XMLUtil.java
package com.mumu.bridge;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class XMLUtil {
	//�÷������ڴ�XML�����ļ�����ȡ������������������һ��ʵ������
	public static Object getBean(String args) {
		try {
			//�����ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File("src//designpatterns//bridge//config.xml"));
			NodeList nl=null;
			Node classNode=null;
			String cName=null;
			nl = doc.getElementsByTagName("className");

			//��ȡ��һ�����������Ľ�㣬�����������
			if(args.equals("image")) {
	            classNode=nl.item(0).getFirstChild();

			}
			//��ȡ�ڶ������������Ľ�㣬������ʵ����
			else if(args.equals("os")) {
	            classNode=nl.item(1).getFirstChild();
			}

	        cName=classNode.getNodeValue();
	        //ͨ����������ʵ�����󲢽��䷵��
	        Class c=Class.forName(cName);
		  	Object obj=c.newInstance();
	        return obj;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
