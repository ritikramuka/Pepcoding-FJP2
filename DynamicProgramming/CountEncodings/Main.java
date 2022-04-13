import java.io.*;
import java.util.*;

public class Main {

    public static int memo(String str, int idx, int[] dp) {
        if (idx == str.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != 0) {
            return dp[idx];
        }

        int ans = 0;
        if (str.charAt(idx) >= '1' && str.charAt(idx) <= '9') {
            ans += memo(str, idx + 1, dp);
        }

        if (idx + 1 < str.length()
                && (str.charAt(idx) == '1' || (str.charAt(idx) == '2' && str.charAt(idx + 1) <= '6'))) {
            ans += memo(str, idx + 2, dp);
        }

        return dp[idx] = ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        // recursion
        // int ans = rec(str, 0);
        // System.out.println(ans);

        // memo
        // int[] dp = new int[str.length() + 1];
        // int ans = memo(str, 0, dp);
        // System.out.println(ans);

        // tabulation
        // int[] dp = new int[str.length() + 1];
        // for(int idx = str.length(); idx >= 0; idx--) {
        // if(idx == str.length()) {
        // dp[idx] = 1;
        // continue;
        // }

        // int ans = 0;
        // if(str.charAt(idx) >= '1' && str.charAt(idx) <= '9') {
        // ans += dp[idx + 1];
        // }

        // if(idx + 1 < str.length() && (str.charAt(idx) == '1' || (str.charAt(idx) ==
        // '2' && str.charAt(idx + 1) <= '6'))) {
        // ans += dp[idx + 2];
        // }

        // dp[idx] = ans;
        // }
        // System.out.println(dp[0]);

        // space opt
        int nxt = 0, nxtknxt = 0;
        for (int idx = str.length(); idx >= 0; idx--) {
            if (idx == str.length()) {
                nxt = 1;
                continue;
            }

            int ans = 0;
            if (str.charAt(idx) >= '1' && str.charAt(idx) <= '9') {
                ans += nxt;
            }

            if (idx + 1 < str.length()
                    && (str.charAt(idx) == '1' || (str.charAt(idx) == '2' && str.charAt(idx + 1) <= '6'))) {
                ans += nxtknxt;
            }

            nxtknxt = nxt;
            nxt = ans;
        }
        System.out.println(nxt);
    }
}