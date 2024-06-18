import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [동전0] - BOJ
 * 그리디
 * 1.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int[] coinSize;
    static int coinCnt;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coinSize = new int[N];
        for (int idx = 0; idx < N; ++idx) {
            coinSize[idx] = Integer.parseInt(br.readLine().trim());
        }

        coinCnt = 0;

        for (int idx = N - 1; idx >= 0; --idx) {
            int currCoin = K / coinSize[idx];
            coinCnt += currCoin;
            K -= currCoin * coinSize[idx];
        }

        System.out.println(coinCnt);
    }
}
