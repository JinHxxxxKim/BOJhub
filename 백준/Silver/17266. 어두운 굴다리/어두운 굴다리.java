import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] lightPos;

    public static void main(String[] args) throws Exception {
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        lightPos = new int[M];
        for (int idx = 0; idx < M; ++idx) {
            lightPos[idx] = Integer.parseInt(st.nextToken());
        }

        // SOLUTION
        // 최소 가로등 높이 계산
        int lightHeight = Math.max(lightPos[0] - 0, N - lightPos[M - 1]);
        if (M == 1) {
            System.out.print(lightHeight);
            return;
        }
        // 가로등 사이 간격을 통해 최소가로등 높이 갱신
        for (int idx = 0; idx < M - 1; ++idx) {
            int dist = lightPos[idx + 1] - lightPos[idx];
            // 갱신이 필요한 경우
            if (lightHeight * 2 < dist) {
                if (dist % 2 == 0) {
                    lightHeight = dist / 2;
                } else {
                    lightHeight = dist / 2 + 1;
                }
            }
        }
        System.out.println(lightHeight);
    }
}
