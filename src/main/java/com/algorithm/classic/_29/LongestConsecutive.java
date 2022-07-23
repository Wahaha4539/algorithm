package com.algorithm.classic._29;

import java.util.HashMap;

/**
 * <a href="https://leetcode.com/problems/longest-consecutive-sequence/">最长连续序列</a>
 */
public class LongestConsecutive {

	public static int longestConsecutive(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int len = 0;
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
				int preLen = map.getOrDefault(num - 1, 0);
				int posLen = map.getOrDefault(num + 1, 0);
				int all = preLen + posLen + 1;
				map.put(num - preLen, all);
				map.put(num + posLen, all);
				len = Math.max(len, all);
			}
		}
		return len;
	}

}
