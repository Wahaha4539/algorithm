package com.algorithm.leetcode;

public class ReverseInteger {
    public static int reverse(int x) {
        int ans = 0;
        int abs = Math.abs(x);
        if (abs < 0) {
            return 0;
        }
        int len = 0;
        int cur = abs;
        do {
            len++;
            cur = cur / 10;
        } while (cur > 0);
        cur = abs;
        for (int i = len; i >= 1; i--) {
            int yinshu1 = (int) Math.pow(10, len - i);
            int yinshu2 = (cur / (int) Math.pow(10, i - 1));
            if (Integer.MAX_VALUE / yinshu1 < yinshu2) {
                return 0;
            }
            int and = yinshu2 * yinshu1;
            if (Integer.MAX_VALUE - ans < and) {
                return 0;
            }
            ans = ans + and;
            cur = cur - (cur / (int) Math.pow(10, i - 1)) * (int) Math.pow(10, i - 1);
        }
        return x >0 ? ans : ans * -1;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }
}
