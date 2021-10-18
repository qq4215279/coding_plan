//designpatterns.facade.FileReader.java
package com.mumu.facade;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//�ļ���ȡ�ࣺ��ϵͳ��
public class FileReader {
	public String read(String fileNameSrc) {
		System.out.print("��ȡ�ļ�����ȡ���ģ�");
		StringBuffer sb = new StringBuffer();
		try{
			FileInputStream inFS = new FileInputStream(fileNameSrc);
			int data;
  		while((data = inFS.read())!= -1) {
  			sb = sb.append((char)data);
  		}
   		inFS.close();
   		System.out.println(sb.toString());
		}
		catch(FileNotFoundException e) {
			System.out.println("�ļ������ڣ�");
		}
		catch(IOException e) {
			System.out.println("�ļ���������");
		}
		return sb.toString();
	}
}
