package com.mumu.simplefactory;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class XMLUtil {
	//�÷������ڴ�XML�����ļ�����ȡͼ�����ͣ�������������
    public static String getChartType() {
        try {
        	//�����ĵ�����
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("src//designpatterns//simplefactory//config.xml"));

            //��ȡ����ͼ�����͵��ı����
            NodeList nl = doc.getElementsByTagName("chartType");
            Node classNode = nl.item(0).getFirstChild();
            String chartType = classNode.getNodeValue().trim();
            return chartType;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
