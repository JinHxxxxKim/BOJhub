import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. N과 K를 입력받는다.
 * 2. 1~N까지의 수를 큐에 저장한다.
 * 3. 큐가 빌때까지 반복한다
 * 		3-1. cnt 변수를 통해 K번째 사람을 찾을 때까지 offer(poll())을 반복한다.
 * 		3-2. cnt가 K가 되면, sb에 append하고 cnt를 초기화한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, K;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sb.append("<");
		// 원탁을 대신한 큐
		Queue<Integer> q = new ArrayDeque<>();
		for (int num = 1; num <= N; ++num) {
			q.offer(num);
		}
		int cnt = 1;
		while (!q.isEmpty()) {
			if (cnt < K) {
				q.offer(q.poll());
				++cnt;
			} else {
				// 출력 형식을 위한 전처리
				if(q.size()==1) {
					sb.append(q.poll()).append(">");
				}else {
					sb.append(q.poll()).append(", ");
				}
				// cnt 초기화
				cnt = 1;
			}
		}
		System.out.println(sb);
	}
}