
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**

 * 
 * 1. 첫 파이프의 끝점을 시작점으로 BFS 탐색
 * 		- 파이르가 놓인 방향을 함꼐 저장한다
 * 2. 현재 파이프를 꺼낸 뒤, 가능한 파이프의 상태를 다시 큐에 넣는다.
 * 3. 목적지에 도달하면 카운트 증가 
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;
	static final int DIAGONAL = 2;

	static int N;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < N; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		Queue<Pipe> q = new ArrayDeque<>();
		q.offer(new Pipe(0, 1, HORIZONTAL));
		while(!q.isEmpty()) {
			Pipe currPipe = q.poll();
			int row = currPipe.x;
			int col = currPipe.y;
			if(row == N-1 && col == N-1) {
				++ans;
				continue;
			}
			int pipeState = currPipe.dir;
			switch (pipeState) {
			case HORIZONTAL:
				if (col + 1 < N && map[row][col + 1] != 1) {
					q.offer(new Pipe(row, col + 1, HORIZONTAL));
				}
				if(col+1<N && row + 1<N 
						&& map[row][col+1]!=1
						&& map[row+1][col+1]!=1
						&& map[row+1][col]!=1) {
					q.offer(new Pipe(row+1, col+1, DIAGONAL));
				}
				break;
			case VERTICAL:
				if (row + 1 < N && map[row + 1][col] != 1) {
					q.offer(new Pipe(row + 1, col, VERTICAL));
				}
				if(col+1<N && row + 1<N 
						&& map[row][col+1]!=1
						&& map[row+1][col+1]!=1
						&& map[row+1][col]!=1) {
					q.offer(new Pipe(row+1, col+1, DIAGONAL));
				}
				break;
			case DIAGONAL:
				if (col + 1 < N && map[row][col + 1] != 1) {
					q.offer(new Pipe(row, col + 1, HORIZONTAL));
				}
				if (row + 1 < N && map[row + 1][col] != 1) {
					q.offer(new Pipe(row + 1, col, VERTICAL));
				}
				if(col+1<N && row + 1<N 
						&& map[row][col+1]!=1
						&& map[row+1][col+1]!=1
						&& map[row+1][col]!=1) {
					q.offer(new Pipe(row+1, col+1, DIAGONAL));
				}
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	static class Pipe{
		int x, y;
		int dir;
		public Pipe(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		
	}
}
