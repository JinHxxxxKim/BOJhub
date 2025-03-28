import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int a = Integer.parseInt(br.readLine().trim());
        int b = Integer.parseInt(br.readLine().trim());
        int c = Integer.parseInt(br.readLine().trim());
        System.out.println(a+b-c);
        sb.append(a).append(b);
        System.out.println(Integer.parseInt(sb.toString())-c);
    }
}