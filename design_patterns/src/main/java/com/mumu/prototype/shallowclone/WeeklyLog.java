//designpatterns.prototype.shallowclone.WeeklyLog.java
package com.mumu.prototype.shallowclone;

public class WeeklyLog implements Cloneable {
    //Ϊ�˼���ƺ�ʵ�֣�����һ�ݹ����ܱ���ֻ��һ����������ʵ������п��԰����������������ͨ��List�ȼ��϶�����ʵ��
	private Attachment attachment;
	private String name;
	private String date;
	private String content;

    public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Attachment getAttachment() {
		return (this.attachment);
	}

	public String getName() {
		return (this.name);
	}

	public String getDate() {
		return (this.date);
	}

	public String getContent() {
		return (this.content);
	}

    //ʹ��clone()����ʵ��ǳ��¡
	public WeeklyLog clone() {
		Object obj = null;
		try {
			obj = super.clone();
			return (WeeklyLog)obj;
		}
		catch(CloneNotSupportedException e) {
			System.out.println("��֧�ָ��ƣ�");
			return null;
		}
	}
}
