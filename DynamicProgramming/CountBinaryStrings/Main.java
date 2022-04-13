import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[][] dp = new int[2][n];
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                dp[0][i] = 1;
                dp[1][i] = 1;
                continue;
            }

            dp[0][i] = dp[1][i + 1];
            dp[1][i] = dp[0][i + 1] + dp[1][i + 1];
        }

        System.out.println(dp[0][0] + dp[1][0]);
    }
}