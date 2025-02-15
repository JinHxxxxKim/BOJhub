import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E;
    static List<Integer>[] adList;
    static PriorityQueue<Integer> pq;
    static boolean[] isSolved;
    static boolean[] hasPreSolved;
    static int[] preSolvedCnt;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adList = new ArrayList[V + 1];
        isSolved = new boolean[V + 1];
        hasPreSolved = new boolean[V + 1];
        preSolvedCnt = new int[V + 1];
        for (int idx = 1; idx <= V; ++idx) {
            adList[idx] = new ArrayList<>();
        }
        for (int cnt = 0; cnt < E; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            adList[node1].add(node2);
            hasPreSolved[node2] = true;
            preSolvedCnt[node2]++;
        }

        // SOLUTION
        pq = new PriorityQueue<>();
        for (int node = 1; node <= V; ++node) {
            if (!hasPreSolved[node]) {
                pq.offer(node);
            }
        }

        while (!pq.isEmpty()) {
            int currProb = pq.poll();
            if (isSolved[currProb]) {
                continue;
            }
            sb.append(currProb).append(" ");
            isSolved[currProb] = true;
            for (int nextProb : adList[currProb]) {
                --preSolvedCnt[nextProb];
                if (!pq.contains(nextProb) && !isSolved[nextProb]) {
                    if (preSolvedCnt[nextProb] == 0) {
                        pq.offer(nextProb);
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
