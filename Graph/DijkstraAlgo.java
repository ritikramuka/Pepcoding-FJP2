import java.io.*;
import java.util.*;

public class DijkstraAlgo {

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

    int src = Integer.parseInt(br.readLine());
    // write your code here
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    boolean[] vis = new boolean[vtces];
    pq.add(new Pair(src, "" + src, 0));
    while (pq.size() != 0) {
      Pair rPair = pq.remove();
      if (vis[rPair.src] == true) {
        continue;
      }
      System.out.println(rPair.src + " via " + rPair.psf + " @ " + rPair.wsf);
      vis[rPair.src] = true;
      for (var e : graph[rPair.src]) {
        if (vis[e.nbr] == false) {
          pq.add(new Pair(e.nbr, rPair.psf + e.nbr, rPair.wsf + e.wt));
        }
      }
    }
  }

  static class Pair implements Comparable<Pair> {
    int src;
    String psf;
    int wsf;

    Pair(int src, String psf, int wsf) {
      this.src = src;
      this.psf = psf;
      this.wsf = wsf;
    }

    public int compareTo(Pair o) {
      return this.wsf - o.wsf;
    }
  }
}
