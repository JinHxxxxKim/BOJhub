import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;

    static ArrayList<Integer>[] adList;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adList = new ArrayList[V + 1];

        for (int i = 1; i < V + 1; ++i) {
            adList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adList[node2].add(node1);
        }

        int[][] ans = new int[V + 1][2]; // [시작노드][최대거리]

        for (int root = 1; root <= V; ++root) {
            Queue<Integer> q = new LinkedList<>();
            q.offer(root);
            visited = new boolean[V + 1];
            int[] dist = new int[V + 1];

            visited[root] = true;
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[root] = 0;

            int cnt = 0;

            while (!q.isEmpty()) {
                int currNode = q.poll();
                cnt++;
                for (Integer nextNode : adList[currNode]) {
                    if (visited[nextNode]) {
                        continue;
                    }
                    visited[nextNode] = true;
                    q.offer(nextNode);
                }
            }

            ans[root][0] = root;
            ans[root][1] = cnt;
        }

        Arrays.sort(ans, (t1, t2) -> {
            if (t2[1] == t1[1]) {
                return t1[0] - t2[0];
            } else {
                return t2[1] - t1[1];
            }
        });
        int max = ans[0][1];
//        for (int i = 0; i < ans.length; ++i) {
//            System.out.println(Arrays.toString(ans[i]));
//        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; ++i) {
            if (max != ans[i][1]) {
                break;
            }
            sb.append(ans[i][0]);
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
