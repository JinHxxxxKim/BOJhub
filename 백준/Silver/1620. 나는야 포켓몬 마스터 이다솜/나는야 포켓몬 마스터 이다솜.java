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
        int id = 1;
        Map<String, Integer> stringMap = new HashMap<>();
        Map<Integer, String> integerMap = new HashMap<>();
        for (int cnt = 0; cnt < N; ++cnt) {
            String str = br.readLine().trim();
            stringMap.put(str, id);
            integerMap.put(id, str);
            ++id;
        }
        for (int cnt = 0; cnt < M; ++cnt) {
            String chkStr = br.readLine().trim();
            try {
                int currId = Integer.parseInt(chkStr);
                sb.append(integerMap.get(currId));
            } catch (Exception e) {
                sb.append(stringMap.get(chkStr));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}