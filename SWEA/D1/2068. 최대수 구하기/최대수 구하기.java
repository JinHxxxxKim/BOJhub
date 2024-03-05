
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 10개의 수를 입력받는다.
 * 2. 모든 요소를 조회하며, 가장큰 수를 탐색한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			int max = Integer.MIN_VALUE;
			for(int idx = 0; idx<10;++idx) {
				max = Math.max(max, Integer.parseInt(st.nextToken()));
			}
			 sb.append(String.format("#%d %d\n", testCase, max));
		}
		System.out.println(sb);
	}
}