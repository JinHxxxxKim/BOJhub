import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] lecture = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            lecture[i][0] = Integer.parseInt(st.nextToken());
            lecture[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(lecture, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                if (a1[1] == a2[1]) {
                    return a1[0] - a2[0];
                }
                return a1[1] - a2[1];
            }
        });
        int ans = 1;
        int currLectureEndTime = lecture[0][1];
        for (int i = 1; i < N; ++i) {
            if (lecture[i][0] < currLectureEndTime) {
                continue;
            }
            currLectureEndTime = lecture[i][1];
            ans++;
        }
        System.out.println(ans);
    }
}