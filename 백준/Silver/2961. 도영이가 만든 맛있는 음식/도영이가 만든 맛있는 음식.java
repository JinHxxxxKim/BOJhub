import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 재료의 개수 N(1 ≤ N ≤ 10)을 입력받는다.
 * 2. N개 줄에 그 재료의 신맛과 쓴맛을 입력받는다.
 * 3. 재귀를 통해 완전 탐색으로 접근한다.
 * 4. SUBSET
 * 		- 기저조건: elementIdx = N일 경우
 * 			-> 단, 재료는 한가지 이상
 * 			=> Ingeredient의 신맛, 쓴맛의 차를 구하고, min을 갱신한다.
 * 		- 선택하거나? 선택하지 않거나?
 * 5. 출력
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	private static Ingredient[] ingredientList;
	private static boolean[] isSelected;
	private static int bestTasteScore;

	public static void main(String[] args) throws Exception {
		// 재료의 개수 입력받기
		N = Integer.parseInt(br.readLine().trim());
		ingredientList = new Ingredient[N];
		isSelected = new boolean[N];
		bestTasteScore = Integer.MAX_VALUE;
		// 재료 맛 입력받기
		for (int idx = 0; idx < N; ++idx) {
			st = new StringTokenizer(br.readLine().trim());
			ingredientList[idx] = new Ingredient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		// 재귀 start
		// 단, 신맛의 경우 곱연산이므로 1을 초기값으로 설정한다.
		subSet(0, 1, 0);
		System.out.println(bestTasteScore);
	}

	private static void subSet(int elementIdx, int sTaste, int bTaste) {
		// 기저조건(모두 조회 완료)
		if (elementIdx == N) {
			// 공집합이 아닐 경우
			if (bTaste != 0) {
				bestTasteScore = Math.min(bestTasteScore, Math.abs(sTaste - bTaste));
			}
			return;
		}

		// 재료선택 O
		isSelected[elementIdx] = true;
		subSet(elementIdx + 1, sTaste * ingredientList[elementIdx].sourScore,
				bTaste + ingredientList[elementIdx].bitterScore);
		// 재료선택 X
		isSelected[elementIdx] = false;
		subSet(elementIdx + 1, sTaste, bTaste);
	}

	// 재료 클래스
	public static class Ingredient {
		int sourScore;
		int bitterScore;

		public Ingredient(int sourScore, int bitterScore) {
			this.sourScore = sourScore;
			this.bitterScore = bitterScore;
		}
	}
}
