import java.io.*;
import java.util.*;

public class BreadthFirstTraversal {

  static class Edge {
    int src;
    int nbr;

    Edge(int src, int nbr) {
      this.src = src;
      this.nbr = nbr;
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
      graph[v1].add(new Edge(v1, v2));
      graph[v2].add(new Edge(v2, v1));
    }

    int src = Integer.parseInt(br.readLine());

    // write your code here
    Queue<Pair> que = new ArrayDeque<Pair>();
    que.add(new Pair(src, "" + src));
    boolean[] vis = new boolean[vtces];
    while (que.size() != 0) {
      Pair rVtce = que.remove();
      if (vis[rVtce.src] == true) {
        continue;
      }
      vis[rVtce.src] = true;
      System.out.println(rVtce.src + "@" + rVtce.psf);
      for (var e : graph[rVtce.src]) {
        if (vis[e.nbr] == false) {
          que.add(new Pair(e.nbr, rVtce.psf + e.nbr));
        }
      }
    }
  }

  static class Pair {
    int src;
    String psf;

    Pair(int src, String psf) {
      this.src = src;
      this.psf = psf;
    }
  }
}
