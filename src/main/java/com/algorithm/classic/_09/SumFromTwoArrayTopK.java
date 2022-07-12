package com.algorithm.classic._09;

import java.util.PriorityQueue;

/**
 * 求2个有序数组笛卡尔相加的topk
 */
public class SumFromTwoArrayTopK {
    private static class SumAndIndex {
        private final int sum;

        public SumAndIndex(int sum, int indexFromArray1, int indexFromArray2) {
            this.sum = sum;
            this.indexFromArray1 = indexFromArray1;
            this.indexFromArray2 = indexFromArray2;
        }

        public int getSum() {
            return sum;
        }

        public int getIndexFromArray1() {
            return indexFromArray1;
        }

        public int getIndexFromArray2() {
            return indexFromArray2;
        }

        private final int indexFromArray1;
        private final int indexFromArray2;
    }

    public static int[] topKOfSumFromTwoArray(int[] array1, int[] array2, int k) {
        if (array1 == null || array2 == null || array1.length < 1 || array2.length < 1 || array1.length * array2.length < k) {
            // 没有topk个
            return null;
        }
        // 大根堆
        PriorityQueue<SumAndIndex> heap = new PriorityQueue<>((c1, c2) -> c2.getSum() - c1.getSum());

        int[] res = new int[k];
        SumAndIndex top = new SumAndIndex(array1[array1.length - 1] + array2[array2.length - 1],
                array1.length - 1,
                array2.length - 1);
        heap.add(top);
        int i = 0;
        while (i <= k && !heap.isEmpty()) {
            SumAndIndex cur = heap.poll();
            res[i++] = cur.getSum();
            int index1 = cur.getIndexFromArray1();
            int index2 = cur.getIndexFromArray2();
            if (index1 == 0 && index2 == 0) {
                break;
            } else if (index1 == 0 && index2 > 0) {
                heap.add(new SumAndIndex(array1[index1] + array2[index2 - 1], index1, index2 - 1));
            } else if (index2 == 0 && index1 > 0) {
                heap.add(new SumAndIndex(array1[index1 - 1] + array2[index2], index1 - 1, index2));
            } else {
                heap.add(new SumAndIndex(array1[index1] + array2[index2 - 1], index1, index2 - 1));
                heap.add(new SumAndIndex(array1[index1 - 1] + array2[index2], index1 - 1, index2));
            }
        }
        return res;
    }
}
