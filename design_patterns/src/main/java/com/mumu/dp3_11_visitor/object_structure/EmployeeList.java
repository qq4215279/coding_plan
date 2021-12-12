/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_11_visitor.object_structure;

import com.mumu.dp3_11_visitor.element.Employee;
import com.mumu.dp3_11_visitor.visitor.Department;

import java.util.*;

// 员工列表类：对象结构
public class EmployeeList {
    // 定义一个集合用于存储员工对象
    private ArrayList<Employee> list = new ArrayList<Employee>();

    public void addEmployee(Employee employee) {
        list.add(employee);
    }

    // 遍历访问员工集合中的每一个员工对象
    public void accept(Department handler) {
        for (Object obj : list) {
            ((Employee)obj).accept(handler);
        }
    }
}
