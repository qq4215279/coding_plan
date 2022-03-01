/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.dynamic;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

public class Client {
    public static void main(String args[]) {
        AbstractUserDAO userDAO = new UserDAO();
        InvocationHandler handler = new DAOLogHandler(userDAO);

        // 动态创建代理对象，用于代理一个AbstractUserDAO类型的真实主题对象
        AbstractUserDAO proxy = (AbstractUserDAO)Proxy.newProxyInstance(AbstractUserDAO.class.getClassLoader(), new Class[] {AbstractUserDAO.class}, handler);
		// 调用代理对象的业务方法
        proxy.findUserById("张无忌");

        System.out.println("------------------------------");

        AbstractDocumentDAO docDAO = new DocumentDAO();
        handler = new DAOLogHandler(docDAO);
        AbstractDocumentDAO proxy_new = null;

        // 动态创建代理对象，用于代理一个AbstractDocumentDAO类型的真实主题对象
		proxy_new = (AbstractDocumentDAO)Proxy.newProxyInstance(AbstractDocumentDAO.class.getClassLoader(), new Class[] {AbstractDocumentDAO.class}, handler);
		// 调用代理对象的业务方法
        proxy_new.deleteDocumentById("D002");
    }
}
