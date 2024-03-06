import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 각 행의 합은 입력을 받으며 동시에 더한다.
 * 2. 각 열의 합은 크기 100짜리 배열을 통해 누적해간다.
 * 3. 대각선의 합은 col=0, col=99 부터 시작하여 누적해간다.
 * 4. 모든 입력이 끝나면, 행의 최대값, 열의 최대값, 대각선의 합 중 최대를 출력한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	static int[] colSum; // 열의 합 저장
	static int diagonalSum1, diagonalSum2; // 대각선 합
	static int max; 
	
	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; ++testCase) {
			br.readLine().trim();
			
			colSum = new int[100];
			max = Integer.MIN_VALUE;
			diagonalSum1 = 0;
			diagonalSum2 = 0;
			int diagonalIdx1 = 0, diagonalIdx2 = 99; // 대각선 인덱스
			int localRowSum = 0; // 행 값 더하는 변수
			
			for (int row = 0; row < 100; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < 100; col++) {
					int currNum = Integer.parseInt(st.nextToken());
					
					// 1. 해당 행에 반드시 저장
					localRowSum += currNum;
					// 2. 해당 열에 반드시 저장
					colSum[col] += currNum;
					// 3. 대각선이라면 저장 (정방향)
					if (col == diagonalIdx1 && row == diagonalIdx1) {
						diagonalSum1 += currNum;
						diagonalIdx1++;
					}
					// 4. 대각선이라면 저장 (역방향)
					if (col == diagonalIdx2) {
						diagonalSum2 += currNum;
						diagonalIdx2--;
					}
				}
				max = Math.max(max, localRowSum);
				// 행 합 초기화
				localRowSum = 0;
			}
			for (int col = 0; col < 100; ++col) {
				max = Math.max(max, colSum[col]);

			}
			max = Math.max(max, diagonalSum1);
			max = Math.max(max, diagonalSum2);
			
			sb.append(String.format("#%d %d\n", testCase, max));
		}
		System.out.println(sb);
	}
}
