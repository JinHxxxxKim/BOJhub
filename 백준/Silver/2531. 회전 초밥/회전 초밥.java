import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, d, k, c;
    static int[] array;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        array = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            array[idx] = Integer.parseInt(br.readLine().trim());

        }
        // SOLUTION: 슬라이딩 윈도우
        int l = 0;
        int r = k;
        int ans = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int idx = 0; idx < k; ++idx) {
            set.add(array[idx]);
        }
        // l포함, r피포함
        while (l < n) {
//            System.out.println("l = " + l + " | " + "r = " + r);
//            System.out.println("set = " + set);
            // 보너스 쿠폰 계산
            if (set.contains(c)) {
                ans = Math.max(ans, set.size());
            } else {
                ans = Math.max(ans, set.size() + 1);
            }

            r = (r + 1) % n;
            ++l;
            set.clear();
            for (int cnt = 0; cnt < k; ++cnt) {
                set.add(array[(l + cnt) % n]);
            }

        }
        System.out.println(ans);
    }
}