import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Set<Character> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        while (true) {
            String password = br.readLine().trim();
            if (password.equals("end")) {
                break;
            }

            boolean result1 = chk1(password);
            boolean result2 = chk2(password);
            boolean result3 = chk3(password);
            if(result1 && result2 && result3){
                sb.append(String.format("<%s> is acceptable.\n", password));
            }else{
                sb.append(String.format("<%s> is not acceptable.\n", password));
            }
        }
        System.out.print(sb);
    }

    // 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
    static boolean chk1(String password) {
        boolean result = false;
        for (int idx = 0; idx < password.length(); ++idx) {
            if(set.contains(password.charAt(idx))){
                result = true;
                break;
            }
        }
        return result;
    }

    // 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
    static boolean chk2(String password) {
        boolean result = true;
        int cntA = 0; // 모음
        int cntB = 0; // 자음
        for (int idx = 0; idx < password.length(); ++idx) {
            // 모음인 경우
            if(set.contains(password.charAt(idx))){
                cntB = 0;
                ++cntA;
                if(cntA == 3){
                    result = false;
                    break;
                }
            }
            // 자음인 경우
            else{
                cntA = 0;
                ++cntB;
                if(cntB == 3){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    // 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
    static boolean chk3(String password) {
        boolean result = true;
        for (int idx = 1; idx < password.length(); ++idx) {
            if(password.charAt(idx) != 'e' && password.charAt(idx) != 'o'){
                if(password.charAt(idx) == password.charAt(idx-1)){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
