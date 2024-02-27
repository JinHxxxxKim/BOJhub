

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * Dynamic Progrmming
 * 
 * 각 집을 색칠하는 방법: 3가지 
 * 	- R: 이전 집을 G, B로 칠하는 비용 중 최소 + 자신을 R로 칠하는 방법
 * 	- G: 이전 집을 R, B로 칠하는 비용 중 최소 + 자신을 G로 칠하는 방법
 * 	- B: 이전 집을 R, G로 칠하는 비용 중 최소 + 자신을 B로 칠하는 방법
 * 
 * 이후 N번 째 집까지 칠한 뒤, R, G, B 중 최소값 출력
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	static int[][] dp;
	static int N; // 집의 개수
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		// 0행: R
		// 1행: G
		// 2행: B
		dp = new int[3][N]; 
		
		// 초기 세팅
		st = new StringTokenizer(br.readLine().trim());
		dp[0][0] = Integer.parseInt(st.nextToken());
		dp[1][0] = Integer.parseInt(st.nextToken());
		dp[2][0] = Integer.parseInt(st.nextToken());
		
		for(int idx = 1; idx<N;++idx) {
			st = new StringTokenizer(br.readLine().trim());
			int redCost = Integer.parseInt(st.nextToken());
			int blueCost = Integer.parseInt(st.nextToken());
			int greenCost = Integer.parseInt(st.nextToken());
			
			dp[0][idx] = Math.min(dp[1][idx-1], dp[2][idx-1]) + redCost;
			dp[1][idx] = Math.min(dp[0][idx-1], dp[2][idx-1]) + blueCost;
			dp[2][idx] = Math.min(dp[0][idx-1], dp[1][idx-1]) + greenCost;
		}
		int ans = dp[0][N-1];
		for(int idx = 1; idx<3; ++idx) {
			ans = Math.min(ans, dp[idx][N-1]);
		}
		System.out.println(ans);
	}
}