package com.algorithm.classic._16;

public class JosephRing {
    public static int lastRemaining(int n, int m) {

        // 约瑟夫环问题
        int curIndex = 1;
        for(int i = 1; i < n; i++) {
            // i == 1, 求出剩余i + 1时 的编号
            // i == n - 1 时 求出 剩余n个时的编号
            curIndex = getLast(curIndex, i + 1, m);
        }
        return curIndex - 1;
    }

    public static int getLast(int curIndex, int i, int m) {
        return (curIndex + m - 1) % i + 1;
    }

    public static void main(String[] args) {
        lastRemaining(5, 3);
    }
}
