import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        int[] height = new int[N];
        Arrays.fill(height, Integer.MAX_VALUE);

        for (int num = 1; num <= N; ++num) {
            int leftTallerCnt = Integer.parseInt(st.nextToken());
            int cnt = 0;
            int idx = 0;

            while (cnt < leftTallerCnt) {
                if (height[idx] > num) {
                    ++cnt;
                    ++idx;
                } else {
                    ++idx;
                }
            }


            if (height[idx] != Integer.MAX_VALUE) {
                while (height[idx] < num) {
                    ++idx;
                }
            }
            height[idx] = num;
        }
        for (int i : height) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
