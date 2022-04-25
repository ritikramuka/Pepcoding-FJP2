import java.io.*;
import java.util.*;

public class Main {

    public static int memo(int idx, int flag, int[] arr, int[][] dp, int fees) {
        if (idx == arr.length) {
            return dp[idx][flag] = 0;
        }

        if (dp[idx][flag] != 0) {
            return dp[idx][flag];
        }

        if (flag == 0) {
            // buy
            int ProfitWhenBuyHere = -arr[idx] + memo(idx + 1, 1, arr, dp, fees);
            int ProfitWhenNotBuyingHere = memo(idx + 1, 0, arr, dp, fees);
            return dp[idx][flag] = Math.max(ProfitWhenBuyHere, ProfitWhenNotBuyingHere);
        } else {
            // sell
            int ProfitWhenSellHere = arr[idx] + memo(idx + 1, 0, arr, dp, fees) - fees;
            int ProfitWhenNotSellingHere = memo(idx + 1, 1, arr, dp, fees);
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
        int fees = scn.nextInt();

        // flag -> 0 (buy), 1 (sell)
        int[][] dp = new int[n + 1][2];
        int ans = memo(0, 0, arr, dp, fees);
        System.out.println(ans);
    }
}