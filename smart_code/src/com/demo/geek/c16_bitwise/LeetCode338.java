package com.demo.geek.c16_bitwise;

/**
 * LeetCode338
 * 比特位计数
 * @author liuzhen
 * @version 1.0.0 2021/8/26 21:32
 */
public class LeetCode338 {

    /**
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * 示例 1:
     * 输入: 2
     * 输出: [0,1,1]
     *
     * 示例 2:
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     *
     * 进阶:
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
     */

    /**
     * DP + 位运算
     * @author liuzhen
     * @date 2021/8/26 22:02
     * @param n
     * @return int[]
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        int i = 0;
        int b = 1;

        // [0, b] is calculated
        while (b <= n) {
            //
            while (i < b && i + b <= n) {
                ans[i + b] = ans[i] + 1;
                ++i;
            }
            i = 0; // reset i
            b <<= 1; // b = 2b
        }

        return ans;
    }

    public static void main(String[] args) {
        long a = ~0;
        long b = ~1;
        long c = ~2;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        // 下面字符串，在常量池中分配中内存，所以字符串不可变！！！
        String aa = "aaa";
        aa.toUpperCase();
        System.out.println(aa);
        String bb = new String("bbb");
        bb.toUpperCase();
        System.out.println(bb);

        StringBuffer sb = new StringBuffer("ccc");
        String cc = sb.toString();
        cc.toUpperCase();
        System.out.println(cc);

    }

}
