package com.algorithm.classic._12;

public class StockTrade {
    public static int stockTrade(int[] arr, int K) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (K > arr.length / 2) {
            // 可以优化成O(N)
            int ans = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > arr[i - 1]) {
                    ans += arr[i] - arr[i - 1];
                }
                return ans;
            }
        }

        int[][] dp = new int[arr.length][K];
        for (int j = 1; j <= K; j++) {
            int t = dp[0][j - 1] - arr[0];
            for (int i = 1; i < arr.length; i++) {
                // 2种情况
                // 1. i不参与交易 dp[i - 1][j]
                // 2. i参与交易 (贪心 要参与 那么i必须是最后一次卖)
                // dp[i][j - 1] + arr[i] - arr[i]
                // dp[i - 1][j - 1] + arr[i] - arr[i - 1]
                // dp[i - 2][j - 1] + arr[i] - arr[i - 2]
                // ...
                // 取最大值
                // dp[i + 1][j]时第二中情况怎么得出呢
                // dp[i + 1][j] + arr[i + 1] - arr[i + 1]
                // dp[i][j - 1] + arr[i + 1] - arr[i]
                // dp[i - 1][j - 1] + arr[i + 1] - arr[i - 1]
                // dp[i - 2][j - 1] + arr[i + 1] - arr[i - 2]
                // ...
                // 取最大值
                //
                // 发现公共的部分
                // dp[i - 1][j - 1] - arr[i - 1]
                //  dp[i - 2][j - 1]- arr[i - 2]
                // ...
                // 在i时已经取过最大值了
                t = Math.max(t, dp[i][j - 1] - arr[i]);
                dp[i][j] = Math.max(t + arr[i], dp[i - 1][j]);
            }
        }
        return dp[arr.length - 1][K];
    }
}
