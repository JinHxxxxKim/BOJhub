import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int V;
    private static int E;

    private static boolean[] visited;
    private static int[] dist;

    private static ArrayList<Edge1916>[] adList;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        visited = new boolean[V + 1];
        dist = new int[V + 1];
        adList = new ArrayList[V + 1];
        for (int i = 1; i <= V; ++i) {
            adList[i] = new ArrayList<>();
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < E; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adList[n1].add(new Edge1916(n2, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int srcNode = Integer.parseInt(st.nextToken());
        int destNode = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Edge1916> pq = new PriorityQueue<>();
        pq.offer(new Edge1916(srcNode, 0));
        dist[srcNode] = 0;
        while (!pq.isEmpty()) {
            Edge1916 temp = pq.poll();
            int currNodeNum = temp.getNodeNum();
            int currWeight = temp.getWeight();
            if (visited[currNodeNum]) {
                continue;
            }
            if (currNodeNum == destNode) {
                break;
            }
            visited[currNodeNum] = true;
            for (Edge1916 edge : adList[currNodeNum]) {
                int nextNodeNum = edge.getNodeNum();
                int cost = edge.getWeight();
                if (visited[nextNodeNum]) {
                    continue;
                }
                dist[nextNodeNum] = Math.min(dist[nextNodeNum], dist[currNodeNum] + cost);
                pq.offer(new Edge1916(nextNodeNum, dist[nextNodeNum]));
            }
        }
        //System.out.println(Arrays.toString(dist));
        System.out.println(dist[destNode]);
        
        //System.out.println("dist[destNode] = " + dist[destNode]);
    }
}

class Edge1916 implements Comparable<Edge1916> {
    private int nodeNum;
    private int weight;

    public Edge1916(int nodeNum, int weight) {
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
    public int compareTo(Edge1916 edge1916) {
        return this.weight - edge1916.getWeight();
    }
}