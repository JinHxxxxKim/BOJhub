import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int S;
    static int[] arr;
    static int ans;
    static boolean[] isSelected;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        isSelected = new boolean[N];
        ans = 0;

        subset(0);
        System.out.println(ans);
    }

    private static void subset(int num) {
        if (num == N) {
            int sum = 0;
            int falseCnt = 0;
            for (int i = 0; i < N; ++i) {
                if (isSelected[i]) {
                    sum += arr[i];
                } else {
                    falseCnt++;
                }
            }
            if (sum == S && falseCnt != N) {
                ans++;
            }
            return;
        }

        isSelected[num] = true;
        subset(num + 1);

        isSelected[num] = false;
        subset(num + 1);
    }
}
