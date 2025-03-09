import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        Set<Integer> set = new HashSet<>();
        for (int idx = 0; idx < N; ++idx) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        for (Integer i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
