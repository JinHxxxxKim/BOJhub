import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int H, W, N, M;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // SOLUTION
        // 가로로 몇명이 배치될 수 있는가
        int horizontalMax = W / (M + 1);
        int horizontal = W % (M + 1) == 0 ? 0 : 1;

        // 세로로 몇명이 배치될 수 있는가
        int verticalMax = H / (N + 1);
        int vertical = H % (N + 1) == 0 ? 0 : 1;

        System.out.println((horizontalMax + horizontal) * (verticalMax + vertical));
    }
}
