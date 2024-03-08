
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 각 레이저가 "생산"해 내는 막대기의 수를 구한다.
 * 2. 레이저가 아닌, 여는 괄호는 스택에 넣는다.
 * 3. 레이저를 만나면, 스택의 크기가 생산해내는 막대기의 수다.
 * 4. 닫는 괄호를 만나면, 하나의 막대기가 생성된 것이므로 +1 해준다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			// 막대기의 상태를 저장할 스택
			Stack<Character> stack = new Stack<>();
			ans = 0;
			
			// 상태를 입력받는다.
			String state = br.readLine().trim();

			for (int idx = 0; idx < state.length() - 1; ++idx) {
				
				char currChar = state.charAt(idx); // 현재 문자
				char nextChar = state.charAt(idx + 1); // 다음 문자
				
				// 레이저 판별
				if(currChar == '(' && nextChar == ')') {
					// 레이저인 경우: 막대기를 자르고, 인덱스 이동(다음 인덱스도 어차피 레이저)					
					ans += stack.size();
					++idx;
				}else if(currChar == '(' && nextChar != ')') {
					// 여는괄호의 연속인 경우
					stack.push(currChar);
				}else if(currChar == ')') {
					// 막대기가 끝났다면, 막대기 하나 생성
					++ans;
					stack.pop();
				}
			}
			// 남은 막대기 꼬다리 개수 카운팅
			ans+=stack.size();
			
			sb.append(String.format("#%d %d\n", testCase, ans));
		}
		System.out.println(sb);
	}
}
