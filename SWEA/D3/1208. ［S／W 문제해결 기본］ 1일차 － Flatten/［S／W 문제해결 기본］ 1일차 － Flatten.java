import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int[] height;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int test_case = 1; test_case <= 10; test_case++) {
            int ans = solution(br);
            sb.append("#");
            sb.append(test_case);
            sb.append(" ");
            sb.append(ans);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int solution(BufferedReader br) throws Exception{
        height = new int[100];
        int dumpCount = Integer.parseInt(br.readLine());
        System.out.println(dumpCount);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 100; ++i) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        while (dumpCount > 0) {
            dump();
            --dumpCount;
        }
        return getDiff();
    }

    private static int getDiff() {
        int maxHeight = Integer.MIN_VALUE;
        int minHeight = Integer.MAX_VALUE;

        for (int i = 0; i < 100; ++i) {
            if (maxHeight < height[i]) {
                maxHeight = height[i];
            }
        }

        for (int i = 0; i < 100; ++i) {
            if (minHeight > height[i]) {
                minHeight = height[i];
            }
        }
        return maxHeight - minHeight;
    }

    private static void dump() {
        int maxHeight = Integer.MIN_VALUE;
        int maxHeightIdx = -1;

        int minHeight = Integer.MAX_VALUE;
        int minHeightIdx = -1;

        for (int i = 0; i < 100; ++i) {
            if (maxHeight < height[i]) {
                maxHeight = height[i];
                maxHeightIdx = i;
            }
        }

        for (int i = 0; i < 100; ++i) {
            if (minHeight > height[i]) {
                minHeight = height[i];
                minHeightIdx = i;
            }
        }

        --height[maxHeightIdx];
        ++height[minHeightIdx];
    }
}
