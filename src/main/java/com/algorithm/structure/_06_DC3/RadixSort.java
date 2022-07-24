package com.algorithm.structure._06_DC3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        int max = arr[0];
        for (int i : arr) {
            max = Math.max(max, i);
        }
        // 准备10个桶
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        int base = 10;
        while (max > 0) {
            for (int i = 0; i < arr.length; i++) {
                int radixNum = (arr[i] / (base / 10)) % 10;
                buckets.get(radixNum).add(arr[i]);
            }
            int i = 0;
            for (List<Integer> bucket : buckets) {
                for (Integer item : bucket) {
                    arr[i] = item;
                    i++;
                }
                bucket.clear();
            }
            max = max / 10;
            base = base * 10;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{22, 31, 44, 111, 2, 442, 1111, 2314};
        int[] arr1 = new int[arr.length];
        System.arraycopy(arr, 0, arr1, 0, arr.length);
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        radixSort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i] + " " + arr2[i]);
        }
    }


}
