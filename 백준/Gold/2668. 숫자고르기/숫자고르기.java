import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] arr;
    static List<Integer>[] adList;
    static boolean[] visited;
    static Stack<Integer> stack;
    static int src;
    static int maxDist;
    static Set<Integer> set;
    static int ans;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        arr = new int[2][N + 1];
        for (int idx = 1; idx < N + 1; ++idx) {
            arr[0][idx] = idx;
            arr[1][idx] = Integer.parseInt(br.readLine().trim());
        }

        // 인접리스트 구성
        adList = new ArrayList[N + 1];
        for (int idx = 0; idx < N + 1; ++idx) {
            adList[idx] = new ArrayList<>();
        }

        for (int idx = 1; idx < N + 1; ++idx) {
            adList[arr[0][idx]].add(arr[1][idx]);
        }

        maxDist = Integer.MIN_VALUE;
        set = new HashSet<>();
        // 모든 노드를 시작점으로 하여 사이클의 여부를 판단한다
        for (int startNode = 1; startNode < N + 1; ++startNode) {
            src = startNode;
            visited = new boolean[N + 1];
            stack = new Stack<>();
            // 자기 자신으로 오는 노드는 제외
            if (arr[0][startNode] == arr[1][startNode]) {
                set.add(startNode);
                continue;
            }
            dfs(startNode, 1);
        }
        ans += set.size();
        sb.append(ans).append("\n");
        Integer[] temp = set.toArray(new Integer[0]);
        Arrays.sort(temp);

        for(int node:temp){
            sb.append(node).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int nodeNum, int dist) {
        stack.push(nodeNum);
        visited[nodeNum] = true;
        for (int nextNode : adList[nodeNum]) {
            if (visited[nextNode]) {
                if (src != nextNode) {
                    continue;
                } else {
                    // Cycle
                    maxDist = dist;
                    while (!stack.isEmpty()) {
                        set.add(stack.pop());
                    }
                }
            }
            dfs(nextNode, dist + 1);
        }
    }
}
