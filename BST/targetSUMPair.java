package BST;
import java.io.*;
import java.util.*;

public class targetSUMPair {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  public static class Pair2 {
    Node node;
    int state;

    Pair2() {}

    Pair2(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static void helper(Node node, int tar) {
    Stack<Pair2> inc = new Stack<>();
    Stack<Pair2> dec = new Stack<>();

    inc.push(new Pair2(node, 0));
    dec.push(new Pair2(node, 0));

    Node left = getNextGreater(inc);
    Node right = getNextSmaller(dec);

    while(left.data < right.data) {
      if(left.data + right.data < tar) {
        left = getNextGreater(inc);
      } else if(left.data + right.data > tar) {
        right = getNextSmaller(dec);
      } else {
        System.out.println(left.data + " " + right.data);
        left = getNextGreater(inc);
        right = getNextSmaller(dec);
      }
    }
  }

  public static Node getNextGreater(Stack<Pair2> st) {
    while(st.size() > 0) {
      Pair2 rpair = st.peek();
      if(rpair.state == 0) {
        if(rpair.node.left != null) {
          st.push(new Pair2(rpair.node.left, 0));
        }
        rpair.state++;
      } else if(rpair.state == 1) {
        if(rpair.node.right != null) {
          st.push(new Pair2(rpair.node.right, 0));
        }
        rpair.state++;
        return rpair.node;
      } else {
        st.pop();
      }
    }

    return null;
  }

  public static Node getNextSmaller(Stack<Pair2> st) {
    while(st.size() > 0) {
      Pair2 rpair = st.peek();
      if(rpair.state == 0) {
        if(rpair.node.right != null) {
          st.push(new Pair2(rpair.node.right, 0));
        }
        rpair.state++;
      } else if(rpair.state == 1) {
        if(rpair.node.left != null) {
          st.push(new Pair2(rpair.node.left, 0));
        }
        rpair.state++;
        return rpair.node;
      } else {
        st.pop();
      }
    }

    return null;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    int target = Integer.parseInt(br.readLine());

    Node root = construct(arr);
    // write your code here
    helper(root, target);
  }

}