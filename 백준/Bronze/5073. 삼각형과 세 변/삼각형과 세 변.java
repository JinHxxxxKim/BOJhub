import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        while(true){
            int a, b, c;
            st = new StringTokenizer(br.readLine().trim());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 0 && b == 0 && c == 0) {
                break;
            }

            int max = Math.max(Math.max(a, b), c);
            if (max >= a + b) {
                sb.append("Invalid").append("\n");
                continue;
            }
            if (a == b && b == c) {
                sb.append("Equilateral").append("\n");
                continue;
            }
            if (a == b || b == c || a == c) {
                sb.append("Isosceles").append("\n");
                continue;
            }
            sb.append("Scalene").append("\n");
        }
        System.out.println(sb);
    }
}
