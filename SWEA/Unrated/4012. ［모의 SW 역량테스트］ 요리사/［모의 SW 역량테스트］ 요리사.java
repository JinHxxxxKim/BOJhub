import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 총 테스트 케이스의 개수 T를 입력받는다.
 * 2. 식재료의 수 N을 입력받는다.
 * 3. N * N개의 시너지 Sij값들을 입력받는다.
 * 4. N의 크기가 최대 16이므로, 완전 탐색을 통해 검색한다.
 * 5. 중복조합이 발생할 수 없는 N combination (N/2)를 통해 모든 조합의 맛을 구한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	private static int[][] synergy;
	private static int ans;
	private static int[] elementList;
	private static int[] selectAList;
	private static int[] selectBList;

	public static void main(String[] args) throws Exception {
		// 총 테스트 케이스의 개수 T를 입력받는다.
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			// 식재료의 수 N을 입력받는다.
			N = Integer.parseInt(br.readLine().trim());
			synergy = new int[N][N];
			// N * N개의 시너지 Sij값들을 입력받는다.
			for (int row = 0; row < N; ++row) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; ++col) {
					synergy[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			elementList = new int[N];
			for (int idx = 0; idx < N; ++idx) {
				elementList[idx] = idx;
			}
			
			selectAList = new int[N/2];
			selectBList = new int[N/2];
			ans = Integer.MAX_VALUE;
			combination(0, 0, 0);
			
			sb.append(String.format("#%d %d\n", testCase, ans));
		}
		System.out.println(sb);
	}

	private static void combination(int selectAIdx, int selectBIdx, int elementIdx) {
		// 기저조건
		if (selectAIdx == N / 2 && selectBIdx == N / 2) {
			//System.out.println(Arrays.toString(selectAList));
			//System.out.println(Arrays.toString(selectBList));

			int sumA = 0;
			int sumB = 0;

			for (int row : selectAList) {
				for (int col : selectAList) {
					if (col == row) {
						continue;
					}
					sumA += synergy[row][col];
				}
			}

			for (int row : selectBList) {
				for (int col : selectBList) {
					if (col == row) {
						continue;
					}
					sumB += synergy[row][col];
				}
			}
			ans = Math.min(ans, Math.abs(sumA - sumB));
			return;
		}
		if (elementIdx == N) {
			return;
		}

		if (selectAIdx == N / 2) {
			selectBList[selectBIdx] = elementList[elementIdx];
			combination(selectAIdx, selectBIdx + 1, elementIdx + 1);
			return;
		}
		if (selectBIdx == N / 2) {
			selectAList[selectAIdx] = elementList[elementIdx];
			combination(selectAIdx + 1, selectBIdx, elementIdx + 1);
			return;
		}

		selectAList[selectAIdx] = elementList[elementIdx];
		combination(selectAIdx + 1, selectBIdx, elementIdx + 1);

		selectBList[selectBIdx] = elementList[elementIdx];
		combination(selectAIdx, selectBIdx + 1, elementIdx + 1);

	}
}
