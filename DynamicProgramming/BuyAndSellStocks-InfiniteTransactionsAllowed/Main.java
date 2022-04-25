import java.io.*;
import java.util.*;

public class Main {

    public static int rec(int idx, int flag, int[] arr) {

        if (idx == arr.length) {
            return 0;
        }

        if (flag == 0) {
            // buy
            int ProfitWhenBuyHere = -arr[idx] + rec(idx + 1, 1, arr);
            int ProfitWhenNotBuyingHere = rec(idx + 1, 0, arr);
            return Math.max(ProfitWhenBuyHere, ProfitWhenNotBuyingHere);
        } else {
            // sell
            int ProfitWhenSellHere = arr[idx] + rec(idx + 1, 0, arr);
            int ProfitWhenNotSellingHere = rec(idx + 1, 1, arr);
            return Math.max(ProfitWhenSellHere, ProfitWhenNotSellingHere);
        }

    }

    public static int memo(int idx, int flag, int[] arr, int[][] dp) {

        if (idx == arr.length) {
            return dp[idx][flag] = 0;
        }

        if (dp[idx][flag] != 0) {
            return dp[idx][flag];
        }

        if (flag == 0) {
            // buy
            int ProfitWhenBuyHere = -arr[idx] + memo(idx + 1, 1, arr, dp);
            int ProfitWhenNotBuyingHere = memo(idx + 1, 0, arr, dp);
            return dp[idx][flag] = Math.max(ProfitWhenBuyHere, ProfitWhenNotBuyingHere);
        } else {
            // sell
            int ProfitWhenSellHere = arr[idx] + memo(idx + 1, 0, arr, dp);
            int ProfitWhenNotSellingHere = memo(idx + 1, 1, arr, dp);
            return dp[idx][flag] = Math.max(ProfitWhenSellHere, ProfitWhenNotSellingHere);
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

        // flag -> 0 (buy), 1 (sell)
        // int ans = rec(0, 0, arr);
        // System.out.println(ans);

        // int[][] dp = new int[n + 1][2];
        // int ans = memo(0, 0, arr, dp);
        // System.out.println(ans);

        // int[][] dp = new int[n + 1][2];
        // for(int idx = n; idx >= 0; idx--) {
        // for(int flag = 0; flag < 2; flag++) {
        // if(idx == arr.length) {
        // dp[idx][flag] = 0;
        // continue;
        // }

        // if(flag == 0) {
        // // buy
        // int ProfitWhenBuyHere = -arr[idx] + dp[idx + 1][1];
        // int ProfitWhenNotBuyingHere = dp[idx + 1][0];
        // dp[idx][flag] = Math.max(ProfitWhenBuyHere, ProfitWhenNotBuyingHere);
        // }
        // else {
        // // sell
        // int ProfitWhenSellHere = arr[idx] + dp[idx + 1][0];
        // int ProfitWhenNotSellingHere = dp[idx + 1][1];
        // dp[idx][flag] = Math.max(ProfitWhenSellHere, ProfitWhenNotSellingHere);
        // }
        // }
        // }
        // System.out.println(dp[0][0]);

        int[] prev = new int[2];
        for (int idx = n; idx >= 0; idx--) {
            int[] curr = new int[2];
            for (int flag = 0; flag < 2; flag++) {
                if (idx == arr.length) {
                    curr[flag] = 0;
                    continue;
                }
                if (flag == 0) {
                    // buy
                    int ProfitWhenBuyHere = -arr[idx] + prev[1];
                    int ProfitWhenNotBuyingHere = prev[0];
                    curr[flag] = Math.max(ProfitWhenBuyHere, ProfitWhenNotBuyingHere);
                } else {
                    // sell
                    int ProfitWhenSellHere = arr[idx] + prev[0];
                    int ProfitWhenNotSellingHere = prev[1];
                    curr[flag] = Math.max(ProfitWhenSellHere, ProfitWhenNotSellingHere);
                }
            }
            prev = curr;
        }
        System.out.println(prev[0]);
    }

}