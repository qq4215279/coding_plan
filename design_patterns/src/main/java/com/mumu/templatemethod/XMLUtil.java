//designpatterns.templatemethod.XMLUtil.java
package com.mumu.templatemethod;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class XMLUtil {
	//�÷������ڴ�XML�����ļ�����ȡ������������������һ��ʵ������
	public static Object getBean() {
		try {
			//����DOM�ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc =builder.parse(new File("src//designpatterns//templatemethod//config.xml"));

			//��ȡ�����������ı��ڵ�
			NodeList nl = doc.getElementsByTagName("className");
			Node classNode=nl.item(0).getFirstChild();
			String cName=classNode.getNodeValue();

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
