package com.mumu.geek.c20_string.basic;

/**
 * LeetCode125
 * 验证回文串
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:57
 */
public class LeetCode125 {

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 解释："amanaplanacanalpanama" 是回文串
     *
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     * 解释："raceacar" 不是回文串
     *
     * 提示：
     * 1 <= s.length <= 2 * 105
     * 字符串 s 由 ASCII 字符组成
     */

    /**
     * 双指针
     * @author liuzhen
     * @date 2021/9/3 10:59
     * @param s
     * @return boolean
     */
    public static boolean isPalindrome(String s) {
        char[] chars = s.trim().toLowerCase().toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            while (left < right && (!Character.isLetterOrDigit(chars[left]))) {
                left++;
            }
            while (right > left && (!Character.isLetterOrDigit(chars[right]))) {
                right--;
            }

            if (chars[left] != chars[right]) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /**
     * 筛选 + 判断：对字符串 s 进行一次遍历，并将其中的字母和数字字符进行保留，放在另一个字符串 sb 中。这样我们只需要判断 sb 是否是一个普通的回文串即可。
     * @author liuzhen
     * @date 2021/9/3 11:01
     * @param s
     * @return boolean
     */
    public boolean isPalindrome2(String s) {
        StringBuffer sb = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sb.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer res = new StringBuffer(sb).reverse();
        return sb.toString().equals(res.toString());
    }

    public static void main(String[] args) {
        String s2 = "a a";
        String s3 = "A man, a plan, a canal: Panama";
        String s = "\"Sue,\" Tom smiles, \"Selim smote us.\"";
        System.out.println(isPalindrome(s));
    }

}
