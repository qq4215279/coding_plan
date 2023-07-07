/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * UserGroup
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/7 11:08
 */
public class UserGroup {
    private String name;
    private List<User> users = new ArrayList<User>();

    public UserGroup() {
    }

    public UserGroup(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
