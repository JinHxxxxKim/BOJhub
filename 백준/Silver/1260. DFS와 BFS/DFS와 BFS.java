import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;
    static int root;

    static ArrayList<Integer>[] adList;
    static boolean[] visited;

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        //== 입력 & 초기화 ==//
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());

        adList = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        for (int i = 1; i < V + 1; ++i) {
            adList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adList[node1].add(node2);
            adList[node2].add(node1);
        }
        for (int i = 1; i < V + 1; ++i) {
            Collections.sort(adList[i]);
        }
        sb = new StringBuilder();
        //== DFS ==//
        visited[root] = true;
        DFS(root);
        sb.append("\n");
        //== BFS ==//
        visited = new boolean[V + 1];
        visited[root] = true;
        BFS(root);
        //== 출력 ==//
        System.out.println(sb);
    }

    private static void BFS(int currNode) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(currNode);
        while (!q.isEmpty()) {
            int curr = q.poll();
            sb.append(curr);
            sb.append(" ");
            for (int i = 0; i < adList[curr].size(); ++i) {
                int nextNode = adList[curr].get(i);
                if (visited[nextNode]) {
                    continue;
                }
                visited[nextNode] = true;
                q.offer(nextNode);
            }
        }
    }

    private static void DFS(int currNode) {
        sb.append(currNode);
        sb.append(" ");
        for (int i = 0; i < adList[currNode].size(); ++i) {
            int nextNode = adList[currNode].get(i);
            if (visited[nextNode]) {
                continue;
            }
            visited[nextNode] = true;
            DFS(nextNode);
        }
    }
}
