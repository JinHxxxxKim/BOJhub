import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] elems;
    static int[] selectElems;
    static int N, M;
    static Set<String> set;
    
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        elems = new int[N];
        selectElems = new int[M];
        set = new HashSet<>();
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; ++idx) {
            elems[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(elems);
//        System.out.println(Arrays.toString(elems));
        comb(0, 0);
        System.out.println(sb);
    }
    // 1 7 9 9
    static void comb(int selectCnt, int selectIdx) {
//        System.out.println("selectCnt = " + selectCnt);
//        System.out.println("selectIdx = " + selectIdx);
//        System.out.println("==================");
        // 기저조건
        if (selectCnt == M) {

            StringBuilder tempSb = new StringBuilder();
            for (int selectElem : selectElems) {
                tempSb.append(selectElem).append(",");
            }
            String temp = tempSb.toString();
            if (!set.contains(temp)) {
                set.add(temp);
                for (int selectElem : selectElems) {
                    sb.append(selectElem).append(" ");
                }
                sb.append("\n");
            }
            return;
        }
        for (int idx = selectIdx; idx < N; ++idx) {
            selectElems[selectCnt] = elems[idx];
            comb(selectCnt + 1, idx);
        }
    }
}