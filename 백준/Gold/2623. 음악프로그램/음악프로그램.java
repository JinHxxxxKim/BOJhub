import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;

    static class Node implements Comparable<Node>{
        int num;
        int inDegree;

        public Node(int num, int inDegree) {
            this.num = num;
            this.inDegree = inDegree;
        }

        @Override
        public int compareTo(Node o) {
            return this.inDegree - o.inDegree;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", inDegree=" + inDegree +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N + 1];
        List<Integer>[] adList = new ArrayList[N + 1];
        for (int node = 1; node < N + 1; ++node) {
            adList[node] = new ArrayList<>();
            nodes[node] = new Node(node, 0);
        }

        for (int cnt = 0; cnt < M; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int size = Integer.parseInt(st.nextToken());
            int currNode = -1;
            for (int idx = 0; idx < size; ++idx) {
                if (idx == 0) {
                    currNode = Integer.parseInt(st.nextToken());
                } else {
                    int nextNode = Integer.parseInt(st.nextToken());
                    adList[currNode].add(nextNode);
                    nodes[nextNode].inDegree++;
                    currNode = nextNode;
                }
            }
        }

        // 위상정렬
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Node node : nodes) {
            if (node != null && node.inDegree == 0) {
                pq.offer(node);
                visited[node.num] = true;
            }
        }
        int visitCnt = 0;
        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            ++visitCnt;
            sb.append(currNode.num).append("\n");

            for (Integer nextNode : adList[currNode.num]) {
                if (visited[nextNode]) {
                    continue;
                }
                nodes[nextNode].inDegree--;
                if (nodes[nextNode].inDegree == 0) {
                    pq.offer(nodes[nextNode]);
                    visited[nextNode] = true;
                }

            }
        }
        if (visitCnt == N) {
            System.out.println(sb);
        } else {
            System.out.println(0);
        }

    }
}
