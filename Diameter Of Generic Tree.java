import java.io.*;
import java.util.*;

public class Main {

  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }

  private static int diameter;

  private static int getHeightButUpdateDia(Node root) {
    if (root == null) return 0;

    int dch = -1;
    int sdch = -1;

    for (Node child : root.children) {
      int ht = getHeightButUpdateDia(child);

      if (ht > dch) {
        sdch = dch;
        dch = ht;
      } else if (ht > sdch) {
        sdch = ht;
      }
    }

    if (dch + sdch + 2 > diameter) {
      diameter = dch + sdch + 2;
    }

    return dch + 1;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);
    // write your code here
    diameter = 0;
    getHeightButUpdateDia(root);
    System.out.println(diameter);
  }
}
