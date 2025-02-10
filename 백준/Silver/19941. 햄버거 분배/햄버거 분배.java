import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int ans;
    static String srcString;
    static boolean[] isAlreadyEat;

    static final char HAMBURGER = 'H';
    static final char PERSON = 'P';

    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = 0;
        srcString = br.readLine().trim();
        isAlreadyEat = new boolean[srcString.length()];

        // SOLUTION
        for (int idx = 0; idx < srcString.length(); ++idx) {
            int currChar = srcString.charAt(idx);
            // 1. 햄버거일 경우
            if (currChar == HAMBURGER) {
                continue;
            }
            // 2. 사람일 경우
            else {
                // 왼쪽 > 오른쪽으로 햄버거 확인
                for (int bIdx = idx - K; bIdx <= idx + K; ++bIdx) {
                    if(bIdx < 0) continue;
                    if(bIdx >= srcString.length()) continue;
                    if(srcString.charAt(bIdx) == PERSON) continue;
                    if(isAlreadyEat[bIdx]) continue;

                    isAlreadyEat[bIdx] = true;
                    ++ans;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}
