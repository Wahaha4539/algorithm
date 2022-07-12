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

    public static class TireTree {
        private Node head = new Node();

        public static class Node {
            //[0] 0 [1] 1
            Node[] nexts = new Node[2];
        }

        /**
         * 加入前缀数
         */
        public void add(int value) {
            Node cur = head;
            for (int i = 31; i >= 0; i--) {
                int path = (value >> i) & 1;
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
        }

        public int maxXOR(int value) {
            int[] paths = new int[32];
            int path = (value >> 31) & 1;
            paths[0] = possibleNode(head, path, 0);
            char[] chars = new char[32];
            chars[0] = (char) ('0' + (paths[0] ^ path));
            Node cur = head.nexts[paths[0]];
            for (int i = 30; i >= 0; i--) {
                path = (value >> i) & 1;
                paths[31 - i] = possibleNode(cur, path, 1);
                chars[31 - i] = (char) ('0' + (paths[31 - i] ^ path));
                cur = cur.nexts[paths[31 - i]];
            }

            return Integer.parseUnsignedInt(new String(chars), 2);
        }

        private int possibleNode(Node cur, int path, int possible) {
            int possiblePath = path ^ possible;
            // possiblePath ^ 1 意思是 如果possiblePath = 0， 则选择1 如果possiblePath = 1 则选择0， 因为cur.nexts[possiblePath] == null
            return cur.nexts[possiblePath] == null ?  (possiblePath ^ 1) :  possiblePath;
        }

    }

    /**
     * 思路 根据前缀数来选择和哪位异或
     * 二进制的结果 如果要尽可能大 那么最高位尽可能结果为0 其它位尽可能为1
     */
    public static int maxSubarryXORSumTire(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int xor = 0;
        TireTree tireTree = new TireTree();
        tireTree.add(xor);
        for (int i = 0; i < array.length; i++) {
            xor = xor ^ array[i];
            max = Math.max(max, tireTree.maxXOR(xor));
            tireTree.add(xor);
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
        int maxLength = 100;
        int maxValue = 100;
        int[] array = generateArray(maxLength, maxValue);
        for (int item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println(maxSubarryXORSumVionetSolution(array));
        System.out.println(maxSubarryXORSumAuxiliaryArray(array));
        System.out.println(maxSubarryXORSumTire(array));
        System.out.println("start");
        int failure = 0;
        for (int i = 0; i < 1000000; i++) {
            array = generateArray(maxLength, maxValue);
            if (maxSubarryXORSumVionetSolution(array) != maxSubarryXORSumTire(array)) {
                System.out.println("Oops");
            }
        }
        System.out.println(failure);
    }

}
