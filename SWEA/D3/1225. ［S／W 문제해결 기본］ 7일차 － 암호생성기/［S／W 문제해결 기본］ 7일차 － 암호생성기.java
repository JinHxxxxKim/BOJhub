import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. QUEUE를 사용해서 풀면 간단하지만, 정수의 범위가 너무 방대하다.
 * 2. 하나의 수가 원래 자신의 위치로 다시 돌아오기 위해서는 총 5Cycle을 거치고 -15가 된 체로 돌아온다.
 * 3. 따라서 최적화하기 위해 가장 작은수를 15로 나눈 몫만큼 나누어준다.
 * 4. 문제에 주어진 사이클을 반복한다.
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	// 숫자를 저장할 배열
	private static int[] data;

	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; testCase++) {
			br.readLine();
			data = new int[8];
			// 숫자 입력받기
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < 8; ++idx) {
				data[idx] = Integer.parseInt(st.nextToken());
			}
			// 15로 나누었을 때 몫구하기
			int minNum = Integer.MAX_VALUE;
			for (int idx = 0; idx < 8; ++idx) {
				minNum = Math.min(minNum, data[idx]);
			}
			// 구한 몫*15 한 값을 모든 숫자에서 빼준다
			int cnt = (minNum-1) / 15;
			for (int idx = 0; idx < 8; ++idx) {
				data[idx] = data[idx] - (cnt * 15);
			}
			Queue<Integer> q = new ArrayDeque<>();
			for (int idx = 0; idx < 8; ++idx) {
				q.offer(data[idx]);
			}
			// 문제에서 주어진 사이클 진행
			int cycleCnt = 0;
			int subtractNum = 1;
			while(true) {
				int currNum = q.poll();
				currNum = currNum - subtractNum;
				// 0또는 음수일 경우 종료
				if(currNum<=0) {
					q.offer(0);
					break;
				}
				subtractNum++;
				cycleCnt++;
				q.offer(currNum);
				// 한 사이클이 종료됨
				if(cycleCnt==5) {
					subtractNum = 1;
					cycleCnt = 0;
				}
			}
			sb.append("#").append(testCase).append(" ");
			while(!q.isEmpty()) {
				sb.append(q.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
