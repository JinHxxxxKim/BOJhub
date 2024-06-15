import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [제곱ㄴㄴ수] - BOJ
 * 에레토스테네스의 체
 *
 *
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long min, max;
    static boolean[] flag; // 해당 수가 제곱수 인지 확인하는 flag

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        int size = (int) (max - min) + 1;
        flag = new boolean[size];

        // 최대 100만 순회
        for (int num = 2; Math.pow(num, 2) <= max; ++num) {
            long powNum = (long) Math.pow(num, 2); // 제곱수 src
            long startNum = (min / powNum);
            if(min % powNum != 0) startNum++;
            long chkNum = powNum * startNum;

            while (chkNum <= max) {
                int chkIdx = (int) (chkNum - min);
                flag[chkIdx] = true;
                
                // 곱하는 수, 확인하려는 수 갱신
                ++startNum;
                chkNum += powNum;
            }
        }

        int cnt = 0;
        for (boolean isAns : flag) {
            if (!isAns) {
                ++cnt;
            }
        }
        System.out.println(cnt);

    }
}
