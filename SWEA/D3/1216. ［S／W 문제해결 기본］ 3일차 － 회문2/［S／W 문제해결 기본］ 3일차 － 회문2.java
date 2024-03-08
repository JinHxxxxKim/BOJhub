
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author 김진형
 * 
 * 1. 2가지 방향으로 회문을 검사한다. (1.행  2.열)
 * 2. 행 방향으로 회문을 검사한다.
 * 	2-1. 0열을 시작으로, 99열까지 진행한다.
 * 		2-1.1. 끝점은 항상 99열로 설정한다.
 * 		2-1-2. 각 점의 문자가 같다면, 회문 검사를 진행한다.
 * 		2-1-3. 회문이 맞다면, 두 열의 차이를 통해 답을 갱신한다.
 * 3. 열 방향으로 회문을 검사한다.
 * 	3-1. 0행을 시작으로, 99행까지 진행한다.
 * 		3-1.1. 끝점은 항상 99행로 설정한다.
 * 		3-1-2. 각 점의 문자가 같다면, 회문 검사를 진행한다.
 * 		3-1-3. 회문이 맞다면, 두 행의 차이를 통해 답을 갱신한다. 
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
//	private static StringTokenizer st;

	static final int SIZE = 100;
	
	static char[][] map;
	static int maxLen;
	
	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; ++testCase) {
			int N = Integer.parseInt(br.readLine().trim());
			maxLen = 1;
			map = new char[SIZE][SIZE];
			for (int row = 0; row < SIZE; ++row) {
				String rowInfo = br.readLine().trim();
				for (int col = 0; col < SIZE; ++col) {
					map[row][col] = rowInfo.charAt(col);
				}
			}
			
			// 1. 행 방향 회문 검사
			for (int row = 0; row < SIZE; ++row) {
				for (int col = 0; col < SIZE; ++col) {
					// 회문 검사에 대한 시작 조건을 검사하는 지점 설정
					int frontCol = col;
					int rearCol = SIZE-1; 
					
					while (frontCol < rearCol) {
						// 두 지점의 문자가 같아야 회문검사 시작
						if (map[row][frontCol] == map[row][rearCol]) {
							if(isPalindromeRow(row, frontCol, rearCol)) {
								maxLen = Math.max(maxLen, rearCol - frontCol + 1);
								break;
							}
						}
						// 아니라면, rear하나 줄이기
						--rearCol;
					}
				}
			}

			// 2. 열 방향 회문 검사
			for (int col = 0; col < SIZE; ++col) {
				for (int row = 0; row < SIZE; ++row) {
					// 회문 검사에 대한 시작 조건을 검사하는 지점 설정
					int frontRow = row;
					int rearRow = SIZE - 1;

					while (frontRow < rearRow) {
						// 두 지점의 문자가 같아야 회문검사 시작
						if (map[frontRow][col] == map[rearRow][col]) {
							if (isPalindromeCol(col, frontRow, rearRow)) {
								maxLen = Math.max(maxLen, rearRow - frontRow + 1);
								break;
							}
						}
						// 아니라면, rear하나 줄이기
						--rearRow;
					}
				}
			}

			sb.append(String.format("#%d %d\n", N, maxLen));
		}
		System.out.println(sb);
	}

	// 행 방향 회문 검사
	private static boolean isPalindromeRow(int row, int frontCol, int rearCol) {
		while(frontCol <= rearCol) {
			if(map[row][frontCol]!=map[row][rearCol])
				return false;
			++frontCol;
			--rearCol;
		}
		return true;
	}

	// 열 방향 회문 검사
	private static boolean isPalindromeCol(int col, int frontRow, int rearRow) {
		while (frontRow <= rearRow) {
			if (map[frontRow][col] != map[rearRow][col])
				return false;
			++frontRow;
			--rearRow;
		}
		return true;
	}
}
