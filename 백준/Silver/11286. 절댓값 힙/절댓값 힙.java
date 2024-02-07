import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 연산의 개수 N(1≤N≤100,000)을 입력받는다.
 * 2. N개의 줄에는 연산에 대한 정보를 나타내는 정수 x를 입력받는다.
 * 		- 0일경우, pq.poll();
 * 			- if pq.isEmpty() -> 0
 * 		- 0이 아닐경우, pq.offer();
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	// [0] => 절대값, [1] => 원래 수 
	private static PriorityQueue<int[]> pq;
	
	public static void main(String[] args) throws Exception {
		// 연산의 개수 N(1≤N≤100,000)을 입력받는다.
		N = Integer.parseInt(br.readLine().trim());
		// 절대값([0])을 기준으로 오름차순으로 정렬하되, 같을 경우 원래 값([1])을 기준으로 오름차순으로 정렬
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
		});
		// N개의 줄에는 연산에 대한 정보를 나타내는 정수 x를 입력받는다.
		for(int cnt = 0;cnt<N;++cnt) {
			int op = Integer.parseInt(br.readLine().trim());
			if(op == 0) {
				// 0일경우, pq.poll();
				// if pq.isEmpty() -> 0
				if(pq.isEmpty()) {
					sb.append(0).append("\n");
				}else {
					sb.append(pq.poll()[1]).append("\n");
				}
			}else {
				pq.offer(new int[] {Math.abs(op), op});
			}
		}
		System.out.println(sb);
	}
}