/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.throwable;

/**
 * TryCatchPositionTest
 * test try catch 在方法中的位置
 * @author liuzhen
 * @version 1.0.0 2023/6/28 13:35
 */
public class TryCatchPositionTest {

    /** 标记是否购买VIP发放不同的奖励 */
    public static final long RANK_WUXIA_TOTAL_TYPE_VALUE1_LIMIT = 10L;
    /**  */
    public static final long RANK_WUXIA_TOTAL_TYPE_VALUE2_LIMIT = 1000000000L;

    public static void main(String[] args) {
        TryCatchPositionTest test = new TryCatchPositionTest();
        test.outMethod1();
        // test.outMethod2();


        int danId = 31;
        // 8千万声望
        long totalReputationNum = 8000000;
        int reputationGoodsId = 1;

        long v2 = totalReputationNum * RANK_WUXIA_TOTAL_TYPE_VALUE1_LIMIT + reputationGoodsId;
        System.out.println("v2: " + v2);
        long value = getAddScore(danId, v2, RANK_WUXIA_TOTAL_TYPE_VALUE2_LIMIT);
        System.out.println("value: " + value);


        // long mod = value % WuxiaConstants.RANK_WUXIA_TOTAL_TYPE_VALUE2_LIMIT;

        int getDanId =(int) (value / RANK_WUXIA_TOTAL_TYPE_VALUE2_LIMIT);
        int getReputationGoodsId = (int) (value % 10);
        System.out.println("getDanId: " + getDanId);
        System.out.println("getReputationGoodsId: " + getReputationGoodsId);
    }

    public static long getAddScore(long inc1, long inc2, long value2MaxLimit) {
        return inc1 * value2MaxLimit + inc2;
    }
    /**
     *
     * @date 2023/6/28 13:41
     * @param
     * @return void
     */
    public void outMethod1() {
        System.out.println("start execute outMethod1...");
        innerMethod1();
        System.out.println("end execute outMethod1...");
    }

    public void innerMethod1() {
        System.out.println("start execute innerMethod1...");

        int res = 0;
        try {
            res = 5 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(res);
        System.out.println("end execute innerMethod1...");
    }

    /**
     *
     * @date 2023/6/28 13:41
     * @param
     * @return void
     */
    public void outMethod2() {
        System.out.println("start execute outMethod1...");
        try {
            innerMethod2();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end execute outMethod1...");
    }

    public void innerMethod2() {
        System.out.println("start execute innerMethod1...");
        int res = 5 / 0;
        System.out.println(res);
        System.out.println("end execute innerMethod1...");
    }

}
