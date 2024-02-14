import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 6개국의 결과가 나라별로 승, 무승부, 패의 순서로 빈칸을 하나 사이에 두고 18개의 숫자를 입력받는다.
 * 
 * 초기 검증
 * 	- 승, 패의 경우 5를 넘지 않는가
 * 	- 전체의 합이 30이 되는가
 * 	- 무가 짝을 이루는가
 * 
 * 총 6개국이 만들어낼 수 있는 경기의 수 = 6 Combination 2 = 15경기
 * 각 경기마다 나올 수 있는 결과는 3가지(승, 무, 패)
 * 따라서 가능한 경우의 수는 3^15 = 14_348_907 대략 1400만가지의 경우의 수가 존재한다.
 * 
 * 완전 탐색
 * 	루트 노드 = A 
 * 	자식 노드 = B, B', B", C, C', C" ...  :승, ': 무승부, ": 패 
 * 
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int[][] gameResult;
	private static int result, combCnt;
	private static int[][] comb;
	private static int[] selected;

	public static void main(String[] args) throws Exception {

		comb = new int[15][2];
		combCnt = 0;
		selected = new int[2];
		combination(0, 0);

		for (int testCase = 0; testCase < 4; ++testCase) {
			st = new StringTokenizer(br.readLine().trim());
			gameResult = new int[6][3];
			for (int row = 0; row < 6; ++row) {
				for (int col = 0; col < 3; ++col) {
					gameResult[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			result = 0;
			// 1경기부터 시작
			dfs(0);
			if (result == 0)
				sb.append(0).append(" ");
			else
				sb.append(1).append(" ");

		}
		System.out.println(sb);
	}

	private static void combination(int elementIdx, int selectedIdx) {
		if (selectedIdx == 2) {
			comb[combCnt++] = new int[] { selected[0], selected[1] };
			return;
		}
		if (elementIdx == 6) {
			return;
		}

		// 전처리
		selected[selectedIdx] = elementIdx;
		// 선택O
		combination(elementIdx + 1, selectedIdx + 1);
		// 전처리
		// 선택O
		combination(elementIdx + 1, selectedIdx);

	}

	private static void dfs(int gameNum) {
		// 기저조건
		if (gameNum == 15) {
			boolean isThereRemainResult = false;
			for(int row = 0;row<6;++row) {
				for(int col = 0;col<3;++col) {
					if(gameResult[row][col]!=0)
						isThereRemainResult = true;
				}
			}
			if(!isThereRemainResult)
				result = 1;
			return;
		}
		int[] currGameInfo = comb[gameNum];
		int country1 = currGameInfo[0];
		int country2 = currGameInfo[1];

		// country1이 이길 경우
		if (gameResult[country1][0] == 0 || gameResult[country2][2] == 0) {
			// 가지치기
		} else {
			--gameResult[country1][0];
			--gameResult[country2][2];
			dfs(gameNum + 1);
			++gameResult[country1][0];
			++gameResult[country2][2];
		}

		// country1이 질 경우
		if (gameResult[country1][2] == 0 || gameResult[country2][0] == 0) {
			// 가지치기
		} else {
			--gameResult[country1][2];
			--gameResult[country2][0];
			dfs(gameNum + 1);
			++gameResult[country1][2];
			++gameResult[country2][0];
		}
		// country1, country2가 비길경우
		if (gameResult[country1][1] == 0 || gameResult[country2][1] == 0) {
			// 가지치기
		} else {
			--gameResult[country1][1];
			--gameResult[country2][1];
			dfs(gameNum + 1);
			++gameResult[country1][1];
			++gameResult[country2][1];
		}
	}
}
