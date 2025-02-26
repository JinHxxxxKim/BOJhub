import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        String numbers = br.readLine().trim();
        int idx = 0;
        int num = 1;
        while (idx < numbers.length()) {
            int tempIdx = idx;
            String chkNum = String.valueOf(num);
            int chkLen = chkNum.length();
            int containCnt = 0;

            for (int numIdx = 0; numIdx < chkLen; ++numIdx) {
                if (chkNum.charAt(numIdx) == numbers.charAt(tempIdx)) {
                    ++containCnt;
                    ++tempIdx;
                    if (tempIdx == numbers.length()) {
                        break;
                    }
                }
            }
            if (containCnt == 0) {
                ++num;
            } else {
                ++num;
                idx += containCnt;
            }
        }
        System.out.print(num - 1);
    }
}
