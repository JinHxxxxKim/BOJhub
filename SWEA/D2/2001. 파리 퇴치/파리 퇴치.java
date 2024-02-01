import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 2차원 슬라이딩 윈도우 + 누적합
 * 
 * NxN의 공간, MxM의 윈도우
 * 
 * 1. 테스트 케이스의 개수 T입력받는다.
 * 2. N 과 M입력받는다.
 * 3. N 줄에 걸쳐 N x N 배열입력받는 동시에 2차원 누적합을 구한다.
 * 4. 슬라이딩 윈도우의 시작점은? 
 * 		=> padding을 누적합에 부여했을 시, [M, M]이 사직점이 된다.
 * 		언제까지?  
 * 			=> 시작점의 행이 N을 "초과"하면 종료(padding의 존재)
 * 5. 결과 출력
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N, M;
	private static int[][] map;
	private static int[][] cumulativeSum;
	
	public static void main(String[] args) throws Exception {	
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			// N, M 입력받기
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			cumulativeSum = new int[N+1][N+1];

			// map 정보 입력받기
			for (int row = 0; row < N; ++row) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; ++col) {
					map[row][col] = Integer.parseInt(st.nextToken());
					// 누적합까지 구하기
					cumulativeSum[row+1][col+1] = cumulativeSum[row+1][col]
												+ cumulativeSum[row][col+1]
												+ map[row][col]
												- cumulativeSum[row][col];
				}
			}
			int pariCnt = Integer.MIN_VALUE;
			int rearRow = M;
			int rearCol = M;
			
			// 슬라이딩 윈도우 탐색 시작
			// 좌 -> 우 로 탐색이므로 열의 종료는 중요X, 행의 종료가 중요
			while(rearRow<=N) {
				pariCnt = Math.max(pariCnt, cumulativeSum[rearRow][rearCol] 
										  	- cumulativeSum[rearRow][rearCol-M]
										  	- cumulativeSum[rearRow-M][rearCol]
										  	+ cumulativeSum[rearRow-M][rearCol-M]);
				// 윈도우 위치 조정
				++rearCol;
				if(rearCol>N) { // 열 초과
					rearCol = M;
					++rearRow;
				}
			}
			// 출력
			sb.append(String.format("#%d %d\n", testCase, pariCnt));
		}
		System.out.println(sb);
	}
}

//for(int idx =0;idx<=N;++idx) {
//	System.out.println(Arrays.toString(cumulativeSum[idx]));
//}