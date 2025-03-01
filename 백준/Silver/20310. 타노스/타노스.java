import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static String srcString;
    static boolean[] isDelete;

    public static void main(String[] args) throws Exception {
        // INPUT
        srcString = br.readLine().trim();
        isDelete = new boolean[srcString.length()];

        // 0의 개수, 1의 개수 COUNT
        int zeroCnt = 0;
        int oneCnt = 0;
        for (int idx = 0; idx < srcString.length(); ++idx) {
            if (srcString.charAt(idx) == '0') {
                ++zeroCnt;
            } else {
                ++oneCnt;
            }
        }

        // 앞의 1 삭제
        int delOneCnt = 0;
        for (int idx = 0; idx < srcString.length(); ++idx) {
            if (srcString.charAt(idx) == '1') {
                isDelete[idx] = true;
                ++delOneCnt;
            }
            if(delOneCnt == oneCnt / 2){
                break;
            }
        }

        // 뒤의 0 삭제
        int delZeroCnt = 0;
        for (int idx = srcString.length() - 1; idx >= 0; --idx) {
            if (srcString.charAt(idx) == '0') {
                isDelete[idx] = true;
                ++delZeroCnt;
            }
            if(delZeroCnt == zeroCnt / 2){
                break;
            }
        }

        for(int idx = 0; idx < srcString.length(); ++idx){
            if(isDelete[idx]) continue;
            sb.append(srcString.charAt(idx));
        }

        // OUTPUT
        System.out.print(sb);
    }
}
