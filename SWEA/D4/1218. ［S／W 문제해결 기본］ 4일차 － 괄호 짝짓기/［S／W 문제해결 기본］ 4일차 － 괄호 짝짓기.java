
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 들어오는 경우의 수
 * 		- 여는 괄호 종류 -> stack에 추가한다.
 * 		- 닫는 괄호 종류 -> stack.peek을 하고 짝이 맞지 않으면 0 출력 후 종료
 * 2. 모든 문자를 탐색하고, 스택이 비어있다면 1 출력
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; ++testCase) {
			br.readLine();
			String bracketInfo = br.readLine().trim();
			
			// 여는 괄호를 저장할 스택
			Stack<Character> stack = new Stack<>();
			
			boolean flag = true;
			
			// '()', '[]', '{}', '<>' 
			for(int idx = 0; idx<bracketInfo.length();++idx) {
				char currChar = bracketInfo.charAt(idx);
				if(currChar == '(' || currChar == '[' ||currChar == '{' ||currChar == '<') {
					// 여는 괄호인 경우
					stack.push(currChar);
				}else {
					// 닫는 괄호인 경우 짝 확인
					switch (currChar) {
					case ')':
						if (stack.peek() == '(') {
							stack.pop();
						}else {
							flag = false;
						}
						break;
					case '}':
						if (stack.peek() == '{') {
							stack.pop();
						}else {
							flag = false;
						}
						break;
					case ']':
						if (stack.peek() == '[') {
							stack.pop();
						}else {
							flag = false;
						}
						break;
					case '>':
						if (stack.peek() == '<') {
							stack.pop();
						}else {
							flag = false;
						}
						break;
					}
				}
				if(!flag)
					break;
			}
			
			if(flag && stack.isEmpty()) {
				sb.append(String.format("#%d %d\n", testCase, 1));
			}else {
				sb.append(String.format("#%d %d\n", testCase, 0));
			}
		}
		System.out.println(sb);
	}
}
