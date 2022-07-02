package com.algorithm.classic._01;

public class MaxSquare {
    public static int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return 0;
        }
        int N = matrix.length;
        int[][] right = new int[N][N];
        int[][] down = new int[N][N];
        for (int row = 0; row < N; row++) {
            right[row][N - 1] = matrix[row][N - 1];
            for (int col = N - 2; col >= 0; col--) {
                right[row][col] = matrix[row][col] == 0 ? 0 : right[row][col + 1] + 1;
            }
        }
        for (int col = 0; col < N; col++) {
            down[N - 1][col] = matrix[N - 1][col];
            for (int row = N - 2; row >= 0; row--) {
                down[row][col] = matrix[row][col] == 0 ? 0 : down[row + 1][col] + 1;
            }
        }
        int max = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                for (int side = 1; side <= Math.min(N - row, N - col); side++) {
                    boolean curPoint = right[row][col] >= side && down[row][col] >= side;
                    boolean rightPoint = down[row][col + side - 1] >= side;
                    boolean downPoint = right[row + side - 1][col] >= side;
                    boolean square = curPoint && rightPoint && downPoint;
                    if (square) {
                        max = Math.max(max, side);
                    }
                }
            }
        }
        return max;
    }

    public static int[][] generate(int lenght) {
        int len = (int) (Math.random() * lenght) + 1;
        int[][] arr = new int[len][len];
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                if (Math.random() > 0.2) {
                    arr[row][col] = 1;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] matrix = generate(10);
        System.out.println(maxSquare(matrix));

    }
}
