import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < N; ++i) {
            stk.push(Integer.parseInt(br.readLine()));
        }
        int ans = 1;
        int maxHeight = stk.pop();
        while (!stk.isEmpty()) {
            int currHeight = stk.pop();
            if (currHeight > maxHeight) {
                maxHeight = currHeight;
                ans++;
            }
        }
        System.out.println(ans);
    }
}
