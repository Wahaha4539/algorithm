package com.algorithm.classic._08;

/**
 * 最大子数组异或和求解
 */
public class MaxSubarrayXORSum {
    /**
     * 暴力求解每个子数组的异或和 求出最大的值
     * 时间复杂度为O(N^3)
     *
     * @param array 给定的数组
     * @return 最大子数组异或和
     */
    public static int maxSubarryXORSumVionetSolution(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            // 子数组为单个元素时
            int cur = array[i];
            max = Math.max(max, cur);
            for (int j = i + 1; j < array.length; j++) {
                int eor = array[i];
                for (int k = i + 1; k <= j; k++) {
                    eor = eor ^ array[k];
                }
                max = Math.max(max, eor);
            }
        }
        return max;
    }

    /**
     * 思路：以某个下标为结尾的子数组异或和最大
     * 通过辅助数组进行优化求解
     * 异或运算性质： A ^ B = C 那么 B = A ^ C
     * 求出array数组前缀异或和eor数组
     * 那么array[j+1...i]的异或和 等于 eor[i] ^ eor[j]
     * 时间复杂度 O(N^2)
     *
     * @param array 给定的数组
     * @return 最大子数组异或和
     */
    public static int maxSubarryXORSumAuxiliaryArray(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        //辅助数组 前缀异或
        int[] eor = new int[array.length];
        eor[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            eor[i] = eor[i - 1] ^ array[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            //临界处理
            // array[0...i]异或和
            max = Math.max(max, eor[i]);
            for (int j = 1; j <= i; j++) {
                // array[j-1, i] 异或和
                int eorji = eor[i] ^ eor[j - 1];
                max = Math.max(max, eorji);
            }
        }
        return max;
    }

    public static int[] generateArray(int maxLength, int maxValue) {
        int len = (int) (Math.random() * maxLength) + 1;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            // 有正有负
            res[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int maxLength = 1000;
        int maxValue = 100;
        int[] array = generateArray(maxLength, maxValue);
//        for (int item : array) {
//            System.out.print(item + " ");
//        }
//        System.out.println();
//
//        System.out.println(maxSubarryXORSumVionetSolution(array));
//        System.out.println(maxSubarryXORSumAuxiliaryArray(array));


        for (int i = 0; i < 100000; i++) {
            array = generateArray(maxLength, maxValue);
            if (maxSubarryXORSumVionetSolution(array) != maxSubarryXORSumAuxiliaryArray(array)) {
                System.out.println("Oops");
            }
        }
    }

}
