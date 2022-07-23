package com.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
public class _2341_MaximumNumberOfPairsInArray {
    public static int[] numberOfPairs(int[] nums) {
        if (nums == null || nums.length < 1) {
            return new int[]{0, 0};
        }
        Map<Integer, Integer> numberIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numberIndexMap.containsKey(nums[i])) {
                numberIndexMap.put(nums[i], numberIndexMap.get(nums[i]) + 1);
            } else {
                numberIndexMap.put(nums[i], 1);
            }
        }
        int[] res = new int[2];
        for (Integer value : numberIndexMap.values()) {
            res[0] += value / 2;
            res[1] += value % 2;
        }
        return res;
    }
}
