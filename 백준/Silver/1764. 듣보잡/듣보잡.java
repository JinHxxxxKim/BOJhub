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

        Set<String> dSet = new HashSet<>();
        Set<String> dbSet = new HashSet<>();

        for (int cnt = 0; cnt < N; ++cnt) {
            String name = br.readLine().trim();
            dSet.add(name);
        }

        for (int cnt = 0; cnt < M; ++cnt) {
            String name = br.readLine().trim();
            if (dSet.contains(name)) {
                dbSet.add(name);
            }
        }
        sb.append(dbSet.size()).append("\n");;
        List<String> list = new ArrayList<>(dbSet);
        Collections.sort(list);
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}