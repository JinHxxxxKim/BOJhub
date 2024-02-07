import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 *         연산 종류 1. 상하 반전 2. 좌우 반전 3. 시계방향 90도 회전 4. 반시계방향 90도 회전 5. 사분면 시계방향 회전
 *         6. 사분면 반시계방향 회전 ======================
 * 
 *         1. 배열의 크기 N, M과 수행해야 하는 연산의 수 R을 입력받는다. 2. N개의 줄에 배열 A의 원소 Aij를
 *         입력받는다. 3. 수행해야 하는 연산을 입력받으면서 반복한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M, R;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		// 배열의 크기 N, M과 수행해야 하는 연산의 수 R을 입력받는다.
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		// N개의 줄에 배열 A의 원소 Aij를 입력받는다.
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < M; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine().trim());
		for (int opCnt = 0; opCnt < R; ++opCnt) {
			int currOp = Integer.parseInt(st.nextToken());
			switch (currOp) {
			case 1:
				map = func1(map.length, map[0].length);
				break;
			case 2:
				map = func2(map.length, map[0].length);
				break;
			case 3:
				map = func3(map.length, map[0].length);
				break;
			case 4:
				map = func4(map.length, map[0].length);
				break;
			case 5:
				map = func5(map.length, map[0].length);
				break;
			case 6:
				map = func6(map.length, map[0].length);
				break;
			}
		}
		for (int row = 0; row < map.length; ++row) {
			for(int col = 0;col<map[0].length;++col) {
				sb.append(map[row][col]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	// 상하 반전
	private static int[][] func1(int rowSize, int colSize) {
		int[][] result = new int[rowSize][colSize];
		// 기존 배열의 행을 거꾸로 조회하며 복사
		for (int row = rowSize - 1; row >= 0; --row) {
			for (int col = 0; col < colSize; ++col) {
				result[(rowSize - 1) - row][col] = map[row][col];
			}
		}
		return result;
	}

	// 좌우 반전
	private static int[][] func2(int rowSize, int colSize) {
		int[][] result = new int[rowSize][colSize];
		// 기존 배열의 열을 거꾸로 조회하며 복사
		for (int col = colSize - 1; col >= 0; --col) {
			for (int row = 0; row < rowSize; ++row) {
				result[row][(colSize - 1) - col] = map[row][col];
			}
		}
		return result;
	}

	// 시계 방향 90도 회전
	private static int[][] func3(int rowSize, int colSize) {
		int[][] result = new int[colSize][rowSize];
		for (int row = 0; row < colSize; ++row) {
			for (int col = 0; col < rowSize; ++col) {
				result[row][col] = map[(rowSize - 1) - col][row];
			}
		}
		return result;
	}

	// 시계 방향 90도 회전
	private static int[][] func4(int rowSize, int colSize) {
		int[][] result = new int[colSize][rowSize];
		for (int row = 0; row < colSize; ++row) {
			for (int col = 0; col < rowSize; ++col) {
				result[row][col] = map[col][(colSize - 1) - row];
			}
		}
		return result;
	}

	// 사분면 시계방향 회전
	private static int[][] func5(int rowSize, int colSize) {
		int[][] result = new int[rowSize][colSize];
		// 사분면 분할
		// 6, 8 -> 3, 4
		int halfRow = rowSize / 2;
		int halfCol = colSize / 2;

		// 4번 옮겨 적기
		// 1번 영역
		for (int row = 0; row < halfRow; ++row) {
			for (int col = 0; col < halfCol; ++col) {
				result[row][col] = map[row + halfRow][col];
			}
		}
		// 2번 영역
		for (int row = 0; row < halfRow; ++row) {
			for (int col = halfCol; col < colSize; ++col) {
				result[row][col] = map[row][col - halfCol];
			}
		}
		// 3번 영역
		for (int row = halfRow; row < rowSize; ++row) {
			for (int col = halfCol; col < colSize; ++col) {
				result[row][col] = map[row - halfRow][col];
			}
		}

		// 4번 영역
		for (int row = halfRow; row < rowSize; ++row) {
			for (int col = 0; col < halfCol; ++col) {
				result[row][col] = map[row][col + halfCol];
			}
		}

		return result;
	}

	// 사분면 반시계방향 회전
	private static int[][] func6(int rowSize, int colSize) {
		int[][] result = new int[rowSize][colSize];
		// 사분면 분할
		// 6, 8 -> 3, 4
		int halfRow = rowSize / 2;
		int halfCol = colSize / 2;

		// 1번 영역
		for (int row = 0; row < halfRow; ++row) {
			for (int col = 0; col < halfCol; ++col) {
				result[row][col] = map[row][col + halfCol];
			}
		}
		// 2번 영역
		for (int row = 0; row < halfRow; ++row) {
			for (int col = halfCol; col < colSize; ++col) {
				result[row][col] = map[row + halfRow][col];
			}
		}
		// 3번 영역
		for (int row = halfRow; row < rowSize; ++row) {
			for (int col = halfCol; col < colSize; ++col) {
				result[row][col] = map[row][col - halfCol];
			}
		}

		// 4번 영역
		for (int row = halfRow; row < rowSize; ++row) {
			for (int col = 0; col < halfCol; ++col) {
				result[row][col] = map[row - halfRow][col];
			}
		}

		return result;
	}
}