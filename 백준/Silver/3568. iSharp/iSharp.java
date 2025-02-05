import java.io.*;
import java.util.*;

// 첫 공백까지 구분
// ,공백 구분
// ; 만나면 끝
public class Main {
    static final char POINTER = '*';
    static final char REF = '&';
    static final char ARR_OPEN = '[';
    static final char ARR_CLOSE = ']';

    static final char BLANK = ' ';
    static final char COMMA = ',';
    static final char SEMI_COLON = ';';


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static String str;
    static String defaultStr;

    public static void main(String[] args) throws Exception {
        str = br.readLine().trim();
        st = new StringTokenizer(str, " ");
        boolean isFirst = true;
        while (st.hasMoreTokens()) {
            if (isFirst) {
                defaultStr = st.nextToken();
                isFirst = false;
                continue;
            }
            String currString = st.nextToken();
            sb.append(defaultStr);
            StringBuilder tempSb = new StringBuilder();
            for (int idx = currString.length()-1; idx >= 0; --idx) {
                switch(currString.charAt(idx)) {
                    case SEMI_COLON:
                        break;
                    case COMMA:
                        break;
                    case POINTER:
                        sb.append(POINTER);
                        break;
                    case REF:
                        sb.append(REF);
                        break;
                    case ARR_OPEN:
                        sb.append(ARR_CLOSE);
                        break;
                    case ARR_CLOSE:
                        sb.append(ARR_OPEN);
                        break;
                    default:
                        tempSb.append(currString.charAt(idx));
                }
            }
            sb.append(" ");
            sb.append(tempSb.reverse().toString());
            sb.append(";\n");
        }
        System.out.println(sb);
    }
}
