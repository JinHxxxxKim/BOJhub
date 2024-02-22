

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 전체 테스트 케이스의 수를 입력받는다.
 * 2. 섬의 개수 N을 입력받는다.
 * 3. 각 섬들의 좌표를 입력받는다.
 * 4. 해저터널 건설의 환경 부담 세율 실수 E를 입력받는다.
 * 5. 완전 그래프 형태로 모든 섬에 대한 간선을 생성하여 간선 리스트에 저장한다.
 * 6. 간선 리스트를 환경부담금 기준으로 오름차순으로 정렬한다.
 * 7. MST 알고리즘 실행
 * 
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N; // 섬의 개수
	private static int edgeNum; // 간선의 수 
	private static Island[] islands; // 섬 위치 정보 배열
	private static double E; // 세율
	private static Edge[] edgeList; // 간선 리스트
	
	private static int[] parents;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			edgeNum = (N * (N - 1)) / 2;
			islands = new Island[N];
			
			// 섬 위치 정보 저장
			for (int islandCnt = 0; islandCnt < N; ++islandCnt) {
				islands[islandCnt] = new Island();
			}
			st = new StringTokenizer(br.readLine().trim());
			for (int islandCnt = 0; islandCnt < N; ++islandCnt) {
				int x = Integer.parseInt(st.nextToken());
				islands[islandCnt].x = x;
			}
			st = new StringTokenizer(br.readLine().trim());
			for (int islandCnt = 0; islandCnt < N; ++islandCnt) {
				int y = Integer.parseInt(st.nextToken());
				islands[islandCnt].y = y;
			}
			
			// 세율 입력
			E = Double.parseDouble(br.readLine().trim());
			
			edgeList = new Edge[edgeNum];
			int edgeIdx = 0;
			// 완전 그래프 형태로 간선 저장 (Max: 1000x1000)
			for (int node1 = 0; node1 < N; ++node1) {
				for (int node2 = node1; node2 < N; ++node2) {
					if(node1 == node2) continue;
					edgeList[edgeIdx++] = new Edge(node1, node2);
				}
			}
			makeSet();
			// 가중치 기준 오름차순 정렬
			Arrays.sort(edgeList);
			
			double totalCost = 0;
			int edgeCnt = 0;
			for (Edge currEdge : edgeList) {
				if(union(currEdge.to, currEdge.from)) {
					totalCost += currEdge.cost;
					++edgeCnt;
				}
				if(edgeCnt == N-1)
					break;
			}
			
			sb.append(String.format("#%d %.0f\n", testCase, totalCost));
		}
		System.out.println(sb);
	}

	private static void makeSet() {
		parents = new int[N];
		for (int node = 0; node < N; ++node) {
			parents[node] = node;
		}
	}

	private static int find(int node) {
		if (parents[node] == node)
			return node;
		else
			return parents[node] = find(parents[node]);
	}
	
	private static boolean union(int node1, int node2) {
		int aRoot = find(node1);
		int bRoot = find(node2);
		if (aRoot == bRoot)
			return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	// 섬의 위치 정보 저장
	static class Island {
		int x, y;

		public Island() {
			super();
		}

		public Island(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	// 간선 클래스
	static class Edge implements Comparable<Edge> {
		int to, from;
		long distSquare;
		double cost;

		public Edge(int to, int from) {
			super();
			this.to = to;
			this.from = from;
			this.distSquare =
					(long)Math.abs(islands[to].x - islands[from].x) * (long)Math.abs(islands[to].x - islands[from].x)+
					(long)Math.abs(islands[to].y - islands[from].y) * (long)Math.abs(islands[to].y - islands[from].y);
			this.cost = (double)distSquare * E;
					
		}

		@Override
		public int compareTo(Edge edge) {
			return Double.compare(this.cost, edge.cost);
		}

	}
}