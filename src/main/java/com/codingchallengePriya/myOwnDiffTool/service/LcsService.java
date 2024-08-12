package com.codingchallengePriya.myOwnDiffTool.service;
import org.springframework.stereotype.Service;
@Service
public class LcsService {

    public LcsResult calculateLcs(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return new LcsResult(0, "");
        }
        return lcsLengthAndString(str1, str2);
    }

    private LcsResult lcsLengthAndString(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct LCS string
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new LcsResult(dp[m][n], lcs.reverse().toString());
    }
}