import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. DNA 문자열 길이 |S|와 비밀번호로 사용할 부분문자열의 길이 |P|를 입력받는다.
 * 2. DNA 문자열을 입력받는다.
 * 3. 부분문자열에 포함되어야 할 {‘A’, ‘C’, ‘G’, ‘T’} 의 최소 개수를 입력받는다.
 * 투포인터? 슬라이딩 윈도우? + 누적합
 * 4. front, rear 변수 설정 => front 0, rear = front + P(부분문자열의 길이)
 * 		- front는 포함, rear는 미포함
 * 5. front ~ rear까지 탐색 진행
 * 6. 만족할 떄마다 cnt ++
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int S, P;
	private static char[] dnaString;
	// 슬라이딩 윈도우 변수
	private static int front, rear;
	// 필요 문자 수
	private static int aMin, cMin, gMin, tMin;
	private static int cnt = 0;
	
	private static int[][] sumDNA;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		// DNA 문자열 길이 |S|와 비밀번호로 사용할 부분문자열의 길이 |P|를 입력받는다.
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		dnaString = new char[S];
		// DNA 문자열을 입력받는다.
		String tempString = br.readLine().trim();
		for (int idx = 0; idx < S; ++idx) {
			dnaString[idx] = tempString.charAt(idx);
		}
		// front, rear 변수 설정
		// front 0, rear = front + P
		front = 0;
		rear = front + P;
		// 부분문자열에 포함되어야 할 {‘A’, ‘C’, ‘G’, ‘T’} 의 최소 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		aMin = Integer.parseInt(st.nextToken());
		cMin = Integer.parseInt(st.nextToken());
		gMin = Integer.parseInt(st.nextToken());
		tMin = Integer.parseInt(st.nextToken());

		// DNA 누적합 구하기
		sumDNA = new int[S + 1][4]; // ‘A’, ‘C’, ‘G’, ‘T’ 순서로 열에 배정
		for (int idx = 1; idx <= S; ++idx) {
			sumDNA[idx][0] = sumDNA[idx - 1][0];
			sumDNA[idx][1] = sumDNA[idx - 1][1];
			sumDNA[idx][2] = sumDNA[idx - 1][2];
			sumDNA[idx][3] = sumDNA[idx - 1][3];
			switch (dnaString[idx - 1]) {
			case 'A':
				sumDNA[idx][0]++;
				break;
			case 'C':
				sumDNA[idx][1]++;
				break;
			case 'G':
				sumDNA[idx][2]++;
				break;
			case 'T':
				sumDNA[idx][3]++;
				break;
			}
		}

		while (rear <= S) {
			int[] rearState = sumDNA[rear];
			int[] frontState = sumDNA[front];
			if (aMin <= rearState[0] - frontState[0] && cMin <= rearState[1] - frontState[1]
					&& gMin <= rearState[2] - frontState[2] && tMin <= rearState[3] - frontState[3]) {
				cnt++;
			}
			++rear;
			++front;
		}
		System.out.println(cnt);
	}
}
