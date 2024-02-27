
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * 
 * 다른 봉우리에 의해 포함되지 않는 봉우리 개수 => 현재 봉우리 밖에 뭐가 있으면 안됨
 * 다른 봉우리를 포함하지 않는 봉우리 개수 => 현재 봉우리 안에 뭐가 있으면 안됨
 * 
 * 1. x축을 지나는 지점을 기록하여 봉우리 정보를 저장한다.
 * 2. 봉우리의 왼쪽 관통지점(point1)을 기준으로 오름차순 정렬한다.
 * 
 * SOLUTION 1. 다른 봉우리에 포함되지 않는 봉우리 개수
 * 		- 모든 봉우리에 대해 순회하며 확인한다.
 * 		- 처음 만나는 봉우리는 반드시 다른 봉우리에 포함되지 않는다.
 * 			-> 봉우리가 끝나는 지점(lastCursor: 해당 x좌표가 어떤 봉우리에도 속하지 않는다)을 첫 봉우리의 오른쪽 관통지점으로 설정한다.
 * 		- 이후 처음만나는 봉우리를 최외곽 봉우리로 설정하고 봉우리가 끝나는 지점을 갱신한다.  
 * 
 * SOLUTION 2. 다른 봉우리를 포함하지 않는 봉우리 개수
 * 		- 모든 봉우리에 대해 순회를 하되, 마지막 봉우리는 반드시 다른 봉우리를 포함하지 않으므로 빼고 순회한다.
 * 		- 현재 봉우리와 이후 봉우리를 비교하여 현재 봉우리의 오른쪽 관통좌표(point1)가 이후 봉우리의 왼쪽 관통좌표(point2)보다 작을 경우 카운팅한다.
 * 
 */

/**
 * 
 * 
12
5 -2
-3 -2
-3 1
-5 1
-5 2
-1 2
-1 -1
1 -1
1 2
2 2
2 3
5 3
----
2 2



22
5 1
3 1
3 -6
-12 -6
-12 -3
-10 -3
-10 3
-8 3
-8 -3
-4 -3
-4 1
-2 1
-2 -1
1 -1
1 3
10 3
10 -1
9 -1
9 1
7 1
7 -2
5 -2
 *
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 점의 수
	static List<Top> topList; // 봉우리 정보 저장 리스트

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		topList = new ArrayList<Top>();

		List<int[]> tempList = new ArrayList<>();
		for(int idx = 0; idx<N;++idx) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			tempList.add(new int[] {x, y});
		}
		
		
		// 좌 하단 찾기
		// x축 아래 있는 애 중에 x값이 젤 작은 애
		int minX = tempList.get(0)[0];
		int minY = tempList.get(0)[1];
		int minIdx = 0;
		for(int idx = 1; idx<tempList.size();++idx) {
			if (0 > tempList.get(idx)[1] && tempList.get(idx)[0] < minX) {

				minX = tempList.get(idx)[0];
				minY = tempList.get(idx)[1];
				minIdx = idx;

			}
		}
//		
//		System.out.println(minX);
//		System.out.println(minY);
//		System.out.println(minIdx);
		
		int beforeX = minX;
		int beforeY = minY;
		
		int pointCnt = 0; // x축을 관통하는 지점의 개수(2개가 되면 객체 생성후 0으로 초기화)
		int point1 = 0; // 관통지점 1
		int point2 = 0; // 관통지점 2
		int currIdx = (minIdx + 1)%N;
		
		for (int idx = 1; idx < N; ++idx) {
//			System.out.println(Arrays.toString(tempList.get(idx)));
			int currX = tempList.get(currIdx)[0];
			int currY = tempList.get(currIdx)[1];
			
			if (currY * beforeY < 0) {
				// 관통 된 경우
				if (pointCnt == 0) {
					point1 = currX;
					pointCnt++;
				} else if (pointCnt == 1) {
					point2 = currX;
					pointCnt++;
					topList.add(new Top(point1, point2));
				}
				
			}
			if (pointCnt == 2) {
				pointCnt = 0;
			}
			// 이전 좌표 갱신
			beforeX = currX;
			beforeY = currY;
			currIdx = (currIdx+1)%N;
		}


		// 봉우리의 왼쪽 관통 좌표를 기준으로 오름차순 정렬
		Collections.sort(topList);
		
//		System.out.println(topList);
		// SOLUTION 1. 다른 봉우리에 포함되지 않는 봉우리 카운팅
		int ans1 = 0;
		int lastCursor = 0; // 마지막 봉우리가 끝난지점
		if (topList.size() > 0) {
			ans1 = 1; // 맨 처음 봉우리는 반드시 어떤 봉우리에도 포함되지 않으므로 + 1
			lastCursor = topList.get(0).point2;
		}
		
		
		for (int idx = 1; idx < topList.size(); ++idx) {
			// 어떤 봉우리에 속해있는 봉우리는 PASS
			if (topList.get(idx).point1 < lastCursor)
				continue;
			else {
				// 아니라면 해당봉우리를 최외곽 봉우리로 설정
				ans1++;
				lastCursor = topList.get(idx).point2;
			}
		}
		
		
		// SOLUTION 2. 다른 봉우리를 포함하지 않는 봉우리 카운팅
		int ans2 = 1; // 마지막 봉우리는 항상 아무것도 포함하지 않을수밖에 없으므로 + 1
		for (int idx = 0; idx < topList.size() - 1; ++idx) {
			Top currTop = topList.get(idx); // 이번 봉우리
			Top nextTop = topList.get(idx + 1); // 다음 봉우리

			// 어떤 한 봉우리가 시작(point1을 지난 상태)시 다른 봉우리가 시작하지 않으면 된다.
			if (currTop.point2 < nextTop.point1) {
				ans2++;
			}
		}

		System.out.println(String.format("%d %d\n", ans1, ans2));
	}

	static class Top implements Comparable<Top> {
		int point1, point2; // x축을 관총하는 지점의 x좌표 두개

		// 정렬을 위해 point1을 작은 값으로 설정
		public Top(int point1, int point2) {
			if (point1 < point2) {
				this.point1 = point1;
				this.point2 = point2;
			} else {
				this.point1 = point2;
				this.point2 = point1;
			}

		}

		// 봉우리의 왼쪽점을 기준으로 오름차순 정렬
		@Override
		public int compareTo(Top o) {
			return Integer.compare(this.point1, o.point1);
		}

		@Override
		public String toString() {
			return "Top [point1=" + point1 + ", point2=" + point2 + "]";
		}
	}
}