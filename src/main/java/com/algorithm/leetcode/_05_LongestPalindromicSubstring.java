package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/">Longest Palindromic Substring</a>
 */
public class _05_LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        char[] string = manacherChars(s);
        // 下标的回文半径 数组
        int[] parr = new int[string.length];
        // 遍历过程中的最大右边界
        int R = -1;
        // R 所对应的中点
        int C = 0;
        for (int i = 0; i < string.length; i++) {
            // i不在R边界上
            if (i > R) {
                // 正常暴力扩
                int step = 1;
                parr[i] = 0;
                while (i - step >= 0 && i + step <= string.length - 1) {
                    if (string[i - step] == string[i + step]) {
                        parr[i] = step;
                        step++;
                    } else {
                        break;
                    }
                }
                C = i;
                R = i + parr[i];
            } else { // 在右边界里
                // i 关于 C 点对称的 i`
                // R 关于 C 点堆成的 L
                // 1） i` - parr[i`] > L : parr[i] = parr[i`]
                // 2) i` - parr[i`] < L : parr[i] = R - i
                // 3) i` - parr[i`] == L : 正常暴力扩
                // C = 4  R = 8, i = 6 --> i` = C - (i - C)
                // parr[i`] = 1
                int i1 = C - (i - C);
                int L = C - (R - C);
                int Li1 = i1 - parr[i1];
                if (Li1 > L) {
                    parr[i] = parr[i1];
                } else if (Li1 < L) {
                    parr[i] = R - i;
                } else {
                    // 正常暴力扩
                    int step = R - i;
                    parr[i] = R - i;
                    while (i - step >= 0 && i + step <= string.length - 1) {
                        if (string[i - step] == string[i + step]) {
                            parr[i] = step;
                            step++;
                        } else {
                            break;
                        }
                    }
                    C = i;
                    R = i + parr[i];
                }
            }
        }

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < parr.length; i++) {
            if (parr[i] > max) {
                max = parr[i];
                maxIndex = i;
            }
        }
        int left = maxIndex - max;
        int right = maxIndex + max;
        char[] res = new char[max];
        int cur = 0;
        for (int i = left; i < right; i++) {
            if (i % 2 != 0) {
                res[cur++] = string[i];
            }
        }
        return new String(res);
    }

    public static char[] manacherChars(String s) {
        char[] chars = s.toCharArray();
        char[] ans = new char[2 * chars.length + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = i % 2 == 0 ? '#' : chars[i / 2];
        }
        return ans;
    }


    public static String longestPalindrome1(String s) {
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
        if (x < 0) {
            return false;
        }
        int digcount = 0;
        int base = 10;
        int cur = x;
        while (cur > 0) {
            digcount++;
            cur = cur / base;
        }

        int p1 = digcount;
        int p2 = 1;
        while (p1 < p2) {
            int left = (int) (cur / Math.pow(10, p1--)) % 10;
            int right = (int) (cur / Math.pow(10, p2++)) % 10;
            if (left != right) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aacabdkacaa"));
        System.out.println(longestPalindrome1("aacabdkacaa"));
    }
}
