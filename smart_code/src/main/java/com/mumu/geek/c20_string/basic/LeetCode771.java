package com.mumu.geek.c20_string.basic;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode771
 * 宝石和石头
 * @author liuzhen
 * @version 1.0.0 2021/8/30 22:37
 */
public class LeetCode771 {

    /**
     * 给定字符串 J 代表石头中宝石的类型，和字符串 S 代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     *
     * 示例 1:
     * 输入: J = "aA", S = "aAAbbbb"
     * 输出: 3
     * 示例 2:
     *
     * 输入: J = "z", S = "ZZ"
     * 输出: 0
     *
     * 注意:
     *  S 和 J 最多含有50个字母。
     *  J 中的字符不重复。
     */

    /**
     * Set 集合
     * @author liuzhen
     * @date 2021/9/2 14:11
     * @param jewels
     * @param stones
     * @return int
     */
    public static int numJewelsInStones(String jewels, String stones) {
        int jewelsCount = 0;
        Set<Character> jewelsSet = new HashSet<>();

        for (int i = 0; i < jewels.length(); i++) {
            char jewel = jewels.charAt(i);
            jewelsSet.add(jewel);
        }

        for (int i = 0; i < stones.length(); i++) {
            char stone = stones.charAt(i);
            if (jewelsSet.contains(stone)) {
                jewelsCount++;
            }
        }
        return jewelsCount;
    }

}
