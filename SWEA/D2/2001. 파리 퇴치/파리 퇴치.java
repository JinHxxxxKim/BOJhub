
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 파리의 마리수를 입력받으며, 각 위치까지의 누적합을 구한다.
 * 2. (M, M)부터 (N, N)까지 슬라이딩윈도우를 통해 최대값을 갱신해나간다.
 * 	2-1. 각 행이 끝나면, 행을 하나 증가시켜 탐색한다.
 * 	2-2. 누적합: 자기자신 - 동일 행 - 동일 열 + 대각선
 * 3. 최대값을 출력한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int N, M;
	static int[][] cumuSum; // 누적합 배열
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 누적합 배열의 경우, padding 존재
			cumuSum = new int[N + 1][N + 1];

			// 배열을 입력받음과 동시에, 누적합 계산
			for (int row = 1; row < N + 1; ++row) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 1; col < N + 1; ++col) {
					cumuSum[row][col] = cumuSum[row - 1][col] + cumuSum[row][col - 1] - cumuSum[row - 1][col - 1]
							+ Integer.parseInt(st.nextToken());
				}
			}

			// 누적합에 대해 슬라이딩 윈도우를 통해 최대 마리수 계산
			// (M, M) 부터 시작 가능
			int max = Integer.MIN_VALUE;
			for (int row = M; row < N + 1; ++row) {
				for (int col = M; col < N + 1; ++col) {
					int currNum = cumuSum[row][col] - cumuSum[row - M][col] - cumuSum[row][col - M]
							+ cumuSum[row - M][col - M];
					max = Math.max(max, currNum);
				}
			}

			sb.append(String.format("#%d %d\n", testCase, max));
		}
		System.out.println(sb);
	}
}
