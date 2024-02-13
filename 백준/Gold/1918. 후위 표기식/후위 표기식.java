
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
 * 
 * 현재 문자가 가능한 경우
 * 		1. 피연산자인 경우
 * 			- StringBuilder에 그냥 append한다.
 * 		2. 연산자인 경우
 * 			- stack이 비어있거나, 가장 최근(peek)연산자가 '('일 경우 그냥 push한다.
 * 			- '+', '-' 인 경우: '('가 나오거나, 스택이 비지 않을 때 까지 pop한 뒤 append한다. 
 * 			- '*', '/' 인 경우: '('가 나오거나, 스택이 비지 않을 때 까지 + 연산자의 순서를 고려하여 '*', '/'이 나오지 않을 때까지 pop한 뒤 append한다.  
 * 		3. 여는 괄호인 경우
 * 			- StringBuilder에 그냥 append한다.
 * 		4. 닫는 괄호인 경우
 * 			- 여는 괄호가 나올 때까지 stack에서 pop한 뒤 append한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		// 중위 연산식 입력
		String infixString = br.readLine().trim();
		char[] charInfixArray = infixString.toCharArray();
		Stack<Character> stack = new Stack<>();
		
		// 0번 인덱스 문자부터 끝까지 순회 
		for (int idx = 0; idx < charInfixArray.length; ++idx) {
			// 현재 문자
			char currChar = charInfixArray[idx];
			switch (currChar) {
			case '+':
			case '-':
				if (stack.isEmpty() || stack.peek() == '(') {
					stack.push(currChar);
				}else {
					while(!stack.isEmpty() && stack.peek()!='(') {
						sb.append(stack.pop());
					}
					stack.push(currChar);
				}
				break;
			case '*':
			case '/':
				if (stack.isEmpty() || stack.peek() == '(') {
					stack.push(currChar);
				}else {
					while (!stack.isEmpty() && stack.peek() != '(' && (stack.peek() == '*' || stack.peek() == '/')) {
						sb.append(stack.pop());
					}
					stack.push(currChar);
				}
				break;
			case '(':
				stack.push(currChar);
				break;
			case ')':
				while(stack.peek()!='(')
					sb.append(stack.pop());
				if(stack.peek()=='(')
					stack.pop();
				break;
			default:
				sb.append(currChar);
				break;
			}
		}
		while(!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb);
	}
}
// A*((B*C)*D)*E