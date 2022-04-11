import java.io.*;
import java.util.*;

public class Main {

    public static int rec(int n, int[] val, int[] wt, int cap, int idx) {
        if (idx == n || cap == 0) {
            return 0;
        }

        int inc = 0, exc = 0;
        if (cap >= wt[idx]) {
            inc = rec(n, val, wt, cap - wt[idx], idx + 1) + val[idx];
        }
        exc = rec(n, val, wt, cap, idx + 1);

        return Math.max(inc, exc);
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = scn.nextInt();
        }
        int[] wt = new int[n];
        for (int i = 0; i < n; i++) {
            wt[i] = scn.nextInt();
        }
        int cap = scn.nextInt();

        // recursion
        // int ans = rec(n, val, wt, cap, 0);
        // System.out.println(ans);

        // tabulation
        int[][] dp = new int[n + 1][cap + 1];
        for (int idx = n; idx >= 0; idx--) {
            for (int c = 0; c <= cap; c++) {
                if (idx == n || c == 0) {
                    dp[idx][c] = 0;
                    continue;
                }

                int inc = 0, exc = 0;
                if (c >= wt[idx]) {
                    inc = dp[idx + 1][c - wt[idx]] + val[idx];
                }
                exc = dp[idx + 1][c];

                dp[idx][c] = Math.max(inc, exc);
            }
        }
        System.out.println(dp[0][cap]);
    }
}