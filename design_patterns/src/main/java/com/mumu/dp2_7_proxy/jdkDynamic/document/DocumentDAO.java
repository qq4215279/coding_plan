/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.jdkDynamic.document;

/**
 * DocumentDAO
 * 具体DocumentDAO类：真实角色
 * @author liuzhen
 * @version 1.0.0 2023/11/13 15:49
 */
public class DocumentDAO implements IDocumentDAO {
	@Override
	public boolean deleteDocumentById(String documentId) {
		if (documentId.equalsIgnoreCase("D001")) {
			System.out.println("删除ID为" + documentId + "的文档信息成功！");
			return true;
		} else {
			System.out.println("删除ID为" + documentId + "的文档信息失败！");
			return false;
		}
	}
}
