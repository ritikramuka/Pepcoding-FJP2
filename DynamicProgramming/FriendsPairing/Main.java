import java.io.*;
import java.util.*;

public class Main {

    public static int rec(int n) {
        if (n == 0) {
            return 1;
        }

        int single = 0, pair = 0;
        if (n - 2 >= 0) {
            pair = (n - 1) * rec(n - 2);
        }

        single = 1 * rec(n - 1);

        return single + pair;
    }

    public static int memo(int n, int[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        int single = 0, pair = 0;
        if (n - 2 >= 0) {
            pair = (n - 1) * memo(n - 2, dp);
        }

        single = 1 * memo(n - 1, dp);

        return dp[n] = single + pair;
    }

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // rec
        // int ans = rec(n);
        // System.out.println(ans);

        // memo
        // int[] dp = new int[n + 1];
        // int ans = memo(n, dp);
        // System.out.println(ans);

        // tabulation
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                dp[i] = 1;
                continue;
            }

            int single = 0, pair = 0;
            if (i - 2 >= 0) {
                pair = (i - 1) * dp[i - 2];
            }

            single = 1 * dp[i - 1];

            dp[i] = single + pair;
        }
        System.out.println(dp[n]);
    }

}