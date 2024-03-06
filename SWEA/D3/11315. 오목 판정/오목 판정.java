
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 좌상단에서 우하단으로 탐색을 진행한다.
 * 	- 탐색 방향: 3시, 5시, 6시, 8시 총 4방향
 * 2. 오목돌이 있는 곳이라면, 해당 위치부터 한 방향으로 DFS탐색을 진행
 * 	2-1. depth가 5가 되면 flag를 참으로 바꾸고 종료한다.
 * 	2-2. 돌이 없거나, 범위 밖이라면 종료한다.
 */
public class Solution {
	// 델타 배열
	static final int[] dx = { 0, 1, 1, 1 };
	static final int[] dy = { 1, 1, 0, -1 };
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int N;
	static char[][] map;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			N = Integer.parseInt(br.readLine().trim());
			map = new char[N][N];
			// 오목판 입력
			for (int row = 0; row < N; ++row) {
				String rowInfo = br.readLine().trim();
				for (int col = 0; col < N; ++col) {
					map[row][col] = rowInfo.charAt(col);
				}
			}
			flag = false;
			// 탐색 시작
			for (int row = 0; row < N; ++row) {
				if(flag)
					break;
				for (int col = 0; col < N; ++col) {
					if(map[row][col] == 'o') {
						// 방향 결정
						for (int dir = 0; dir < 4; ++dir) {
							int tempRow = row + dx[dir];
							int tempCol = col + dy[dir];

							// 범위 검증
							if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= N)
								continue;
							// 바둑돌 유무
							if(map[tempRow][tempCol]=='.')
								continue;
							dfs(row, col, 1, dir);
						}
					}
				}
			}
			
			if(flag)
				sb.append(String.format("#%d %s\n", testCase, "YES"));
			else
				sb.append(String.format("#%d %s\n", testCase, "NO"));
		}
		System.out.println(sb);
	}


	// 해당 위치에서 dir방향으로 놓이 바둑돌 검사
	private static void dfs(int row, int col, int depth, int dir) {
		// 오목 완성
		if(depth == 5) {
			flag = true;
			return;
		}
		int tempRow = row + dx[dir];
		int tempCol = col + dy[dir];
		// 범위 검증
		if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= N)
			return;
		// 바둑돌 유무
		if(map[tempRow][tempCol]=='.')
			return;
		
		dfs(tempRow, tempCol, depth+1, dir);
	}
}
