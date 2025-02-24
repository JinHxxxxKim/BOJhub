import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, X;
    static int[] visitors;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        visitors = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; ++idx) {
            visitors[idx] = Integer.parseInt(st.nextToken());
        }

        // SOLUTION
        // 최대 방문자수 구하기
        int maxCnt = 0;
        for (int idx = 0; idx < X; ++idx) {
            maxCnt += visitors[idx];
        }
        int tempSum = maxCnt;
        int l = 0; // 뺄 방문자 일자 index
        int r = X; // 더할 방문자 일자 index
        while (r < N) {
            tempSum -= visitors[l];
            tempSum += visitors[r];
            maxCnt = Math.max(maxCnt, tempSum);
            ++l;
            ++r;
        }

        // 최대 방문자 수가 0일 경우
        if (maxCnt == 0) {
            System.out.print("SAD");
            return;
        }

        // 최대 방문자 수가 0이 아닐 경우
        int ans = 0;
        tempSum = 0;
        for (int idx = 0; idx < X; ++idx) {
            tempSum += visitors[idx];
        }
        if (tempSum == maxCnt) {
            ++ans;
        }
        l = 0; // 뺄 방문자 일자 index
        r = X; // 더할 방문자 일자 index
        while (r < N) {
            tempSum -= visitors[l];
            tempSum += visitors[r];
            if (tempSum == maxCnt) {
                ++ans;
            }
            ++l;
            ++r;
        }
        System.out.println(maxCnt);
        System.out.println(ans);
    }
}
