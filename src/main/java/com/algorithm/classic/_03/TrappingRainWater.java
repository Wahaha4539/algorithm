package com.algorithm.classic._03;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrappingRainWater {
    public static int trappingRainWater(int[][] grids) {
        if (grids == null || grids.length < 1 || grids[0].length < 1) {
            return 0;
        }

        PriorityQueue<Grid> heap = new PriorityQueue<Grid>(Comparator.comparingInt(Grid::getNum));
        int N = grids.length;
        int M = grids[0].length;
        boolean[][] isenter = new boolean[N][M];
        // 第一行
        for (int col = 0; col < M; col++) {
            if (!isenter[0][col]) {
                Grid g = new Grid(grids[0][col], 0, col);
                heap.add(g);
                isenter[0][col] = true;
            }
        }
        // 最后一行
        for (int col = 0; col < M; col++) {
            if (!isenter[N - 1][col]) {
                Grid g = new Grid(grids[N - 1][col], N - 1, col);
                heap.add(g);
                isenter[N - 1][col] = true;
            }
        }
        // 第1列
        for (int row = 0; row < N; row++) {
            if (!isenter[row][0]) {
                Grid g = new Grid(grids[row][0], row, 0);
                heap.add(g);
                isenter[row][0] = true;
            }
        }
        // 最后一列
        for (int row = 0; row < N; row++) {
            if (!isenter[row][M - 1]) {
                Grid g = new Grid(grids[row][M - 1], row, M - 1);
                heap.add(g);
                isenter[row][M - 1] = true;
            }
        }
        int ans = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            Grid cur = heap.poll();
            int row = cur.getRow();
            int col = cur.getCol();
            int num = cur.getNum();
            max = Math.max(max, num);
            // 上 row - 1, col
            if (row - 1 >= 0 && !isenter[row - 1][col]) {
                Grid g = new Grid(grids[row - 1][col], row - 1, col);
                ans += Math.max(0, max - grids[row - 1][col]);
                heap.add(g);
            }
            // 下 row + 1
            if (row + 1 <= N - 1 && !isenter[row + 1][col]) {
                Grid g = new Grid(grids[row + 1][col], row + 1, col);
                ans += Math.max(0, max - grids[row + 1][col]);
                heap.add(g);
            }
            // 左 row, col - 1
            if (col - 1 >= 0 && !isenter[row][col - 1]) {
                Grid g = new Grid(grids[row][col - 1], row, col - 1);
                ans += Math.max(0, max - grids[row][col - 1]);
                heap.add(g);
            }
            // 右
            if (col + 1 >= 0 && !isenter[row][col + 1]) {
                Grid g = new Grid(grids[row][col + 1], row, col + 1);
                ans += Math.max(0, max - grids[row][col + 1]);
                heap.add(g);
            }
        }
        return ans;
    }

    public static class Grid {
        public int num;
        public int row;

        public Grid(int num, int row, int col) {
            this.num = num;
            this.row = row;
            this.col = col;
        }

        public int col;

        public int getNum() {
            return num;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }


}
