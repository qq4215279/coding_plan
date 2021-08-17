package com.demo.geek.c11_binary_search;

/**
 * LeetCode074
 * 搜索二维矩阵
 * @author liuzhen
 * @version 1.0.0 2021/8/17 21:30
 */
public class LeetCode074 {

    /**
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     * 示例 1：
     *     1    3   5   7
     *     10   11  16  20
     *     23   30  34  60
     *
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * 输出：true
     *
     * 示例 2：
     *     1    3   5   7
     *     10   11  16  20
     *     23   30  34  60
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * 输出：false
     *  
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -104 <= matrix[i][j], target <= 104
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrix02(int[][] matrix, int target) {

        return false;
    }

}
