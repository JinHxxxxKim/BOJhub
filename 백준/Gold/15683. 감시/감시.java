
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 사무실의 세로 크기 N과 가로 크기 M을 입력받는다.
 * 2. 사무실 각 칸의 정보를 입력받는다.
 * 3. 각 CCTV마다 설정할 수 있는 모든 경우의 수를 구한 뒤, 사각지대를 구한다.
 * 		- CCTV1: 총 4가지 경우의 수 
 * 		- CCTV2: 종 2가지 경우의 수
 * 		- CCTV3: 총 4가지 경우의 수
 * 		- CCTV4: 총 4가지 경우의 수
 * 		- CCTV5: 총 1가지 경우의 수
 * 		CCTV의 개수는 최대 8개를 넘지않는다. -> 4^8 = 65536
 * 
 * 모든  CCTV는 CCTV1번의 반복이다.
 */
public class Main {
	// 12시, 3시, 6시, 9시 순서 [0, 1, 2, 3]
	private static final int[] dx = new int[] { -1, 0, 1, 0 };
	private static final int[] dy = new int[] { 0, 1, 0, -1 };

	private static final int CCTV_1 = 1;
	private static final int CCTV_2 = 2;
	private static final int CCTV_3 = 3;
	private static final int CCTV_4 = 4;
	private static final int CCTV_5 = 5;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M;
	private static int[][] map;
	private static int[][] tempMap;
	private static List<CCTV> cctvs; // cctv 정보 저장 리스트
	private static int[] cctvDirection;

