import java.io.*;
import java.util.*;

class Solution{
    private static int[] buildings;
    
	public static void main(String args[]) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int test_case = 1; test_case <= 10; test_case++){
            int answer = solution(br);
            sb.append("#");
            sb.append(test_case);
            sb.append(" ");
            sb.append(answer);
            sb.append("\n");
		}
        System.out.println(sb);
	}
    private static int solution(BufferedReader br) throws Exception{
        int N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
        int sum = 0;
        for (int i = 0; i < N; ++i) {
            int temp = getAnswer(i);
            sum += temp;
        }
        return sum;
    }

    private static int getAnswer(int currBuilderIndex) {
        if (buildings[currBuilderIndex] == 0) {
            return 0;
        }
        int maxHeight = Integer.MIN_VALUE;
        for (int chkIdx = currBuilderIndex - 2; chkIdx <= currBuilderIndex + 2; ++chkIdx) {
            if (chkIdx == currBuilderIndex) {
                continue;
            }
            maxHeight = Math.max(buildings[chkIdx], maxHeight);
        }
        if (maxHeight >= buildings[currBuilderIndex]) {
            return 0;
        }
        return buildings[currBuilderIndex] - maxHeight;
    }
}