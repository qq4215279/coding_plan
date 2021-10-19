package com.mumu.dp1_4_prototype.deepclone;
import java.io.*;

public class Attachment implements Serializable {
	private String name; // 附件名

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void download() {
		System.out.println("下载附件，文件名为" + name);
	}
}
