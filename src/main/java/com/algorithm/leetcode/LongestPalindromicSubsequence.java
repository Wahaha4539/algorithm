package com.algorithm.leetcode;

/**
 * 最长回文子序列
 * <a href="https://leetcode.com/problems/longest-palindromic-subsequence/">...</a>
 */
public class LongestPalindromicSubsequence {
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        return process(s.toCharArray(), 0, s.length() - 1);
    }

    /**
     * 在leetcode上跑 超时
     * 范围尝试
     * [L, R]上最长回文子序列是什么
     * base case
     * L==R 时 返回1
     * L+1 == R 时 返回string[L] == string[R] ? 2 : 1
     * <p>
     * 可能性分析                            对应的表达
     * 最长回文子序列有可能包含 L 不包含 R          proccess(L, R -1)
     * 不包含L  有可能包含 R           process(L + 1, R)
     * 不包含L 不包含 R              process(L + 1, R - 1)
     * 包含L 包含R                  string[L] == string[R] ?process(L + 1, R - 1) + 2 : 0;
     */
    public static int process(char[] string, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L + 1 == R) {
            return string[L] == string[R] ? 2 : 1;
        }
        int p1 = process(string, L, R - 1);
        int p2 = process(string, L + 1, R);
        int p3 = process(string, L + 1, R - 1);
        int p4 = string[L] == string[R] ? process(string, L + 1, R - 1) + 2 : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    /**
     * 上面的方法在leetcode上跑超时， 改为动态规划方法
     */
    public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] string = s.toCharArray();
        int N = string.length;
        int[][] dp = new int[N][N];
        // l < r 没有意义
        // l == r
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
            if (i + 1 < N) {
                dp[i][i + 1] = string[i] == string[i + 1] ? 2 : 1;
            }
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int p1 = dp[L][R - 1];
                int p2 = dp[L + 1][R];
                int p3 = dp[L + 1][R - 1];
                int p4 = string[L] == string[R] ? dp[L + 1][R - 1] + 2 : 0;
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }
}
