import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, d, k, c;
    static int[] array;
    static int[] isSelected;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        array = new int[n];
        isSelected = new int[d + 1];
        for (int idx = 0; idx < n; ++idx) {
            array[idx] = Integer.parseInt(br.readLine().trim());

        }
        // SOLUTION: 슬라이딩 윈도우
        int l = 0;
        int r = k;
        int ans = Integer.MIN_VALUE;
        int cnt = 0;
        for (int idx = 0; idx < k; ++idx) {
            if (isSelected[array[idx]] == 0) {
                ++cnt;
            }
            isSelected[array[idx]]++;

            if (isSelected[c] != 0) {
                ans = Math.max(ans, cnt);
            } else {
                ans = Math.max(ans, cnt + 1);
            }
        }

        // l포함, r피포함
        while (l < n) {
            // l빼고, r더하기
            isSelected[array[l]]--;
            if (isSelected[array[l]] == 0) {
                --cnt;
            }
            if (isSelected[array[r]] == 0) {
                ++cnt;
            }
            isSelected[array[r]]++;
//            System.out.println("l = " + l + " | " + "r = " + r);
//            System.out.println("cnt = " + cnt);

//            for (int idx = 0; idx < isSelected.length; ++idx) {
//                if (isSelected[idx] != 0) {
//                    System.out.print(idx + "(" + isSelected[idx] + ")" + " | ");
//                }
//            }
//            System.out.println();
//            System.out.println("isSelected[c] = " + isSelected[c]);
//            System.out.println("==============");
            // 보너스 쿠폰 계산
            if (isSelected[c] != 0) {
                ans = Math.max(ans, cnt);
            } else {
                ans = Math.max(ans, cnt + 1);
            }

            r = (r + 1) % n;
            ++l;
        }
        System.out.println(ans);
    }
}
