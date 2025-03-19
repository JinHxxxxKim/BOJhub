import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static class Node{
        int num;
        Node left;
        Node right;
        public Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }

    static Node root;
    public static void main(String[] args) {
        while (true) {
            try {
                int num = Integer.parseInt(br.readLine().trim());
                if (root == null) {
                    root = new Node(num, null, null);
                } else {
                    addNode(root, num);   
                }
            } catch (Exception e) {
                break;
            }
        }
        // POST ORDER
        postOrder(root);
        System.out.println(sb);
    }

    private static void postOrder(Node node) {
        // base
        if (node == null) {
            return;
        }

        // leaf node
        if (node.left == null & node.right == null) {
            sb.append(node.num).append("\n");
        } else {
            // inner node
            postOrder(node.left);
            postOrder(node.right);
            sb.append(node.num).append("\n");
        }
    }

    private static Node addNode(Node currNode, int num) {
        if (currNode == null) {
            currNode = new Node(num, null, null);
        } else if (currNode.num < num) {
            // 오른쪽 노드로 go
            currNode.right = addNode(currNode.right, num);
        } else {
            // 왼쪽 노드로 go
            currNode.left = addNode(currNode.left, num);
        }
        return currNode;
    }
}