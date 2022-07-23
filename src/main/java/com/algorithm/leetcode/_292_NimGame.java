package com.algorithm.leetcode;

public class _292_NimGame {
    public static boolean canWinNim1(int n) {
        return process(n);
    }
    public static boolean canWinNim(int n) {
        return n %(4) != 0;
    }



    // 当前先手 还剩rest个 先手是赢还是输
    public static boolean process(int rest) {
        if (rest == 0) {
            return false;
        }
        if (rest >=1 && rest <= 3) {
            return true;
        }
        // 此时调用process 先手变为后手
        boolean p1 = process(rest - 1);
        if (!p1) {
            return true;
        }
        boolean p2 = process(rest - 2);
        if (!p2) {
            return true;
        }
        boolean p3 = process(rest - 3);
        return !p3;
    }

    public static void main(String[] args) {
        for (int i = 1; i <=100; i++) {
            System.out.println(i + " : " + canWinNim(i));
        }
    }
}
