package com.mumu.geek.c8_divide_backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode078
 * 子集
 * @author liuzhen
 * @version 1.0.0 2021/8/15 13:05
 */
public class LeetCode078 {

    /**
     * 给你一个整数数组 nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * 示例 2：
     * 输入：nums = [0]
     * 输出：[[],[0]]
     *  
     * 提示：
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 中的所有元素 互不相同
     */

    /**
     * 分治回溯
     * 思路：这道题跟括号生成(LeetCode022)解法思路是一样的，整数数组 nums有几个元素，就可想象成有几层，一层一层考虑，每一层有两种情况，1: 是不选择这个数, 2: 是选择这个数，。。。
     * @author liuzhen
     * @date 2021/8/15 13:06
     * @param nums
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }

        dfs(ans, nums, new ArrayList<>(), 0);
        return ans;
    }

    private static void dfs(List<List<Integer>> ans, int[] nums, ArrayList<Integer> list, int index) {
        // terminator
        if (index == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // 1.不选当前层的数
        dfs(ans, nums, list, index + 1);

        // 2. 选择当前层的数
        list.add(nums[index]);
        dfs(ans, nums, list, index + 1);

        // restore state: 清理当前层的list局部变量，避免影响其他层
        list.remove(list.size() - 1);
    }

    /**
     * 迭代法
     * 思路：输入: nums = {1,2,3}   输出: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]  举例如下：
     * 1. 外层遍历nums
     * 2. 内层遍历返回结果ans，当num = 2时，此时ans：[[], [1]]  内层第一次遍历：[] -> [2]  内层第二次遍历: [1] -> [1,2]
     * 3. 重复1,2步骤...
     *
     * @author liuzhen
     * @date 2021/8/15 16:09
     * @param nums
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public static List<List<Integer>> subsets02(int[] nums) { // [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }

        // 添加空集合
        ans.add(new ArrayList<>());

        for (int num : nums) {
            // all 重要！！！记录此时ans的总长度
            int all = ans.size();
            // 遍历内置循环，产生新子集
            for (int j = 0; j < all; j++) {
                List<Integer> list = ans.get(j);

                List<Integer> newList = new ArrayList<>(list);
                newList.add(num);

                ans.add(newList);
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets.toString());

        System.out.println("------------------->");

        List<List<Integer>> subsets02 = subsets02(nums);
        System.out.println(subsets02.toString());
    }

}
