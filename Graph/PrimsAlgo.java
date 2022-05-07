import java.io.*;
import java.util.*;

public class PrimsAlgo {

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
    for (int i = 0; i < vtces; i++) {
      prims(graph, i, vtces);
    }
  }

  static void prims(ArrayList<Edge>[] graph, int src, int vtces) {
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.add(new Pair(src, -1, 0));
    boolean[] vis = new boolean[vtces];
    while (pq.size() != 0) {
      Pair rp = pq.remove();
      if (vis[rp.src] == true) {
        continue;
      }
      vis[rp.src] = true;
      if (rp.parent != -1) System.out.println(
        "[" + rp.src + "-" + rp.parent + "@" + rp.wt + "]"
      );
      for (var e : graph[rp.src]) {
        if (vis[e.nbr] == false) {
          pq.add(new Pair(e.nbr, e.src, e.wt));
        }
      }
    }
    System.out.println("-------------");
  }

  static class Pair implements Comparable<Pair> {
    int src;
    int parent;
    int wt;

    Pair(int src, int parent, int wt) {
      this.src = src;
      this.parent = parent;
      this.wt = wt;
    }

    public int compareTo(Pair other) {
      return this.wt - other.wt;
    }
  }
}
