package com.algorithm.classic._09;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长无重复字符子串长度<br/>
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters">Longest Substring Without Repeating Characters</a>
 */
public class NoRepeatSubStringLength {

    public static int maxNoRepeatSubStringLength(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        //必须以i结尾最大无重复字符子串的开始下标(长度也可以，下标方便计算而已)
        int[] dp = new int[s.length()];

        Map<Character, Integer> lastIndex = new HashMap<>();
        dp[0] = 0;
        int ans = 1;
        lastIndex.put(chars[0], 0);
        for (int i = 1; i < chars.length; i++) {
            int lastI = lastIndex.getOrDefault(chars[i], -1);
            int pre = dp[i - 1];
            dp[i] = Math.max(pre, lastI + 1);
            ans = Math.max(ans, i - dp[i] + 1);
            lastIndex.put(chars[i], i);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxNoRepeatSubStringLength("abcabcbb"));
    }
}
