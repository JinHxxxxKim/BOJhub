import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        int ans = 0;
        // Solution
        // Stack 에 있다 == 현재 해단 높이의 건물이 있다.
        Stack<Integer> stack = new Stack<>();
        for (int cnt = 0; cnt < N; ++cnt) {
            // STACK > 현재 고도 변화를 관찰
            st = new StringTokenizer(br.readLine().trim());
            int xPos = Integer.parseInt(st.nextToken());
            int yPos = Integer.parseInt(st.nextToken());

            // #0. 처음 건물을 발견한 경우
            if (stack.isEmpty() && yPos != 0) {
                stack.push(yPos);
            }
            if (!stack.isEmpty()) {
                // #1. Stack.peek() 보다 작을 경우
                //   하나의 최소 건물 발견 > Stack.pop() & ++ans (peek()이 크거나 같을 떄까지)
                if (stack.peek() > yPos) {
                    while (!stack.isEmpty() && stack.peek() > yPos) {
                        stack.pop();
                        ++ans;
                    }

                    if (stack.isEmpty() && yPos != 0) {
                        stack.push(yPos);
                    }

                    if (!stack.isEmpty() && stack.peek() != yPos) {
                        stack.push(yPos);
                    }
                }
                // #2. Stack.peek() 보다 클 경우
                //   새로운 건물 발견 > Stack.push()
                else if (stack.peek() < yPos) {
                    stack.push(yPos);
                }
            }
        }

        while (!stack.isEmpty()) {
            stack.pop();
            ++ans;
        }
        System.out.println(ans);
    }
}
