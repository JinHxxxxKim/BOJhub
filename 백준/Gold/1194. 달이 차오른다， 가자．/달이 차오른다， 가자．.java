import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * 
 * BFS
 * 
 * 조건1: 열쇠를 얻어야 대응하는 문을 들어갈 수 있다.
 * 조건2: 0->1로 가는 최단 경로를 구해야한다.
 * 
 * a = 1 / b = 2 / c = 4 / d = 8 / e = 16 / f = 32
 * 
 * 1. 맵 정보를 입력받는다. 
 *   1-1. 0을 입력 받으면, State Class에 row, col을 저장한다.
 * 2. visted 배열은 64 x N x N 의 3차원 배열로 설정한다. 
 * 3. 열쇠는 비트마스킹을 통해 저장한다.
 * 4. 시작점에서 BFS를 실행한다.
 *   4-1. 현재 열쇠 조합에서 4방 탐색을 실행한다.
 *     4-1-1. 현재 열쇠 조합으로 방문한 적이 없다면 Queue에 offer
 *     4-1-2. 현재 열쇠 조합으로 방문한 적이 있다면, PASS
 *   4-2. 다음 노드의 경우의 수
 *     - 벽: PASS
 *     - 열쇠: 현재 열쇠 조합 || 만나 열쇠 => 새로운 열쇠 조합을 만든다.
 *     - 문: 현재 열쇠 조합에서 통과할 수 있는 문인지 검증한다.
 *     - 빈칸: 방문한적이 없다면 Queue offer
 *     - 출구: 최단 경로를 출력하고 저장한다.
 * 
 */
public class Main {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	static final char[] keys = {'a', 'b', 'c', 'd', 'e', 'f'};
	static final char[] doors = {'A', 'B', 'C', 'D', 'E', 'F'};
	static final char WALL = '#';
	static final char FLOOR = '.';
	static final char EXIT = '1';
	
	static char[][] map;
	static boolean[][][] isVisited;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		isVisited = new boolean[64][N][M]; // 열쇠를 가지고 있을 수 있는 조합은 총 64가지

		State startState = null;
		// 맵 입력
		for (int row = 0; row < N; ++row) {
			String rowInfo = br.readLine().trim();
			for (int col = 0; col < M; ++col) {
				char currChar = rowInfo.charAt(col);
				if (currChar == '0') {
					// 시작점 저장
					startState = new State(row, col, 0, 0);
					map[row][col] = '.';
				}else {
					map[row][col] = currChar;
				}
			}
		}
//		for (int idx = 0; idx < doors.length; ++idx) {
//			System.out.println((int)Math.pow(2, (int)doors[idx] - 'A'));
//		}
		// 로직 시작
		Queue<State> q = new ArrayDeque<>();
		q.offer(startState);
		isVisited[0][startState.row][startState.col] = true;
		while(!q.isEmpty()) {
			State currNode = q.poll();
			int currRow = currNode.row;
			int currCol = currNode.col;
			int currDist = currNode.dist;
			int currKeyStatus = currNode.keyStatus;
//			System.out.println(currNode);
			// 4방 탐색
			for (int dir = 0; dir < 4; ++dir) {
				int nextRow = currRow + dx[dir];
				int nextCol = currCol + dy[dir];
				int nextDist = currDist + 1;
				int nextKeyStatus = currKeyStatus;

				// 범위 검증
				if (nextCol < 0 || nextRow < 0 || nextCol >= M || nextRow >= N)
					continue;
				// 나올 수 있는 경우의 수 확인
				char nextChar = map[nextRow][nextCol];

				// 1. 벽
				if (nextChar == WALL) {
					continue;
				}
				// 2. 빈칸
				else if (nextChar == FLOOR) {
					if (!isVisited[nextKeyStatus][nextRow][nextCol]) {
						isVisited[nextKeyStatus][nextRow][nextCol] = true;
						q.offer(new State(nextRow, nextCol, nextDist, nextKeyStatus));
					}
					continue;
				}
				// 3. 문
				for (int idx = 0; idx < doors.length; ++idx) {
					if (nextChar == doors[idx]) {
						// 현재 그 키를 가지고 있다
						if (((int) Math.pow(2, (int) doors[idx] - 'A') & currKeyStatus) > 0) {
							if (!isVisited[nextKeyStatus][nextRow][nextCol]) {
								isVisited[nextKeyStatus][nextRow][nextCol] = true;
								q.offer(new State(nextRow, nextCol, nextDist, nextKeyStatus));
							}
						}
						// 현재 그 키를 가지고 있지 않다
						else {
							break;
						}
					}
				}
				// 4. 키
				for (int idx = 0; idx < keys.length; ++idx) {
					if(nextChar == keys[idx]) {
						// 키 합치기
						nextKeyStatus = (int) Math.pow(2, (int) doors[idx] - 'A') | nextKeyStatus;
						if(!isVisited[nextKeyStatus][nextRow][nextCol]) {
							isVisited[nextKeyStatus][nextRow][nextCol] = true;
							q.offer(new State(nextRow, nextCol, nextDist, nextKeyStatus));
						}else {
							break;
						}
					}
				}
				// 5. 출구
				if(nextChar == EXIT) {
					System.out.println(nextDist);
					return;
				}
			}
		}
		System.out.println(-1);
	}

	static class State {
		int row, col;
		int dist;
		int keyStatus;

		public State(int row, int col, int dist, int keyStatus) {
			super();
			this.row = row;
			this.col = col;
			this.dist = dist;
			this.keyStatus = keyStatus;
		}

		@Override
		public String toString() {
			return "State [row=" + row + ", col=" + col + ", dist=" + dist + ", keyStatus=" + keyStatus + "]";
		}
		
	}
}
