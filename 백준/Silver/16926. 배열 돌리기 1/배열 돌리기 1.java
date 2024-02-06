

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < M; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		ans = new int[N][M];
		int totalLayer = Math.min(N, M) / 2;
		for (int currLayer = 0; currLayer < totalLayer; ++currLayer) {
			int startRow = currLayer;
			int startCol = currLayer;
			int startDir = 0;
			
			int goalRow = currLayer;
			int goalCol = currLayer;
			int goalDir = 0;
			
			
			
			for (int rotateCnt = 0; rotateCnt < R; ++rotateCnt) {
				if (goalRow + dx[goalDir] >= N - currLayer || goalRow + dx[goalDir] < 0 + currLayer || goalCol + dy[goalDir] >= M - currLayer || goalCol + dy[goalDir] < 0 + currLayer) {
					// 방향 전환
					goalDir = (goalDir + 1) % 4;
				}
				goalRow += dx[goalDir];
				goalCol += dy[goalDir];
			}
			
			while(true) {
				
				if (goalRow + dx[goalDir] >= N - currLayer || goalRow + dx[goalDir] < 0 + currLayer || goalCol + dy[goalDir] >= M - currLayer || goalCol + dy[goalDir] < 0 + currLayer) {
					// 방향 전환
					goalDir = (goalDir + 1) % 4;
				}
				
				
				if (startRow + dx[startDir] >= N - currLayer || startRow + dx[startDir] < 0 + currLayer || startCol + dy[startDir] >= M - currLayer || startCol + dy[startDir] < 0 + currLayer) {
					// 방향 전환
					startDir = (startDir + 1) % 4;
				}
				ans[goalRow][goalCol] = map[startRow][startCol];
				
				goalRow += dx[goalDir];
				goalCol += dy[goalDir];
				
				startRow += dx[startDir];
				startCol += dy[startDir];
				
				if(startRow == currLayer && startCol == currLayer) {
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