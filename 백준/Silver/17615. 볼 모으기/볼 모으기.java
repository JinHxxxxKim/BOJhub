import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final char RED = 'R';
    static final char BLUE = 'B';

    static int N, ans;
    static char[] array;

    public static void main(String[] args) throws Exception {
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        array = br.readLine().toCharArray();

        //SOLUTION
        // 어떤 공을 움직일 것인가? (RED, BLUE 모두 실행)
        // 해당 공을 오른쪽, 왼쪽 어디로 옮길 것인가? (왼쪽 오른쪽 모두 실행)
        int redCnt = 0;
        int blueCnt = 0;
        for (int idx = 0; idx < N; ++idx) {
            if (array[idx] == BLUE) {
                ++blueCnt;
            } else {
                ++redCnt;
            }
        }
        // 모두 빨간 공 혹은 파란 공일 경우
        if (redCnt == N || blueCnt == N) {
            System.out.println(0);
            return;
        }
        int ans = Integer.MAX_VALUE;
        // 파란 공 이동
        // 파란공 왼쪽으로 이동: 가장 왼쪽의 빨간 공 전까지 파란공의 개수 탐색
        int cnt = 0;
        for (int idx = 0; idx < N; ++idx) {
            if (array[idx] == RED) {
                break;
            }
            ++cnt;
        }
        ans = Math.min(ans, blueCnt - cnt);

        // 파란공 오른쪽으로 이동: 가장 오른쪽의 빨간 공 탐색
        cnt = 0;
        for (int idx = N-1; idx >= 0; --idx) {
            if (array[idx] == RED) {
                break;
            }
            ++cnt;
        }
        ans = Math.min(ans, blueCnt - cnt);
        // 빨간 공 이동
        // 빨간공 왼쪽으로 이동: 가장 왼쪽의 파란 공 전까지 빨간공의 개수 탐색
        cnt = 0;
        for (int idx = 0; idx < N; ++idx) {
            if (array[idx] == BLUE) {
                break;
            }
            ++cnt;
        }
        ans = Math.min(ans, redCnt - cnt);

        cnt = 0;
        for (int idx = N - 1; idx >= 0; --idx) {
            if (array[idx] == BLUE) {
                break;
            }
            ++cnt;
        }
        ans = Math.min(ans, redCnt - cnt);

        System.out.println(ans);
    }
}
