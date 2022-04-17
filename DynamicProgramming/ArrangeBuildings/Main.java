import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        // n == 1
        long space = 1;
        long building = 1;

        for (int i = 2; i <= n; i++) {
            // if adding space, BS', SS'
            long ns = space + building;
            // if adding building, SB'
            long nb = space;
            space = ns;
            building = nb;
        }

        long ans = (space + building) * (space + building);
        System.out.println(ans);
    }

}