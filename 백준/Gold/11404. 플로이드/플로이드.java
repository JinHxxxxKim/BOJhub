import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        int M = Integer.parseInt(br.readLine().trim());
        int[][] adMatrix = new int[N + 1][N + 1];
        for (int row = 1; row < N + 1; ++row) {
            for (int col = 1; col < N + 1; ++col) {
                adMatrix[row][col] = Integer.MAX_VALUE;
            }
            adMatrix[row][row] = 0;
        }

        for (int cnt = 0; cnt < M; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adMatrix[src][dest] = Math.min(cost, adMatrix[src][dest]);
        }

        // 플로이드 와샬
        // #1. 경유지
        for(int viaNode = 1; viaNode < N + 1; ++viaNode){
            // #2 모든 간선을
            for (int srcNode = 1; srcNode < N + 1; ++srcNode) {
                for (int destNode = 1; destNode < N + 1; ++destNode) {
                    if (adMatrix[srcNode][viaNode] == Integer.MAX_VALUE || adMatrix[viaNode][destNode] == Integer.MAX_VALUE) {
                        continue;
                    }
                    adMatrix[srcNode][destNode] = Math.min(adMatrix[srcNode][destNode], adMatrix[srcNode][viaNode] + adMatrix[viaNode][destNode]);
                }
            }
        }

        for (int row = 1; row < N + 1; ++row) {
            for (int col = 1; col < N + 1; ++col) {
                if (adMatrix[row][col] == Integer.MAX_VALUE) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(adMatrix[row][col]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}