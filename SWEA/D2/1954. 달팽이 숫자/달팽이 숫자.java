
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. [0, 0]부터 탐색을 시작한다.
 * 2. 우, 하, 좌, 상 방향으로 탐색을 한다
 * 	2-1. 탐색 가능한 지역이 있다면	
 * 		- 범위를 벗어나거나.
 * 		- 이미 방문한적이 있는 노드를 만날 때까지 직진한다.
 * 3. 더이상 탐색할 노드가 없다면, 반복문을 종료한다.
 */
public class Solution {
	// 우, 하, 좌, 상 순서 탐색
	static final int[] dx = { 0, 1, 0, -1 };
	static final int[] dy = { 1, 0, -1, 0 };
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int N;
	static int currNum;
	static int[][] map;
	static boolean[][] isVisited;
	
	static int currRow, currCol; // 현재 위치한 행렬
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			isVisited = new boolean[N][N];
			
			currRow = 0;
			currCol = 0;
			currNum = 1;
			int dir;
			
			
			sb.append("#").append(testCase).append("\n");
			if(N == 1) {
				sb.append(1).append("\n");
			}else {
				while((dir = canMove())>=0) {
					move(dir);
				}
				
				
				for(int row = 0; row<N;++row) {
					for(int col = 0; col< N ; ++col) {
						sb.append(map[row][col]).append(" ");
					}
					sb.append("\n");
				}
			}			
		}
		System.out.println(sb);
	}

	private static void move(int dir) {
		
		while (true) {
			map[currRow][currCol] = currNum;
			isVisited[currRow][currCol] = true;
			
			
			// 범위 검증
			if (currRow + dx[dir] < 0 || currCol + dy[dir] < 0 | currRow + dx[dir] >= N || currCol + dy[dir] >= N)
				return;
			// 방문 검증
			if (isVisited[currRow + dx[dir]][currCol+ dy[dir] ])
				return;
			
			// 숫자 채우기
			currRow += dx[dir];
			currCol += dy[dir];
			currNum++;
		}
	}

	// 움직일 좌표가 있는지 확인 후, 움직일 방향 반롼
	private static int canMove() {
		// 4방향 탐색
		for (int dir = 0; dir < 4; ++dir) {
			int tempRow = currRow + dx[dir];
			int tempCol = currCol + dy[dir];
			
//			System.out.println("============");
//			System.out.println(tempRow);
//			System.out.println(tempCol);
			
			// 범위 검증
			if (tempRow < 0 || tempCol < 0 | tempRow >= N || tempCol >= N)
				continue;
			// 방문 검증
			if(isVisited[tempRow][tempCol])
				continue;
			// 움직일 방향 반환
			return dir;
		}
		// 불가능하다면 -1 반환
		return -1;
	}
}
