//designpatterns.bridge.BMPImage.java
package com.mumu.bridge;

//BMP��ʽͼ���࣬�䵱���������
public class BMPImage extends Image {
	public void parseFile(String fileName) {
      //ģ�����BMP�ļ������һ�����ؾ������m;
      Matrix m = new Matrix();
      imp.doPaint(m);
      System.out.println(fileName + "����ʽΪBMP��");
  }
}
