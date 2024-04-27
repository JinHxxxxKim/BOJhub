import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 표의 크기 N과 합을 구해야 하는 횟수 M을 입력받는다.
 * 2. N개의 줄에는 표에 채워져 있는 수를 1행부터 차례대로 입력받으며, 누적합을 구한다.
 * 3. M만큼 반복
 * 		3-1. 네 개의 정수 x1, y1, x2, y2를 입력받는다.
 * 		3-2. 우하단 누적합 - 우상단 누적합 - 좌하단 누적합 + 좌상단 누적합 을 sb에 append한다.
 * 4. 출력
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M;
	private static int cumulativeSum[][];

	public static void main(String[] args) throws Exception {
		// N, M입력받기
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cumulativeSum = new int[N + 1][N + 1];
		// 입력받으며 동시에 누적합 구하기
		// 2차원 배열의 누적합 = 위에거 + 왼쪽거 + 자신 - 대각선
		for (int row = 1; row <= N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 1; col <= N; ++col) {
				cumulativeSum[row][col] = cumulativeSum[row - 1][col] + // 위
						cumulativeSum[row][col - 1] + // 왼쪽
						Integer.parseInt(st.nextToken()) - // 자신
						cumulativeSum[row - 1][col - 1]; // 대각선
			}
		}

		for (int cnt = 0; cnt < M; ++cnt) {
			st = new StringTokenizer(br.readLine().trim());
			// 네 개의 정수 x1, y1, x2, y2를 입력받는다.
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			// 우하단 누적합 - 우상단 누적합 - 좌하단 누적합 + 좌상단 누적합 을 sb에 append한다.
			sb.append(cumulativeSum[x2][y2] 		// 우하단 누적합
					- cumulativeSum[x2][y1 - 1] 	// 좌하단 누적합 
					- cumulativeSum[x1 - 1][y2]		// 우상단 누적합
					+ cumulativeSum[x1 - 1][y1 - 1]+"\n");	// 좌상단 누적합
		}
		System.out.println(sb);
	}
}
