
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 문자열을 입력받는다.
 * 2. 초기 세팅: 
 * 		인덱스1 = 0
 * 		인덱스2 = 길이 - 1
 * 3. 인덱스 1 == 인덱스 2가 될 때까지 반복
 * 	3-1. 두 문자가 다르다면 0 출력후 종료
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			String str = br.readLine().trim();
			// 초기 세팅
			int idx1 = 0;
			int idx2 = str.length() - 1;

			int isPalindrome = 1;

			// 두 인덱스가 교차되기 전까지
			while (idx1 <= idx2) {
				if (str.charAt(idx1) == str.charAt(idx2)) {
					// 검사 진행
					++idx1;
					--idx2;
					continue;
				} else {
					isPalindrome = 0;
					break;
				}
			}

			sb.append(String.format("#%d %d\n", testCase, isPalindrome));

		}
		System.out.println(sb);
	}
}
