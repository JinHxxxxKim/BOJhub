import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static Info[] infos;

    static class Info {
        String name;
        int underBound, upperBound;

        public Info(String name, int underBound, int upperBound) {
            this.name = name;
            this.underBound = underBound;
            this.upperBound = upperBound;
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        infos = new Info[N];
        for (int idx = 0; idx < N; ++idx) {
            st = new StringTokenizer(br.readLine().trim());
            String name = st.nextToken();
            int upperBound = Integer.parseInt(st.nextToken());
            if (idx == 0) {
                infos[idx] = new Info(name, 0, upperBound);
            } else {
                infos[idx] = new Info(name, infos[idx - 1].upperBound + 1, upperBound);
            }

        }

        for (int cnt = 0; cnt < M; ++cnt) {
            int val = Integer.parseInt(br.readLine().trim());
            // 이분탐색
            int l = 0;
            int r = N - 1;
            while (l <= r) {
                int m = (l + r) / 2;
                // m의 범위에 있는지 확인
                Info midInfo = infos[m];
                if (val >= midInfo.underBound && val <= midInfo.upperBound) {
                    int tempUpperBound = midInfo.upperBound;
                    int ansIdx = m;
                    for (int idx = m; idx >= 0; --idx) {
                        if (infos[idx].upperBound == tempUpperBound) {
                            ansIdx = idx;
                        } else {
                            break;
                        }
                    }
                    sb.append(infos[ansIdx].name).append("\n");
                    break;
                } else if (val < midInfo.underBound) {
                    r = m - 1;
                } else if (val > midInfo.upperBound) {
                    l = m + 1;
                }
            }
        }

        System.out.print(sb);

    }
}