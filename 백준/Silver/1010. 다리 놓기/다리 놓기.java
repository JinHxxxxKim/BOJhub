
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 서쪽 다리 M개 중 N개의 다리를 선택하는 경우의 수를 구하면 된다.
 * 2. 미리 30 Combination 30을 저장하는 DP 테이블을 생성해서 저장한다.
 * 3. 주어지는 M, N값에 따라 테이블 값을 참조하여 출력한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int TC;
	static int[][] DP;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		TC = Integer.parseInt(br.readLine().trim());
		init();
		for(int testCase = 0;testCase<TC;++testCase) {
			st = new StringTokenizer(br.readLine().trim());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			sb.append(DP[N][M]).append("\n");
		}
		System.out.println(sb);
	}

	// 동적 테이블을 초기화
	private static void init() {
		DP = new int[31][31];
		for (int n = 0; n < 31; ++n) {
			for (int m = 0, end = Math.min(n, 30); m <= end; ++m) {
				if (m == 0 || m == n)
					DP[n][m] = 1;
				else
					DP[n][m] = DP[n - 1][m - 1] + DP[n - 1][m];
			}
		}
	}
}