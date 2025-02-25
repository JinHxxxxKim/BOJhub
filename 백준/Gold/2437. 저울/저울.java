import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        int totalWeight = 0;
        for (int idx = 0; idx < N; ++idx) {
            numbers[idx] = Integer.parseInt(st.nextToken());
            totalWeight += numbers[idx];
        }

        // SOLUTION
        Arrays.sort(numbers);
        int sum = 0;
        // 1 1 2 3 6 7 30
        for (int idx = 0; idx < N; ++idx) {
            int currWeight = numbers[idx];
            if (sum + 1 >= currWeight) {
                sum += currWeight;
            } else {
                break;
            }
        }

        System.out.println(sum + 1);
    }
}
