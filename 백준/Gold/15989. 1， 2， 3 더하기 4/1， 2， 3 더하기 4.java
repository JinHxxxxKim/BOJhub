import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 0; testCase < TC; ++testCase) {
            int N = Integer.parseInt(br.readLine().trim());
            int ans = 0;
            // 포함될 3의 개수 정하기 > 포함될 2의 개수 정하기
            // 1. 포함될 3의 개수 정하기
            int divThree = N / 3;
            for (int cnt = 0; cnt <= divThree; ++cnt) {
                int tempN = N - (cnt * 3);
                // 2. 포함될 2의 개수 정하기
                ans += (tempN / 2) + 1;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
