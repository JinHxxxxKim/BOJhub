import java.util.*;
import java.io.*;

public class Main {
    /**
     * 1번연산: A 추가 (A삭제)
     * 2번연산: B 추가 후 뒤집기 (뒤집고 B삭제)
     *
     * A....A: 직전연산이 반드시 1번 > 1번 역연산(A 삭제)
     * A....B: 불가능
     * B....A:  1) 직전연산이 1번
     *          2) 직전연산이 2번
     *          > 분기처리
     * B....B: 직전연산이 반드시 2번 > 2번 역연산(뒤집고 B 삭제)
     *
     */

    static boolean canBuild = false;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String source = br.readLine().trim();
        String target = br.readLine().trim();

        // SOLUTION
        solution(source, target);

        if (canBuild) {
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    public static void solution(String src, String tgt) {
        if (src.length() == tgt.length()) {
            // 비교로직
            if (src.equals(tgt)) {
                canBuild = true;
            }
            return;
        }
        char front = tgt.charAt(0);
        char rear = tgt.charAt(tgt.length() - 1);
        if (front == 'A' && rear == 'A') {
            solution(src, reverseA(tgt));
        } else if (front == 'B' && rear == 'A') {
            solution(src, reverseA(tgt));
            solution(src, reverseB(tgt));
        } else if (front == 'B' && rear == 'B') {
            solution(src, reverseB(tgt));
        }
    }

    public static String reverseA(String src) {
        char[] srcArr = src.toCharArray();
        char[] ret = new char[src.length() - 1];
        for (int idx = 0; idx < ret.length; ++idx) {
            ret[idx] = srcArr[idx];
        }
        return new String(ret);
    }

    public static String reverseB(String src) {
        char[] srcArr = src.toCharArray();
        char[] ret = new char[src.length() - 1];
        int retIdx = 0;
        for (int idx = src.length() - 1; idx > 0; --idx) {
            ret[retIdx++] = srcArr[idx];
        }
        return new String(ret);
    }
}
