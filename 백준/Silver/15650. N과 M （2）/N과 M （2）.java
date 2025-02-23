import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] elements;
    static int[] selectElements;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        elements = new int[N];
        selectElements = new int[M];
        selected = new boolean[N];
        for (int idx = 0; idx < N; ++idx) {
            elements[idx] = idx + 1;
        }

        permutation(0, 0);
        System.out.print(sb);
    }

    private static void permutation(int selectCnt, int selIdx) {
        // 기저조건
        if (selectCnt == M) {
            for (int idx = 0; idx < M; ++idx) {
                sb.append(selectElements[idx]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int idx = selIdx; idx < N; ++idx) {
            if (selected[idx]) {
                continue;
            }
            // 전처리
            selected[idx] = true;
            selectElements[selectCnt] = elements[idx];
            
            permutation(selectCnt + 1, idx);
            // 후처리
            selected[idx] = false;
        }
    }

}
