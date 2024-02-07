

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 필요한 입력을 모두 받아서 초기화한다.
 * 2. 2차원이 아닌 3차원 배열 사용
 * 3. visited 배열은 2차원 사용
 * 4. 벽을 뚫었다면[][][1]로 내려가서 탐색
 */
public class Main {
	private static final int[] dx = new int[] {-1, 1, 0, 0};
	private static final int[] dy = new int[] {0, 0, -1, 1};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N, M;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[][][] dist;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//====================초기화, 선언======================//
		map = new int[N][M];
		visited = new boolean[N][M][2];
		dist = new int[N][M][2];
		for (int row = 0; row < N; ++row) {
			for(int col = 0;col<M;++col)
				Arrays.fill(dist[row][col], Integer.MAX_VALUE);
		}
		
		//====================입력======================//
		for (int row = 0; row < N; ++row) {
			String temp = br.readLine().trim();
			for (int col = 0; col < M; ++col) {
				int state = temp.charAt(col) - '0';
				map[row][col] = state;
				map[row][col] = state;
			}
		}

		bfs();
//		for (int i = 0; i < N; ++i)
//			System.out.println(Arrays.toString(dist[i]));
		int ans = Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0, 0 });
		visited[0][0][0] = true;
		dist[0][0][0] = 1;
		while (!q.isEmpty()) {
			int[] currNode = q.poll();
			int currRow = currNode[0];
			int currCol = currNode[1];
			int isAlreadyBreak = currNode[2]; // 0->아직, 1->이미

			if (currRow == N - 1 && currCol == M - 1) {
				break;
			}

			for (int dir = 0; dir < 4; ++dir) {
				int tempRow = currRow + dx[dir];
				int tempCol = currCol + dy[dir];

				// 범위 체크
				if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M)
					continue;
				// 방문체크
				if (visited[tempRow][tempCol][isAlreadyBreak])
					continue;

				if (isAlreadyBreak == 1) {
					// 벽뚫기 기회 이미 사용
					// 또 벽이라면
//					System.out.println("aaaa");
					if (map[tempRow][tempCol] == 1) {
//						System.out.println("CURRROW: "+currRow);
//						System.out.println("CURRCOL: "+currCol);
						continue;
					}
//					System.out.println("asd");
					visited[tempRow][tempCol][1] = true;
					dist[tempRow][tempCol][1] = Math.min(dist[tempRow][tempCol][1], dist[currRow][currCol][1] + 1);
					q.offer(new int[] { tempRow, tempCol, 1 });
				} else {
					// 벽뚫기 기회 아직 사용 X
					if (map[tempRow][tempCol] == 1) {
						visited[tempRow][tempCol][1] = true;
						
						dist[tempRow][tempCol][1] = Math.min(dist[tempRow][tempCol][1], dist[currRow][currCol][0] + 1);
						
						q.offer(new int[] { tempRow, tempCol, 1 });
					} else {
						visited[tempRow][tempCol][0] = true;
						dist[tempRow][tempCol][0] = Math.min(dist[tempRow][tempCol][0], dist[currRow][currCol][0] + 1);
						q.offer(new int[] { tempRow, tempCol, 0 });
					}
				}
			}
		}
		
	}
}