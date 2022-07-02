package com.findjob.algorithm._02_maxwindow;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 在窗口滑动时 给出当前窗口的最大值
 */
public class MaxValueWindow {
    // 双端队列 头 -> 尾 从大到小(不能等于 等于时后面替换前面)
    LinkedList<Integer> maxDeque;
    LinkedList<Integer> minDeque;

    private int L;
    private int R;

    int[] origin;

    public MaxValueWindow(int[] arr) {
        this.L = -1;
        this.R = -1;
        this.maxDeque = new LinkedList<>();
        this.minDeque = new LinkedList<>();
        this.origin = new int[arr.length];
        System.arraycopy(arr, 0, this.origin, 0, arr.length);
    }

    // 滑动窗口向右移动时返回此窗口最大值
    public int[] moveR() {
        if (this.R == this.origin.length - 1) {
            throw new RuntimeException("R越界");
        }
        this.R += 1;
        // 如果尾部小于等于arr[R]
        while (!this.maxDeque.isEmpty() && this.origin[this.maxDeque.peekLast()] <= this.origin[this.R]) {
            this.maxDeque.removeLast();
        }
        while (!this.minDeque.isEmpty() && this.origin[this.minDeque.peekLast()] >= this.origin[this.R]) {
            this.minDeque.removeLast();
        }
        this.minDeque.add(this.R);
        this.maxDeque.add(this.R);
        return new int[]{this.origin[maxDeque.peekFirst()], this.origin[minDeque.peekFirst()]};
    }

    public int[] moveL() {
        if (L == R) {
            throw new RuntimeException("L越界");
        }
        if (this.maxDeque.peekFirst() <= this.L) {
            this.maxDeque.removeFirst();
        }
        if (this.minDeque.peekFirst() <= this.L) {
            this.minDeque.removeFirst();
        }
        this.L = this.L + 1;
        return new int[]{this.origin[maxDeque.peekFirst()], this.origin[minDeque.peekFirst()]};
    }

    public static void main(String[] args) {
        int[] arr = new int[]{
                4, 3, 5, 4, 3, 3, 6, 7
        };
        MaxValueWindow window = new MaxValueWindow(arr);
        // 窗口大小为3
        int res[] = new int[arr.length - 2];
        for (int i = 0; i < arr.length; i++) {
            window.moveR();
            if (i > 1) {
                res[i - 2] = window.moveL()[0];
            }
        }
        Arrays.stream(res).forEach(System.out::println);
        System.out.println();
    }


}
