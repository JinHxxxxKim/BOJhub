

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1. 높이: K + 1, 가로: W, 세로: H인 DP 배열을 생성한다.
 * 		- DP의 동적 테이블에서 높이가 의미하는 것 = 말처럼 움직인 횟수
 * 			- 높이가 0이다  = 해당 위치까지 도달할 때, 말처럼 움직인 횟수가 0이다.
 * 2. [0, 0]부터 BFS를 통해 정점을 순차적으로 탐색한다.
 * 		2-1. 해당 위치에서 도달할 수 있는 방법을 모두 구하게 된다.
 * 			2-1-1. 기존 원숭이의 움직임
 * 				- 사방탐색을 통해 DP[0 ~ K][row][col]을 업데이트 한다.
 * 			2-1-2. 말의 움직임
 * 				- 말의 움직임을 통해 DP[현재 높이 + 1][row][col]을 업데이트 한다.
 * 3. 목적지에 도달하면 모든 값 중 최소값을 출력한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static final int INF = Integer.MAX_VALUE;
	// 원숭이의 움직임
	static final int[] monkeyDX = new int[] { -1, 0, 1, 0 };
	static final int[] monkeyDY = new int[] { 0, 1, 0, -1 };
	// 말의 움직임
	static final int[] horseDX = new int[] { -2, -1, 1, 2, 2, 1, -1, -2 };
	static final int[] horseDY = new int[] { 1, 2, 2, 1, -1, -2, -2, -1 };
	
	static int K;
	static int rowSize, colSize;
	static int[][] map;
	
	static int[][][] DP;

	public static void main(String[] args) throws Exception {
		K = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		map = new int[rowSize][colSize];
		DP = new int[K + 1][rowSize][colSize];
		
		for (int row = 0; row < rowSize; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		moveMonkey();

//		for (int[][] h : DP) {
//			for (int[] r : h) {
//				System.out.println(Arrays.toString(r));
//			}
//			System.out.println("+================+");
//		}

		if(rowSize == 1 && colSize == 1) {
			System.out.println(0);
		}else {
			int ans = INF;
			for (int idx = 0; idx <= K; ++idx) {
				if(DP[idx][rowSize - 1][colSize - 1] == 0)
					continue;
				ans = Math.min(ans, DP[idx][rowSize - 1][colSize - 1]);
			}
			if (ans == INF) {
				System.out.println(-1);
			} else {
				System.out.println(ans);
			}
		}
	}

	private static void moveMonkey() {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(new Pos(0, 0), 0));
		boolean[][][] isVisited = new boolean[K+1][rowSize][colSize];
		isVisited[0][0][0] = true;
		DP[0][0][0] = 0;
		while (!q.isEmpty()) {
			Node currNode = q.poll();
//			System.out.println(currNode);
			int row = currNode.pos.x;
			int col = currNode.pos.y;
			int currCnt = currNode.cnt;

			for (int dir = 0; dir < monkeyDX.length; ++dir) {
				int tempRow = row + monkeyDX[dir];
				int tempCol = col + monkeyDY[dir];

				// 범위 검증
				if (tempRow < 0 || tempCol < 0 || tempRow >= rowSize || tempCol >= colSize)
					continue;
				// 벽 검증
				if (map[tempRow][tempCol] == 1)
					continue;
				// 방문 검증
				if (isVisited[currCnt][tempRow][tempCol])
					continue;

				q.offer(new Node(new Pos(tempRow, tempCol), currCnt));
				DP[currCnt][tempRow][tempCol] = DP[currCnt][row][col] + 1;
				isVisited[currCnt][tempRow][tempCol] = true;
			}
			
			for (int dir = 0; dir < horseDX.length; ++dir) {
				if(currCnt >= K)
					break;
				
				int tempRow = row + horseDX[dir];
				int tempCol = col + horseDY[dir];

				// 범위 검증
				if (tempRow < 0 || tempCol < 0 || tempRow >= rowSize || tempCol >= colSize)
					continue;
				// 벽 검증
				if (map[tempRow][tempCol] == 1)
					continue;
				// 방문 검증
				if (isVisited[currCnt + 1][tempRow][tempCol])
					continue;

				q.offer(new Node(new Pos(tempRow, tempCol), currCnt + 1));
				DP[currCnt + 1][tempRow][tempCol] = DP[currCnt][row][col] + 1;
				isVisited[currCnt + 1][tempRow][tempCol] = true;	
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

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
		
	}
	
	static class Node{
		Pos pos;
		int cnt;
		public Node(Pos pos, int cnt) {
			super();
			this.pos = pos;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "Node [pos=" + pos + ", cnt=" + cnt + "]";
		}
		
	}
}
