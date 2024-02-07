

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
 * 3. visited 배열, dist 배열 모두 3차원 배열 사용
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
		
		// 벽 뚫고 이동([1]) vs 벽 안뚫고 이동([0]) 중 더 가까운 거리 선택
		int ans = Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		// BFS 초기 세팅
		// 0, 0에서 출발
		q.offer(new int[] { 0, 0, 0 });
		visited[0][0][0] = true;
		dist[0][0][0] = 1;
		while (!q.isEmpty()) {
			int[] currNode = q.poll();
			int currRow = currNode[0];
			int currCol = currNode[1];
			// 0->아직 벽을 뚫은 적이 없다. 
			// 1->이미 벽을 한번 뚫었다.
			int isAlreadyBreak = currNode[2]; 
			
			// 탈출 조건(종료 지점을 만나면)
			if (currRow == N - 1 && currCol == M - 1) {
				break;
			}
			
			// 4방 탐색
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
					// 또 벽이라면 PASS
					if (map[tempRow][tempCol] == 1) {
						continue;
					}
					// 최적화를 위한 방문처리 
					visited[tempRow][tempCol][1] = true;
					// 해당 위치 (tempRow, tempCol)이 이미 방문한
					dist[tempRow][tempCol][1] = dist[currRow][currCol][1] + 1;
					// dist[tempRow][tempCol][1] = Math.min(dist[tempRow][tempCol][1], dist[currRow][currCol][1] + 1);
					q.offer(new int[] { tempRow, tempCol, 1 });
				} else {
					// 벽뚫기 기회 아직 사용 X
					if (map[tempRow][tempCol] == 1) {
						// 이번에 만난 위치가 벽일 경우
						visited[tempRow][tempCol][1] = true;
						// 기존에 벽을 뚫고 방문한 적이 있을 수 있으므로, 비교를 통해 갱신
						dist[tempRow][tempCol][1] = Math.min(dist[tempRow][tempCol][1], dist[currRow][currCol][0] + 1);
						q.offer(new int[] { tempRow, tempCol, 1 });
					} else {
						// 이번에 만난 위치가 그냥 길인 경우
						visited[tempRow][tempCol][0] = true;
						dist[tempRow][tempCol][0] = dist[currRow][currCol][0] + 1;
						// dist[tempRow][tempCol][0] = Math.min(dist[tempRow][tempCol][0], dist[currRow][currCol][0] + 1);
						q.offer(new int[] { tempRow, tempCol, 0 });
					}
				}
			}
		}
		
	}
}