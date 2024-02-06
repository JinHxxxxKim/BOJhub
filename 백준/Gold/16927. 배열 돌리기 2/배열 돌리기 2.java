

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 배열의 크기 N, M과 수행해야 하는 회전의 수 R을 입력받는다.
 * 2. N개의 줄에 배열 A의 원소 Aij를 입력받는다.
 * 3. 모든 layer의 시작점은 (n, n)이 된다.
 * 4. 시작점과 대응되는 목적지를 먼저 구한다.
 * 5. 시작점과 목적지를 한칸씩 rotate하며 ans 배열에 옮겨적는다.
 * 6. 모든 layer에 대해 반복한다. 
 */
public class Main {
	private static final int[] dx = new int[] { 1, 0, -1, 0 };
	private static final int[] dy = new int[] { 0, 1, 0, -1 };

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M, R;
	private static int[][] map;
	private static int[][] ans;

	public static void main(String[] args) throws Exception {
		// 배열의 크기 N, M과 수행해야 하는 회전의 수 R을 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		// N개의 줄에 배열 A의 원소 Aij를 입력받는다.
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < M; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		// 이동 결과를 옮겨적을 배열
		ans = new int[N][M];
		// 총 layer의 수 계산
		int totalLayer = Math.min(N, M) / 2;
		for (int currLayer = 0; currLayer < totalLayer; ++currLayer) {
			// 모든 layer의 시작점은 (n, n)이 된다.
			int startRow = currLayer;
			int startCol = currLayer;
			// 시작점을 옮길 방향벡터
			int startDir = 0;

			// 목적 위치 
			int goalRow = currLayer;
			int goalCol = currLayer;
			// 목적위치를 옮길 방향벡터
			int goalDir = 0;

			// 최적화를 위한 rotation 횟수 mod 연산
			int newRotateCnt = ((N - currLayer * 2) + (M - currLayer * 2)) * 2 - 4;
			newRotateCnt = R % newRotateCnt;
			
			// 초기 목적 위치 구하기
			for (int rotateCnt = 0; rotateCnt < newRotateCnt; ++rotateCnt) {
				// 범위 체크 (layer를 벗어날 경우 방향 벡터 수정)
				if (goalRow + dx[goalDir] >= N - currLayer || goalRow + dx[goalDir] < 0 + currLayer
						|| goalCol + dy[goalDir] >= M - currLayer || goalCol + dy[goalDir] < 0 + currLayer) {
					// 방향 전환
					goalDir = (goalDir + 1) % 4;
				}
				goalRow += dx[goalDir];
				goalCol += dy[goalDir];
			}

			while (true) {
				// 목적 위치의 범위 체크 (layer를 벗어날 경우 방향 벡터 수정)
				if (goalRow + dx[goalDir] >= N - currLayer || goalRow + dx[goalDir] < 0 + currLayer
						|| goalCol + dy[goalDir] >= M - currLayer || goalCol + dy[goalDir] < 0 + currLayer) {
					// 방향 전환
					goalDir = (goalDir + 1) % 4;
				}
				
				// 소스(시작) 위치의 범위 체크 (layer를 벗어날 경우 방향 벡터 수정)
				if (startRow + dx[startDir] >= N - currLayer || startRow + dx[startDir] < 0 + currLayer
						|| startCol + dy[startDir] >= M - currLayer || startCol + dy[startDir] < 0 + currLayer) {
					// 방향 전환
					startDir = (startDir + 1) % 4;
				}
				// 옮겨적기
				ans[goalRow][goalCol] = map[startRow][startCol];

				// 위치 갱신
				goalRow += dx[goalDir];
				goalCol += dy[goalDir];

				startRow += dx[startDir];
				startCol += dy[startDir];

				// 처음위치로 돌아왔다면 종료
				if (startRow == currLayer && startCol == currLayer) {
					break;
				}
			}

		}
		// 출력
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				sb.append(ans[row][col]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}