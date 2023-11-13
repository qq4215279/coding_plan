/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.jdkDynamic.document;

/**
 * IDocumentDAO
 * 抽象DocumentDAO：抽象主题角色
 * @author liuzhen
 * @version 1.0.0 2023/11/13 15:49
 */
public interface IDocumentDAO {

    /**
     * 删除
     * @param documentId documentId
     * @return boolean
     * @date 2023/11/13 15:50
     */
    boolean deleteDocumentById(String documentId);
}
