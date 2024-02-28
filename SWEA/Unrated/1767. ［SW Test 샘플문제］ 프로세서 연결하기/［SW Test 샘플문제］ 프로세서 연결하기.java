
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. 각 코어마다 전선이 뻗어 나갈 수 있는 방향은 4가지가 존재 + 해당 코어를 연결하지 않는 경우 1가지
 * 2. 모든 코어에 대해 모든 방향으로 뻗어나가는 경우를 모두 고려한다.(5^12) 백트래킹으로 불가능한 경우 pruning
 * 3. 멕시노스 가장자리에 붙은 코어는 바로 연결하고, 제외한다.
 * 4. 상, 우, 하, 좌, 연결X 순서로 탐색
 * 5. 재귀 호출 전 맵 변경, 반환 후 원복
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static final int[] dx = new int[] {-1, 0, 1, 0};
	private static final int[] dy = new int[] {0, 1, 0, -1};

	private static int N;
	private static int[][] map;
	private static List<Core> coreList;
	private static int ans;
	private static int maxCoreCnt, minCableLen;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			coreList = new ArrayList<>();
			// 입력 및 코어 위치 저장
			for (int row = 0; row < N; ++row) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; ++col) {
					map[row][col] = Integer.parseInt(st.nextToken());
					if (map[row][col] == 1) {
						// 가장자리 코어 확인
						if(row == 0 || row == N-1 || col == 0 || col == N-1) {
							++ans;
						}else {
							coreList.add(new Core(row, col));
						}
					}
				}
			}
			
//			System.out.println(ans);
//			System.out.println(coreList.size());
			maxCoreCnt = 0;
			minCableLen = Integer.MAX_VALUE;
			for (int dir = 0; dir < 5; ++dir) {
				connectCore(0, dir, 0, 0);
			}
			
			if(minCableLen == Integer.MAX_VALUE) {
				System.out.println("#"+testCase+" 0");
//				sb.append(String.format("#%d %d\n", testCase, 0));
			}else {
				System.out.println("#"+testCase+" "+minCableLen);
//				sb.append(String.format("#%d %d\n", testCase, minCableLen));
			}
			
		}
		System.out.println(sb);
	}

	// dir 방향으로 해당 인덱스의 코어를 연결
	private static void connectCore(int coreIdx, int dir, int coreCnt, int cableLen) {
		// 기저 조건(모든 코어 확인)
		if(coreIdx == coreList.size()) {
			if(coreCnt < maxCoreCnt) {
				return;
			} else if(coreCnt == maxCoreCnt) {
				minCableLen = Math.min(minCableLen, cableLen);
			} else {
				maxCoreCnt = coreCnt;
				minCableLen = cableLen;
			}
			
			return;
		}
		if(coreList.size() - coreIdx + coreCnt < maxCoreCnt)
			return;
		
		if (dir == 4) {
			// 해당 코어를 연결하지 않겠다.
			for (int nextDir = 0; nextDir < 5; ++nextDir) {
				connectCore(coreIdx + 1, nextDir, coreCnt, cableLen);
			}
		} else {
			// 해당 코어를 dir방향으로 연결 시도
			if (canConnect(coreIdx, dir)) {
				changeMap(coreIdx, dir, 1);
				int currCableLen = getLen(coreIdx, dir);
				// 연결 가능
				for (int nextDir = 0; nextDir < 5; ++nextDir) {
					connectCore(coreIdx + 1, nextDir, coreCnt + 1, cableLen + currCableLen);
				}
				changeMap(coreIdx, dir, 0);
			} else {
				// 연결 불가능
				for (int nextDir = 0; nextDir < 5; ++nextDir) {
					connectCore(coreIdx + 1, nextDir, coreCnt, cableLen);
				}
			}
		}
	}
	
	private static void changeMap(int coreIdx, int dir, int type) {
		Core currCore = coreList.get(coreIdx);
		int row = currCore.x;
		int col = currCore.y;

		int tempRow = row + dx[dir];
		int tempCol = col + dy[dir];

		while (tempRow >= 0 && tempCol >= 0 && tempRow < N && tempCol < N) {
			map[tempRow][tempCol] = type;
			tempRow += dx[dir];
			tempCol += dy[dir];
		}
	}

	private static int getLen(int coreIdx, int dir) {
		Core currCore = coreList.get(coreIdx);
		if (dir == 0) {
			return currCore.x;
		} else if (dir == 1) {
			return (N - 1) - currCore.y;
		} else if (dir == 2) {
			return (N - 1) - currCore.x;
		} else if (dir == 3) {
			return currCore.y;
		}
		return 0;
	}

	private static boolean canConnect(int coreIdx, int dir) {
		Core currCore = coreList.get(coreIdx);
		int row = currCore.x;
		int col = currCore.y;

		int tempRow = row + dx[dir];
		int tempCol = col + dy[dir];

		while (tempRow >= 0 && tempCol >= 0 && tempRow < N && tempCol < N) {
			if(map[tempRow][tempCol] == 1)
				return false;
			tempRow += dx[dir];
			tempCol += dy[dir];
		}
		return true;
	}
	static class Core{
		int x, y;

		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}