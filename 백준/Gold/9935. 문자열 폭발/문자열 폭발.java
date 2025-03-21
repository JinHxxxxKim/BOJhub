import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        String srcString = br.readLine().trim();
        String trgString = br.readLine().trim();
        Character lastChar = trgString.charAt(trgString.length() - 1); // 마지막 문자
        Stack<Character> stack = new Stack<>();

        for (int idx = 0; idx < srcString.length(); ++idx) {
            char currChar = srcString.charAt(idx);
            stack.push(currChar);
            // 폭발문자열 비교
            if (currChar == lastChar) {
                // 비교 가능
                // 비교 범위: stack.size() - trgString.length() ~ stack.size() - 1
                if (stack.size() >= trgString.length()) {
                    boolean boom = true;
                    int trgIdx = 0;
                    for (int chkIdx = stack.size() - trgString.length(); chkIdx < stack.size(); ++chkIdx) {
                        if (stack.get(chkIdx) != trgString.charAt(trgIdx++)) {
                            boom = false;
                            break;
                        }
                    }
                    // 폭발
                    if (boom) {
                        for (int cnt = 0; cnt < trgString.length() ; ++cnt) {
                            stack.pop();
                        }
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            System.out.println(sb.reverse().toString());
        }

    }
}