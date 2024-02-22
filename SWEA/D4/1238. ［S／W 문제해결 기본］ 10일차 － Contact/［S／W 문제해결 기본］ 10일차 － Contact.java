
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 입력 받는 데이터의 길이와 시작점을 입력받는다.
 * 2. 그래프 정보를 입력받아 유향 그래프를 완성한다.
 * 3. 시작점에서 부터 BFS 탐색을 시작한다.
 * 		- 시작점의 Dist값은 0으로 설정한다.
 * 		- Queue에 삽입할 때, Dist값을 함께 계산해서 저장한다.
 * 4. 탐색이 끝나면 시작점에서 가장 멀리 떨어져있는 노드의 거리를 구한다.
 * 5. 해당 거리와 동일한 거리에 있는 노드 중 노드 번호가 가장 큰 노드를 출력한다.
 * 
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int startPoint;
	private static int N;
	private static List<Integer>[] adList;
	private static int[] dist;
	private static boolean[] isVisited;
	private static int maxDist;
	
	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			int len = Integer.parseInt(st.nextToken());
			startPoint = Integer.parseInt(st.nextToken());
			
			N = len / 2;
			adList = new List[101];
			for (int node = 0; node < 101; ++node) {
				adList[node] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine().trim());
			// 인접리스트 생성
			for(int idx = 0;idx<N;++idx) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				// 유향 그래프
				adList[from].add(to);
			}
			
			dist = new int[101];
			isVisited = new boolean[101];
			maxDist = 0;
			
			contact(startPoint);
			int ans = -1;
			for(int nodeNum = 1;nodeNum<101;++nodeNum) {
				if(dist[nodeNum]==maxDist && ans<nodeNum)
					ans = nodeNum;
			}
			
			sb.append(String.format("#%d %d\n", testCase, ans));
		}
		System.out.println(sb);
	}

	private static void contact(int startPoint) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(startPoint);
		isVisited[startPoint] = true;
		dist[startPoint] = 0;
		while (!q.isEmpty()) {
			int currNode = q.poll();
			for (int nextNode : adList[currNode]) {
				if (isVisited[nextNode])
					continue;
				q.offer(nextNode);
				isVisited[nextNode] = true;
				dist[nextNode] = dist[currNode] + 1;
				maxDist = Math.max(maxDist, dist[nextNode]);
			}
		}
	}
}
