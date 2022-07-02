package com.algorithm.classic;

public class CordCoverMaxPoint {
    public static int cordCoverMaxPoint(int[] arr, int L) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;

        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - left);
            left++;
        }
        return max;
    }

    public static void main(String[] args) {
        int [] arr = new int[] {2, 4,7,12,42,111};
        System.out.println(cordCoverMaxPoint(arr, 60));
    }
}
