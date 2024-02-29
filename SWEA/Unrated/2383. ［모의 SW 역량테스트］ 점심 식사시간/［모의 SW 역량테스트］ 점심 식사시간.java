

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * @author 김진형
 * 
 * 1. 총 N명의 사람들에 대해 어느계단으로 내려갈지 정한다
 * 2. 1번계단, 2번 계단에 대해 모든 조합이 완성되었다면 각 계단별로 시뮬레이션을 돌린다.
 * 		2-1. 각 계단별로 도착하는 사람들을 거리가 짧은 순으로 정렬한다.
 * 		2-2. 두 계단에 대해 현재시간을 1분부터 1분단위로 어떤 상태인지 갱신해나간다.
 * 			2-2-1. 아무도 계단에 도달하지 않았다면 시간 + 1
 * 			2-2-2. 계단에 도달하고 내려갈 준비를 마친 인원이 있다면 계단에 넣는다.
 * 			2-2-3. 계단에 이미 3명의 사람이 있다면 시간 + 1
 * 			2-2-4. 계단을 빠져나간 인원이 있다면 계단에서 뺀	다.
 * 3. 두 계단의 소요시간이 모두 구해졌다면, 두 계단 중 최대값을 구한다.
 * 4. 구한 최대값이 기존의 소요시간보다 작다면 갱신한다.
 * 

1
5
0 1 1 0 0
0 0 1 0 3
0 1 0 1 0
0 0 0 0 0
1 0 5 0 0


 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N; // 맵 크기
	private static int[][] map; // 맵 정보
	private static ArrayList<Pos> person;
	private static ArrayList<Stair> stair;
	private static int ans, temp;
	
	// 부분집합 관련 변수
	private static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			stair = new ArrayList<>();
			person = new ArrayList<>();
			for(int row = 0; row < N; ++row) {
				st = new StringTokenizer(br.readLine().trim());
				for(int col = 0; col < N; ++col) {
					map[row][col] = Integer.parseInt(st.nextToken());
					if(map[row][col] == 1)
						person.add(new Pos(row, col));
					if(map[row][col] > 1) {
						stair.add(new Stair((new Pos(row, col)), map[row][col]));
					}
				}
			}
			ans = Integer.MAX_VALUE;
			isSelected = new boolean[person.size()];
			powerSet(0);
			sb.append(String.format("#%d %d\n", testCase, ans));
//			System.out.println(temp);
		}
		System.out.println(sb);
	}
	
	private static void powerSet(int elementIdx) {
		if (elementIdx == person.size()) {
//			System.out.println(Arrays.toString(isSelected));
//			System.out.println(++temp);
//			System.out.println(Arrays.toString(isSelected));
			int aCnt = 0;
			int bCnt = 0;
			for(boolean isA:isSelected) {
				if(isA) aCnt++;
				else bCnt++;
			}
			// 각 계단에 도착까지 걸리는 시간
			int[] aDist = new int[aCnt];
			int[] bDist = new int[bCnt];
			
			aCnt = 0;
			bCnt = 0;
			for(int idx = 0; idx < person.size(); ++idx) {
				if(isSelected[idx]) {
					aDist[aCnt++] = Math.abs(stair.get(0).pos.row - person.get(idx).row) 
							+ Math.abs(stair.get(0).pos.col - person.get(idx).col);
				}else {
					bDist[bCnt++] = Math.abs(stair.get(1).pos.row - person.get(idx).row) 
							+ Math.abs(stair.get(1).pos.col - person.get(idx).col);
				}
			}
			
	
			// 각 계단의 종료시간
			int aTime = 0;
			int bTime = 0;
			
			// true: A계단으로
			stair.get(0).q = new ArrayDeque<>();
			Arrays.sort(aDist);
			aCnt = 0;
			while(aCnt < aDist.length) {
//				System.out.println("===================");
//				System.out.println("aCnt: "+aCnt);
//				System.out.println("aTime: "+aTime);
//				System.out.println("QSIZE: "+stair.get(0).q.size());
				if(aTime <= aDist[aCnt]) {
					while (!stair.get(0).q.isEmpty()) {
						if (stair.get(0).q.peek() <= aTime) {
							stair.get(0).q.poll();
						} else {
							break;
						}
					}
					// 아직 도달한 사람이 없을 경우 시간 증가
					++aTime;
				}else {
					// 가장 빨리 나오는 애 시간 확인
					while (!stair.get(0).q.isEmpty()) {
						if (stair.get(0).q.peek() <= aTime) {
							stair.get(0).q.poll();
						} else {
							break;
						}
					}
					// 도달한 사람들이 존재할 경우
					// 큐의 크기가 3이 될때까지 넣기
					while (stair.get(0).q.size() < 3 && aCnt < aDist.length && aDist[aCnt] < aTime) {
						stair.get(0).q.offer(aTime + stair.get(0).reqTime);
						aCnt++;
					}
					++aTime;
				}
			}
			while(!stair.get(0).q.isEmpty()) {
				aTime = stair.get(0).q.poll();
			}
			// false: B계단으로
			stair.get(1).q = new ArrayDeque<>();
			Arrays.sort(bDist);
			bCnt = 0;
			while(bCnt < bDist.length) {
				if(bTime <= bDist[bCnt]) {
					while (!stair.get(1).q.isEmpty()) {
						if (stair.get(1).q.peek() <= bTime) {
							stair.get(1).q.poll();
						} else {
							break;
						}
					}
					// 아직 도달한 사람이 없을 경우 시간 증가
					++bTime;
				}else {
					// 가장 빨리 나오는 애 시간 확인
					while (!stair.get(1).q.isEmpty()) {
						if (stair.get(1).q.peek() <= bTime) {
							stair.get(1).q.poll();
						} else {
							break;
						}
					}
					// 도달한 사람들이 존재할 경우
					// 큐의 크기가 3이 될때까지 넣기
					while (stair.get(1).q.size() < 3 && bCnt < bDist.length && bDist[bCnt] < bTime) {
						stair.get(1).q.offer(bTime + stair.get(1).reqTime);
						bCnt++;
					}
					++bTime;
				}
			}
			while (!stair.get(1).q.isEmpty()) {
				bTime = stair.get(1).q.poll();
			}
//			System.out.println("ATIME: "+aTime);
//			System.out.println("BTIME: "+bTime);
			ans = Math.min(ans, Math.max(aTime, bTime));
			
			return;
		}
		isSelected[elementIdx] = true;
		powerSet(elementIdx+1);
		
		isSelected[elementIdx] = false;
		powerSet(elementIdx + 1);
	}

	static class Pos{
		int row, col;

		public Pos(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	static class Stair{
		Queue<Integer> q;
		Pos pos;
		int reqTime;
		public Stair(Pos pos, int reqTime) {
			super();
			this.pos = pos;
			this.reqTime = reqTime;
			this.q = new ArrayDeque<>();
		}
		
	}
}
