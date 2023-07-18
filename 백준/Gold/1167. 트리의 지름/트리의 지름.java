import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{
    private int nodeNum;
    private int distance;

    @Override
    public String toString() {
        return "Node{" +
                "nodeNum=" + nodeNum +
                ", distance=" + distance +
                '}';
    }

    public Node(int nodeNum, int distance) {
        this.nodeNum = nodeNum;
        this.distance = distance;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
public class Main {
    static int V;
    static int ans;
    static int onePointOfDiameter;

    static ArrayList<Node>[] adList;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        adList = new ArrayList[V + 1];
        for (int i = 1; i <= V; ++i) {
            adList[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int mainNode = Integer.parseInt(st.nextToken());
            int subNode = Integer.parseInt(st.nextToken());
            while (subNode != -1) {
                adList[mainNode].add(new Node(subNode, Integer.parseInt(st.nextToken())));
                subNode = Integer.parseInt(st.nextToken());
            }
        }
        ans = Integer.MIN_VALUE;
        // 임의의 노드: 1
        int tempNodeNum = 1;
        visited = new boolean[V + 1];
        visited[tempNodeNum] = true;
        DFS(tempNodeNum, 0);
        ans = 0;
        visited = new boolean[V + 1];
        visited[onePointOfDiameter] = true;
        DFS(onePointOfDiameter, 0);


        System.out.println(ans);
    }

    private static void DFS(int currNode, int diameter) {
        for (int i = 0; i < adList[currNode].size(); ++i) {
            Node nextNode = adList[currNode].get(i);
            if (visited[nextNode.getNodeNum()]) {
                continue;
            }
            if (ans < diameter + nextNode.getDistance()) {
                onePointOfDiameter = nextNode.getNodeNum();
                ans = diameter + nextNode.getDistance();
            }
            visited[nextNode.getNodeNum()] = true;
            DFS(nextNode.getNodeNum(), diameter + nextNode.getDistance());
        }
    }
}