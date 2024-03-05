
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 주어진 배열을 입력받는다.
 * 2. 먼저 행을 탐색하며, 크기에 맞는 공간이 있는지 확인한다.
 * 	2-1. 작아도 안되고, 커도안된다.
 * 	2-2. 크기에 맞다면 카운팅을 늘려준다.
 * 3. 출력 
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int N;
	static int wordSize; // 글자 크기
	static int[][] map; // 퍼즐
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			wordSize = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			// 퍼즐 입력받기
			for (int row = 0; row < N; ++row) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; ++col) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			// 공간 탐색(행)
			for (int row = 0; row < N; ++row) {
				int spaceCnt = 0; // 공간 개수 카운팅
				for (int col = 0; col < N; ++col) {
					if(map[row][col] == 0) {
						// 0을 만났을 떄
						if(spaceCnt == wordSize) {
							cnt++;
							spaceCnt = 0;
						}else {
							spaceCnt = 0;
						}
					}else {
						// 1을 만났을 떄: 카운팅 증가
						++spaceCnt;
					}
				}
				// 종료지점에서 한번 더 체크
				if(spaceCnt == wordSize) {
					cnt++;
				}
			}
			
			// 공간 탐색(열)
			for (int col = 0; col < N; ++col) {
				int spaceCnt = 0; // 공간 개수 카운팅
				for (int row = 0; row < N; ++row) {
					if (map[row][col] == 0) {
						// 0을 만났을 떄
						if (spaceCnt == wordSize) {
							spaceCnt = 0;
							cnt++;
						} else {
							spaceCnt = 0;
						}
					} else {
						// 1을 만났을 떄: 카운팅 증가
						++spaceCnt;
					}
				}
				// 종료지점에서 한번 더 체크
				if (spaceCnt == wordSize) {
					cnt++;
				}
			}
			
			 sb.append(String.format("#%d %d\n", testCase, cnt));
		}
		System.out.println(sb);
	}
}
