package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/">Longest Palindromic Substring</a>
 */
public class _05_LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        char[] s1 = s.toCharArray();
        int N = s1.length;
        // dp[i][j] i...j 是否有效
        boolean[][] dp = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }
        int max = 1;
        int L = 0;
        int R = 0;
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (s1[i] == s1[j]) {
                    if (j - i > 2) {
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = true;
                    }
                    if (dp[i][j] && j - i + 1 > max) {
                        L = i;
                        R = j;
                        max = j - i + 1;
                    }
                }
            }
        }
        char[] res = new char[max];
        for (int i = 0; i < res.length; i++) {
            res[i] = s1[i + L];
        }
        return new String(res);
    }

    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        int digcount = 0;
        int base = 10;
        int cur = x;
        while(cur > 0) {
            digcount++;
            cur = cur / base;
        }

        int p1 = digcount;
        int p2 = 1;
        while(p1 < p2) {
            int left = (int)(cur / Math.pow(10, p1--)) % 10;
            int right = (int)(cur / Math.pow(10, p2++)) % 10;
            if(left != right) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aacabdkacaa"));
    }
}
