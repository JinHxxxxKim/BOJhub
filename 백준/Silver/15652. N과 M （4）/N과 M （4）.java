import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] elems;
    static int[] selectElems;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        elems = new int[N];
        for(int idx =0; idx < N; ++idx){
            elems[idx] = idx + 1;
        }
        selectElems = new int[M];

        // SOLUTION
        comb(0, 0);
        // OUTPUT
        System.out.println(sb);
    }

    private static void comb(int selectCnt, int selectIdx) {
        // 기저조건 1
        if (selectCnt == M) {
            for (int idx = 0; idx < M; ++idx) {
                sb.append(elems[selectElems[idx]]).append(" ");
            }
            sb.append("\n");
            return;
        }
        // 기저조건 2
        if (selectCnt > M) {
            return;
        }

        for (int idx = selectIdx; idx < N; ++idx) {
            selectElems[selectCnt] = idx;
            comb(selectCnt + 1, idx);
        }
    }
}