	private static int minArea;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cctvs = new ArrayList<CCTV>();
		map = new int[N][M];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < M; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] != 0 && map[row][col] != 6) {
					cctvs.add(new CCTV(row, col, map[row][col]));
				}
			}
		}

		// cctv의 방향 저장
		cctvDirection = new int[cctvs.size()];
		minArea = M * N;
		combination(0);

		System.out.println(minArea);
	}

	private static void combination(int currCCTVIdx) {
		// 기저조건
		if (currCCTVIdx == cctvs.size()) {
//			System.out.println(Arrays.toString(cctvDirection));
			tempMap = new int[N][M];
			for (int row = 0; row < N; ++row) {
				for (int col = 0; col < M; ++col) {
					tempMap[row][col] = map[row][col];
				}
			}
			for (int cctcIdx = 0; cctcIdx < cctvs.size(); ++cctcIdx) {
				CCTV currCCTV = cctvs.get(cctcIdx);
				int direction = cctvDirection[cctcIdx];
				switch (currCCTV.type) {
				case CCTV_1:
					// dir: {0: 상, 1: 우, 2: 하, 3: 좌}
					oneWayCCTV(currCCTV.x, currCCTV.y, direction);
					break;
				case CCTV_2:
					// dir: {0: 수평, 1: 수직}
					switch (direction) {

					case 0:

						oneWayCCTV(currCCTV.x, currCCTV.y, 1);
						oneWayCCTV(currCCTV.x, currCCTV.y, 3);
						break;
					case 1:
						oneWayCCTV(currCCTV.x, currCCTV.y, 0);
						oneWayCCTV(currCCTV.x, currCCTV.y, 2);
						break;
					}
					break;
				case CCTV_3:
					// dir: {0: 상우, 1: 우하, 2: 하좌, 3: 좌상}
					switch (direction) {
					case 0:
						oneWayCCTV(currCCTV.x, currCCTV.y, 0);
						oneWayCCTV(currCCTV.x, currCCTV.y, 1);
						break;
					case 1:
						oneWayCCTV(currCCTV.x, currCCTV.y, 1);
						oneWayCCTV(currCCTV.x, currCCTV.y, 2);
						break;
					case 2:
						oneWayCCTV(currCCTV.x, currCCTV.y, 2);
						oneWayCCTV(currCCTV.x, currCCTV.y, 3);
						break;
					case 3:
						oneWayCCTV(currCCTV.x, currCCTV.y, 3);
						oneWayCCTV(currCCTV.x, currCCTV.y, 0);
						break;
					}
					break;
				case CCTV_4:
					// dir: {0: 좌상우, 1: 상우하, 2: 우하좌, 3: 하좌상}
					switch (direction) {
					case 0:
						oneWayCCTV(currCCTV.x, currCCTV.y, 3);
						oneWayCCTV(currCCTV.x, currCCTV.y, 0);
						oneWayCCTV(currCCTV.x, currCCTV.y, 1);
						break;
					case 1:
						oneWayCCTV(currCCTV.x, currCCTV.y, 0);
						oneWayCCTV(currCCTV.x, currCCTV.y, 1);
						oneWayCCTV(currCCTV.x, currCCTV.y, 2);
						break;
					case 2:
						oneWayCCTV(currCCTV.x, currCCTV.y, 1);
						oneWayCCTV(currCCTV.x, currCCTV.y, 2);
						oneWayCCTV(currCCTV.x, currCCTV.y, 3);
						break;
					case 3:
						oneWayCCTV(currCCTV.x, currCCTV.y, 2);
						oneWayCCTV(currCCTV.x, currCCTV.y, 3);
						oneWayCCTV(currCCTV.x, currCCTV.y, 0);
						break;
					}
					break;
				case CCTV_5:
					// dir: {-1: 상하좌우}
					oneWayCCTV(currCCTV.x, currCCTV.y, 0);
					oneWayCCTV(currCCTV.x, currCCTV.y, 1);
					oneWayCCTV(currCCTV.x, currCCTV.y, 2);
					oneWayCCTV(currCCTV.x, currCCTV.y, 3);
					break;
				}
			}
//			System.out.println("=====================");
//			for(int[] row:tempMap) {
//				System.out.println(Arrays.toString(row));
//			}
			int result = getZeroArea();
//			System.out.println("result: "+result);
			minArea = Math.min(minArea, result);

			return;
		}

		CCTV currCCTV = cctvs.get(currCCTVIdx);
		switch (currCCTV.type) {
		case CCTV_1:
			// dir: {0: 상, 1: 우, 2: 하, 3: 좌}
			for (int dir = 0; dir < 4; ++dir) {
				cctvDirection[currCCTVIdx] = dir;
				combination(currCCTVIdx + 1);
			}
			break;
		case CCTV_2:
			// dir: {0: 수평, 1: 수직}
			for (int dir = 0; dir < 2; ++dir) {
				cctvDirection[currCCTVIdx] = dir;
				combination(currCCTVIdx + 1);
			}
			break;
		case CCTV_3:
			// dir: {0: 상우, 1: 우하, 2: 하좌, 3: 좌상}
			for (int dir = 0; dir < 4; ++dir) {
				cctvDirection[currCCTVIdx] = dir;
				combination(currCCTVIdx + 1);
			}
			break;
		case CCTV_4:
			// dir: {0: 좌상우, 1: 상우하, 2: 우하좌, 3: 하좌상}
			for (int dir = 0; dir < 4; ++dir) {
				cctvDirection[currCCTVIdx] = dir;
				combination(currCCTVIdx + 1);
			}
			break;
		case CCTV_5:
			// dir: {-1: 상하좌우}
			cctvDirection[currCCTVIdx] = -1;
			combination(currCCTVIdx + 1);
			break;
		}
	}

	// dir 방향으로 [row, col]부터 사각지대를 없애는 함수
	public static void oneWayCCTV(int row, int col, int dir) {
		// 벽을 만나거나, 범위 밖으로 나가기 전까지
		int tempRow = row + dx[dir];
		int tempCol = col + dy[dir];

		while (true) {
//			System.out.println("tempRow: "+tempRow);
//			System.out.println("tempCol: "+tempCol);
//			System.out.println("dir: "+dir);

			// 범위 체크
			if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M)
				break;
			// 벽 체크
			if (tempMap[tempRow][tempCol] == 6)
				break;
			// -1: CCTV의 감시 구역
			tempMap[tempRow][tempCol] = -1;

			tempRow = tempRow + dx[dir];
			tempCol = tempCol + dy[dir];
		}
	}

	// 사각지대 구하는 메소드
	public static int getZeroArea() {
		int sum = 0;
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < M; ++col) {
				if (tempMap[row][col] == 0) {
					++sum;
				}
			}
		}
		return sum;
	}

	static class CCTV {
		int x, y;
		int type;

		public CCTV(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public String toString() {
			return "CCTV [x=" + x + ", y=" + y + ", type=" + type + "]";
		}

	}
}