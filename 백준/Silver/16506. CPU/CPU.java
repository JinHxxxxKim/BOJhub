import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static final String[] opCd = {"ADD", "SUB", "MOV", "AND", "OR", "NOT", "MULT", "LSFTL", "LSFTR", "ASFTR", "RL", "RR"};

    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine().trim());

        for (int cnt = 0; cnt < N; ++cnt) {
            int ans = 0;
            st = new StringTokenizer(br.readLine().trim());
            String op = st.nextToken();
            int rD = Integer.parseInt(st.nextToken());
            int rA = Integer.parseInt(st.nextToken());
            int rBrC = Integer.parseInt(st.nextToken());
            boolean flag = false;

            for (int idx = 0; idx < opCd.length ; ++idx) {
                if (op.contains(opCd[idx])) {
                    sb.append(makeString(4, Integer.toBinaryString(idx)));
                    if (op.length() > opCd[idx].length()) {
                        sb.append(makeString(1, Integer.toBinaryString(1)));
                        flag = true;
                    }else{
                        sb.append(makeString(1, Integer.toBinaryString(0)));
                    }
                }
            }
            sb.append(makeString(1, Integer.toBinaryString(0)));
            sb.append(makeString(3, Integer.toBinaryString(rD)));
            if (op.equals("NOT") || op.contains("MOV")) {
                sb.append(makeString(3, Integer.toBinaryString(0)));
            }else{
                sb.append(makeString(3, Integer.toBinaryString(rA)));
            }
            if (flag) {
                sb.append(makeString(4, Integer.toBinaryString(rBrC)));
            }else{
                sb.append(makeString(3, Integer.toBinaryString(rBrC)));
                sb.append(makeString(1, Integer.toBinaryString(0)));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static String makeString(int targetLen, String srcString) {
        StringBuilder tempSb = new StringBuilder();
        if (srcString.length() < targetLen) {
            int needZero = targetLen - srcString.length();
            for (int cnt = 0; cnt < needZero; ++cnt) {
                tempSb.append("0");
            }
        } else if (srcString.length() > targetLen) {
            return srcString.subSequence(srcString.length() - targetLen,
                    srcString.length())
                    .toString();
        } else {
            return srcString;
        }
        return tempSb.append(srcString).toString();
    }
}
