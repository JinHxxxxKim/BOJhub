import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine().trim());
        while (TC-- > 0) {
            int row = Integer.parseInt(br.readLine().trim());
            Map<String, Set<String>> map = new HashMap<>();
            for (int cnt = 0; cnt < row; ++cnt) {
                st = new StringTokenizer(br.readLine().trim());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!map.containsKey(b)) {
                    Set<String> set = new HashSet<>();
                    set.add(a);
                    map.put(b, set);
                } else {
                    map.get(b).add(a);
                }
            }
            int total = 1;
            for (String key : map.keySet()) {
                total *= (map.get(key).size() + 1);
            }
            sb.append(total - 1).append("\n");
        }
        System.out.println(sb);
    }
}