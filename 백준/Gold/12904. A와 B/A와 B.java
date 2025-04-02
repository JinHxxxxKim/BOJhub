import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static boolean ans;

    public static void main(String[] args) throws Exception {
        String src = br.readLine().trim();
        String target = br.readLine().trim();
        ans = false;
        sb = new StringBuilder(target);

        while (sb.length() > src.length()) {
            if (sb.toString().charAt(sb.length() - 1) == 'A') {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.deleteCharAt(sb.length() - 1);
                sb.reverse();
            }
        }

        if (sb.toString().equals(src)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }
}
