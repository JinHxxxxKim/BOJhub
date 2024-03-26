import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * 0-1 Knapsack 문제
 * 
 * 1. 모든 물품을 순회하며 경우의 수를 확인한다.
 * 2. 총 2가지의 경우의 수가 존재한다.
 * 	2-1. 물품을 넣을 수 있는 경우(현재 확인 중인 무게보다 작을 경우)
 * 	 2-1-1. 넣는다: dp[idx-1][col-currWeight] + currValue
 * 	 2-1-2. 안 넣는다: dp[idx-1][col]
 * 	  => 두 경우의 수 중 큰 것을 선택한다.
 *  2-2. 물품을 넣을 수 없는 경우(현재 확인 중인 무게보다 클 경우)
 *    2-2-1. 안 넣는다: dp[idx-1][col]
 * 3. 최종적으로 dp[N][K]를 출력한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	static int[][] dpTable;
	static Item[] items;
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		items = new Item[N];
		
		// dp테이블 초기화
		dpTable = new int[N + 1][K + 1];
		// 0열 초기화
		for (int idx = 0; idx < N + 1; ++idx) {
			dpTable[idx][0] = 0;
		}
		// 0행 초기화
		for (int idx = 0; idx < K + 1; ++idx) {
			dpTable[0][idx] = 0;
		}
		
		// Item 입력 받기
		for(int cnt = 0; cnt<N;++cnt) {
			st = new StringTokenizer(br.readLine().trim());
			items[cnt] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// dp 로직 시작
		// 모든 item 순회하며 조회
		for (int idx = 1; idx < N + 1; ++idx) {
			Item currItem = items[idx - 1];
			for (int col = 1; col < K + 1; ++col) {
				// 넣을 수 없는 경우
				if (col < currItem.weight) {
					dpTable[idx][col] = dpTable[idx - 1][col];
				}
				// 넣을 수 있는 경우
				else {
					// 넣는다 vs 안 넣는다
					dpTable[idx][col] = 
							Math.max(dpTable[idx - 1][col],
									dpTable[idx - 1][col - currItem.weight] + currItem.value);
				}
			}
		}
		System.out.println(dpTable[N][K]);
	}

	// 물품 클래스
	static class Item {
		int weight, value;

		public Item(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}
		
	}
}