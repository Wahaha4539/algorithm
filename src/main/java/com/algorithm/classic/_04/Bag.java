package com.algorithm.classic._04;

// 背包问题
public class Bag {
    public static int bag(int[] arr, int W) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        return process(arr, 0, W);
    }

    public static int bag2(int[] arr, int W) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        return process(arr, 0, W);
    }

    public static int process(int[] arr, int i, int rest) {
        if (i >= arr.length) {
            return rest < 0 ? 0 : 1;
        }
        int p1 = process(arr, i + 1, rest - arr[i]);
        int p2 = process(arr, i + 1, rest);
        return p1 + p2;
    }


    public static int process2(int[] arr, int i, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (i == arr.length) {
            return 1;
        }
        int p1 = process(arr, i + 1, rest - arr[i]);
        int p2 = process(arr, i + 1, rest);
        return p2 + (p1 == -1 ? 0 : p1);
    }

    // 这个是从上面代码得来的
    public static int bagDp(int[] arr, int W) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        int dp[][] = new int[N + 1][W + 1];
        for (int i = 0; i <= W; i++) {
            dp[N][i] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= W; j++) {
                int p1 = j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0;

                int p2 = dp[i + 1][j];
                dp[i][j] = p1 + p2;
            }
        }
        return dp[0][W];
    }
    public static int[] generate(int maxValue, int maxLength) {
        int len = (int) (Math.random() * maxLength) + 1;
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int mavLength = 40;
        int maxWeith = 100;
        System.out.println("测试开始");
        for (int i = 0; i < 100000; i++) {
            int[] value = generate(maxValue, mavLength);
            int bagWeight = (int) (Math.random() * maxWeith) + 1;
            if (bag2(value, bagWeight) != bagDp(value, bagWeight)) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
