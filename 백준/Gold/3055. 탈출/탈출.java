import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS
 * 
 * 1. 고슴도치의 시작 위치, 비버 굴의 위치를 저장해둔 뒤, 해당 칸은 .으로 입력받는다.
 * 
 * 2. 초기 물이 차 있는 지역을 waterQueue에 offer한다
 *   2-1. 물이 각 지점까지 도달하는데 걸리는 시간을 계산하여 저장해둔다.
 * 
 * 3. BFS 탐색을 시작한다.
 *   3-1. 고슴도치의 사방탐색을 진행한다.
 *     3-1-1. 물이 이미 차 있거나(물의 소요시간보다 크거나 같다), 범위 밖이라면, PASS
 *     3-1-2. 이동 가능한 위치라면,
 *     	 - 방문처리
 *       - 거리 계산 후 저장
 *       - moveQueue에 저장
 * 4. 최종적으로 도착지점의 dist가 0일 경우, KAKTUS 출력
 */
public class Main {
	static final int[] dx = { -1, 1, 0, 0 };
	static final int[] dy = { 0, 0, -1, 1 };
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static char[][] map;
	static boolean[][] isVisited;
	static int[][] dist, waterDist;
	static Pos startPos, endPos;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		isVisited = new boolean[N][M];
		dist = new int[N][M];
		waterDist = new int[N][M];
		
		Queue<Pos> q = new ArrayDeque<>();
		
		// 맵 입력
		for (int row = 0; row < N; ++row) {
			String rowInfo = br.readLine().trim();
			for (int col = 0; col < M; ++col) {
				waterDist[row][col] = Integer.MAX_VALUE;
				map[row][col] = rowInfo.charAt(col);
				if(map[row][col] == 'D') {
					endPos = new Pos(row, col);
				}else if(map[row][col] == 'S') {
					startPos = new Pos(row, col);
				}else if(map[row][col] == '*') {
					waterDist[row][col] = 0;
					q.offer(new Pos(row, col));
					isVisited[row][col] = true;
				}
			}
		}
		
		// 물이 각 지점까지 도달하는 시간 계산
		while(!q.isEmpty()) {
			Pos currWaterPos = q.poll();
			int cRow = currWaterPos.row;
			int cCol = currWaterPos.col;
			// 4방 탐색
			for (int dir = 0; dir < 4; ++dir) {
				int nRow = cRow + dx[dir];
				int nCol = cCol + dy[dir];
				
				// 범위 검증
				if(nRow<0||nCol<0||nRow>=N||nCol>=M) continue;
				// 방문, 벽 검증
				if(isVisited[nRow][nCol] || map[nRow][nCol] == 'X') continue;
				// 비버의 집 검증
				if(map[nRow][nCol] == 'D' ) continue;
				
				waterDist[nRow][nCol] = waterDist[cRow][cCol] + 1; 
				isVisited[nRow][nCol] = true;
				q.offer(new Pos(nRow, nCol));
			}
		}
		
//		for(int[] r: waterDist) {
//			System.out.println(Arrays.toString(r));
//		}
//		System.out.println("=====================");
		
		// 비버 이동
		isVisited = new boolean[N][M];
		q = new ArrayDeque<>();
		q.offer(startPos);
		isVisited[startPos.row][startPos.col] = true;
		while(!q.isEmpty()) {
			Pos pos = q.poll();
			int cRow = pos.row;
			int cCol = pos.col;
			// 4방 탐색
			for (int dir = 0; dir < 4; ++dir) {
				int nRow = cRow + dx[dir];
				int nCol = cCol + dy[dir];
				
				// 범위 검증
				if(nRow<0||nCol<0||nRow>=N||nCol>=M) continue;
				// 방문, 벽 검증
				if(isVisited[nRow][nCol] || map[nRow][nCol] == 'X') continue;
				// 물 검증
				if(dist[cRow][cCol] + 1 >= waterDist[nRow][nCol]) continue;
				// 비버의 집 검증
				if(map[nRow][nCol] == 'D') {
//					for(int[] r: dist) {
//						System.out.println(Arrays.toString(r));
//					}
					System.out.println(dist[cRow][cCol]+1);
					return;
				}
				dist[nRow][nCol] = dist[cRow][cCol] + 1; 
				isVisited[nRow][nCol] = true;
				q.offer(new Pos(nRow, nCol));
			}
		}

//		for(int[] r: dist) {
//			System.out.println(Arrays.toString(r));
//		}
		
		System.out.println("KAKTUS");
	}
	
	static class Pos{
		int row, col;

		public Pos(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		
	}
}
