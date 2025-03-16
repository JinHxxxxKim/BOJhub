import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> map = new HashMap<>();

        for (int cnt = 0; cnt < N; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            map.put(st.nextToken(), st.nextToken());
        }

        for (int cnt = 0; cnt < M; ++cnt) {
            String url = br.readLine().trim();
            sb.append(map.get(url)).append("\n");
        }
        System.out.println(sb);
    }
}
