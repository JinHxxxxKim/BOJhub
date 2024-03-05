
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. N의 배열을 선언, 양끝 2개 요소는 0으로 설정한다.
 * 2. 0번 인덱스부터 순회하며, 앞뒤로 2개의 요소를 확인한다.
 * 	2-1. (현재 자신의 높이 - 앞뒤 2개씩 총 4개의 건물 높이의 최대값) 이 양수일 경우 해당 건물의 조망권이 확보된 세대 수이다. 
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	static int[] buildings;

	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			st = new StringTokenizer(br.readLine().trim());
			buildings = new int[N + 4];

			// 빌딩 높이 입력
			for (int idx = 0; idx < N; ++idx) {
				buildings[idx] = Integer.parseInt(st.nextToken());
			}

			int sum = 0;
			// 모든 빌딩 순회
			for (int idx = 0; idx < N; ++idx) {
				int currBuildingHeight = buildings[idx];
				// 건물이 없으면 PASS
				if (currBuildingHeight == 0)
					continue;

				int tempHaxHeight = Integer.MIN_VALUE;
				// 앞뒤 건물 높이 확인
				for (int tempIdx = idx - 2; tempIdx <= idx + 2; ++tempIdx) {
					// 현재 확인 중인 건물이면 PASS
					if (tempIdx == idx)
						continue;
					tempHaxHeight = Math.max(tempHaxHeight, buildings[tempIdx]);
				}

				if (tempHaxHeight < currBuildingHeight) {
					sum += currBuildingHeight - tempHaxHeight;
				}
			}
			sb.append(String.format("#%d %d\n", testCase, sum));
		}
		System.out.println(sb);
	}
}