import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 과일의 개수 N (1 ≤ N ≤ 1,000) 과 스네이크버드의 초기 길이 정수 L (1 ≤ L ≤ 10,000)을 입력받는다.
 * 2. 과일의 높이에 대한 정보를 배열에 저장한다.
 * 3. 과일의 높이를 정렬한다.
 * 4. 과일을 순회한다.
 * 		- 현재 자신의 길이보다 작거나 같다면, 자신의 길이 +1
 * 		- 현재 자신의 길이 보다 크다면 종료
 * 5. 최종 길이를 출력
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, L;
	private static int[] fruitHeight;
	
	public static void main(String[] args) throws Exception {
		// N, L에 대한 입력받기
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		fruitHeight = new int[N];
		// 과일 높이 입력받기
		st = new StringTokenizer(br.readLine().trim());
		for(int cnt = 0;cnt<N;++cnt) {
			fruitHeight[cnt] = Integer.parseInt(st.nextToken());
		}
		
		// 과일 높이 오름차순으로 정렬
		Arrays.sort(fruitHeight);
		
		// 과일 순회
		int currHeight = L;
		for(int idx = 0;idx<N;++idx) {
			// 현재 길이보다 작거나 같을 경우
			if(fruitHeight[idx]<=currHeight) {
				++currHeight;
			}else {
				break;
			}
		}
		
		System.out.println(currHeight);
	}
}