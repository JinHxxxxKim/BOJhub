import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static String srcString;
    static String[] targetStrings;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        srcString = br.readLine().trim();
        int[] srcAlphaCnt = new int[26];
        for (int idx = 0; idx < srcString.length(); ++idx) {
            char currChar = srcString.charAt(idx);
            srcAlphaCnt[currChar - 'A']++;
        }

        targetStrings = new String[N - 1];
        for (int cnt = 0; cnt < N - 1; ++cnt) {
            targetStrings[cnt] = br.readLine().trim();
        }
        int ans = 0;

        // SOLUTION
        for (int idx = 0; idx < N - 1; ++idx) {
            String targetString = targetStrings[idx];
            int[] targetAlphaCnt = new int[26];
            for (int tIdx = 0; tIdx < targetString.length(); ++tIdx) {
                char currChar = targetString.charAt(tIdx);
                targetAlphaCnt[currChar - 'A']++;
            }

            int same = 0;
            for (int alpha = 0; alpha < 26; ++alpha) {
                same += Math.min(srcAlphaCnt[alpha], targetAlphaCnt[alpha]);
            }

            // 1. 두 문자열의 길이가 같을 경우 (같거나, 변경하거나)
            if (srcString.length() == targetString.length()) {
                if (same == srcString.length() || same == srcString.length() - 1) {
                    ++ans;
                }
            }
            // 2. src의 문자열의 길이가 길 경우 (src에서 하나 빼기)
            else if (srcString.length() == targetString.length() + 1) {
                if (same == srcString.length() - 1) {
                    ++ans;
                }
            }
            // 3. target의 문자열의 길이가 길 경우 (target에서 하나 빼기)
            else if (srcString.length() + 1 == targetString.length()) {
                if (same == targetString.length() - 1) {
                    ++ans;
                }
            }
        }
        System.out.println(ans);
    }
}
