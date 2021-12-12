/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_11_visitor.visitor;

import com.mumu.dp3_11_visitor.element.FulltimeEmployee;
import com.mumu.dp3_11_visitor.element.ParttimeEmployee;

// 部门类：抽象访问者类
public abstract class Department {
    // 声明一组重载的访问方法，用于访问不同类型的具体元素
    public abstract void visit(FulltimeEmployee employee);

    public abstract void visit(ParttimeEmployee employee);
}
