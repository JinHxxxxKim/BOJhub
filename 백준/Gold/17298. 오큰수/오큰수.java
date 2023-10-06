import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        int[] ans = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        for (int i = N - 1; i >= 0; --i) {
            int currNum = array[i];
            if (stk.isEmpty()) {
                stk.push(currNum);
                ans[i] = -1;
            } else {
                while (!stk.isEmpty()) {
                    if (stk.peek() > currNum) {
                        ans[i] = stk.peek();
                        stk.push(currNum);
                        break;
                    } else {
                        stk.pop();
                    }
                }
                if (stk.isEmpty()) {
                    stk.push(currNum);
                    ans[i] = -1;
                }
            }
        }
        for (int i : ans) {
            sb.append(i);
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
