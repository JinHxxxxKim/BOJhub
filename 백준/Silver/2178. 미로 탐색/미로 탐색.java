import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 필요한 입력을 모두 받는다.
 * 2. (1, 1)에서 BFS를 실행한다.
 * 3. 4방 탐색을 통해 다음 위치에 대한 검증을 한 뒤, 이동한다.
 * 4. Queue<int[]> 에서 [0]: row, [1]: col, [2]: dist 값으로 설정한다
 */
public class Main {
	private static final int[] dx = new int[] { -1, 1, 0, 0 };
	private static final int[] dy = new int[] { 0, 0, 1, -1 };

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M;
	private static int[][] map;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		// 필요한 입력을 모두 받는다.
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int row = 0; row < N; ++row) {
			String rowInfo = br.readLine().trim();
			for (int col = 0; col < M; ++col) {
				map[row][col] = rowInfo.charAt(col) - '0';
			}
		}

		// BFS Start
		Queue<int[]> q = new ArrayDeque<>();
		int ans = 0;
		// [행, 열, 거리]
		q.offer(new int[] { 0, 0, 1 });
		visited[0][0] = true;
		while (!q.isEmpty()) {
			int[] nodeInfo = q.poll();
			int currRow = nodeInfo[0];
			int currCol = nodeInfo[1];
			int currDist = nodeInfo[2];

			// 목적지 도착
			if (currRow == N - 1 && currCol == M - 1) {
				ans = currDist;
				break;
			}

			// 4방탐색
			for (int dir = 0; dir < 4; ++dir) {
				int tempRow = currRow + dx[dir];
				int tempCol = currCol + dy[dir];

				// 범위 검증
				if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M || map[tempRow][tempCol] == 0)
					continue;
				// 방문 검증
				if (visited[tempRow][tempCol])
					continue;
				visited[tempRow][tempCol] = true;
				q.offer(new int[] { tempRow, tempCol, currDist + 1 });
			}
		}

		System.out.println(ans);
	}
}
