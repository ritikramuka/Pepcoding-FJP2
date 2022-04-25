import java.io.*;
import java.util.*;

public class Main {

    public static int memo(int idx, int flag, int[] arr, int[][][] dp, int tr) {
        if (tr == 0) {
            return dp[idx][flag][tr] = 0;
        }

        if (idx == arr.length) {
            return dp[idx][flag][tr] = 0;
        }

        if (dp[idx][flag][tr] != 0) {
            return dp[idx][flag][tr];
        }

        if (flag == 0) {
            // buy
            int ProfitWhenBuyHere = -arr[idx] + memo(idx + 1, 1, arr, dp, tr);
            int ProfitWhenNotBuyingHere = memo(idx + 1, 0, arr, dp, tr);
            return dp[idx][flag][tr] = Math.max(ProfitWhenBuyHere, ProfitWhenNotBuyingHere);
        } else {
            // sell
            int ProfitWhenSellHere = arr[idx] + memo(idx + 1, 0, arr, dp, tr - 1);
            int ProfitWhenNotSellingHere = memo(idx + 1, 1, arr, dp, tr);
            return dp[idx][flag][tr] = Math.max(ProfitWhenSellHere, ProfitWhenNotSellingHere);
        }

    }

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }

        int k = scn.nextInt();

        // flag -> 0 (buy), 1 (sell)
        // int[][][] dp = new int[n + 1][2][k + 1];
        // int ans = memo(0, 0, arr, dp, k);
        // System.out.println(ans);

        // tabulation
        // int[][][] dp = new int[n + 1][2][k + 1];
        // for(int idx = n; idx >= 0; idx--) {
        // for(int flag = 0; flag < 2; flag++) {
        // for(int tr = 0; tr <= k; tr++) {
        // if(tr == 0) {
        // dp[idx][flag][tr] = 0;
        // continue;
        // }

        // if(idx == arr.length) {
        // dp[idx][flag][tr] = 0;
        // continue;
        // }

        // if(flag == 0) {
        // // buy
        // int ProfitWhenBuyHere = -arr[idx] + dp[idx + 1][1][tr];
        // int ProfitWhenNotBuyingHere = dp[idx + 1][0][tr];
        // dp[idx][flag][tr] = Math.max(ProfitWhenBuyHere, ProfitWhenNotBuyingHere);
        // }
        // else {
        // // sell
        // int ProfitWhenSellHere = arr[idx] + dp[idx + 1][0][tr - 1];
        // int ProfitWhenNotSellingHere = dp[idx + 1][1][tr];
        // dp[idx][flag][tr] = Math.max(ProfitWhenSellHere, ProfitWhenNotSellingHere);
        // }
        // }
        // }
        // }
        // System.out.println(dp[0][0][k]);

        int[][] prev = new int[2][k + 1];
        int[][] curr = new int[2][k + 1];
        for (int idx = n; idx >= 0; idx--) {
            for (int flag = 0; flag < 2; flag++) {
                for (int tr = 0; tr <= k; tr++) {
                    if (tr == 0) {
                        curr[flag][tr] = 0;
                        continue;
                    }

                    if (idx == arr.length) {
                        curr[flag][tr] = 0;
                        continue;
                    }

                    if (flag == 0) {
                        // buy
                        int ProfitWhenBuyHere = -arr[idx] + prev[1][tr];
                        int ProfitWhenNotBuyingHere = prev[0][tr];
                        curr[flag][tr] = Math.max(ProfitWhenBuyHere, ProfitWhenNotBuyingHere);
                    } else {
                        // sell
                        int ProfitWhenSellHere = arr[idx] + prev[0][tr - 1];
                        int ProfitWhenNotSellingHere = prev[1][tr];
                        curr[flag][tr] = Math.max(ProfitWhenSellHere, ProfitWhenNotSellingHere);
                    }
                }
            }
            prev = curr;
        }
        System.out.println(prev[0][k]);
    }

}