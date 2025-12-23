import java.io.*;
import java.util.*;

public class Main {

    private static final String sep = " => ";

    private static int N;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static List<Integer>[] adList;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        adList = new List[123];
        for (int idx = 0; idx < 123; ++idx) {
            adList[idx] = new ArrayList<>();
        }
        for (int cnt = 0; cnt < N; ++cnt) {
            st = new StringTokenizer(br.readLine().trim(), sep);
            int srcNode = st.nextToken().charAt(0);
            int destNode = st.nextToken().charAt(0);
            adList[srcNode].add(destNode);
        }

        int totalCnt = 0;
        List<Proposition> ans = new LinkedList<>();
        // A -> Z
        for (int node = 'A'; node <= 'Z'; ++node) {
            boolean[] visited = new boolean[123];
            visited[node] = true;
            Queue<Integer> q = new LinkedList<>();
            q.offer(node);
            while (!q.isEmpty()) {
                int currNode = q.poll();
                if (currNode != node) {
                    ans.add(new Proposition(node, currNode));
                }
                // 인접 노드 탐색
                for (int nextNode : adList[currNode]) {
                    if (!visited[nextNode]) {
                        visited[nextNode] = true;
                        q.offer(nextNode);
                    }
                }
            }
        }
        totalCnt += ans.size();
        Collections.sort(ans);
        ans.forEach(item -> sb.append(item).append("\n"));
        ans = new LinkedList<>();
        // a -> z
        for (int node = 'a'; node <= 'z'; ++node) {
            boolean[] visited = new boolean[123];
            visited[node] = true;
            Queue<Integer> q = new LinkedList<>();
            q.offer(node);
            while (!q.isEmpty()) {
                int currNode = q.poll();
                if (currNode != node) {
                    ans.add(new Proposition(node, currNode));
                }
                // 인접 노드 탐색
                for (int nextNode : adList[currNode]) {
                    if (!visited[nextNode]) {
                        visited[nextNode] = true;
                        q.offer(nextNode);
                    }
                }
            }
        }
        totalCnt += ans.size();
        Collections.sort(ans);
        ans.forEach(item -> sb.append(item).append("\n"));
        System.out.println(totalCnt);
        System.out.println(sb);
    }

    static class Proposition implements Comparable<Proposition>{
        int src, dest;
        public Proposition(int src, int dest){
            this.src = src;
            this.dest = dest;
        }

        @Override
        public int compareTo(Proposition o) {
            if (src == o.src) {
                return this.dest - o.dest;
            } else {
                return this.src - o.src;
            }
        }

        @Override
        public String toString(){
            return (char) this.src + sep + (char) this.dest;
        }
    }
}
