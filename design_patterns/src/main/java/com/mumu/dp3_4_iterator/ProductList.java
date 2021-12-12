/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_4_iterator;

import java.util.*;

// 商品数据类：具体聚合类
public class ProductList extends AbstractObjectList {
    public ProductList(List<Object> products) {
        super(products);
    }

    // 实现创建迭代器对象的具体工厂方法
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}
