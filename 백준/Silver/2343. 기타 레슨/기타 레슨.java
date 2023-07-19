

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] array = new int[N + 1];
        array[0] = 0;

        //==이분탐색사용 변수==//
        int left = -1;
        int right = 0;
        int mid = -1;
        //=================//

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            array[i] = Integer.parseInt(st.nextToken());
            left = Math.max(array[i], left);
            right += array[i];
        }
        mid = (left + right) / 2;
        int ans = mid;
        while (left <= right) {
            int blueRaySize = mid;
            int sum = 0;
            int blueRayCnt = 0;

            for (int i = 1; i <= N; ++i) {
                if ((sum + array[i]) > blueRaySize) {
                    sum = 0;
                    blueRayCnt++;
                }
                sum += array[i];
            }
            if (sum != 0) {
                blueRayCnt++;
            }

            if (blueRayCnt > M) {
                left = mid + 1;
            } else {
                ans = blueRaySize > ans ? ans : blueRaySize;
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }
        System.out.println(left);
    }
}
