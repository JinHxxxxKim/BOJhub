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
        sol(src, target);

        if (ans) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void sol(String src, String target) {
//        System.out.println("src = " + src);
        if (src.length() == target.length()) {
            if (src.equals(target)) {
                ans = true;
            }
            return;
        }
        if (!target.contains(src) && !target.contains(new StringBuilder(src).reverse())) {
            return;
        }

        sol(src + "A", target);
        sol(new StringBuilder(src).reverse() + "B", target);
    }
}
