import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] buildingHeights, nearBuilding, buildingCnt;

    public static void main(String[] args) throws Exception {
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        buildingHeights = new int[N + 1];
        nearBuilding = new int[N + 1];
//        Arrays.fill(nearBuilding, Integer.MAX_VALUE);
        buildingCnt = new int[N + 1];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx <= N; ++idx) {
            buildingHeights[idx] = Integer.parseInt(st.nextToken());
        }

        // SOLUTION
        Stack<int[]> stack = new Stack<>(); // 0: 번호, 1: 높이
        // 바라보는 방향: 왼쪽
        for (int idx = 1; idx <= N; ++idx) {
            int currHeight = buildingHeights[idx];
            // 현재 건물에서 볼 수 없는 건물 삭제
            while (!stack.isEmpty() && stack.peek()[1] <= currHeight) {
                stack.pop();
            }
            buildingCnt[idx] = stack.size();
            if (!stack.isEmpty()) {
                nearBuilding[idx] = stack.peek()[0];
            }
            stack.push(new int[]{idx, currHeight});
        }
        // System.out.println(Arrays.toString(nearBuilding));
        // 바라보는 방향: 오른쪽
        stack.clear();
        for (int idx = N; idx > 0; --idx) {
            int currHeight = buildingHeights[idx];
            // 현재 건물에서 볼 수 없는 건물 삭제
            while (!stack.isEmpty() && stack.peek()[1] <= currHeight) {
                stack.pop();
            }
            buildingCnt[idx] += stack.size();
            if (!stack.isEmpty()) {
                if (nearBuilding[idx] == 0) {
                    nearBuilding[idx] = stack.peek()[0];
                } else {
                    // 거리계산
                    int originDist = idx - nearBuilding[idx];
                    int newDist = stack.peek()[0] - idx;
                    if (originDist > newDist) {
                        nearBuilding[idx] = stack.peek()[0];
                    }
                }
            }
            stack.push(new int[]{idx, currHeight});
        }

        for (int idx = 1; idx <= N; ++idx) {
            int ans = buildingCnt[idx];
            if (ans == 0) {
                sb.append(0).append("\n");
            } else {
                sb.append(ans).append(" ").append(nearBuilding[idx]).append("\n");
            }
        }
        System.out.println(sb);
    }
}
