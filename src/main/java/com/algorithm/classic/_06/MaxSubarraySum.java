package com.algorithm.classic._06;

public class MaxSubarraySum {


    public static int maxSubarraySum2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            cur = cur + j;
            max = Math.max(cur, max);
            if (cur < 0) {
                cur = 0;
            }
        }
        return max;
    }


    public static int maxSubarrySum(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] presum = new int[arr.length];
        //前缀和
        presum[0] = arr[0];
        for (int i = 1; i < presum.length; i++) {
            presum[i] = presum[i - 1] + arr[i];
        }
        int max = presum[0];
        int min = presum[0];
        for (int i = 1; i < arr.length; i++) {
            // 先计算 后维持滑动串口最小值
            max = Math.max(max, presum[i] - Math.min(0, min));
            min = Math.min(min, presum[i]);
        }
        return max;
    }

    public static int[] generateArray(int maxLength, int maxValue) {
        int len = (int) (Math.random() * maxLength) + 1;
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * maxValue - Math.random() * maxValue);
        }
        return array;
    }


    public static void main(String[] args) {
        int maxValue = 100;
        int maxLength = 10;
        for (int i = 0; i < 1000000; i++) {
            int[] array = generateArray(maxLength, maxValue);
            if (maxSubarraySum2(array) != maxSubarrySum(array)) {
                System.out.println("Oops");
            }
        }
    }
}
