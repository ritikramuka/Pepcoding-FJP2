import java.io.*;
import java.util.*;

public class PerfectFriends {

  public static class Edge {
    int src;
    int nbr;

    Edge() {}

    Edge(int src, int nbr) {
      this.src = src;
      this.nbr = nbr;
    }
  }

  public static void dfs(
    ArrayList<Edge>[] graph,
    int src,
    boolean[] vis,
    ArrayList<Integer> comp
  ) {
    comp.add(src);
    vis[src] = true;
    for (Edge e : graph[src]) {
      if (vis[e.nbr] == false) {
        dfs(graph, e.nbr, vis, comp);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int k = scn.nextInt();

    ArrayList<Edge>[] graph = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<Edge>();
    }

    for (int i = 0; i < k; i++) {
      int src = scn.nextInt();
      int nbr = scn.nextInt();
      graph[src].add(new Edge(src, nbr));
      graph[nbr].add(new Edge(nbr, src));
    }

    boolean[] vis = new boolean[n];
    ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (vis[i] == false) {
        ArrayList<Integer> comp = new ArrayList<Integer>();
        dfs(graph, i, vis, comp);
        comps.add(comp);
      }
    }

    int pair = 0;
    for (int i = 0; i < comps.size(); i++) {
      for (int j = i + 1; j < comps.size(); j++) {
        pair += comps.get(i).size() * comps.get(j).size();
      }
    }
    System.out.println(pair);
  }
}
