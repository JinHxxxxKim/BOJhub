import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] tower = new int[N];

        Stack<int[]> stk = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            tower[i] = Integer.parseInt(st.nextToken());
            if (stk.isEmpty()) {
                sb.append("0 ");
                stk.push(new int[]{i, tower[i]});
            } else {
                while (!stk.isEmpty()) {
                    if (stk.peek()[1] > tower[i]) {
                        sb.append(stk.peek()[0]+1);
                        sb.append(" ");
                        stk.push(new int[]{i, tower[i]});
                        break;
                    } else {
                        stk.pop();
                    }
                }
                if (stk.isEmpty()) {
                    sb.append("0 ");
                    stk.push(new int[]{i, tower[i]});
                }
            }
        }
        System.out.println(sb);
    }
}
