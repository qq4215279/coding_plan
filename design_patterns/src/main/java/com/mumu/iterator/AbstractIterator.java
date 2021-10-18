//designpatterns.iterator.AbstractIterator.java
package com.mumu.iterator;

//���������
public interface AbstractIterator {
	public void next(); //������һ��Ԫ��
	public boolean isLast(); //�ж��Ƿ�Ϊ���һ��Ԫ��
	public void previous(); //������һ��Ԫ��
	public boolean isFirst(); //�ж��Ƿ�Ϊ��һ��Ԫ��
	public Object getNextItem(); //��ȡ��һ��Ԫ��
	public Object getPreviousItem(); //��ȡ��һ��Ԫ��
}
