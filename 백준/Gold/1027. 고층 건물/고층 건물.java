import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] buildings;

    public static void main(String[] args) throws Exception{
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        buildings = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; ++idx) {
            buildings[idx] = Integer.parseInt(st.nextToken());
        }

        // SOLUTION
        int ans = Integer.MIN_VALUE;
        // 1번 건물부터 N번 건물까지 선택해가며 최대 건물을 관측할 수 있는 건물을 탐색한다.
        // idx: 현재 선택한 건물의 x좌표로 간주
        for (int idx = 0; idx < N; ++idx) {
            // 다른 건물들까지 관측 가능 여부를 확인한다.
            int visibleCnt = 0;
            for (int tempIdx = 0; tempIdx < N; ++tempIdx) {
                // 목표건물 관측 가능 여부
                boolean canWatch = true;
                // 현재 건물과 목표 건물까지의 기울기 계산
                double degree = (double) (buildings[tempIdx] - buildings[idx]) / (tempIdx - idx);
                // 현재 건물 기준 왼쪽
                if (idx > tempIdx) {
                    boolean isHigher = degree < 0;
                    for (int chkIdx = tempIdx + 1; chkIdx < idx; ++chkIdx) {
                        double chkDegree = (double) (buildings[chkIdx] - buildings[idx]) / (chkIdx - idx);
                        if (isHigher) {
                            if (chkDegree <= degree) {
                                canWatch = false;
                                break;
                            }
                        } else {
                            if (chkDegree <= degree) {
                                canWatch = false;
                                break;
                            }
                        }
                    }
                }
                else if (idx == tempIdx) {
                    continue;
                }
                // 현재 건물 기준 오른쪽
                else {
                    boolean isHigher = degree > 0;
                    for (int chkIdx = idx + 1; chkIdx < tempIdx; ++chkIdx) {
                        double chkDegree = (double) (buildings[chkIdx] - buildings[idx]) / (chkIdx - idx);
                        if (isHigher) {
                            if (chkDegree >= degree) {
                                canWatch = false;
                                break;
                            }
                        } else {
                            if (chkDegree >= degree) {
                                canWatch = false;
                                break;
                            }
                        }
                    }
                }
//                System.out.println("idx = " + idx);
//                System.out.println("tempIdx = " + tempIdx);
//                System.out.println("canWatch = " + canWatch);
//                System.out.println("==================");
                if (canWatch) {
                    ++visibleCnt;
                }
            }
            ans = Math.max(ans, visibleCnt);
        }
        System.out.print(ans);
    }
}

/**
 * N<=50
 * 15
 * 1 5 3 2 6 3 2 6 4 2 5 7 3 1 5
 *
 */