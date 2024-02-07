

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 필요한 입력을 모두 받는다.
 * 	
 * 	- map[][] => 맵 전체의 정보를 저장
 * 	- visited[N][M][K] => N, K위치에 대해 K번 벽을 뚫고 방문한 적이 있는지 여부를 저장
 * 	- dist[N][M][K] => N, K위치에 대해 K번 벽을 뚫고 도착했을 때의 최소값
 * 
 * 2. BFS를 통해 최단 거리를 구한다.
 * 3. Queue<int[]> => 저장되는 데이터: int[] {A, B, C}
 * 		- A: row정보
 * 		- B: col정보
 * 		- C: row, col까지 도달하는데 벽을 뚫은 횟수
 * 4. row, col의 위치에서 4방 탐색을 진행
 * 		- 유효한 위치가 벽인 경우.
 * 			-> C(row, col까지 도달하는데 벽을 뚫은 횟수)+1가 K를 초과하는지 확인
 * 				=> 초과할 경우 PASS
 * 				=> 초과하지 않을 경우, dist[tempRow][tempCol][C+1]을 최소값으로 갱신
 * 		- 유효한 위치가 벽이 아닌 경우.
 * 			-> C(row, col까지 도달하는데 벽을 뚫은 횟수)를 유지한체로 dist[tempRow][tempCol][C]에 값을 저장 
 */
public class Main{
	private static final int[] dx = new int[] {-1, 1, 0, 0};
	private static final int[] dy = new int[] {0, 0, -1, 1};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N, M, K;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[][][] dist; 
	
	public static void main(String[] args) throws Exception {
		// 필요한 입력을 모두 받는다.
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][K+1];
		dist = new int[N][M][K+1];
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				Arrays.fill(dist[row][col], Integer.MAX_VALUE);
			}
		}
		for (int row = 0; row < N; ++row) {
			String mapInfo = br.readLine().trim();
			for (int col = 0; col < M; ++col) {
				map[row][col] = mapInfo.charAt(col) - '0';
			}
		}
		
		bfs();
		
		int ans = Integer.MAX_VALUE;
		for(int breakCnt = 0;breakCnt<=K;++breakCnt) {
			ans = Integer.min(ans, dist[N-1][M-1][breakCnt]);
		}
		if(ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0, 0 });
		dist[0][0][0] = 1;
		visited[0][0][0] = true;
		while (!q.isEmpty()) {
			int[] currNode = q.poll();
			// 현재 행
			int currRow = currNode[0];
			// 현재 열
			int currCol = currNode[1];
			// 현재 위치까지 도달했을 때 벽을 부순 횟수
			int currBreakCnt = currNode[2];

			// 4방 탐색
			for (int dir = 0; dir < 4; ++dir) {
				// 4방 탐색 행
				int nextRow = currRow + dx[dir];
				// 4방 탐색 열
				int nextCol = currCol + dy[dir];
				
				// 범위유효성 검사
				if(nextRow<0||nextCol<0||nextRow>=N||nextCol>=M)
					continue;
				
				// 목적위치가 벽인지 길인 여부에 따라 분기
				if(map[nextRow][nextCol] == 1) {
					// 벽일 경우 -> 벽을 뚫을 수 있는 횟수가 남아있는지 검증 필요
					if(currBreakCnt+1<=K) {
						// 방문 검증
						if(visited[nextRow][nextCol][currBreakCnt+1])
							continue;
						// 횟수가 남아있는 경우 -> 기존에 방문한 이력이 있을 수 있으므로 비교를 통해 갱신
						dist[nextRow][nextCol][currBreakCnt + 1] = Math.min(dist[currRow][currCol][currBreakCnt] + 1, dist[nextRow][nextCol][currBreakCnt + 1]);
						visited[nextRow][nextCol][currBreakCnt+1] = true;
						q.offer(new int[] {nextRow, nextCol, currBreakCnt+1});
					}else {
						// 횟수가 안남아 있는 경우
						continue;
					}
					
				}else {
					// 방문 검증
					if(visited[nextRow][nextCol][currBreakCnt])
						continue;
					// 길일 경우 -> 현재 벽뚫은 횟수 그대로 사용
					dist[nextRow][nextCol][currBreakCnt] = dist[currRow][currCol][currBreakCnt] + 1;
					visited[nextRow][nextCol][currBreakCnt] = true;
					q.offer(new int[] {nextRow, nextCol, currBreakCnt});
				}
				
			}
		}
		
	}
}
