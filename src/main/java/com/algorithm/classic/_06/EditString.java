package com.algorithm.classic._06;

/**
 * 编辑str1成str2
 */
public class EditString {
    public static int edit(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] strchars1 = str1.toCharArray();
        char[] strchars2 = str2.toCharArray();
        int N = strchars1.length + 1;
        int M = strchars1.length + 1;
        int[][] dp = new int[N][M];
        for (int i = 1; i < N; i++) {
            dp[i][0] = dc * i;
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = ic * j;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int ans;
                // i 代表
                if (strchars1[i] == strchars2[j]) {
                    // 第i 个数 cp到 j 个数 (此代价为0) + 前i - 1个数 剪辑成 前j - 1个数
                    ans = dp[i - 1][j - 1];
                } else {
                    // 第i 替换(rc) j  + 前i - 1个数 剪辑成 前j - 1个数
                    ans = dp[i - 1][j - 1] + rc;
                }
                // dp[i][j - 1] 前i个 剪辑成 前j - 1个数 + 插入 第j个数
                ans = Math.min(ans, dp[i][j - 1] + ic);
                // dp[i - 1][j] 前i - 1个 剪辑成 前j个数 + 删除第i
                ans = Math.min(ans, dp[i - 1][j] + dc);
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][M - 1];
    }
}
