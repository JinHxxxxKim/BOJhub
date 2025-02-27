import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int aLen, bLen;
    static int[] a, b;

    public static void main(String[] args) throws Exception {
        // INPUT
        aLen = Integer.parseInt(br.readLine().trim());
        a = new int[aLen];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < aLen; ++idx) {
            a[idx] = Integer.parseInt(st.nextToken());
        }

        bLen = Integer.parseInt(br.readLine().trim());
        b = new int[bLen];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < bLen; ++idx) {
            b[idx] = Integer.parseInt(st.nextToken());
        }

        // SOLUTION
        // 사전순으로 가장 뒤에 있는 수를 반복해서 찾아나간다.
        List<Integer> ans = new LinkedList<>();
        int aStartIdx = 0;
        int bStartIdx = 0;
        while (aStartIdx < aLen && bStartIdx < bLen) {
            int maxVal = getMaxVal(aStartIdx, bStartIdx);
            if (maxVal == -1) {
                break;
            }
            int[] idxs = getMaxValIdx(aStartIdx, bStartIdx, maxVal);
            int aMaxIdx = idxs[0];
            int bMaxIdx = idxs[1];
            ans.add(maxVal);
            aStartIdx = aMaxIdx + 1;
            bStartIdx = bMaxIdx + 1;
        }

        sb.append(ans.size()).append("\n");
        if (!ans.isEmpty()) {
            for (int num : ans) {
                sb.append(num).append(" ");
            }
        }
        System.out.println(sb);
    }

    // a, b의 부분배열 중 최대값 구하기
    static int getMaxVal(int aStartIdx, int bStartIdx) {
        int maxVal = -1;
        for (int aIdx = aStartIdx; aIdx < aLen; ++aIdx) {
            for (int bIdx = bStartIdx; bIdx < bLen; ++bIdx) {
                if (a[aIdx] == b[bIdx] && a[aIdx] > maxVal) {
                    maxVal = Math.max(maxVal, a[aIdx]);
                }
            }
        }
        return maxVal;
    }

    // a, b의 부분배열 중 최대값 인덱스 구하기
    static int[] getMaxValIdx(int aStartIdx, int bStartIdx, int val) {
        int[] ret = new int[2];
        for (int aIdx = aStartIdx; aIdx < aLen; ++aIdx) {
            if (a[aIdx] == val) {
                ret[0] = aIdx;
                break;
            }
        }
        for (int bIdx = bStartIdx; bIdx < bLen; ++bIdx) {
            if (b[bIdx] == val) {
                ret[1] = bIdx;
                break;
            }
        }
        return ret;
    }
}
