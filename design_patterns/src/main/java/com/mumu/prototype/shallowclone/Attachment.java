//designpatterns.prototype.shallowclone.Attachment.java
package com.mumu.prototype.shallowclone;

public class Attachment {
	private String name; //������

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

    public void download() {
    	System.out.println("���ظ������ļ���Ϊ" + name);
    }
}
