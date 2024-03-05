
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 점수를 카운팅하는 크기 101의 배열을 만든다.
 * 2. 점수가 들어올 때 마다, 점수를 인덱스로 하여 개수를 하나 증가시킴을 반복한다.
 * 3. 100번 인덱스부터 순회하며, 최대 점수(인덱스)를 구한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;
	static int[] scoresCnt;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			// 테케 번호 입력받기
			br.readLine();
			
			scoresCnt = new int[101];
			// 점수 카운팅
			st = new StringTokenizer(br.readLine().trim());
			for (int studentCnt = 0; studentCnt < 1000; ++studentCnt) {
				scoresCnt[Integer.parseInt(st.nextToken())]++;
			}

			// 최빈 점수 구하기
			int maxScoreCnt = -1;
			int maxScore = -1;
			for(int score = 100; score>=0;--score) {
				if (scoresCnt[score] > maxScoreCnt) {
					maxScoreCnt = scoresCnt[score]; 
					maxScore = score;
				}
			}
			
			 sb.append(String.format("#%d %d\n", testCase, maxScore));
		}
		System.out.println(sb);
	}
}