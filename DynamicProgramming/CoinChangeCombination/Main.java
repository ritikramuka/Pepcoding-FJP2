import java.io.*;
import java.util.*;

public class Main {

    public static int rec(int n, int[] coins, int amt, int idx) {
        if (idx == n) {
            if (amt == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        int ans = 0;
        if (amt >= coins[idx]) {
            ans += rec(n, coins, amt - coins[idx], idx);
        }
        ans += rec(n, coins, amt, idx + 1);

        return ans;
    }

    public static int memo(int n, int[] coins, int amt, int idx, int[][] dp) {
        if (idx == n) {
            if (amt == 0) {
                return dp[idx][amt] = 1;
            } else {
                return dp[idx][amt] = 0;
            }
        }

        if (dp[idx][amt] != 0) {
            return dp[idx][amt];
        }

        int ans = 0;
        if (amt >= coins[idx]) {
            ans += memo(n, coins, amt - coins[idx], idx, dp);
        }
        ans += memo(n, coins, amt, idx + 1, dp);

        return dp[idx][amt] = ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = scn.nextInt();
        }
        int amt = scn.nextInt();

        // recursion
        // int ans = rec(n, coins, amt, 0);
        // System.out.println(ans);

        // memo
        // int[][] dp = new int[n + 1][amt + 1];
        // int ans = memo(n, coins, amt, 0, dp);
        // System.out.println(ans);

        // tabulation
        int[][] dp = new int[n + 1][amt + 1];
        for (int idx = n; idx >= 0; idx--) {
            for (int a = 0; a <= amt; a++) {
                if (idx == n) {
                    if (a == 0) {
                        dp[idx][a] = 1;
                        continue;
                    } else {
                        dp[idx][a] = 0;
                        continue;
                    }
                }

                int ans = 0;
                if (a >= coins[idx]) {
                    ans += dp[idx][a - coins[idx]];
                }
                ans += dp[idx + 1][a];

                dp[idx][a] = ans;
            }
        }
        System.out.println(dp[0][amt]);
    }
}