import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 테스트 케이스의 수 T를 입력받는다.
 * 2. 재료의 수, 제한 칼로리를 입력받는다.
 * 3. 재료에 대한 민기의 맛에 대한 점수와 칼로리를 나타내는 Ti, Ki를 입력받는다.
 * 
 * (조합 풀이)
 * 1. N combination 1~N 까지의 모든 조합을 통해 칼로리의 합, 맛의 점수를 갱신한다.
 * -> 칼로리의 합을 통해 가지치키를 실행하고 백트래킹이 이뤄질 수 있도록한다.
 * 
 * (부분집합 풀이)
 * 1. 주어진 재료에 대해 부분집합을 구하고, 기저조건 도달 시, 칼로리 합, 맛의 합을 통해 정답을 구한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, limitCalorie;
	private static int[][] ingredientInfo;

	private static int maxScore;
	private static int[] selectList;
	private static int M;
	
	private static boolean[] isSelect;

	public static void main(String[] args) throws Exception {
		// 테스트 케이스의 수 T를 입력받는다.
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			// 재료의 수, 제한 칼로리를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			limitCalorie = Integer.parseInt(st.nextToken());

			ingredientInfo = new int[N][2];
			for (int idx = 0; idx < N; ++idx) {
				st = new StringTokenizer(br.readLine().trim());
				ingredientInfo[idx][0] = Integer.parseInt(st.nextToken());
				ingredientInfo[idx][1] = Integer.parseInt(st.nextToken());
			}
			maxScore = Integer.MIN_VALUE;
			// 조합 풀이 START
			/*
			for (int select = 1; select <= N; ++select) {
				// 1개선택, 2개선택, 3개선택, ,,, n개 선택까지 모두 확인
				M = select;
				selectList = new int[M];
				combination(0, 0, 0, 0);
			}
			*/
			// 조합 풀이 END
			
			// 부분집합 풀이 START
			isSelect = new boolean[N];
			powerSet(0, 0, 0);
			// 부분집합 풀이 END
			sb.append(String.format("#%d %d\n", testCase, maxScore));
		}
		System.out.println(sb);
	}

	private static void powerSet(int elementIdx, int calSum, int flavorScore) {
		if (calSum > limitCalorie) {
			return;
		}
		// 기저조건
		if (elementIdx == N) {
			maxScore = Math.max(maxScore, flavorScore);
			return;
		}

		// 전처리
		isSelect[elementIdx] = true;
		// 선택 O
		powerSet(elementIdx + 1, calSum + ingredientInfo[elementIdx][1], flavorScore + ingredientInfo[elementIdx][0]);
		// 전처리
		isSelect[elementIdx] = false;
		// 선택 X
		powerSet(elementIdx + 1, calSum, flavorScore);
	}

	private static void combination(int selectIdx, int elementIdx, int calSum, int flavorScore) {
		// 가지치기
		if (calSum > limitCalorie) {
			return;
		}

		// 기저조건
		if (selectIdx == M) {
			maxScore = Math.max(flavorScore, maxScore);
			return;
		}
		if (elementIdx == N) {
			return;
		}

		// 전처리
		selectList[selectIdx] = elementIdx;
		// 선택 O
		combination(selectIdx + 1, elementIdx + 1, calSum + ingredientInfo[elementIdx][1],
				flavorScore + ingredientInfo[elementIdx][0]);

		// 전처리

		// 선택 X
		combination(selectIdx, elementIdx + 1, calSum, flavorScore);
	}
}
