package com.demo.common.utils;

/**
 * ArrayUtil
 * 数组工具类
 * @author liuzhen
 * @version 1.0.0 2021/8/12 14:57
 */
public class ArrayUtil {

    /**
     * 交换数组的两个数
     * @author liuzhen
     * @date 2021/8/12 14:58
     * @param arr
     * @param i
     * @param j
     * @return void
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 获取坐标的参考点
     * @author liuzhen
     * @date 2021/8/23 15:47
     * @param row 横坐标
     * @param col 纵坐标
     * @param cols 列数量
     * @return int
     */
    public static int getReferencePoint(int row, int col, int cols) {
        return row * cols + col;
    }

    /**
     * 获取X坐标
     * @author liuzhen
     * @date 2021/8/23 16:05
     * @param referencePoint 参考点
     * @param cols 列数量
     * @return int
     */
    public static int getX(int referencePoint, int cols) {
        return referencePoint / cols;
    }

    /**
     * 获取Y坐标
     * @author liuzhen
     * @date 2021/8/23 16:05
     * @param referencePoint 参考点
     * @param cols 列数量
     * @return int
     */
    public static int getY(int referencePoint, int cols) {
        return referencePoint % cols;
    }
}
