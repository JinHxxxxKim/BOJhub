import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
5 60
30 10 20 35 40
3 0 3 5 4

5 100
20 20 20 20 20
0 0 0 0 0
답 : 0

7 120
20 91 92 93 94 95 100
1 2 2 2 2 2 2
답 : 3


 * 
 * 
19 20169
240 2560 434 6 31 577 500 2715 2916 952 2490 258 1983 1576 3460 933 1660 2804 2584
82 77 81 0 36 6 53 78 49 82 82 33 66 8 60 0 98 91 93
 * 
 * 
 * 1 ≤ N ≤ 100, 1 ≤ M ≤ 10,000,000
 * 최대 100개의 실행 중인 앱이 존재하며, 확보 공간은 최대 1000만이다. 
 * 
 * "최소" M 바이트 공간을 확보하는 "최소" 비용
 * 
 * 5 x 60 개의 동적 테이블을 통한 접근
 * 기존의 DP 테이블과 반대 방식으로 동작하게 된다.
 * 
 * idx=0: 0~30까지는 비용 3
 * idx=1: 0~10까지는 비용 0, 11~30까지는 비용 3, 31~40까지는 비용 3 
 * idx=2: 0~10까지는 비용 0, 11~30까지는 비용 3, 31~40까지는 비용 3
 * 
 * 정렬이 필요할까? 비용을 기준으로 오름차순? 굳이? 
 * 
 * 로직
 *     1. 모든 필요한 입력을 받는다.
 *     2. N+1*M+1 크기의 2차원 동적테이블을 생성한다.
 *      2-1. 0열은 0으로, 0행은 Integer.MAX_VALUE로 초기화한다.
 *  3. 1행 1열부터 탐색을 시작한다.
 *      3-0. 기본 전제 조건: 이전 행 동일 열의 값이 Integer.MAX_VALUE라면 카운팅을 시작한다.
 *          -> 카운팅이 현재 확보할 수 있는 메모리의 크기가 된다면 다음 행으로 넘어간다.     
 *      3-1. 이전 행의 0열부터 확인하기 시작한다.
 *          3-1-1. 0열 ~ 현재 확보 가능한 메모리 크기까지: Min(이전 행 동일 열 vs 현재 비용)
 *          3-1-2. 이후 ~ 이전 행의 값이 Integer.MAX_VALUE가 아닐 때까지: Min(이전 행 동일 열 vs 현재 비용 + 이전 행 동일 열-확보 가능 메모리 크기)
 *          3-1-3. 이전 행의 값이 Integer.MAX_VALUE일 때 ~ 카운팅<확보가능 메모리 일 때까지: 이전 행 마지막 열 비용 + 현재 비용 
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static int[][] dpTable;

	static App[] applications;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dpTable = new int[2][M + 1];
		Arrays.fill(dpTable[0], Integer.MAX_VALUE);
		Arrays.fill(dpTable[1], Integer.MAX_VALUE);
		dpTable[0][0] = 0;
		dpTable[1][0] = 0;

		applications = new App[N];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < N; ++idx) {
			applications[idx] = new App();
			applications[idx].setMemory(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < N; ++idx) {
			applications[idx].setCost(Integer.parseInt(st.nextToken()));
		}

		// 로직 시작
		for (int appIdx = 1; appIdx < N + 1; ++appIdx) {
			App currApp = applications[appIdx - 1];
			int cnt = 0;
			for (int col = 1; col < M + 1; ++col) {
				if (col <= currApp.memory) {
					// 현재 app만을 통해 확보할 수 있는 메모리크기
					dpTable[1][col] = Math.min(dpTable[0][col], currApp.cost);
				} else if (col > currApp.memory && dpTable[0][col] != Integer.MAX_VALUE) {
					// 이전에 확보할 수 있는 메모리 크기
					dpTable[1][col] = Math.min(dpTable[0][col], currApp.cost + dpTable[0][col - currApp.memory]);
				} else if (col > currApp.memory && dpTable[0][col] == Integer.MAX_VALUE && dpTable[0][col - currApp.memory] != Integer.MAX_VALUE) {
					// 이전에 확보할 수 없는 메모리 크기
					if (appIdx == 1)
						break;
					++cnt;
					dpTable[1][col] = Math.min(dpTable[0][col], currApp.cost + dpTable[0][col - currApp.memory]);
//					System.out.print(dpTable[1][col]+" ");
				} else {
//					System.out.println("ㅋㅋ");
					break;
				}
			}
			for (int idx = 0; idx < M + 1; ++idx) {
				dpTable[0][idx] = dpTable[1][idx];
			}
//			System.out.println(Arrays.toString(dpTable[1]));
		}

		System.out.println(dpTable[0][M]);
	}

	static class App {
		int memory, cost;

		public App() {
			super();
		}

		public App(int memory, int cost) {
			super();
			this.memory = memory;
			this.cost = cost;
		}

		public void setMemory(int memory) {
			this.memory = memory;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

	}

}