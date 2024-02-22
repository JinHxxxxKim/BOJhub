import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. test case의 수 T(1≤T≤10)를 입력받는다.
 * 2. 정점의 개수 V(1≤V≤100,000)와 간선의 개수 E(1≤E≤200,000)를 입력받는다.
 * 3. 각 간선에 대한 정보를 나타내는 세 정수 A, B, C를 입력받아 간선의 정보를 저장한다.
 * 		- 이때 가중치의 최대값은 1,000,000, MST 간선의 개수는 100,000 따라서 가중치의 합은 long형으로 선언
 * 4. 간선의 정보를 가중치를 기준으로 오름차순으로 정렬한다.
 * 5. makeSet() 호출
 * 6. 간선의 개수가 V-1이 될때까지 반복
 * 		- 간선을 하나 뽑는다.
 * 		- to, from이 서로소 집합이라면 union한 뒤, 가중치를 더한다.
 * 7. 가중치 출력
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	// 그래프 정보
	private static int V, E;
	private static Edge[] edgeList;
	
	// UnionFind 정보
	private static int[] parents;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			edgeList = new Edge[E];
			
			for(int edgeCnt = 0;edgeCnt<E;++edgeCnt) {
				st = new StringTokenizer(br.readLine().trim());
				int node1 = Integer.parseInt(st.nextToken()) - 1;
				int node2 = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				edgeList[edgeCnt] = new Edge(node1, node2, weight);
			}
			// 간선 정렬
			Arrays.sort(edgeList);
			// makeSet()호출
			makeSet();

			long weightSum = 0;
			int edgeCnt = 0;
			for(Edge currEdge: edgeList) {
				// 서로 다른 집합에 속할 경우
				if (union(currEdge.node1, currEdge.node2)) {
					weightSum += currEdge.weight;
					++edgeCnt;
				}
				if(edgeCnt == V-1)
					break;
			}
			
			sb.append(String.format("#%d %d\n", testCase, weightSum));
		}
		System.out.println(sb);
	}

	// 매개변수로 전달된 node1, node2의 집합을 합치는 메소드
	private static boolean union(int node1, int node2) {
		int aRoot = find(node1);
		int bRoot = find(node2);
		// 이미 같은 집합에 속한다
		if(aRoot == bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}

	// 매개변수로 전달됭 node의 루트를 찾는 메소드
	private static int find(int node) {
		if(parents[node] == node) return node;
		else return parents[node] = find(parents[node]);
	}

	// unionFind 전처리
	private static void makeSet() {
		parents = new int[V];
		for (int node = 0; node < V; ++node) {
			parents[node] = node;
		}

	}

	// 간선 클래스
	static class Edge implements Comparable<Edge>{
		int node1, node2; 
		long weight;

		public Edge(int node1, int node2, long weight) {
			super();
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge edge) {
			return Long.compare(this.weight, edge.weight);
		}
		
	}
}