package com.algorithm.classic._09;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长无重复字符子串长度
 */
public class NoRepeatSubStringLength {

    public static int maxNoRepeatSubStringLength(String string) {
        if (string == null || string.length() < 1) {
            return 0;
        }
        char[] chars = string.toCharArray();
        //必须以i结尾最大无重复字符子串的开始下标(长度也可以，下标方便计算而已)
        int[] dp = new int[string.length()];

        Map<Character, Integer> lastIndex = new HashMap<>();
        dp[0] = 0;
        int ans = 1;
        lastIndex.put(chars[0], 0);
        for (int i = 1; i < chars.length; i++) {
            int lastI = lastIndex.getOrDefault(chars[i], -1);
            int pre = dp[i - 1];
            dp[i] = Math.max(pre, lastI + 1);
            ans = Math.max(ans, i - dp[i]);
        }
        return ans;
    }
}
