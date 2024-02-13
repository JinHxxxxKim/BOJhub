import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 알파벳
 * @author 김진형
 * 
 * 1. R, C를 입력받는다.
 * 2. 알파벳 배열을 입력받는다.
 * 3. [1,1]을 시작으로 DFS를 시작한다.
 * 		- Queue에서 뽑은 현재 위치에 대해 사방탐색을 진행한다.
 * 		- 방문한적이 있거나, 이전에 나왔던 알파벳의 경우 PASS
 * 		- 해당되지 않는다면, Dist를 넣고, max값을 갱신한다.
 * A = 65
 */
public class Main {
	private static final int[] dx = new int[] { -1, 1, 0, 0 };
	private static final int[] dy = new int[] { 0, 0, -1, 1 };

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int R, C, maxDist;
	private static char[][] map;
	private static int[][] dist;
	private static boolean[][] isVisited;
	private static boolean[] alphaVisited;

	public static void main(String[] args) throws Exception {
		// R, C입력받기
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		dist = new int[R][C];
		isVisited = new boolean[R][C];

		// 알파벳 사용 정보 배열
		alphaVisited = new boolean['Z' - 'A' + 1];

		// map정보 입력받기
		for (int row = 0; row < R; ++row) {
			String rowInfo = br.readLine().trim();
			for (int col = 0; col < C; ++col) {
				map[row][col] = rowInfo.charAt(col);
			}
		}
		// 탐색 전 변수 초기화
		maxDist = 1;
		dist[0][0] = 1;
		dfs(0, 0, 1);
		System.out.println(maxDist);
	}

	private static void dfs(int row, int col, int currDist) {
		if(isVisited[row][col])
			return;
		if(alphaVisited[map[row][col] - 'A'])
			return;
		// 방문처리 및 알파벳 사용처리
		isVisited[row][col] = true;
		alphaVisited[map[row][col] - 'A'] = true;
		
		// 사방탐색
		for (int dir = 0; dir < 4; ++dir) {
			int tempRow = row + dx[dir];
			int tempCol = col + dy[dir];

			// 범위검증
			if (tempCol < 0 || tempRow < 0 || tempRow >= R || tempCol >= C)
				continue;
			// 방문검증
			if (isVisited[tempRow][tempCol])
				continue;
			// 알파벳 사용 검증
			if (alphaVisited[map[tempRow][tempCol] - 'A'])
				continue;
			
			// 최대 거리 갱신
			maxDist = Math.max(maxDist, currDist + 1);
			dfs(tempRow, tempCol, currDist + 1);

		}
		
		// 재방문을 위해 방문처리 및 알파벳 사용처리 취소
		isVisited[row][col] = false;
		alphaVisited[map[row][col] - 'A'] = false;
	}
}