import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int num = 0;
        int c = 0;
        for (int cnt = 0; cnt < 3; ++cnt) {
            String str = br.readLine().trim();
            if (str.equals("Fizz") || str.equals("Buzz") || str.equals("FizzBuzz")) {
                continue;
            }
            num = Integer.parseInt(str);
            c = cnt;
        }
        if (c == 0) {
            num = (num + 3);
        } else if (c == 1) {
            num = (num + 2);
        } else if (c == 2) {
            num = (num + 1);
        }

        if (num % 3 == 0 && num % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (num % 3 == 0 && num % 5 != 0) {
            System.out.println("Fizz");
        } else if (num % 3 != 0 && num % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(num);
        }
    }
}