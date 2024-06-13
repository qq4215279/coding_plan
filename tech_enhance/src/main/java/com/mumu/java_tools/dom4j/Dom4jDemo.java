package com.mumu.java_tools.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Dom4jDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/12 14:39
 */
public class Dom4jDemo {

    
    /**
     *
     * dom4j 提供了丰富的 API 来处理 XML 文档，以下是一些常用的 API：
     *
     * 1. Document： 表示整个 XML 文档，是 XML 文档的根节点，可以通过 DocumentHelper.createDocument() 方法创建新的文档对象。
     * 2. Element： 表示 XML 文档中的元素节点，包括根元素、子元素等。可以通过 element.addElement() 方法添加子元素，element.attribute() 方法添加属性，element.getText() 方法获取元素的文本内容等。
     * 3. Attribute： 表示 XML 元素的属性节点，包含属性的名称和值，可以通过 element.attribute() 方法获取元素的属性。
     * 4. Node： 表示 XML 文档中的节点，包括元素节点、文本节点、注释节点等，可以通过 element.node() 方法获取节点。
     * 5. XPath： 提供了对 XML 文档进行查询和定位的功能，可以使用 XPath 表达式选择文档中的节点，例如 document.selectSingleNode()、element.selectNodes() 等方法。
     * 6. SAXReader： 用于从输入流中读取 XML 文档并将其解析为 dom4j 的 Document 对象，可以通过 SAXReader.read() 方法读取 XML 文档。
     * 7. OutputFormat 和 XMLWriter： 用于将 dom4j 的 Document 对象输出为 XML 格式的字符串或写入到输出流中，可以通过 OutputFormat.createPrettyPrint() 方法创建格式化的输出格式，然后使用 XMLWriter.write() 方法输出 XML 内容。
     * 8. DocumentHelper： 提供了创建 dom4j Document 对象的静态方法，例如 DocumentHelper.createDocument()、DocumentHelper.createElement() 等。
     *
     * Namespace： 表示 XML 文档中的命名空间，可以通过 Namespace.get() 方法创建命名空间对象，然后通过 element.addNamespace() 方法添加到元素中。 
     * @return void
     * @date 2024/4/12 14:49
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("F:\\Code\\MumuSpace\\coding_plan\\tech_enhance\\src\\main\\java\\com\\mumu\\java_tools\\dom4j\\xml\\config_item.xml");
        InputStream inputStream = new FileInputStream(file);

        try {
            // 创建 SAXReader 对象
            SAXReader reader = new SAXReader();

            // 读取 XML 文件
            Document document = reader.read(inputStream);

            // 获取根元素
            Element root = document.getRootElement();
            String excelName = root.getName();

            // 遍历子元素
            for (Object o : root.elements()) {
                Element excelElement = (Element) o;

                for (Object o2 : excelElement.elements()) {
                    Element subTableElement = (Element) o2;

                    Element uniqueId = subTableElement.element("uniqueId");
                    System.out.println("uniqueId:: " + uniqueId.getText());

                    for (Object o3 : subTableElement.elements()) {
                        Element fieldElement = (Element) o3;

                        // 输出元素名和文本内容
                        System.out.println(fieldElement.getName() + ": " + fieldElement.getText());
                    }
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }



    }


}
