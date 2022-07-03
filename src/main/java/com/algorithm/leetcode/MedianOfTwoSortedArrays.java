package com.algorithm.leetcode;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {


    // 时间复杂度 log(n + m)
    // top k问题就可以解决了啊
    // bfp
    // 快排
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1) {
            if (nums2 == null || nums2.length < 1) {
                return 0;
            }
            int len2 = nums2.length;
            if (nums2.length % 2 == 0) {
                return (nums2[len2 / 2 - 1] + nums2[(len2 / 2)]) / 2.0;
            }
            return nums2[len2 / 2];
        }
        if (nums2 == null || nums2.length < 1) {
            int len1 = nums1.length;
            if (nums1.length % 2 == 0) {
                return (nums1[len1 / 2 - 1] + nums1[len1 / 2]) / 2.0;
            }
            return nums1[len1 / 2];
        }
        int[] mergeArr = new int[nums2.length + nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            mergeArr[i] = nums1[i];
        }
        for (int i = nums1.length; i < mergeArr.length; i++) {
            mergeArr[i] = nums2[i - nums1.length];
        }
        if (mergeArr.length % 2 == 0) {
            return (topk(mergeArr, 0, mergeArr.length - 1, nums1[nums1.length / 2], (mergeArr.length / 2) - 1)
                    + topk(mergeArr, 0, mergeArr.length - 1, nums1[nums1.length / 2], mergeArr.length / 2)) / 2.0;
        }
        return topk(mergeArr, 0, mergeArr.length - 1, nums1[nums1.length / 2], mergeArr.length / 2);
    }

    public static int topk(int[] arr, int L, int R, int partion, int K) {
        int[] part = partion(arr, L, R, partion);
        if (K < part[0]) {
            return topk(arr, L, part[0] - 1, arr[L + (int) (Math.random() * (part[0] - L))], K);
        }
        if (K > part[1]) {
            return topk(arr, part[1] + 1, R, arr[R - (int) (Math.random() * (R - part[1]))], K);
        }
        return arr[part[0]];
    }

    // 荷兰国旗为题
    public static int[] partion(int[] arr, int L, int R, int partion) {
        if (L == R) {
            return new int[]{L, R};
        }
        int left = L - 1;
        int right = R + 1;
        int i = L;
        while (i < right && i < arr.length) {
            if (arr[i] < partion) {
                swap(arr, left + 1, i);
                left++;
                i++;
            } else if (arr[i] > partion) {
                swap(arr, i, right - 1);
                right--;
            } else if (arr[i] == partion) {
                i++;
            }
        }
        return new int[]{left + 1, right - 1};
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{};
        int[] arr2 = new int[]{1};
        System.out.println(findMedianSortedArrays(arr1, arr2));
    }

}
