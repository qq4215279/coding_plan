/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_11_visitor;

import com.mumu.dp3_11_visitor.element.Employee;
import com.mumu.dp3_11_visitor.element.FulltimeEmployee;
import com.mumu.dp3_11_visitor.element.ParttimeEmployee;
import com.mumu.dp3_11_visitor.object_structure.EmployeeList;
import com.mumu.dp3_11_visitor.visitor.Department;

public class Client {
    public static void main(String args[]) {
        EmployeeList el = new EmployeeList();
        Employee fte1, fte2, fte3, pte1, pte2;

        fte1 = new FulltimeEmployee("张无忌", 3200.00, 45);
        fte2 = new FulltimeEmployee("杨过", 2000.00, 40);
        fte3 = new FulltimeEmployee("段誉", 2400.00, 38);
        pte1 = new ParttimeEmployee("洪七公", 80.00, 20);
        pte2 = new ParttimeEmployee("郭靖", 60.00, 18);

        el.addEmployee(fte1);
        el.addEmployee(fte2);
        el.addEmployee(fte3);
        el.addEmployee(pte1);
        el.addEmployee(pte2);

        Department dep;
        dep = (Department)XMLUtil.getBean();
        el.accept(dep);
    }
}
