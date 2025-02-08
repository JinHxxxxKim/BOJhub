import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3 = 41
 * 4 = 32
 * 4141
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int kbs1Idx;
    static int kbs2Idx;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine().trim());
        for (int idx = 0; idx < N; ++idx) {
            String str = br.readLine().trim();
            if (str.equals("KBS1")) {
                kbs1Idx = idx;
            } else if (str.equals("KBS2")) {
                kbs2Idx = idx;
            }
        }

        for (int cnt = 0; cnt < kbs1Idx; ++cnt) {
            sb.append("1");
        }
        for (int cnt = 0; cnt < kbs1Idx; ++cnt) {
            sb.append("4");
        }

        if (kbs1Idx < kbs2Idx) {
            for (int cnt = 0; cnt < kbs2Idx; ++cnt) {
                sb.append("1");
            }
            for (int cnt = 0; cnt < kbs2Idx - 1; ++cnt) {
                sb.append("4");
            }
        }else{
            for (int cnt = 0; cnt < kbs2Idx + 1; ++cnt) {
                sb.append("1");
            }
            for (int cnt = 0; cnt < kbs2Idx; ++cnt) {
                sb.append("4");
            }
        }

        System.out.println(sb);
    }

}
