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
        int[] array = new int[N];
        st = new StringTokenizer(br.readLine());
        int lowerBound = Integer.MIN_VALUE;
        int upperBound = 0;
        for (int i = 0; i < N; ++i) {
            array[i] = Integer.parseInt(st.nextToken());
            upperBound += array[i];
            lowerBound = Math.max(lowerBound, array[i]);
        }
        //System.out.println(Arrays.toString(array));

        int ans = Integer.MAX_VALUE;

        while (lowerBound <= upperBound) {
            int mid = (lowerBound + upperBound) / 2; // 블루레이 크기
//            System.out.println("=================");
//            System.out.println("lowerBound = " + lowerBound);
//            System.out.println("mid = " + mid);
//            System.out.println("upperBound = " + upperBound);
            int sum = 0;
            int blueRayCnt =0;

            for (int i = 0; i < N; ++i) {
                if (array[i] + sum > mid) {
                    sum = 0;
                    blueRayCnt++;
                }
                sum += array[i];
            }
            if (sum != 0) {
                blueRayCnt++;
            }

//            System.out.println("++++++++++++++++++++");
//            System.out.println("blueRayCnt = " + blueRayCnt);
            if (blueRayCnt > M) {
                lowerBound = mid + 1;
                continue;
            } else {
                upperBound = mid - 1;
            }
            ans = Math.min(ans, mid);

        }
        System.out.println(ans);
    }
}
