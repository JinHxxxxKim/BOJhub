

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 설탕 배달
 * @author 김진형
 * 
 * 1. N을 입력받는다.
 * 2. 5kg을 줄이고, 3kg을 늘려가며 반복한다.
 * 		- 5kg을 최대로 사용하고, 3kg으로 나누어 떨어지면 정답
 * 		- 나누어 떨어지지 않는다면, 5kg 개수 한개 감소
 * 		- 5kg의 개수가 음수로 떨어지면 -1 출력
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	private static int kg5Cnt;
	private static int kg3Cnt;
	private static int currWeight;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		
		kg3Cnt = 0;
		kg5Cnt = N/5;
		
		// 5kg의 개수가 음수가 아닐 때까지
		while(kg5Cnt>=0) {
			currWeight = N - 5*kg5Cnt;
			if(currWeight%3 == 0) {
				// 나머지 무게가 3kg으로 나누어 떨어질 경우
				kg3Cnt = currWeight/3;
				break;
			}else {
				// 나머지 무게가 3kg으로 나누어 떨어지지 않는 경우
				--kg5Cnt;
			}
		}
		
		if(kg5Cnt<0) {
			System.out.println(-1);
		}else {
			System.out.println(kg3Cnt+kg5Cnt);
		}
	}
}