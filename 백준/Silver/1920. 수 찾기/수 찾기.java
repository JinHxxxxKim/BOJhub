import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] array;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        int[] testCase = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            testCase[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);
        sb = new StringBuilder();
        for (int i = 0; i < M; ++i) {
            binSearch(testCase[i]);
        }
        System.out.println(sb);
    }

    private static void binSearch(int targetNum) {
        int frontIdx = 0;
        int rearIdx = array.length - 1;
        int midIdx = (frontIdx + rearIdx) / 2;

        while (frontIdx <= rearIdx) {
            if (array[midIdx] == targetNum) {
                sb.append(1);
                sb.append("\n");
                return;
            } else if (array[midIdx] > targetNum) {
                rearIdx = midIdx - 1;
            } else {
                frontIdx = midIdx + 1;
            }
            midIdx = (frontIdx + rearIdx) / 2;
        }
        sb.append(0);
        sb.append("\n");
    }
}