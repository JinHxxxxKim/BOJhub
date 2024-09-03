import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 완전탐색 - 순열
 */
public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static int N;
    private static int[] nums;
    private static int[] elements;
    private static boolean[] isSelected;
    private static int[] selectElems;
    private static int[] opCnt = new int[4];

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        // 변수 초기화
        N = Integer.parseInt(br.readLine().trim());
        nums = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        elements = new int[N - 1];
        isSelected = new boolean[N - 1];
        selectElems = new int[N - 1];
        int idx = 0;
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < 4; i++) {
            opCnt[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < opCnt[i]; ++j) {
                elements[idx++] = i;
            }
        }

        // 로직 시작
        permutation(0);
        System.out.println(max);
        System.out.println(min);
    }

    private static void permutation(int currIdx) {
        if (currIdx == N - 1) {
            int result = nums[0];
            for (int idx = 0; idx < N - 1; ++idx) {
                switch (selectElems[idx]) {
                    case 0:
                        result += nums[idx + 1];
                        break;
                    case 1:
                        result -= nums[idx + 1];
                        break;
                    case 2:
                        result *= nums[idx + 1];
                        break;
                    case 3:
                        result /= nums[idx + 1];
                        break;
                }
            }
            max = Math.max(result, max);
            min = Math.min(result, min);
            return;
        }
        for (int idx = 0; idx < N - 1; idx++) {
            if(isSelected[idx]){ continue;}

            isSelected[idx] = true;
            selectElems[currIdx] = elements[idx];
            permutation(currIdx + 1);
            isSelected[idx] = false;
        }
    }
}
