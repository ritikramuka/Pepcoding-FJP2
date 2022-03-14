import java.util.*;

public class Main {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        TreeNode node;
        int state;

        Pair() {
        }

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static TreeNode Construct(Integer[] arr) {
        if (arr.length == 0) {
            return new TreeNode();
        }

        TreeNode root = new TreeNode(arr[0], null, null);
        Pair rtp = new Pair(root, 1);

        Stack<Pair> st = new Stack<>();
        st.push(rtp);
        int i = 1;
        while (i < arr.length) {
            Pair top = st.peek();
            if (top.state == 1) {
                if (arr[i] != null) {
                    TreeNode left = new TreeNode(arr[i], null, null);
                    top.node.left = left;

                    Pair lp = new Pair(left, 1);
                    st.push(lp);
                }
                i++;
                top.state++;
            } else if (top.state == 2) {
                if (arr[i] != null) {
                    TreeNode right = new TreeNode(arr[i], null, null);
                    top.node.right = right;

                    Pair rp = new Pair(right, 1);
                    st.push(rp);
                }
                i++;
                top.state++;
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static void display(TreeNode root) {
        if (root == null)
            return;

        if (root.left != null) {
            System.out.print(root.left.val + " -> ");
        } else {
            System.out.print(". -> ");
        }

        System.out.print(root.val);

        if (root.right != null) {
            System.out.print(" <- " + root.right.val);
        } else {
            System.out.print(" <- .");
        }

        System.out.println();

        display(root.left);
        display(root.right);
    }

    public static int size(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }

    public static int sum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return sum(node.left) + sum(node.right) + node.val;
    }

    public static int max(TreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(max(node.left), Math.max(max(node.right), node.val));
    }

    public static int height(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode node = q.poll();
                System.out.print(node.val + " ");
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            System.out.println();
        }
    }

    public static void iterativePreTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                st.push(node.right);
            }
            if (node.left != null) {
                st.push(node.left);
            }
        }
    }

    public static void iterativeInTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> st = new Stack<>();
        TreeNode node = root;
        while (node != null || !st.isEmpty()) {
            while (node != null) {
                st.push(node);
                node = node.left;
            }
            node = st.pop();
            System.out.print(node.val + " ");
            node = node.right;
        }
    }

    public static void iterativePostTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> st = new Stack<>();
        TreeNode node = root;
        TreeNode prev = null;
        while (node != null || !st.isEmpty()) {
            while (node != null) {
                st.push(node);
                node = node.left;
            }
            node = st.peek();
            if (node.right == null || node.right == prev) {
                System.out.print(node.val + " ");
                prev = st.pop();
                node = null;
            } else {
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };
        TreeNode root = Construct(arr);
        // display(root);
        System.out.println("Size: " + size(root));
        System.out.println("Sum: " + sum(root));
        System.out.println("Max: " + max(root));
        System.out.println("Height: " + height(root));
        System.out.println("Preorder: ");
        preorder(root);
        System.out.println();
        System.out.println("Inorder: ");
        inorder(root);
        System.out.println();
        System.out.println("Postorder: ");
        postorder(root);
        System.out.println();
        System.out.println("LevelOrder: ");
        levelOrder(root);
        System.out.println("IterativePreTraversal: ");
        iterativePreTraversal(root);
        System.out.println();
        System.out.println("IterativeInTraversal: ");
        iterativeInTraversal(root);
        System.out.println();
        System.out.println("IterativePostTraversal: ");
        iterativePostTraversal(root);
    }
}