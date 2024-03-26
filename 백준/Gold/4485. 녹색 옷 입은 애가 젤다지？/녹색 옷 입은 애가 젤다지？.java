import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * BFS, Dijkstra
 * 
 * 0. 각 지점까지 도달하는 비용을 INF로 설정한다. 
 * 1. [0, 0]에서부터 Queue가 비지 않을 때 까지 탐색을 진행한다.
 * 2. 4방 탐색을 진행한다.
 * 	2-1. 기존의 도달 비용보다 저렴하다 => 비용을 갱신하고, Queue에 넣는다.
 *  2-2. 기존의 도달 비용보다 비싸다 => PASS
 * 3. 도착지의 비용을 출력한다.
 */
public class Main {
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, 1, -1};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	static int N;
	static int[][] map;
	static int[][] cost;
	
	public static void main(String[] args) throws Exception {
		int tc = 0;
		while((N = Integer.parseInt(br.readLine().trim())) != 0) {
			++tc;
			map = new int[N][N];
			cost = new int[N][N];
			// 비용 초기화
			for(int[] row:cost) {
				Arrays.fill(row, Integer.MAX_VALUE);
			}
			// 맵 초기화
			for(int row = 0; row<N;++row) {
				st = new StringTokenizer(br.readLine().trim());
				for(int col = 0; col<N;++col) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<Pos> q = new ArrayDeque<>();
			q.offer(new Pos(0, 0));
			cost[0][0] = map[0][0];
			// 탐색 시작
			while(!q.isEmpty()) {
				Pos currPos = q.poll();
				// 4방 탐색
				for(int dir = 0; dir < 4; ++dir) {
					int nextRow = currPos.row + dx[dir];
					int nextCol = currPos.col + dy[dir];
					
					// 범위 검증
					if (nextCol < 0 || nextRow < 0 || nextCol >= N || nextRow >= N)
						continue;
					// 비용 검증(갱신 할 필요가 있는가)
					if(cost[nextRow][nextCol] <= 
							cost[currPos.row][currPos.col]+map[nextRow][nextCol])
						continue;

					cost[nextRow][nextCol] = cost[currPos.row][currPos.col] + map[nextRow][nextCol];
					q.offer(new Pos(nextRow, nextCol));
				}
			}
			sb.append(String.format("Problem %d: %d\n", tc, cost[N-1][N-1]));
		}
		System.out.println(sb);
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