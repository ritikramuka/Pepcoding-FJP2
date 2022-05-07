import java.io.*;
import java.util.*;

public class NumberOfIslands {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int m = Integer.parseInt(br.readLine());
    int n = Integer.parseInt(br.readLine());
    int[][] arr = new int[m][n];

    for (int i = 0; i < arr.length; i++) {
      String parts = br.readLine();
      for (int j = 0; j < arr[0].length; j++) {
        arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
      }
    }

    // write your code here
    int ans = 0;
    for (int r = 0; r < m; r++) {
      for (int c = 0; c < n; c++) {
        if (arr[r][c] == 0) {
          dfs(arr, r, c);
          ans++;
        }
      }
    }
    System.out.println(ans);
  }

  public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  public static void dfs(int[][] arr, int r, int c) {
    arr[r][c] = 2;
    for (int i = 0; i < 4; i++) {
      int nr = r + dir[i][0];
      int nc = c + dir[i][1];
      if (
        nc >= 0 &&
        nr >= 0 &&
        nc < arr[0].length &&
        nr < arr.length &&
        arr[nr][nc] == 0
      ) {
        dfs(arr, nr, nc);
      }
    }
  }
}
