
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 도착점을 시작점으로 하여 탐색을 시작한다.
 * 2. 탐색 순서는 좌, 우, 상 순서로 진행한다.
 * 	2-1. 탐색 시, 1을 발견하면, 위치를 갱신하고 continue;
 * 	2-2. 행의 값이 -1이 되면 종료한다.(기저조건)
 * 3. 이 때, 열의 값을 출력한다.
 */
public class Solution {
	// 좌, 우, 상 순서 탐색
	static final int[] dx = {0, 0, -1};
	static final int[] dy = {-1, 1, 0};
	static final int SIZE = 100;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int[][] ladder;
	static boolean[][] isVisited;
	static int currRow, currCol;

	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; ++testCase) {
			// 입력 버리기
			br.readLine();

			isVisited = new boolean[SIZE][SIZE];
			ladder = new int[SIZE][SIZE];
			// 사다리 정보 입력받기
			for (int row = 0; row < SIZE; ++row) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < SIZE; ++col) {
					ladder[row][col] = Integer.parseInt(st.nextToken());
					if (ladder[row][col] == 2) {
						// 도착지점 정보
						currCol = col;
					}
				}
			}
			currRow = 99;

			// 도착지부터 거슬로 올라가기
			while (currRow > 0) {
				isVisited[currRow][currCol] = true;
				for(int dir = 0; dir<3;++dir) {
					int tempRow = currRow + dx[dir];
					int tempCol = currCol+dy[dir];
					
					// 범위 검증
					if(tempRow>=SIZE||tempCol>=SIZE||tempRow<0||tempCol<0)
						continue;
					// 길 여부
					if(ladder[tempRow][tempCol] == 0)
						continue;
					// 방문검증(왔던 길 돌아가지 않기)
					if(isVisited[tempRow][tempCol])
						continue;
					
					currCol = tempCol;
					currRow = tempRow;
				}
			}

			sb.append(String.format("#%d %d\n", testCase, currCol));
		}
		System.out.println(sb);
	}
}
