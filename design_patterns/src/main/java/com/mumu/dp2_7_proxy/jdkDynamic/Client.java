/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.jdkDynamic;

import com.mumu.dp2_7_proxy.jdkDynamic.document.IDocumentDAO;
import com.mumu.dp2_7_proxy.jdkDynamic.document.DocumentDAO;
import com.mumu.dp2_7_proxy.jdkDynamic.user.IUserDAO;
import com.mumu.dp2_7_proxy.jdkDynamic.user.UserDAO;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Client
 * 客户端test
 * @author liuzhen
 * @version 1.0.0 2023/11/13 15:57
 */
public class Client {
    public static void main(String args[]) {
		IUserDAO userDAO = new UserDAO();
		InvocationHandler handler = new DAOLogHandler(userDAO);

		// 动态创建代理对象，用于代理一个 IUserDAO 类型的真实主题对象
		IUserDAO proxy = (IUserDAO) Proxy.newProxyInstance(IUserDAO.class.getClassLoader(),
                new Class[] { IUserDAO.class }, handler);
        // 调用代理对象的业务方法
        proxy.findUserById("张无忌");


		System.out.println("-------------------------------------------------------->");


		IDocumentDAO docDAO = new DocumentDAO();
        InvocationHandler handler2 = new DAOLogHandler(docDAO);

		// 动态创建代理对象，用于代理一个 IDocumentDAO 类型的真实主题对象
		IDocumentDAO proxy2 = (IDocumentDAO) Proxy.newProxyInstance(IDocumentDAO.class.getClassLoader(),
                new Class[] { IDocumentDAO.class }, handler2);
		// 调用代理对象的业务方法
		proxy2.deleteDocumentById("D002");
    }

}
