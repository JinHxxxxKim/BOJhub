
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 상자의 높이를 모두 입력받는다.
 * 2. 덤프 횟수만큼 반복한다.
 * 	2-1. 최고점과 최저점을 찾은 뒤, 조정해준다.
 * 	2-2. 최고점과 최저점의 높이가 동일하거나 1차이가 난다면  종료한다.
 * 3. 최고점과 최저점의 높이의 차이를 반환한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int N;
	static int[] heights;
	static int dumpCnt; // 덤프 횟수
	
	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; ++testCase) {
			dumpCnt = Integer.parseInt(br.readLine().trim());
			heights = new int[100];
			
			// 높이 입력받기
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx<100;++idx) {
				heights[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 덤프횟수만큼 반복
			while (dumpCnt > 0) {
				int minHeight = 101, maxHeight = 0; // 최대, 최소 높이
				int minHeightIdx = -1, maxHeightIdx = -1; // 최대 최소 높이를 갖는 인덱스
				// 탐색
				for (int idx = 0; idx < 100; ++idx) {
					if (minHeight > heights[idx]) {
						minHeight = heights[idx];
						minHeightIdx = idx;
					}

					if (maxHeight < heights[idx]) {
						maxHeight = heights[idx];
						maxHeightIdx = idx;
					}
				}

				// 종료조건
				if (Math.abs(maxHeight - minHeight) <= 1) {
					break;
				}
				
				// 덤프 진행
				heights[minHeightIdx]++;
				heights[maxHeightIdx]--;
				--dumpCnt;
			}
			
			int minHeight = 101, maxHeight = 0; // 최대, 최소 높이
			// 탐색
			for (int idx = 0; idx < 100; ++idx) {
				if (minHeight > heights[idx]) {
					minHeight = heights[idx];
				}

				if (maxHeight < heights[idx]) {
					maxHeight = heights[idx];
				}
			}
			sb.append(String.format("#%d %d\n", testCase, Math.abs(maxHeight - minHeight)));
		}
		System.out.println(sb);
	}
}

