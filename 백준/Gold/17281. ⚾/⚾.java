

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. 이닝 수 N(2 ≤ N ≤ 50)를 입력받는다.
 * 2. 각 선수가 각 이닝에서 얻는 결과가 1번 이닝부터 N번 이닝까지 입력받는다.
 * 3. 1번 선수가 4번 타자는 확정이므로, 8명의 선수에 대해 순열을 구한다.
 * 4. permutation
 * 		- selectIdx = 9 (기저조건)
 * 			- N개의 이닝에 대해 점수를 계산하고 더한다.
 * 			- 최대 점수를 갱신한다.
 */
public class Main {
	private static final int ANTA_1 = 1;
	private static final int ANTA_2 = 2;
	private static final int ANTA_3 = 3;
	private static final int HOMERUN = 4;
	private static final int OUT = 0;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int inning;
	private static int[][] playerResult;
	private static int ans;
	
	// 순열 관련 변수
	private static int[] selected;
	private static boolean[] isSelected;
	
	private static int cnt;
	
	public static void main(String[] args) throws Exception {
		inning = Integer.parseInt(br.readLine().trim());
		playerResult = new int[inning][9];
		for (int inningCnt = 0; inningCnt < inning; ++inningCnt) {
			st = new StringTokenizer(br.readLine().trim());
			for (int playerCnt = 0; playerCnt < 9; ++playerCnt) {
				playerResult[inningCnt][playerCnt] = Integer.parseInt(st.nextToken());
			}
		}
		selected = new int[9];
		isSelected = new boolean[9];
		// 1번 선수는 4번 타자 고정
		isSelected[0] = true;
		permutation(0);
		System.out.println(ans);
	}

	private static void permutation(int selectIdx) {
		// 1번 선수는 4번 타자 고정
		if (selectIdx == 3) {
			selected[selectIdx] = 0;
			permutation(selectIdx + 1);
		}
		// 기저조건
		if (selectIdx == 9) {
			++cnt;
			// 점수 계산
			int localScore = 0;
			int currPlayerIdx = 0;
			for (int inningCnt = 0; inningCnt < inning; ++inningCnt) {
				GameState currInning = new GameState(false, false, false, 0);
				while (currInning.outCnt < 3) {
					int currplayer = selected[currPlayerIdx];
					switch (playerResult[inningCnt][currplayer]) {
					case ANTA_1:
						// 3루에 주자가 있을 경우
						if(currInning.base3) {
							localScore++;
							currInning.base3 = false;
						}
						// 2루에 주자가 있을 경우
						if(currInning.base2) {
							currInning.base3 = true;
							currInning.base2 = false;
						}
						// 1루에 주자가 있을 경우
						if(currInning.base1) {
							currInning.base2 = true;
							currInning.base1 = false;
						}
						currInning.base1 = true;
						break;
					case ANTA_2:
						// 3루에 주자가 있을 경우
						if(currInning.base3) {
							localScore++;
							currInning.base3 = false;
						}
						// 2루에 주자가 있을 경우
						if(currInning.base2) {
							localScore++;
							currInning.base2 = false;
						}
						// 1루에 주자가 있을 경우
						if(currInning.base1) {
							currInning.base3 = true;
							currInning.base1 = false;
						}
						currInning.base2 = true;
						break;
					case ANTA_3:
						// 3루에 주자가 있을 경우
						if(currInning.base3) {
							localScore++;
							currInning.base3 = false;
						}
						// 2루에 주자가 있을 경우
						if(currInning.base2) {
							localScore++;
							currInning.base2 = false;
						}
						// 1루에 주자가 있을 경우
						if(currInning.base1) {
							localScore++;
							currInning.base1 = false;
						}
						currInning.base3 = true;
						break;
					case HOMERUN:
						if(currInning.base3) {
							localScore++;
							currInning.base3 = false;
						}
						// 2루에 주자가 있을 경우
						if(currInning.base2) {
							localScore++;
							currInning.base2 = false;
						}
						// 1루에 주자가 있을 경우
						if(currInning.base1) {
							localScore++;
							currInning.base1 = false;
						}
						localScore++;
						break;
					case OUT:
						currInning.outCnt++;
						break;
					}
					currPlayerIdx = (currPlayerIdx + 1) % 9;  
				}
			}
			ans = Math.max(ans, localScore);
			return;
		}
		
		for (int elementIdx = 1; elementIdx < 9; ++elementIdx) {
			if (isSelected[elementIdx])
				continue;

			// 전처리
			isSelected[elementIdx] = true;
			selected[selectIdx] = elementIdx;
			// 재귀
			permutation(selectIdx + 1);
			// 후처리
			isSelected[elementIdx] = false;

		}
	}

	static class GameState {
		boolean base1, base2, base3;
		int outCnt;

		public GameState(boolean base1, boolean base2, boolean base3, int outCnt) {
			super();
			this.base1 = base1;
			this.base2 = base2;
			this.base3 = base3;
			this.outCnt = outCnt;
		}

	}
}