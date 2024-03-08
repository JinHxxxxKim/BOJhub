
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 두가지 종류의 입력이 있다.
 * 	- 0: -> stack에서 숫자 하나를 뺀다.(stack이 비지 않았을 경우에만) 
 * 	- 0이 아닌 수: -> stack에 해당 수를 push
 * 2. K만큼 반복하고, stack에 있는 모든 수를 더한다
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			int K = Integer.parseInt(br.readLine().trim());
			// 수를 저장할 스택
			Stack<Integer> stack = new Stack<>();
			for (int cnt = 0; cnt < K; ++cnt) {
				int currNum = Integer.parseInt(br.readLine().trim());

				// 0일 경우
				if (currNum == 0) {
					if (!stack.isEmpty())
						stack.pop();
				}
				// 0이 아닐 경우
				else
					stack.push(currNum);
			}

			// 최종적인 수 확인
			int ans = 0;
			while (!stack.isEmpty()) {
				ans += stack.pop();
			}
			 sb.append(String.format("#%d %d\n", testCase, ans));
		}
		System.out.println(sb);
	}
}
