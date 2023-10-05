import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] sum = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                sum[i] = 0;
            } else {
                sum[i] = sum[i - 1] + arr[i - 1]; // 누적합
            }
        }
        sum[N] = sum[N - 1] + arr[N - 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken());

            sb.append(sum[end] - sum[start]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
