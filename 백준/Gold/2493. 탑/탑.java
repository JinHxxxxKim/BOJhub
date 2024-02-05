import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 기본적인 아이디어: Stack 사용
 * 
 * 1. 탑의 수를 나타내는 정수 N을 입력받는다.
 * 2. N개의 탑들의 높이를 입력받는다.
 * 3. N의 개수가 50만이므로, 한번의 조회에 마무리해야한다.
 * 4. Stack<int[]> stack을 통해 해결한다. 
 * 		- int[]는 1X2크기의 2차원 배열로 0열에는 높이, 1열에는 해당 건물의 인덱스를 저장한다.
 * 5. N만큼 반복한다.
 * 		5-1. 스택이 비어있다 -> sb.append(0);
 * 		5-2. 스택.peek() 했는데 나보다 높다 -> peek()한 놈의 인덱스 append 하고 offer(나)
 * 		5-3. 스택.peek() 했는데 나보다 낮거나 같다  -> 나보다 높은애 or 빌때가지 poll()
 * 6. 출력
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	private static int[] height;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		height = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0;idx<N;++idx) {
			height[idx] = Integer.parseInt(st.nextToken());
		}
		Stack<int[]> stack = new Stack<>();
		
		// 초기 세팅
		sb.append(0).append(" ");
		stack.push(new int[] {height[0], 0});
		// N개의 건물을 반복
		for(int idx = 1;idx<N;++idx) {
			int currHeight = height[idx];
			
			// stack이 비어있다면 그냥 0
			if(stack.isEmpty()) {
				sb.append(0).append(" ");
				stack.push(new int[] {currHeight, idx});
			}else {
				if(stack.peek()[0]>currHeight) {
					// stack에 있는 애가 나보다 클 경우
					// index와 위치 보정을 위해 sb에 append할 때는 +1
					sb.append(stack.peek()[1]+1).append(" ");
					stack.push(new int[] {currHeight, idx});
				}else {
					while(!stack.isEmpty() && stack.peek()[0]<=currHeight) {
						// 나보다 높은애를 만나거나 or 빌때가지 poll()
						stack.pop();
					}
					// 역시 비었다면, 0
					if(stack.isEmpty()) {
						sb.append(0).append(" ");
						stack.push(new int[] {currHeight, idx});
					}else {
						// pop을 했는데도 남아있다면, 그 건물이 레이저 신호를 받는 것
						sb.append(stack.peek()[1]+1).append(" ");
						stack.push(new int[] {currHeight, idx});
					}
				}
			}
		}
		
		System.out.println(sb);
	}
}
