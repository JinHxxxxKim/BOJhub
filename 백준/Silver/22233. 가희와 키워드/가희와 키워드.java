import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Set<String> keywords = new HashSet<>();
        for (int cnt = 0; cnt < N; ++cnt) {
            String keyword = br.readLine().trim();
            keywords.add(keyword);
        }
        for (int cnt = 0; cnt < M; ++cnt) {
            st = new StringTokenizer(br.readLine().trim(), ",");
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                keywords.remove(word);
            }
            sb.append(keywords.size()).append("\n");
        }
        System.out.print(sb);
    }
}