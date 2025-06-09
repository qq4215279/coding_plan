/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * CompletableFutureDemo
 * CompletableFuture
 * @author liuzhen
 * @version 1.0.0 2024/11/17 15:27
 */
public class CompletableFutureDemo {

    /**
     * CompletableFuture 实现了 Future 接口，所以它也具有 Future 的特性：调用 get() 方法会阻塞在那，直到结果返回。
     * 另外1个线程调用  complete 方法完成该Future，则所有阻塞在get()方法的线程都将获得返回结果。
     * @date 2024/11/17 15:30
     */
    @Test
    public void demo01() {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 另一个线程执行任务，将结果赋值给future
            future.complete("hello lagou");
        }).start();

        System.out.println("任务已经提交");

        // 阻塞的方法
        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
    }

    /**
     * demo01是一个空的任务，下面尝试提交一个真的任务，然后等待结果返回。
     * CompletableFuture.runAsync(...)  传入的是一个Runnable接口。
     * @date 2024/11/17 15:31
     */
    @Test
    public void demo02() {
        // 通过异步的方式给future指派任务，future没有返回值
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行完毕");
        });

        // 阻塞，等待任务执行完成
        Object o = null;
        try {
            o = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // null
        System.out.println(o);
    }
    
    /**
     * 例2和例1的区别在于，例2的任务有返回值。没有返回值的任务，提交的是Runnable，返回的是CompletableFuture<Void>；
     * 有返回值的任务，提交的是 Supplier，返回的是 CompletableFuture<String>。Supplier和前面的Callable很相似。
     * 通过上面两个例子可以看出，在基本的用法上，CompletableFuture和Future很相似，都可以提交两类任务：一类是无返回值的，另一类是有返回值的。
     * @date 2024/11/17 15:31
     */
    @Test
    public void demo03() {
        // 指定future要执行的任务，同时future会有返回值
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello lagou";
            }
        });

        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // hello lagou
        System.out.println(result);
    }

    /**
     * thenRun、thenAccept 和 thenApply
     * 对于 Future，在提交任务之后，只能调用 get()等结果返回；但对于 CompletableFuture，可以在结果上面再加一个callback，当得到结果之后，再接着执行callback。
     * 该案例最后不能获取到结果，只会得到一个null。
     * @date 2024/11/17 15:32
     */
    public void demo04() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello lagou";
            }
        });

        CompletableFuture<Void> voidCompletableFuture = future.thenRun(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务执行结束后的代码执行");
            }
        });

        // 阻塞等待任务执行完成
        try {
            voidCompletableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("任务执行结束");
    }

    /**
     * 下面代码在 thenAccept 中可以获取任务的执行结果，接着进行处理
     * @date 2024/11/17 15:37
     */
    @Test
    public void demo05() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello lagou";
            }
        }).thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                // 可以获取上个任务的执行结果，接着进行处理
                try {
                    System.out.println(s);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(s.length());
            }
        });

        // 阻塞等待任务执行完成
        Void aVoid = null;
        try {
            aVoid = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        // aVoid: null
        System.out.println("aVoid: " + aVoid);
    }

    /**
     * 三个例子都是在任务执行完成之后，接着执行回调，只是回调的形式不同：
     * 1. thenRun后面跟的是一个无参数、无返回值的方法，即Runnable，所以最终的返回值是 CompletableFuture<Void>类型。
     * 2. thenAccept后面跟的是一个有参数、无返回值的方法，称为Consumer，返回值也是 CompletableFuture<Void>类型。
     * 顾名思义，只进不出，所以称为Consumer；前面的 Supplier，是无参数，有返回值，只出不进，和Consumer刚好相反。
     * 3. thenApply 后面跟的是一个有参数、有返回值的方法，称为Function。返回值是 CompletableFuture<String>类型。
     * <p>
     * 而参数接收的是前一个任务，即 supplyAsync(...)这个任务的返回值。因此这里只能用supplyAsync，不能用runAsync。
     * 因为runAsync没有返回值，不能为下一个链式方法传入参数。
     * @date 2024/11/17 15:37
     */
    @Test
    public void demo06() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello lagou";
            }
        }).thenApply(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                // 接收上个任务的返回值，接着处理，同时将处理结果返回给future
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return s.length();
            }
        });

        Integer integer = null;
        try {
            integer = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(integer);
    }

    /**
     * thenCompose 与 thenCombine
     * 在上面的例子中，thenApply 接收的是一个 Function ，但是这个Function的返回值是一个通常的基本数据类型或一个对象，而不是另外一个 CompletableFuture。
     * 如果 Function 的返回值也是一个 CompletableFuture，就会出现嵌套的 CompletableFuture 。考虑下面的例子：
     * @date 2024/11/17 15:39
     */
    @Test
    public void demo07() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello lagou";
            }
        }).thenCompose(new Function<String, CompletableFuture<Integer>>() {
            @Override
            public CompletableFuture<Integer> apply(String s) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        return s.length();
                    }
                });
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        // 如果希望返回值是一个非嵌套的CompletableFuture，可以使用thenCompose：
        // CompletableFuture<CompletableFuture<Integer>> future = CompletableFuture.supplyAsync(new Supplier<String>() {
        //     @Override
        //     public String get() {
        //         return "hello lagou";
        //     }
        // }).thenApply(new Function<String, CompletableFuture<Integer>>() {
        //     @Override
        //     public CompletableFuture<Integer> apply(String s) {
        //         return CompletableFuture.supplyAsync(new Supplier<Integer>() {
        //             @Override
        //             public Integer get() {
        //                 return s.length();
        //             }
        //         });
        //     }
        // });
        //
        // Integer integer = future.get().get();
        // System.out.println(integer);
    }

    /**
     * CompletableFuture中的 thenCompose 实现：
     * 从该方法的定义可以看出，它传入的参数是一个Function类型，并且Function的返回值必须是CompletionStage的子类，也就是CompletableFuture类型。
     * <p>
     * thenCombine thenCombine方法的接口定义如下，从传入的参数可以看出，它不同于thenCompose。
     * 第1个参数是一个CompletableFuture类型，第2个参数是一个方法，并且是一个BiFunction，也就是该方法有2个输入参数，1个返回值。
     * 从该接口的定义可以大致推测，它是要在2个 CompletableFuture 完成之后，把2个 CompletableFuture的返回值传进去，再额外做一些事情。实例如下：
     * @date 2024/11/17 15:39
     */
    @Test
    public void demo08() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        }).thenCombine(CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "lagou";
            }
        }), new BiFunction<String, String, Integer>() {
            @Override
            public Integer apply(String s, String s2) {
                System.out.println("s = " + s);
                System.out.println("s2 = " + s2);
                return s.length() + s2.length();
            }
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
