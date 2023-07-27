import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int V;
    private static int E;

    private static boolean[] visited;
    private static int[] dist;

    private static ArrayList<Edge1753>[] adList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int startNodeNum = Integer.parseInt(br.readLine());
        adList = new ArrayList[V + 1];
        for (int i = 1; i <= V; ++i) {
            adList[i] = new ArrayList<Edge1753>();
        }
        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int srcNode = Integer.parseInt(st.nextToken());
            int destNode = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adList[srcNode].add(new Edge1753(destNode, weight));
        }
        visited = new boolean[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node1753> pq = new PriorityQueue<>();
        pq.offer(new Node1753(startNodeNum, 0));
        dist[startNodeNum] = 0;
        while (!pq.isEmpty()) {
            Node1753 currNode = pq.poll();
            int currNodeNum = currNode.getNodeNum();
            int currWeight = currNode.getWeight();
            if (visited[currNodeNum]) {
                continue;
            }
            visited[currNodeNum] = true;
            for (Edge1753 edge : adList[currNodeNum]) {
                int destNodeNum = edge.getDestNode();
                int useCost = edge.getWeight();
                if (visited[destNodeNum]) {
                    continue;
                }
                dist[destNodeNum] = Math.min(dist[destNodeNum], currWeight + useCost);
                pq.offer(new Node1753(destNodeNum, dist[destNodeNum]));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; ++i) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(dist[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

class Node1753 implements Comparable<Node1753> {
    private int nodeNum;
    private int weight;

    public Node1753(int nodeNum, int weight) {
        this.nodeNum = nodeNum;
        this.weight = weight;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Node1753 node1753) {
        return this.weight - node1753.getWeight();
    }
}

class Edge1753 {
    private int destNode;
    private int weight;

    public Edge1753(int destNode, int weight) {
        this.destNode = destNode;
        this.weight = weight;
    }

    public int getDestNode() {
        return destNode;
    }

    public int getWeight() {
        return weight;
    }
}