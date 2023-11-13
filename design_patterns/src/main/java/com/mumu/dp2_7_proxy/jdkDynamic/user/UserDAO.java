/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_7_proxy.jdkDynamic.user;

/**
 * UserDAO
 * 具体UserDAO类：真实角色
 * @author liuzhen
 * @version 1.0.0 2023/11/13 15:52
 */
public class UserDAO implements IUserDAO {
    @Override
    public boolean findUserById(String userId) {
        if (userId.equalsIgnoreCase("张无忌")) {
            System.out.println("查询ID为" + userId + "的用户信息成功！");
            return true;
        } else {
            System.out.println("查询ID为" + userId + "的用户信息失败！");
            return false;
        }
    }

}
