package com.algorithm.structure._01_monotonie_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈 找到左右2边比当前位置小的位置
 */
public class MonotonieStack {

    public static int[][] getNearLess(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[][] ans = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        // 一定要注意 栈存放的是下标
        for (int i = 0; i < ans.length; i++) {
            // 能够弹出的条件
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIndexes = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int c : popIndexes) {
                    ans[c][0] = leftIndex;
                    // i位置时让你跑出来的 所以右边比你小的就是i下标
                    ans[c][1] = i;
                }
            }
            // 此刻栈中没有比arr[i]小的了 所以要入栈
            // 入栈分为2个情况
            // 1。 栈中没有数据了 直接插入就好
            // 2 栈中有数据 要查看栈里的下标arr[s] 与arr[i]是不是相等的 如果相等 那么就要插入相同的list中
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            for (int c : list) {
                int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                ans[c][0] = leftIndex;
                // i位置时让你跑出来的 所以右边比你小的就是i下标
                ans[c][1] = -1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 2, 2, 4, 2, 5, 1, 9};
        int[][] ret = getNearLess(arr);
        System.out.println();

    }
}
