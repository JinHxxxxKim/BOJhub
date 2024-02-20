import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000)을 입력받는다.
 * 2. N개의 노드를 생성, M개의 간선에 대한 정보를 입력받는다.
 * 3. Node1 -> Node2 로의 유향 그래프를 구성한다.
 * 4. 각 노드의 진입 차수를 모두 구한다.
 * 5. 위상 정렬을 수행한다.
 * 		- 진입 차수가 0인 모든 노드를 큐에 삽입한다.
 * 		- 큐에서 노드를 하나씩 꺼내어 확인한다.
 * 			- 꺼낸 노드를 sb에 append
 * 			- 꺼낸 노드에 연결된(진출 간선) 노드의 진입 차수를 -1 하고, 0이라면 큐에 넣는다.
 * 6. 출력
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M;
	private static int[] inEdgeCount;
	private static List<Integer>[] adList;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inEdgeCount = new int[N];
		
		adList = new ArrayList[N];
		for (int nodeCnt = 0; nodeCnt < N; ++nodeCnt) {
			adList[nodeCnt] = new ArrayList<>();
		}
		
		for (int edgeCnt = 0; edgeCnt < M; ++edgeCnt) {
			st = new StringTokenizer(br.readLine().trim());
			int node1 = Integer.parseInt(st.nextToken()) - 1;
			int node2 = Integer.parseInt(st.nextToken()) - 1;
			adList[node1].add(node2);
			
			// 진입 차수 카운팅
			inEdgeCount[node2] += 1;
		}
		
		// 위상정렬 START
		Queue<Integer> q = new ArrayDeque<>();
		// 위상정렬 전처리: 진입차수가 0인 노드 삽입
		for(int nodeNum = 0;nodeNum<N;++nodeNum) {
			if(inEdgeCount[nodeNum] == 0)
				q.offer(nodeNum);
		}
		
		while (!q.isEmpty()) {
			int currNode = q.poll();
			sb.append(currNode+1).append(" ");
			// 인접 노드 확인
			for (int adNode : adList[currNode]) {
				// 인접 노드의 진입차수 감소
				inEdgeCount[adNode]--;
				// 진입 차수가 0이 되었다면 큐에 삽입
				if(inEdgeCount[adNode] == 0)
					q.offer(adNode);
			}
		}
		System.out.println(sb);
	}
}