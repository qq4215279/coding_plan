/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.collection.traverse;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * MapTraverseDemo
 * Map集合遍历
 * @author liuzhen
 * @version 1.0.0 2022/4/10 10:05
 */
public class MapTraverseDemo {

    /**
     * 总结：
     * 　基本上使⽤第三种⽅法是性能最好的，
     * 　第⼀种遍历⽅法在我们只需要 key 集合或者只需要 value 集合时使⽤；
     *   第⼆种⽅法效率很低，不推荐使⽤；
     * 　第四种⽅法效率也挺好，关键是在遍历的过程中我们可以对集合中的元素进⾏删除。
     */

    private Map<String,Object> map = new HashMap<>();

    @Before
    public void before() {
        map.put("A","1");
        map.put("B","2");
        map.put("C","3");
    }

    /**
     * 1. 分别获取 key 集合和 value 集合。
     * @author liuzhen
     * @date 2022/4/10 10:07
     * @param
     * @return void
     */
    @Test
    public void demo01() {
        // 1、分别获取key和value的集合
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        for (Object value : map.values()) {
            System.out.println(value);
        }
    }

    /**
     * 2. 获取 key 集合，然后遍历key集合，根据key分别得到相应value
     * @author liuzhen
     * @date 2022/4/10 10:07
     * @param
     * @return void
     */
    @Test
    public void demo02() {
        // 2、获取key集合，然后遍历key，根据key得到 value
        Set<String> keySet = map.keySet();
        for (String str : keySet) {
            System.out.println(str + "-" + map.get(str));
        }
    }

    /**
     * 3. 分别获取 key 集合和 value 集合。
     * @author liuzhen
     * @date 2022/4/10 10:07
     * @param
     * @return void
     */
    @Test
    public void demo03() {
        // 3、得到 Entry 集合，然后遍历 Entry
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    /**
     * 4. 迭代
     * @author liuzhen
     * @date 2022/4/10 10:07
     * @param
     * @return void
     */
    @Test
    public void demo04() {
        // 4、迭代
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> mapEntry = iterator.next();
            System.out.println(mapEntry.getKey() + "-" + mapEntry.getValue());
        }
    }
}
