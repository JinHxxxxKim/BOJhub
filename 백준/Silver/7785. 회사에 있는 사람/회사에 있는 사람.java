import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        Set<String> set = new HashSet<>();
        for (int cnt = 0; cnt < N; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            String name = st.nextToken();
            String op = st.nextToken();
            if (op.equals("enter")) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }
        List<String> list = new ArrayList<>(set);
        Collections.sort(list, Collections.reverseOrder());
        for (String string : list) {
            sb.append(string).append("\n");
        }
        System.out.println(sb);

    }
}
