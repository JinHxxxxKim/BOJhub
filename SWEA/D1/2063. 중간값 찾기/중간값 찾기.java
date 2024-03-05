
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 모든 요소를 입력받는다.
 * 2. 요소들을 정렬한다.
 * 3. 요소의 크기 / 2번 인덱스의 값을 출력한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		int TC = 1;
		for (int testCase = 1; testCase <= TC; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			numbers = new int[N];
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < N; ++idx)
				numbers[idx] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(numbers);
			System.out.println(numbers[N / 2]);
		}
	}
}