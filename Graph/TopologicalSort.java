import java.io.*;
import java.util.*;

public class TopologicalSort {

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
    }

    // write your code here
    boolean[] vis = new boolean[vtces];
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < vtces; i++) {
      if (vis[i] == false) {
        dfs(graph, i, vis, st);
      }
    }
    while (st.size() != 0) {
      System.out.println(st.pop());
    }
  }

  public static void dfs(
    ArrayList<Edge>[] graph,
    int src,
    boolean[] vis,
    Stack<Integer> st
  ) {
    vis[src] = true;
    for (var e : graph[src]) {
      if (vis[e.nbr] == false) {
        dfs(graph, e.nbr, vis, st);
      }
    }
    st.push(src);
  }
}
