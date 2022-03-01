/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.dynamic;

// 具体DocumentDAO类：真实角色
public class DocumentDAO implements AbstractDocumentDAO {

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
