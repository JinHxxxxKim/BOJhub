import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int pointer1 = 0;
        int pointer2 = 0;
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15

        int ans = 0;
        int sum = 0;

        while (pointer2 <= N) {
            if (sum < N) {
                pointer2++;
                sum += pointer2;
            } else if (sum == N) {
                ans++;
                pointer2++;
                sum += pointer2;
            } else {
                pointer1++;
                sum -= pointer1;
            }
        }
        System.out.println(ans);
    }
}
