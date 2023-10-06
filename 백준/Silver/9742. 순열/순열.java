import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    static char [] arr;
    static int R;
    static int [] select;
    static boolean [] isSelected;
    static int cnt;
    static int userCnt;
    static boolean isFind;
    static StringBuilder sb;

    public static void permutation(int r) {

        if(r == R) {
            userCnt++;
            if (userCnt == cnt && !isFind) {
                isFind = true;
                for (int i = 0; i < select.length; i++) {
                    sb.append(arr[select[i]]);
                }
                sb.append("\n");
            }
            return;
        }
        for(int i=0; i<arr.length; i++) {
            if(isSelected[i] == true) continue;
            select[r] = i;
            isSelected[i] = true;
            permutation(r+1);
            isSelected[i] = false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String input = "";
        while((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            String seq = st.nextToken();
            arr = new char[seq.length()];
            arr = seq.toCharArray();
            R = seq.length();
            cnt = Integer.parseInt(st.nextToken());
            userCnt = 0;
            select = new int[R];
            isSelected = new boolean[arr.length];
            isFind = false;
            sb.append(input + " = ");
            permutation(0);
            if (!isFind) {
                sb.append("No permutation\n");
            }
        }
        System.out.println(sb.toString());
    }
}