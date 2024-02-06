import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 배열의 크기 N, M과 수행해야 하는 회전의 수 R을 입력받는다.
 * 2. N개의 줄에 배열 A의 원소 Aij를 입력받는다.
 * 
 * 모든 Rotate는 좌상단을 기준으로 시작한다
 * 		- [0, 0], [1, 1], [2, 2] ...
 * 		- 이 때, 좌표[n, n]에 대해 n이 min(M, N)/2 미만일 때까지만 Rotate한다.
 * 
 * 하, 우, 상, 좌 순서로 ratate하도록 구현한다.
 */
public class Main {
	private static final int[] dx = new int[] {1, 0, -1, 0};
	private static final int[] dy = new int[] {0, 1, 0, -1};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M, R;
	private static int[][] map;
	private static boolean[][] visited;
	private static int initPos;
	
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
		visited = new boolean[N][M];
		// 회전 START
		int limitPos = Math.min(M, N) / 2;
		for (int rCnt = 0; rCnt < R; ++rCnt) {
			visited = new boolean[N][M];
			for (int cnt = 0; cnt < limitPos; ++cnt) {
				initPos = cnt;
				rotate(cnt, cnt, 0, map[cnt][cnt]);
			}
		}
		
		
		for(int row = 0;row<N;++row) {
			for(int col = 0;col<M;++col) {
				sb.append(map[row][col]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void rotate(int row, int col, int direction, int num) {
		if(row == initPos && col == initPos && visited[row][col]) {
			return;
		}
		
		int tempRow = row + dx[direction];
		int tempCol = col + dy[direction];
		
		visited[row][col] = true;
		
		// 범위 체크
		if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M || visited[tempRow][tempCol]) {
			if(tempRow != initPos || tempCol != initPos) {
				// 방향 전환
				++direction;
				tempRow = row + dx[direction];
				tempCol = col + dy[direction];
			}
		}
		int temp = map[tempRow][tempCol];
		map[tempRow][tempCol] = num;
		rotate(tempRow, tempCol, direction, temp);
	}
}