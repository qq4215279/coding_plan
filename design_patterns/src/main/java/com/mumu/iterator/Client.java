//designpatterns.iterator.Client.java
package com.mumu.iterator;
import java.util.*;

public class Client {
	public static void main(String args[]) {
		List<Object> products = new ArrayList<Object>();
		products.add("���콣");
		products.add("������");
		products.add("�ϳ���");
		products.add("��������");
		products.add("��ʮ���¾�");

		AbstractObjectList list;
		AbstractIterator iterator;

		list = new ProductList(products); //�����ۺ϶���
		iterator = list.createIterator();	//��������������

		System.out.println("���������");
		while(!iterator.isLast()) {
			System.out.print(iterator.getNextItem() + "��");
			iterator.next();
		}
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("���������");
		while(!iterator.isFirst()) {
			System.out.print(iterator.getPreviousItem() + "��");
			iterator.previous();
		}
	}
}
