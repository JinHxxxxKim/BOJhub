import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static Pos[] lineArray;

    public static void main(String[] args) throws Exception {
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        lineArray = new Pos[N];
        for (int idx = 0; idx < N; ++idx) {
            st = new StringTokenizer(br.readLine().trim());
            int pos1 = Integer.parseInt(st.nextToken());
            int pos2 = Integer.parseInt(st.nextToken());
            if (pos1 < pos2) {
                lineArray[idx] = new Pos(pos1, pos2);
            }else{
                lineArray[idx] = new Pos(pos2, pos1);
            }
        }
        // SOLUTION
        int sum = 0;
        int start = -1_000_000_001;
        int end = -1_000_000_001;
        Arrays.sort(lineArray);
        for (int idx = 0; idx < N; ++idx) {
            Pos currPos = lineArray[idx];
            if (currPos.start >= end) {
                sum += (end - start);
                start = currPos.start;
                end = currPos.end;
            }else{
                if (currPos.end > end) {
                    end = currPos.end;
                }
            }
        }
        sum += (end - start);
        System.out.println(sum);
    }

    static class Pos implements Comparable<Pos>{
        int start, end;

        public Pos(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pos o) {
            return this.start - o.start;
        }
    }
}
