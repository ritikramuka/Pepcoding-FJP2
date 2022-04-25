import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = scn.nextInt();
        }

        int ans = 0;
        int minTillNow = cost[0];
        for (int i = 1; i < n; i++) {
            if (minTillNow > cost[i]) {
                minTillNow = cost[i];
            }

            int profit = cost[i] - minTillNow;
            ans = Math.max(ans, profit);
        }

        System.out.println(ans);
    }
}