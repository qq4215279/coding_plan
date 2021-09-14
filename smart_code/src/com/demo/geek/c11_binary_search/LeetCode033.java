package com.demo.geek.c11_binary_search;

/**
 * LeetCode033
 * 搜索旋转排序数组
 * @author liuzhen
 * @version 1.0.0 2021/8/17 21:28
 */
public class LeetCode033 { // 等价于 LeetCode153

    /**
     * 整数数组 nums 按升序排列，数组中的值互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标从0开始计数）。
     * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     * 示例 1：
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * 示例 2：
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     *
     * 示例 3：
     * 输入：nums = [1], target = 0
     * 输出：-1
     *  
     * 提示：
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     * nums 中的每个值都 独一无二
     * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
     * -10^4 <= target <= 10^4
     *  
     * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
     *
     * 思路：
     * 1. 暴力：还原 O(logN)  -> 升序 -> 二分: O(logN)
     * 2. 正解：二分查找:   - 单调  - 边界  - index
     *
     */

    /**
     * 二分查找
     * 思路：1 2 3 4 5 6 7 可以大致分为两类，（num[0] < num[middle] 则说明前半部分一定是有序的！！！）
     *  第一类: 2 3 4 5 6 7 1 这种，也就是 nums[start] <= nums[mid]。此例子中就是 2 <= 5。这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
     *  第二类: 6 7 1 2 3 4 5 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2。这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]，则在后半部分找，否则去前半部分找。
     * @author liuzhen
     * @date 2021/8/17 23:08
     * @param nums
     * @param target
     * @return int
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int middle;
        while (start <= end) {
            middle = start + (end - start) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            // 1. 前半部分有序,注意此处用小于等于
            if (nums[start] <= nums[middle]) {
                // a.target在前半部分（向前规约）
                if (target >= nums[start] && target < nums[middle]) {
                    end = middle - 1;
                } else { // b.否则在后半部分（向后规约）
                    start = middle + 1;
                }
            } else { // 2. 后半部分有序
                // a. target在后半部分（向后规约）
                if (target > nums[middle] && target <= nums[end]) {
                    start = middle + 1;
                } else { // b. 否则在前半部分（向前规约）
                    end = middle - 1;
                }
            }

        }
        return -1;
    }

    // 0 1 2 3 4 5 6
    /**
     * TODO 暴力法：先还原，在二分法查找  程序待完善...
     * @author liuzhen
     * @date 2021/8/23 16:25
     * @param nums
     * @param target
     * @return int
     */
    public static int search2(int[] nums, int target) { //   3 4 5 6 7 1 2
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 还原
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int middle = low + (high - low) / 2;
            // 左边升序，向后规约
            if (nums[low] < nums[middle]) {
                low = middle;
            } else { // 右边升序，所以向左规约
                high = middle;
            }
        }

        int r = high;
//        System.out.println(r);
        // 数组复制
        int[] newArr = new int[nums.length];
        System.arraycopy(nums, r + 1, newArr, 0, nums.length - r - 1);
        System.arraycopy(nums, 0, newArr, nums.length - r - 1, r + 1);

        // 升序

        // 二分
        int res = binarySearch(newArr, target);

        return res;
    }

    /**
     * 二分查找
     * @author liuzhen
     * @date 2021/8/23 16:26
     * @param nums
     * @param target
     * @return int
     */
    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length < 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (target == nums[middle]) {
                return middle;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {         // right = 4   7
        System.out.println(search(new int[] {3, 4, 5, 6, 7, 1, 2}, 1));
        System.out.println(search2(new int[] {3, 4, 5, 6, 7, 1, 2}, 1));
    }

}
