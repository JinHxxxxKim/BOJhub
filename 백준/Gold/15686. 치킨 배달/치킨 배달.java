

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)을 입력받는다.
 * 2. 도시의 정보를 입력받는다.
 * 		- 치킨집의 정보, 집의 정보를 별도로 저장한다.
 * 3. 총 치킨집의 수 중에서 M개의 치킨집을 선택한다.(조합)
 * 4. Combination
 * 		- selectIdx == M or elementIdx == 치킨집의 총 개수 (기저조건)
 * 			- 각 집까지의 거리의 합을 구하고 갱신한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M;
	private static Pos[] chickenPos;
	private static Pos[] clientPos;
	private static int[][] map;
	
	private static int chickenNum;
	private static int clientNum;
	
	private static int[] selected;
	private static int ans;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < N; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1) {
					++clientNum;
				} else if (map[row][col] == 2) {
					++chickenNum;
				}
			}
		}
		
		chickenPos = new Pos[chickenNum];
		clientPos = new Pos[clientNum];

		int chickenIdx = 0;
		int clientIdx = 0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if (map[row][col] == 1) {
					clientPos[clientIdx++] = new Pos(row, col);
				} else if (map[row][col] == 2) {
					chickenPos[chickenIdx++] = new Pos(row, col);
				}
			}
		}
		
		selected = new int[M];
		ans = Integer.MAX_VALUE;
		combination(0, 0);
		
		System.out.println(ans);
	}
	
	private static void combination(int selectIdx, int elementIdx) {
		// 기저조건1
		if(selectIdx == M) {
			// 해당 조합에서의 거리의 합
			int localDistSum = 0;

			// 각 집에서 모든 치킨집까지의 거리 중 최소값을 더한다.
			for (Pos client : clientPos) {
				int personalDist = Integer.MAX_VALUE;
				for (int idx = 0; idx < M; ++idx) {
					Pos chicken = chickenPos[selected[idx]];
					personalDist = Math.min(personalDist,
							Math.abs(client.x - chicken.x) + Math.abs(client.y - chicken.y));
				}
				localDistSum += personalDist;
			}
			ans = Math.min(ans, localDistSum);
			return;
		}
		// 기저조건2
		if (elementIdx == chickenNum) {
			return;
		}
		// 전처리
		selected[selectIdx] = elementIdx;
		// 선택O
		combination(selectIdx + 1, elementIdx + 1);
		// 선택X
		combination(selectIdx, elementIdx + 1);
	}

	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}