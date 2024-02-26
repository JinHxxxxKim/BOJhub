import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 업무는 가장 최근에 주어진 순서대로 한다
 * 업무를 받으면 바로 시작한다
 * 업무를 하던 도중 새로운 업무가 추가 된다면, 하던 업무를 중단하고 새로운 업무를 진행한다
 * 새로운 업무가 끝났다면, 이전에 하던 업무를 이전에 하던 부분부터 이어서 한다
 * 
 * 1. 1분째부터 N분째까지 순차적으로 분을 늘려나가며 확인한다.
 * 2. 명령어가 1인 경우 (업무가 주어진다)
 * 		2-1. 받자마자 해당 분에는 받은 업무를 1분간 할 수 있으므로, 총 소요시간 - 1을 해서 stack에 넣는다.
 * 			2-1-1. 만일 총소요시간 - 1 == 0이라면, 바로 점수를 더하고 스택에 넣지 않는다.
 * 3. 명령어가 0인 경우 (업무가 주어지지 않는다)
 * 		3-1. 스택에서 peek 해서 상위 업무의 소요시간을 -1 해준다.
 * 			3-1-1. 소요시간이 0이되면 빼고 점수를 더한다.
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 소요시간

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());

		Stack<Work> stk = new Stack<>();
		int totalScore = 0;
		for (int currTime = 1; currTime <= N; ++currTime) {
			st = new StringTokenizer(br.readLine().trim());
			int op = Integer.parseInt(st.nextToken());

			// 업무를 안받았을 경우
			if (op == 0) {
				// 진행중인 업무가 있다면
				if (!stk.isEmpty()) {
					Work currWork = stk.pop(); // 이번 시간에 진행할 업무
					currWork.remainTime--;
					// 진행중인 업무를 완료했다면
					if (currWork.remainTime == 0) {
						totalScore += currWork.score;
					}
					// 진행중인 업무를 완료하지 못했다면
					else {
						stk.push(currWork);
					}
				}
			}
			// 업무를 받았을 경우
			else {
				int score = Integer.parseInt(st.nextToken());
				int remainTime = Integer.parseInt(st.nextToken()) - 1;
				// 1분짜리 업무일 경우
				if (remainTime == 0) {
					totalScore += score;
				} else {
					Work newWork = new Work(score, remainTime);
					stk.push(newWork);
				}

			}
		}
		System.out.println(totalScore);
	}

	// 업무 클래스
	static class Work {
		int score; // 점수
		int remainTime; // 완료까지 남은시간

		public Work(int score, int remainTime) {
			super();
			this.score = score;
			this.remainTime = remainTime;
		}

	}
}
