package com.algorithm.leetcode;

/**
 * <a href="https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits/">数位和相等数对的最大和</a>
 */
public class _2342_MaxSumOfAPairWithEqualSumOfDigits {

        public static int maximumSum(int[] nums) {
            // 生成数位和数组
            int[] digits = new int[nums.length];
            for (int i = 0; i < digits.length; i++) {
                digits[i] = sumOfDigit(nums[i]);
            }
            return 0;
        }
        public static int sumOfDigit(int num) {
            int res = 0;
            while (num > 0) {
                res = res + num % 10;
                num = num / 10;
            }
            return res;
        }

}
