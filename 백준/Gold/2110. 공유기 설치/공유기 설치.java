import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));

        // 두 공유기 사이 거리의 최대(right), 최소값(left)
        int right = array[N - 1] - array[0] + 1;
        int left = 1;
        int ans = left;

        while (left <= right) {
            int routerCnt = 1;
            int prevRouterPos = array[0];
            int mid = (right + left) / 2; // 공유기 간 최소 거리

            for (int i = 1; i < N; ++i) {
                if (array[i] - prevRouterPos >= mid) {
                    // i의 위치에 공유기 설치
                    // 이전 공유기 위치와의 거리 계산 후, ans 업데이트
                    prevRouterPos = array[i];
                    ++routerCnt;
                }
            }
            if (routerCnt < C) {
                // 최소거리가 너무 크다
                // 공유기가 너무 적게 설치
                right = mid - 1;
                continue;
            } else {
                // 최소거리가 너무 작다
                // 너무 많은 공유기가 설치
                left = mid + 1;
            }
            ans = Math.max(ans, mid);
        }
        System.out.println(ans);
    }
}
