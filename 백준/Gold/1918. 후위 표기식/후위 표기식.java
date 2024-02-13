import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 후위 표기식
 * @author 김진형
 * 
 * 모든 연산자에 대해 동일한 연산 우선순위로 만들어야한다.
 * 
 * 1. 중위 표기식을 입력받는다.
 * 2. 중위 표기식의 0번째 인덱스부터 순회한다.
 * 3. 숫자의 경우, 그냥 append
 * 4. 연산자의 경우
 * 		- 스택이 비어있다면 push
 * 		- 비어있지 않다면, 연산자 비교 후, 나보다 큰 연산자의 경우 pop 후 push
 * 		- 괄호의 경우, 여는 괄호는 push, 닫는 괄호까지 위의 과정 반복
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		String infixString = br.readLine().trim();
		char[] charInfixArray = infixString.toCharArray();
		Stack<Character> stack = new Stack<>();
		for (int idx = 0; idx < charInfixArray.length; ++idx) {
			char currChar = charInfixArray[idx];	
			if (currChar == '+' || currChar == '-') {
				if (stack.isEmpty() || stack.peek() == '(') {
					stack.push(currChar);
				}else {
					while(!stack.isEmpty() && stack.peek()!='(') {
						sb.append(stack.pop());
					}
					stack.push(currChar);
				}
			} else if (currChar == '*' || currChar == '/') {
				if (stack.isEmpty() || stack.peek() == '(') {
					stack.push(currChar);
				}else {
					while (!stack.isEmpty() && stack.peek() != '(' && (stack.peek() == '*' || stack.peek() == '/')) {
						sb.append(stack.pop());
					}
					stack.push(currChar);
				}
			} else if (currChar == '(') {
				stack.push(currChar);
			} else if (currChar == ')') {
				while(stack.peek()!='(')
					sb.append(stack.pop());
				if(stack.peek()=='(')
					stack.pop();
			} else {
				sb.append(currChar);
			}
		}
		while(!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb);
	}
}
// A*((B*C)*D)*E