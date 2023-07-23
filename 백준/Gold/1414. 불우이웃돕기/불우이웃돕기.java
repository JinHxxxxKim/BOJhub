import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;

    private static PriorityQueue<Edge1414> array;
    private static int[][] matrix;
    private static int[] rootInfo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        int totalLen = 0;

        for (int i = 0; i < N; ++i) {
            String str = br.readLine();
            for (int j = 0; j < N; ++j) {
                if (str.charAt(j) == '0') {
                    matrix[i][j] = 0;
                }else {
                    matrix[i][j] = str.charAt(j) - 96;

                    if (matrix[i][j] < 0) {
                        matrix[i][j] += 58;
                    }
                }
                totalLen += matrix[i][j];
            }
        }

        array = new PriorityQueue<>();

        rootInfo = new int[N];
        for (int i = 0; i < N; ++i) {
            rootInfo[i] = i;
        }
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (matrix[i][j] == 0 || i == j) {
                    continue;
                }
                array.add(new Edge1414(i, j, matrix[i][j]));
            }
        }

        int ans = 0;

        while(!array.isEmpty()){
            Edge1414 currEdge = array.poll();
            int srcNode = currEdge.getSrcNode();
            int destNode = currEdge.getDestNode();
            int weight = currEdge.getWeight();

            int srcNode_root = find(srcNode);
            int destNode_root = find(destNode);

            if (srcNode_root != destNode_root) {
                union(srcNode_root, destNode_root);
                ans += weight;
            }
        }

        int root = find(0);

        for (int i = 0; i < N; ++i) {
            if (find(i) != root) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(totalLen - ans);
    }

    private static void union(int n1, int n2) {
        if (n1 < n2) {
            rootInfo[n2] = n1;
        } else {
            rootInfo[n1] = n2;
        }
    }

    private static int find(int node) {
        if (rootInfo[node] == node) {
            return node;
        } else {
            return rootInfo[node] = find(rootInfo[node]);
        }
    }
}

class Edge1414 implements Comparable<Edge1414>{
    private int srcNode;
    private int destNode;
    private int weight;

    public int getSrcNode() {
        return srcNode;
    }

    public int getDestNode() {
        return destNode;
    }

    public int getWeight() {
        return weight;
    }

    public Edge1414(int srcNode, int destNode, int weight) {
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge1414 edge) {
        return this.weight - edge.weight;
    }
}
