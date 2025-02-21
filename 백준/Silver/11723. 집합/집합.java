import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        Set<Integer> set = new HashSet<>();
        for (int cnt = 0; cnt < N; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            String op = st.nextToken();
            int num = 0;
            if (!op.equals("all") && !op.equals("empty")) {
                num = Integer.parseInt(st.nextToken());
            }


            switch(op) {
                case "add":
                    set.add(num);
                    break;
                case "remove":
                    set.remove(num);
                    break;
                case "check":
                    sb.append(set.contains(num) ? 1 : 0).append("\n");
                    break;
                case "toggle":
                    if (set.contains(num)) {
                        set.remove(num);
                    } else {
                        set.add(num);
                    }
                    break;
                case "all":
                    set.clear();
                    for (int temp = 1; temp <= 20; ++temp) {
                        set.add(temp);
                    }
                    break;
                case "empty":
                    set.clear();
            }
        }
        System.out.println(sb);
    }

}
