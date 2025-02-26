import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; ++idx) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        int[][] sumDP = new int[2][N];
        // 초기값 Setting
        int maxVal = numbers[0];
        for (int idx = 0; idx < N; ++idx) {
            sumDP[0][idx] = numbers[idx];
        }
        sumDP[1][0] = numbers[0];

        for (int idx = 1; idx < N; ++idx) {
            sumDP[1][idx] = numbers[idx];
            for (int tempIdx = 0; tempIdx < idx; ++tempIdx) {
                // 이전 값 보다 큰 경우(증가하는 경운)
                if (numbers[tempIdx] < numbers[idx]) {
                    sumDP[1][idx] = Math.max(sumDP[1][tempIdx] + numbers[idx], sumDP[1][idx]);
                }
            }

            maxVal = Math.max(sumDP[1][idx], maxVal);
        }
        System.out.print(maxVal);
    }
}
