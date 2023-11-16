import java.io.*;
import java.util.*;

class Solution {

    private static int ans;
    private static int[] array;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++) {
            ans = Integer.MIN_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            String numStr = st.nextToken();
            int changeCnt = Integer.parseInt(st.nextToken());

            array = new int[numStr.length()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = numStr.charAt(i) - '0';
            }

            if (changeCnt > array.length) {
                changeCnt = array.length;
            }
            dfs(changeCnt);

            sb.append("#");
            sb.append(test_case);
            sb.append(" ");
            sb.append(ans);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int changeCnt) {
        if (changeCnt == 0) {
            ans = Math.max(changeArrayToInt(array), ans);
            return;
        }
        for (int i = 0; i < array.length - 1; ++i) {
            for (int j = i + 1; j < array.length; ++j) {
                swap(i, j);
                dfs(changeCnt - 1);
                swap(i, j);
            }
        }
    }

    private static void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    private static int changeArrayToInt(int[] array) {
        int ret = 0;
        int idx = array.length;
        for (int i = 0; i < array.length; ++i) {
            ret += array[i] * (int)(Math.pow(10, (idx - 1)));
            --idx;
        }
        return ret;
    }
}