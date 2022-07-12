package com.algorithm.classic._08;

/**
 * 最少步数跳到结尾
 */
public class MinSteps {
    public static int minStepsRecursion(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }

        return process(array, 0);
    }

    /**
     * 递归函数定义 在index位置上所需要的最少步数
     * base:
     * index == array.length - 1 （跳到结尾 不用再跳了）
     * return 1
     * index >= array.length （跳过了 不算是有效选择）
     * return -1
     * <p>
     * array[index] = value
     * 1 ... value
     * min {
     * proccess(array, index + 1)
     * ...
     * proccess(array, index + value)
     * }
     * min + 1
     * <p>
     * 如果value值很大怎么办
     */
    public static int process(int[] array, int index) {
        if (index == array.length - 1) {
            return 0;
        }
        if (index >= array.length) {
            return -1;
        }
        int value = array[index];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= value && index + i < array.length; i++) {
            int next = process(array, index + i);
            if (next != -1) {
                ans = Math.min(ans, next);
            }
        }
        // 说明剩下的都是不能取的
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans + 1;
    }

    /**
     * 最优解
     */
    public static int jump(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int curR = 0; // step步的有边界
        int step = 0; // 跳了几步
        int next = 0; // step+1步的右边界
        for (int i = 0; i < array.length; i++) {
            //假设next的值是从j位置产生的
            // 如果i 超过了 step步数的右边界
            // 那么说明应该在j时跳
            // 调整步数并且重设右边界
            if (curR < i) {
                step++;
                curR = next;
            }
            next = Math.max(next, array[i] + i);
        }
        return step;
    }

    public static void main(String[] args) {
        System.out.println(minStepsRecursion(new int[]{2, 4, 1, 22, 1}));
        System.out.println(jump(new int[]{2, 4, 1, 22, 1}));
    }
}
