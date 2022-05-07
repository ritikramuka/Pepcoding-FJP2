import java.io.*;
import java.util.*;

public class IsGraphBipartite {

  static class Edge {
    int src;
    int nbr;
    int wt;

    Edge(int src, int nbr, int wt) {
      this.src = src;
      this.nbr = nbr;
      this.wt = wt;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int vtces = Integer.parseInt(br.readLine());
    ArrayList<Edge>[] graph = new ArrayList[vtces];
    for (int i = 0; i < vtces; i++) {
      graph[i] = new ArrayList<>();
    }

    int edges = Integer.parseInt(br.readLine());
    for (int i = 0; i < edges; i++) {
      String[] parts = br.readLine().split(" ");
      int v1 = Integer.parseInt(parts[0]);
      int v2 = Integer.parseInt(parts[1]);
      int wt = Integer.parseInt(parts[2]);
      graph[v1].add(new Edge(v1, v2, wt));
      graph[v2].add(new Edge(v2, v1, wt));
    }

    // write your code here
    int[] vis = new int[vtces];
    Arrays.fill(vis, -1);
    for (int i = 0; i < vtces; i++) {
      if (vis[i] == -1) {
        boolean isOdd = isOddCyclePresent(graph, i, vis);
        if (isOdd == true) {
          System.out.println(false);
          return;
        }
      }
    }

    System.out.println(true);
  }

  public static boolean isOddCyclePresent(
    ArrayList<Edge>[] graph,
    int src,
    int[] vis
  ) {
    Queue<Pair> que = new ArrayDeque<Pair>();
    que.add(new Pair(src, 1));
    while (que.size() != 0) {
      Pair rPair = que.remove();
      if (vis[rPair.src] != -1) {
        // found cycle
        if ((rPair.level + vis[rPair.src]) % 2 == 1) {
          return true;
        } else {
          continue;
        }
      }
      vis[rPair.src] = rPair.level;
      for (var e : graph[rPair.src]) {
        if (vis[e.nbr] == -1) {
          que.add(new Pair(e.nbr, rPair.level + 1));
        }
      }
    }

    return false;
  }

  static class Pair {
    int src;
    int level;

    Pair(int src, int level) {
      this.src = src;
      this.level = level;
    }
  }
}
