import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] matrix;
    static int[] selectedElems;
    static int N;
    static int minDiff;

    public static void main(String[] args) throws Exception {
        // 입력
        N = Integer.parseInt(br.readLine().trim());
        selectedElems = new int[N / 2];
        matrix = new int[N][N];
        minDiff = Integer.MAX_VALUE;
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; col++) {
                matrix[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        // 로직 (조합: 20 Comb 10)
        comb(0, 0);
        System.out.println(minDiff);
    }

    private static void comb(int selected, int start) {
        if (selected == N / 2) {
            // 종료조건
            boolean[] teams = new boolean[N];
            for (int selectedElem : selectedElems) {
                teams[selectedElem] = true;
            }
            // System.out.println("Arrays.toString(teams) = " + Arrays.toString(teams));
            // teamA: false
            int teamA = 0;
            for (int idx = 0; idx < N; ++idx) {
                if(teams[idx])
                    continue;
                for(int tempIdx = 0; tempIdx < N; ++tempIdx) {
                    if (idx == tempIdx || teams[idx] != teams[tempIdx]) {
                        continue;
                    }
                    teamA += matrix[idx][tempIdx];
                }
            }

            // teamB: true
            int teamB = 0;
            for (int idx = 0; idx < N; ++idx) {
                if(!teams[idx])
                    continue;
                for(int tempIdx = 0; tempIdx < N; ++tempIdx) {
                    if (idx == tempIdx|| teams[idx] != teams[tempIdx]) {
                        continue;
                    }
                    teamB += matrix[idx][tempIdx];
                }
            }
            // System.out.println("Arrays.toString(selectedElems) = " + Arrays.toString(selectedElems));
            // System.out.println("teamA = " + teamA);
            // System.out.println("teamB = " + teamB);
            // System.out.println("==============");
            minDiff = Math.min(minDiff, Math.abs(teamA - teamB));
            return;
        }


        for (int idx = start; idx < N; ++idx) {
            // 선택 O
            selectedElems[selected] = idx;
            comb(selected + 1, idx + 1);
        }
    }
}
