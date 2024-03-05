
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 모든 요소를 배열에 저장한다.
 * 2. 0번 인덱스, 9번 인덱스를 제외하고 편균을 구한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;
	static int[] nums;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			nums = new int[10];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx<10;++idx) {
				nums[idx] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(nums);
			// 최소수, 최대수 빼고 더하기
			int sum = 0;
			for(int idx =1; idx<9;++idx) {
				sum += nums[idx];
			}
			 sb.append(String.format("#%d %d\n", testCase, Math.round((double)sum / 8.0)));
		}
		System.out.println(sb);
	}
}