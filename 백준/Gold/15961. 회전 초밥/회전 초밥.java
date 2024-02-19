import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c를 입력받는다.
 * 2. 접시의 상태를 입력받는다.
 * 3. front = 0 ~ N-1 까지, rear = k ~ (N-1+k)%k까지 슬라이딩 윈도우를 적용한다.
 * 		- front는 포함, rear는 미포함
 * 4. 각 접시를 확인한 뒤, Set에 넣는다.
 * 		- 보너스 접시를 포함 했을 때의 총 가짓 수를 counting하고 max값을 업데이트 한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	// 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
	private static int N, d, k, c;
	private static Map<Integer, Integer> currSushiState;
	private static int maxCnt;
	private static int[] sushiArray;
	
	// 슬라이딩 윈도우
	private static int front, rear;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		maxCnt = 0;
		
		sushiArray = new int[N];
		for (int idx = 0; idx < N; ++idx) {
			sushiArray[idx] = Integer.parseInt(br.readLine().trim());
		}
		
		// 슬라이딩 윈도우 시작
		front = 0;
		rear = (front + k) % N;
		currSushiState = new HashMap<>();
		for (int idx = front; idx < rear; ++idx) {
			int currSushi = sushiArray[idx];
			// 해당 접시가 이미 있다면, 개수 하나 증가
			if(currSushiState.containsKey(currSushi)) {
				currSushiState.put(currSushi, currSushiState.get(currSushi)+1);
			}
			// 없다면
			else {
				currSushiState.put(currSushi, 1);
			}
		}
		
		int localCnt = currSushiState.size();
		if(!currSushiState.containsKey(c)) {
			++localCnt;
		}
		maxCnt = localCnt;
		
		// front를 빼고, rear를 넣는다.
		for (; front < N; ++front) {
//			System.out.println("============");
//			System.out.println("front: " + front);
//			System.out.println("rear: " + rear);
//			System.out.println(currSushiState);
			
			// front 빼기
			int removeSushi = sushiArray[front];
			if (currSushiState.get(removeSushi) == 1) {
				currSushiState.remove(removeSushi);
			} else {
				currSushiState.put(removeSushi, currSushiState.get(removeSushi) - 1);
			}
			
			// rear 넣기
			int insertSushi = sushiArray[rear];
			// 해당 접시가 이미 있다면, 개수 하나 증가
			if (currSushiState.containsKey(insertSushi)) {
				currSushiState.put(insertSushi, currSushiState.get(insertSushi) + 1);
			}
			// 없다면
			else {
				currSushiState.put(insertSushi, 1);
			}
			
			rear = (rear + 1) % N;
			localCnt = currSushiState.size();
			if(!currSushiState.containsKey(c)) {
				++localCnt;
			}
			maxCnt = Math.max(localCnt, maxCnt);
		}
		
		System.out.println(maxCnt);
	}
}