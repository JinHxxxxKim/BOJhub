import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] elems;
    static int[] sElems;

    public static void main(String[] args) throws Exception {
        while (true) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;
            }
            elems = new int[N];
            sElems = new int[6];

            for (int idx = 0; idx < N; ++idx) {
                elems[idx] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(elems);
            // comb
            comb(0, 0);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void comb(int selectedIdx, int elemIdx) {
        if (selectedIdx == 6) {
            for (int sElem : sElems) {
                sb.append(sElem).append(" ");
            }
            sb.append("\n");
            return;
        }
        if (elemIdx >= N) {
            return;
        }


        // 선택
        sElems[selectedIdx] = elems[elemIdx];
        comb(selectedIdx + 1, elemIdx + 1);

        // 미선택
        comb(selectedIdx, elemIdx + 1);
    }
}
