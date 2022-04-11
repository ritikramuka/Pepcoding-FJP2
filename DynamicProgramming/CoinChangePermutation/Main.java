import java.io.*;
import java.util.*;

public class Main {

    public static int rec(int n, int[] coins, int amt) {
        if (amt == 0) {
            return 1;
        }

        int ans = 0;
        for (int coin : coins) {
            if (amt - coin >= 0) {
                ans += rec(n, coins, amt - coin);
            }
        }

        return ans;
    }

    public static int memo(int n, int[] coins, int amt, int[] dp) {
        if (amt == 0) {
            return dp[amt] = 1;
        }

        if (dp[amt] != 0) {
            return dp[amt];
        }

        int ans = 0;
        for (int coin : coins) {
            if (amt - coin >= 0) {
                ans += memo(n, coins, amt - coin, dp);
            }
        }

        return dp[amt] = ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = scn.nextInt();
        }
        int amt = scn.nextInt();

        // recusrion
        // int ans = rec(n, coins, amt);
        // System.out.println(ans);

        // memo
        // int [] dp = new int[amt + 1];
        // int ans = memo(n, coins, amt, dp);
        // System.out.println(ans);

        // tabulation
        int[] dp = new int[amt + 1];
        for (int a = 0; a <= amt; a++) {
            if (a == 0) {
                dp[a] = 1;
                continue;
            }

            int ans = 0;
            for (int coin : coins) {
                if (a - coin >= 0) {
                    ans += dp[a - coin];
                }
            }

            dp[a] = ans;
        }
        System.out.println(dp[amt]);
    }
}