package com.findjob.algorithm._02_maxwindow;

import java.util.LinkedList;

/**
 * 在窗口滑动时 给出当前窗口的最大值
 */
public class MaxValueWindow {
    // 双端队列 头 -> 尾 从大到小(不能等于 等于时后面替换前面)
    LinkedList<Integer> deque;

    private int L;
    private int R;

    int[] origin;

    public MaxValueWindow(int[] arr) {
        this.L = -1;
        this.R = -1;
        this.deque = new LinkedList<>();
        this.origin = new int[arr.length];
        System.arraycopy(arr, 0, this.origin, 0, arr.length);
    }

    // 滑动窗口向右移动时返回此窗口最大值
    public int moveR() {
        if (this.R == this.origin.length - 1) {
            throw new RuntimeException("R越界");
        }
        this.R += 1;
        // 如果尾部小于等于arr[R]
        while (!this.deque.isEmpty() && this.origin[this.deque.peekLast()] <= this.origin[this.R]) {
            this.deque.removeLast();
        }
        this.deque.add(this.R);
        return this.origin[deque.peekFirst()];
    }

    public int moveL() {
        if (L == R) {
            throw new RuntimeException("L越界");
        }
        if (this.deque.peekFirst() <= this.L) {
            this.deque.removeFirst();
        }
        this.L = this.L + 1;
        return this.origin[this.deque.peekFirst()];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{
                2, 3, 4, 12, 1, 2, 31, 4, 2, 3
        };
        MaxValueWindow window = new MaxValueWindow(arr);
        // 窗口大小为3
        for (int i = 0; i < arr.length; i++) {
            System.out.print(window.moveR() + " ");
            if (i > 1) {
                System.out.print(window.moveL() + " ");
            }
            System.out.println("第 " + i + "次");
        }

        System.out.println();
    }


}
