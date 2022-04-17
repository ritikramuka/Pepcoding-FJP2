import java.io.*;
import java.util.*;

public class Main {
    public static int rec(int n) {
        if (n == 0) {
            return 1;
        }

        int hz = 0, vt = 0;
        if (n - 2 >= 0) {
            hz = 1 * rec(n - 2);
        }
        vt = 1 * rec(n - 1);

        return vt + hz;
    }

    public static int memo(int n, int[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        int hz = 0, vt = 0;
        if (n - 2 >= 0) {
            hz = 1 * memo(n - 2, dp);
        }
        vt = 1 * memo(n - 1, dp);

        return dp[n] = vt + hz;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        // rec
        // int ans = rec(n);
        // System.out.println(ans);

        // memo
        // int[] dp = new int[n + 1];
        // int ans = memo(n, dp);
        // System.out.println(ans);

        // dp
        // int[] dp = new int[n + 1];
        // for(int i = 0; i <= n; i++) {
        // if(i == 0) {
        // dp[i] = 1;
        // continue;
        // }

        // int hz = 0, vt = 0;
        // if(i - 2 >= 0) {
        // hz = 1 * dp[i - 2];
        // }
        // vt = 1 * dp[i - 1];

        // dp[i] = vt + hz;
        // }
        // System.out.println(dp[n]);

        // space optimize
        int prev1 = 1, prev2 = 1;
        for (int i = 2; i <= n; i++) {
            int currAns = prev1 + prev2;
            prev2 = prev1;
            prev1 = currAns;
        }
        System.out.println(prev1);
    }
}