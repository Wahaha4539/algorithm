package com.algorithm.classic._04;

/**
 * 最长公共子序列
 */
public class LongestCommonSubsequence {
    public static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s1.length() < 1) {
            return 0;
        }
        if (s2 == null || s2.length() < 1) {
            return 0;
        }
        return process(s1.toCharArray(), s2.toCharArray(), s1.length() - 1, s2.length() - 1);
    }

    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s1.length() < 1) {
            return 0;
        }
        if (s2 == null || s2.length() < 1) {
            return 0;
        }
        return process1(s1.toCharArray(), s2.toCharArray(), s1.length() - 1, s2.length() - 1);
    }

    /**
     * 一个做行 一个做列 样本对应
     * str1[0...i] str2[0...j] 最长公共子序列长度
     * base case
     * i == 0 && j==0 str1[i] == str2[j] ? 1 : 0
     * 可能性分析 (把定义当成已知条件)
     * 最长子序列中               表达式
     * 可能要i 不要j            process(i, j-1)
     * 不要i 可能要j            process(i - 1,j)
     * 不要i 不要j              process(i - 1, j - 1)
     * 既要i 也要j             str1[i] == str2 ? 1 + process(i - 1, j - 1) : 0
     */
    public static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        }
        if (i < 0 || j < 0) {
            return 0;
        }
        int p1 = process(str1, str2, i, j - 1);
        int p2 = process(str1, str2, i - 1, j);
        int p3 = process(str1, str2, i - 1, j - 1);
        int p4 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    public static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        }
        if (i == 0) {
            return str1[i] == str2[j] ? 1 : process(str1, str2, i, j - 1);
        }
        if (j == 0) {
            return str1[i] == str2[j] ? 1 : process(str1, str2, i - 1, j);
        }
        int p1 = process(str1, str2, i, j - 1);
        int p2 = process(str1, str2, i - 1, j);
        int p3 = process(str1, str2, i - 1, j - 1);
        int p4 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
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
        int length = 100;
        System.out.println("测试开始");
        for (int i = 0; i < 1000; i++) {
            String string1 = generate(length);
            String string2 = generate(length);
            if (longestCommonSubsequence(string1, string2) != longestCommonSubsequence1(string1, string2)) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
