package com.algorithm.leetcode;

public class _09_PalindromeNumber {
    public static boolean isPalindrome(int x) {
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
        while(p1 > p2) {
            int left = (int)(x / Math.pow(10, p1 - 1)) % 10;
            p1--;
            int right = (int)(x / Math.pow(10, p2 - 1)) % 10;
            p2++;
            if(left != right) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }
}
