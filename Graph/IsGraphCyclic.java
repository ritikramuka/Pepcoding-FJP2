import java.io.*;
import java.util.*;

public class IsGraphCyclic {

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
    boolean[] vis = new boolean[vtces];
    for (int i = 0; i < vtces; i++) {
      if (vis[i] == false) {
        boolean hasCycle = isCycle(graph, i, vis);
        if (hasCycle == true) {
          System.out.println(true);
          return;
        }
      }
    }
    System.out.println(false);
  }

  public static boolean isCycle(
    ArrayList<Edge>[] graph,
    int src,
    boolean[] vis
  ) {
    Queue<Integer> que = new ArrayDeque<>();
    que.add(src);
    while (que.size() != 0) {
      int rSrc = que.remove();
      if (vis[rSrc] == true) {
        // found cycle
        return true;
      }
      vis[rSrc] = true;
      for (var e : graph[rSrc]) {
        if (vis[e.nbr] == false) {
          que.add(e.nbr);
        }
      }
    }

    return false;
  }
}
