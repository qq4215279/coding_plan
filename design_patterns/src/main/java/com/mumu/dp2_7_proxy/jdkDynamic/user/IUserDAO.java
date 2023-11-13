/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.jdkDynamic.user;

/**
 * IUserDAO
 * 抽象UserDAO：抽象主题角色
 * @author liuzhen
 * @version 1.0.0 2023/11/13 15:51
 */
public interface IUserDAO {

    /**
     * 查找
     * @param userId userId
     * @return boolean
     * @date 2023/11/13 15:53
     */
    boolean findUserById(String userId);
}
