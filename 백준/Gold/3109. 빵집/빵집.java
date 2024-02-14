

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. R과 C를 입력받는다.
 * 2. 빵집 근처의 상태를 배열로 입력 받는다.
 * 
 * 모든 파이프를 최대한 위로 붙인다는 아이디어(경로는 겹칠 수 없고, 접할 수 없다.)
 * 
 * 1만 * 500 = 500만 
 * 
 * [0, 0]부터 시작해서 우로 이동한다.
 * 다음 열이 막혀있다면, 위, 가운데, 아래 순서로 조회한다.
 * 방문했다면, 방문처리 필수
 * 마지막 열에 도달하였다면, CNT + 1
 * 
 */
public class Main {
	private static final int[] dx = new int[] {-1, 0, 1};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int R, C;
	private static char[][] map;
	private static boolean[][] isVisited;
	private static int cnt;
	private static boolean findFlag;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isVisited = new boolean[R][C];
		for (int row = 0; row < R; ++row) {
			String rowInfo = br.readLine().trim();
			for (int col = 0; col < C; ++col) {
				map[row][col] = rowInfo.charAt(col);
			}
		}
		
		for (int row = 0; row < R - 1; ++row) {
			findFlag = false;
			dfs(row, 0);
		}
		
		System.out.println(cnt);
	}

	private static void dfs(int row, int col) {
		if(findFlag)
			return;
		
		isVisited[row][col] = true;
		// 빵집 도달
		if (col == C - 1) {
			++cnt;
			findFlag = true;
			return;
		}
		for (int dir = 0; dir < dx.length; ++dir) {
			int tempRow = row + dx[dir];
			int tempCol = col + 1;

			// 범위검증
			if (tempRow < 0 || tempRow >= R)
				continue;
			// 막힌 길 검증
			if (map[tempRow][tempCol] == 'x')
				continue;
			// 방문검증
			if (isVisited[tempRow][tempCol])
				continue;

			dfs(tempRow, tempCol);
		}
	}
}