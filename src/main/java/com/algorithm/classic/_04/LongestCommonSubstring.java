package com.algorithm.classic._04;

/**
 * 最长公共子串
 */
public class LongestCommonSubstring {
    public static int longestCommonSubstring(String s1, String s2) {
        if (s1 == null || s1.length() < 1) {
            return 0;
        }
        if (s2 == null || s2.length() < 1) {
            return 0;
        }
        char[] string1 = s1.toCharArray();
        char[] string2 = s2.toCharArray();
        // dp[i][j] string1 必须以i结尾 string2 必须以j结尾 最长公共子串
        int[] help = new int[string2.length];
        // 初始化第一行
        int max = 0;
        for (int j = 0; j < string2.length; j++) {
            help[j] = string1[0] == string2[j] ? 1 : 0;
            max = Math.max(max, help[j]);
        }

        for (int i = 1; i < string1.length; i++) {
            for (int j = string2.length - 1; j >= 0; j--) {
                if (string1[i] == string2[j]) {
                    help[j] = j - 1 >= 0 ? help[j - 1] + 1 : 1;
                } else {
                    help[j] = 0;
                }
                max = Math.max(max, help[j]);
            }
        }
        return max;
    }


    public static int dp2(String s1, String s2) {
        if (s1 == null || s1.length() < 1) {
            return 0;
        }
        if (s2 == null || s2.length() < 1) {
            return 0;
        }
        char[] string1 = s1.toCharArray();
        char[] string2 = s2.toCharArray();
        // dp[i][j] string1 必须以i结尾 string2 必须以j结尾 最长公共子串
        int[][] dp = new int[string1.length][string2.length];
        // 初始化第一行
        int max = 0;
        for (int j = 0; j < string2.length; j++) {
            dp[0][j] = string1[0] == string2[j] ? 1 : 0;
            max = Math.max(max, dp[0][j]);
        }

        for (int i = 1; i < string1.length; i++) {
            for (int j = 0; j < string2.length; j++) {
                if (string1[i] == string2[j]) {
                    dp[i][j] = j - 1 >= 0 ? dp[i - 1][j - 1] + 1 : 1;
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static String generate(int length) {
        int len = (int) (Math.random() * length) + 1;
        char[] string = new char[len];
        for (int i = 0; i < string.length; i++) {
            string[i] = (char) (Math.random() * 128);
        }
        return new String(string);
    }

    public static void main(String[] args) {
        int length = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < 1000000; i++) {
            String string1 = generate(length);
            String string2 = generate(length);
            if (longestCommonSubstring(string1, string2) != dp2(string1, string2)) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
