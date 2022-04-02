import java.io.*;
import java.util.*;

public class Main {

    public static int dig(int n, int m, int[][] mine, int r, int c) {
        if (c == m - 1) {
            return mine[r][c];
        }

        int ans = 0;
        if (r + 1 < n) {
            ans = Math.max(ans, dig(n, m, mine, r + 1, c + 1));
        }

        ans = Math.max(ans, dig(n, m, mine, r, c + 1));

        if (r - 1 >= 0) {
            ans = Math.max(ans, dig(n, m, mine, r - 1, c + 1));
        }

        return ans + mine[r][c];
    }

    public static int memoDig(int n, int m, int[][] mine, int r, int c, int[][] dp) {
        if (c == m - 1) {
            return dp[r][c] = mine[r][c];
        }

        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        int ans = 0;
        if (r + 1 < n) {
            ans = Math.max(ans, memoDig(n, m, mine, r + 1, c + 1, dp));
        }

        ans = Math.max(ans, memoDig(n, m, mine, r, c + 1, dp));

        if (r - 1 >= 0) {
            ans = Math.max(ans, memoDig(n, m, mine, r - 1, c + 1, dp));
        }

        return dp[r][c] = (ans + mine[r][c]);
    }

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] mine = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mine[i][j] = scn.nextInt();
            }
        }

        // recursion
        // int ans = Integer.MIN_VALUE;
        // for(int r = 0; r < n; r++) {
        // ans = Math.max(ans, dig(n, m, mine, r, 0));
        // }
        // System.out.println(ans);

        // memoization
        // int[][] dp = new int[n][m];
        // int ans = Integer.MIN_VALUE;
        // for(int r = 0; r < n; r++) {
        // ans = Math.max(ans, memoDig(n, m, mine, r, 0, dp));
        // }
        // System.out.println(ans);

        // tabulation
        int[][] dp = new int[n][m];
        int sol = Integer.MIN_VALUE;
        for (int c = m - 1; c >= 0; c--) {
            for (int r = 0; r < n; r++) {
                if (c == m - 1) {
                    dp[r][c] = mine[r][c];
                    continue;
                }

                int ans = 0;
                if (r + 1 < n) {
                    ans = Math.max(ans, dp[r + 1][c + 1]);
                }

                ans = Math.max(ans, dp[r][c + 1]);

                if (r - 1 >= 0) {
                    ans = Math.max(ans, dp[r - 1][c + 1]);
                }

                dp[r][c] = (ans + mine[r][c]);
            }
        }

        for (int r = 0; r <= n - 1; r++) {
            sol = Math.max(sol, dp[r][0]);
        }
        System.out.println(sol);
    }

}