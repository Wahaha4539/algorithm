package com.algorithm.classic._05;

/**
 * 最长递增子序列
 */
public class LongestIncreaseSubsequence {

    public static int longestIncreaseSubsequence2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] string = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            string[i] = chars[i];
        }
        int[] dp = new int[string.length];
        int[] ends = new int[string.length];
        ends[0] = string[0];
        dp[0] = 1;
        int right = 0;
        for (int i = 1; i < string.length; i++) {
            // 二分查找 >=string[i] 的 最左数 如果没有 则扩充
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (string[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(l, right);
            ends[l] = string[i];
            dp[i] = l + 1;
        }
        return right + 1;
    }

    public static String generate(int length) {
        int len = (int) (Math.random() * length) + 1;
        char[] string = new char[len];
        for (int i = 0; i < string.length; i++) {
            string[i] = (char) (Math.random() * 10 + '0');
        }
        return new String(string);
    }

    public static void main(String[] args) {
        int length = 10;
        System.out.println("测试开始");
        for (int i = 0; i < 1000; i++) {
            String string = generate(length);
            int ans2 = longestIncreaseSubsequence2(string);

        }
        System.out.println("测试结束");
    }
}
