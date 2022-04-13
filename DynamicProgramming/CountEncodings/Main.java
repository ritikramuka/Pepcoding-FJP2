import java.io.*;
import java.util.*;

public class Main {

    public static int rec(String str, int idx) {
        if (idx == str.length()) {
            return 1;
        }

        int ans = 0;
        if (str.charAt(idx) >= '1' && str.charAt(idx) <= '9') {
            ans += rec(str, idx + 1);
        }

        if (idx + 1 < str.length()
                && (str.charAt(idx) == '1' || (str.charAt(idx) == '2' && str.charAt(idx + 1) <= '6'))) {
            ans += rec(str, idx + 2);
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();

        int ans = rec(str, 0);
        System.out.println(ans);
    }
}