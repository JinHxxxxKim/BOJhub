import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int opCnt = 0; opCnt < N; ++opCnt) {
            int op = Integer.parseInt(br.readLine().trim());
            if (op == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(pq.poll()).append("\n");
                }
            } else {
                pq.offer(op);
            }
        }
        System.out.print(sb);
    }
}