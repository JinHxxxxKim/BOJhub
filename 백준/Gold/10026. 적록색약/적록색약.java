

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. N을 입력받는다.
 * 2. 그림을 입력받는다.
 * 3. 적록색약이 아닌 사람이 바라보는 구역의 수를 카운팅한다.
 * 		- R, G, B 모두 다른 영역으로 카운팅한다.
 * 4. 적록색약인 사람이 바라보는 구역의 수를 카운팅한다.
 * 		- R=G, B에 대한 영역으로 카운팅한다.
 * 5. [0, 0]부터 [N-1, N-1]까지 순회한다.
 * 		- visited가 true라면 넘어가고, false라면 해당 위치부터 bfs를 시작한다.
 */
public class Main {
	private static final int[] dx = new int[] {-1, 0, 1, 0};
	private static final int[] dy = new int[] {0, 1, 0, -1};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	private static char[][] picture;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		picture = new char[N][N];
		for (int row = 0; row < N; ++row) {
			String rowInfo = br.readLine().trim();
			for (int col = 0; col < N; ++col) {
				picture[row][col] = rowInfo.charAt(col);
			}
		}
		
		// 적록 색약이 아닌 사람이 바라보는 그림
		int personACnt = 0;
		visited = new boolean[N][N];
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(visited[row][col])
					continue;
				countPersonAArea(row, col);
				++personACnt;
			}
		}
		
		// 적록 색약인 사람이 바라보는 그림
		int personBCnt = 0;
		visited = new boolean[N][N];
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if(visited[row][col])
					continue;
				countPersonBArea(row, col);
				++personBCnt;
			}
		}
		
		System.out.printf("%d %d", personACnt, personBCnt);
	}

	private static void countPersonAArea(int row, int col) {
		int color = picture[row][col];
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(row, col));
		visited[row][col] = true;
		while (!q.isEmpty()) {
			Pos currNode = q.poll();
			int currRow = currNode.x;
			int currCol = currNode.y;

			for (int dir = 0; dir < 4; ++dir) {
				int tempRow = currRow + dx[dir];
				int tempCol = currCol + dy[dir];

				// 범위 검증
				if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= N)
					continue;
				// 방문 검증
				if (visited[tempRow][tempCol])
					continue;
				// 색 검증
				if (picture[tempRow][tempCol] != color)
					continue;

				q.offer(new Pos(tempRow, tempCol));
				visited[tempRow][tempCol] = true;
			}
		}
	}
	
	private static void countPersonBArea(int row, int col) {
		int color = picture[row][col];
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(row, col));
		visited[row][col] = true;
		while (!q.isEmpty()) {
			Pos currNode = q.poll();
			int currRow = currNode.x;
			int currCol = currNode.y;

			for (int dir = 0; dir < 4; ++dir) {
				int tempRow = currRow + dx[dir];
				int tempCol = currCol + dy[dir];

				// 범위 검증
				if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= N)
					continue;
				// 방문 검증
				if (visited[tempRow][tempCol])
					continue;
				// 색 검증
				if(color == 'R' || color == 'G') {
					if (picture[tempRow][tempCol] == 'B')
						continue;
					q.offer(new Pos(tempRow, tempCol));
					visited[tempRow][tempCol] = true;
				}else {
					if (picture[tempRow][tempCol] != color)
						continue;
					
					q.offer(new Pos(tempRow, tempCol));
					visited[tempRow][tempCol] = true;
				}
			}
		}
	}
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}