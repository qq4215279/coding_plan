/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureDemo9
 *
 * @author liuzhen
 * @version 1.0.0 2024/11/17 15:40
 */
public class CompletableFutureDemo9 {
    private static final Random RANDOM = new Random();
    private static volatile int result = 0;

    /**
     * 四种任务原型
     * 通过上面的例子可以总结出，提交给CompletableFuture执行的任务有四种类型：Runnable、Consumer、Supplier、Function。下面是这四种任务原型的对比。
     *
     * 四种任务原型                   无参数                                                 有参数
     * 无返回值                 Runnable接口；对应的提交方法：runAsync，thenRun         Consumer接口；对应的提交方法：thenAccept
     * 有返回值                 Supplier接口：对应的提交方法：supplierAsync            Function接口；对应的提交方法：thenApply
     *
     * runAsync 与 supplierAsync 是 CompletableFuture 的静态方法；而 thenAccept、thenAsync、thenApply是CompletableFutre的成员方法。
     * 因为初始的时候没有CompletableFuture对象，也没有参数可传，所以提交的只能是Runnable或者Supplier，只能是静态方法；
     * 通过静态方法生成CompletableFuture对象之后，便可以链式地提交其他任务了，这个时候就可以提交Runnable、Consumer、Function，且都是成员方法。
     *
     * CompletionStage接口
     * CompletableFuture不仅实现了Future接口，还实现了CompletableStage接口。
     * CompletionStage接口定义的正是前面的各种链式方法、组合方法，如下所示。
     * public interface CompletionStage<T> {
     *     public CompletionStage<Void> thenRun(Runnable action);
     *     public CompletionStage<Void> thenAccept(Consumer<? super T> action);
     *     public <U> CompletionStage<U> thenApply(Function<? super T,? extends U>fn);
     *     public <U> CompletionStage<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
     *     public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn);
     *     // ...
     * }
     *
     * 关于CompletionStage接口，有几个关键点要说明：
     *  1. 所有方法的返回值都是CompletionStage类型，也就是它自己。正因为如此，才能实现如下的
     *  链式调用：future1.thenApply(...).thenApply(...).thenCompose(...).thenRun(...)。
     *  2. thenApply接收的是一个有输入参数、返回值的Function。这个Function的输入参数，必须是？Super T 类型，
     *      也就是T或者T的父类型，而T必须是调用thenApplycompletableFuture对象的类型；返回值则必须是？
     *      Extends U类型，也就是U或者U的子类型，而U恰好是thenApply的返回值的CompletionStage对应的类型。
     * 其他方法，诸如thenCompose、thenCombine也是类似的原理。
     */

    /**
     * 任意个CompletableFuture的组合
     * 上面的thenCompose和thenCombine只能组合2个CompletableFuture，而接下来的allOf 和 anyOf 可以组合任意多个CompletableFuture。方法接口定义如下所示。
     * <p>
     * 首先，这两个方法都是静态方法，参数是变长的CompletableFuture的集合。其次，allOf和anyOf 的区别，前者是“与”，后者是“或”。
     * allOf的返回值是CompletableFuture<Void>类型，这是因为每个传入的CompletableFuture的返回值都可能不同，所以组合的结果是无法用某种类型来表示的，索性返回Void类型。
     * anyOf 的含义是只要有任意一个 CompletableFuture 结束，就可以做接下来的事情，而无须像AllOf那样，等待所有的CompletableFuture结束。
     * 但由于每个CompletableFuture的返回值类型都可能不同，任意一个，意味着无法判断是什么类型，所以anyOf的返回值是CompletableFuture<Object>类型。
     *
     * @param args
     * @return void
     * @date 2023/8/13 23:40
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture[] futures = new CompletableFuture[10];

        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> myFuture = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 + RANDOM.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    result++;
                }
            })/* .thenRun(new Runnable() {

                @Override
                public void run() {
                    System.out.println("myFuture finish: " + finalI);
                }
            }) */;

            futures[i] = myFuture;
        }

        for (int i = 0; i < 10; i++) {
            futures[i].get();
            System.out.println(result);
        }

        CompletableFuture<Void> future = CompletableFuture.allOf(futures).thenRun(new Runnable() {
            @Override
            public void run() {
                System.out.println("计算完成");
            }
        });

        future.get();
        System.out.println(result);


        // CompletableFuture myfuture = CompletableFuture.anyOf(futures).thenRun(new Runnable() {
        //     @Override
        //     public void run() {
        //         System.out.println(result);
        //     }
        // });
        //
        // myfuture.get();
    }
}
