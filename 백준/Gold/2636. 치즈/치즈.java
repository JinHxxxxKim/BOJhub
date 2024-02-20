

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. 사각형 모양 판의 세로와 가로의 길이를 입력받는다.
 * 2. 한시간 간격마다 [0, 0]에서 인접한 치즈를 찾는다.
 * 		- bfs탐색을 통해 공기층과 인접한 치즈 칸을 Queue에 저장한다.
 * 		- 탐색이 끝났다면, Queue에 저장되어있는 위치 정보를 모두 읽어와 0으로 바꾼다.
 * 3. 한시간 간격으로 지워지는 치즈의 개수를 총 치즈의 개수에서 뺀다.
 * 4. 치즈의 총 개수가 0개가 되면, 이전 치즈 개수를 총 걸린 시간과 함께 출력한다. 
 * 
 */
public class Main {
	private static final int[] dx = new int[]{-1, 0, 1, 0};
	private static final int[] dy = new int[]{0, 1, 0, -1};
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int rowSize;
	private static int colSize;
	private static int[][] map;
	private static boolean[][] visited;
	private static int currCheeseCnt;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colSize; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
				// 총 치즈 개수 카운팅
				if(map[row][col] == 1)
					++currCheeseCnt;
			}
		}
		
		// 로직 시작
		// 종료 직전 치즈 개수를 저장하는 변수
		int beforeEndCheeseCnt = currCheeseCnt;
		// 걸린 시간
		int reqTime = 0;
		while(currCheeseCnt>0) {
//			System.out.println("================================================");
//			for(int[] row:map) {
//				System.out.println(Arrays.toString(row));
//			}
			int nearAirCheese = getNearAirCheese();
			beforeEndCheeseCnt = nearAirCheese;
			currCheeseCnt -= nearAirCheese;
			++reqTime;
		}
		System.out.println(reqTime);
		System.out.println(beforeEndCheeseCnt);
	}

	// 공기와 접한 치즈 개수를 구하는 메소드
	private static int getNearAirCheese() {
		Pos startPos = new Pos(0, 0);
		Queue<Pos> saveCheese = new ArrayDeque<>();
		Queue<Pos> q = new ArrayDeque<>();
		visited = new boolean[rowSize][colSize];
		q.add(startPos);
		visited[0][0] = true;
		while (!q.isEmpty()) {
			Pos currNode = q.poll();
			int currRow = currNode.x;
			int currCol = currNode.y;

			// 4방 탐색
			for (int dir = 0; dir < 4; ++dir) {
				int tempRow = currRow + dx[dir];
				int tempCol = currCol + dy[dir];

				// 범위 검증
				if (tempRow < 0 || tempCol < 0 || tempRow >= rowSize || tempCol >= colSize)
					continue;
				// 방문 검증
				if (visited[tempRow][tempCol])
					continue;

				// 공기라면 그냥 큐에 추가
				if (map[tempRow][tempCol] == 0) {
					q.offer(new Pos(tempRow, tempCol));
				} else {
					// 치즈일 경우
					saveCheese.offer(new Pos(tempRow, tempCol));
				}
				visited[tempRow][tempCol] = true;
			}
		}
		int cheeseCnt = saveCheese.size();

		// 공기와 접한 치즈를 녹이기
		while (!saveCheese.isEmpty()) {
			Pos cheese = saveCheese.poll();
			map[cheese.x][cheese.y] = 0;
		}
		return cheeseCnt;
	}

	// 위치 클래스
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}