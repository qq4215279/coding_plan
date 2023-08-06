/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.queue;

import com.mumu.common.pojo.User;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * PriorityQueueTest
 * 优先级队列test
 * @author liuzhen
 * @version 1.0.0 2021/8/2 14:29
 */
public class PriorityQueueTest {

    /**
     * 优先队列PriorityQueue是Queue接口的实现，可以对其中元素进行排序（add元素后，队列自动排序），
     * 可以放基本数据类型的包装类（如：Integer，Long等）或自定义的类
     * 对于基本数据类型的包装器类，优先队列中元素默认排列顺序是升序排列
     * 但对于自己定义的类来说，需要自己定义比较器
     *
     * 常用api:
     * peek(): 返回队首元素
     * poll(): 返回队首元素，队首元素出队列
     * add() / offer(): 添加元素
     * remove(Object o): 从此队列中删除指定元素的单个实例（如果存在）。
     * removeAll(Collection<?> c) / clear(): 删除此集合的所有元素，这些元素也包含在指定的集合中（可选操作）。
     * size(): 返回队列元素个数
     * isEmpty(): 判断队列是否为空，为空返回true,不空返回false
     * iterator(): 不保证以任何特定顺序遍历队列元素。若想按特定顺序遍历，先将队列转成数组，然后排序遍历(Arrays.sort(arr))
     * toArray() / toArray(T[] a)：返回包含此队列中所有元素的数组; 返回数组的运行时类型是指定数组的运行时类型。
     */

    /**
     * 不使用比较器
     */
    @Test
    public void test01() {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(200);
        queue.add(11);
        queue.add(99);
        queue.add(100);

        // 注：这种遍历出来的元素不是有序的！！！！
//        Iterator<Integer> it = queue.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    /**
     *
     */
    @Test
    public void test02() {
        Queue<User> queue = new PriorityQueue<>(new AgeUser());
        queue.add(new User("wangwu", 200));
        queue.add(new User("lisi", 11));
        queue.add(new User("hehe", 99));
        queue.add(new User("zhangsan", 100));

        while (!queue.isEmpty()) {
            User user = queue.poll();
            System.out.println(user.toString());
        }
    }

}

class AgeUser implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return u2.getAge() - u1.getAge();
    }
}
