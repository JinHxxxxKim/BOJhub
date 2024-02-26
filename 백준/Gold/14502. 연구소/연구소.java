

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * 
 * 1. 행, 열에 대해 3개를 선택하는 조합으로 완전 탐색 진행
 * 2. 해당 좌표가 이미 벽, 바이러스라면 PASS
 * 		2-1. 기저조건(3개 모두 선택했다면): 바이러스에 대해 BFS 실행 후, 남아있는 영역 계산
 * 		2-2. 안전 지대 영역의 수 갱신
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	static final int[] dx = new int[] { -1, 0, 1, 0 };
	static final int[] dy = new int[] { 0, 1, 0, -1 };
	static final int WALL_NUMBER = 3;

	static int rowMax, colMax;
	static int[][] map;
	static int emtpySpace, virusSpace;
	static int ans;
	static Pos[] initVirusPos;

	// 조합 관련 변수
	static Pos[] selectList;
	static Pos[] elementList;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		rowMax = Integer.parseInt(st.nextToken());
		colMax = Integer.parseInt(st.nextToken());
		map = new int[rowMax][colMax];
		selectList = new Pos[WALL_NUMBER];

		// 맵 정보 입력
		for (int row = 0; row < rowMax; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < colMax; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 0) {
					// 빈 공간 개수 확인
					emtpySpace++;
				} else if (map[row][col] == 2) {
					virusSpace++;
				}
			}
		}

		int elementCnt = 0;
		int virusCnt = 0;
		elementList = new Pos[emtpySpace];
		initVirusPos = new Pos[virusSpace];
		for (int row = 0; row < rowMax; ++row) {
			for (int col = 0; col < colMax; ++col) {
				if (map[row][col] == 0) {
					elementList[elementCnt++] = new Pos(row, col);
				} else if (map[row][col] == 2) {
					initVirusPos[virusCnt++] = new Pos(row, col);
				}
			}
		}
		
		ans = 0;
		selectWall(0, 0);
		System.out.println(ans);

	}

	private static void selectWall(int selectIdx, int elementIdx) {
		// 기저조건
		if (selectIdx == WALL_NUMBER) {
			// 선정한 위치 벽 세우기
			for (Pos wallPos : selectList)
				map[wallPos.x][wallPos.y] = 1;

			int virusArea = countVirusArea();
			
			// 선정한 위치 벽 허물기
			for (Pos wallPos : selectList)
				map[wallPos.x][wallPos.y] = 0;


			// 안전 영역크기 계산 후 갱신
			ans = Math.max(ans, emtpySpace - 3 - virusArea + virusSpace);

			return;
		}
		if (elementIdx == emtpySpace) {
			return;
		}

		// 해당 좌표 선택O
		selectList[selectIdx] = elementList[elementIdx];
		selectWall(selectIdx + 1, elementIdx + 1);

		// 해당 좌표 선택X
		selectWall(selectIdx, elementIdx + 1);
	}

	private static int countVirusArea() {
		int ret = 0;
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[rowMax][colMax];
		for (Pos initVirus : initVirusPos) {
			q.offer(initVirus);
			isVisited[initVirus.x][initVirus.y] = true;
		}
		
		while (!q.isEmpty()) {
			Pos currNode = q.poll();
			ret++;
			int currRow = currNode.x;
			int currCol = currNode.y;

			for (int dir = 0; dir < 4; ++dir) {
				int tempRow = currRow + dx[dir];
				int tempCol = currCol + dy[dir];

				// 범위 검증
				if (tempRow < 0 || tempCol < 0 || tempRow >= rowMax || tempCol >= colMax)
					continue;
				// 방문 검증
				if (isVisited[tempRow][tempCol])
					continue;
				// 벽 검증
				if (map[tempRow][tempCol] == 1)
					continue;
				
				isVisited[tempRow][tempCol] = true;
				q.offer(new Pos(tempRow, tempCol));
			}
		}
		return ret;
	}

	static class Pos {
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
}