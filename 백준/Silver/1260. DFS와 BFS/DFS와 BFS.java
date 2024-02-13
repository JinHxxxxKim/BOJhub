import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V를 입력받는다.
 * 2. 각 정점에 대한 정보를 인접리스트의 형태로 저장한다. (ArrayList[])
 * 		- 이 때, 모든 정점의 정보를 입력받았다면, 오름차순으로 정렬한다.(방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문한다) 		
 * 3. DFS를 수행한다.
 * 		- stack에서 정점하나를 꺼낸다.
 * 		- 방문처리를 진행한다.
 * 		- 현재 정점에 인접한 정점을 살펴보며, 방문한 적이 없다면, stack에 추가한다. 
 * 4. BFS를 수행한다.
 * 		- queue에서 정점하나를 꺼낸다.
 * 		- 현재 정점에 인접한 정점을 살펴보며, 방문한 적이 없다면, queue에 추가하며, 방문처리를한다.
 * 5. 출력
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M, V;
	private static List<Integer>[] adList;
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		// 정점의 개수
		N = Integer.parseInt(st.nextToken());
		// 간선의 개수
		M = Integer.parseInt(st.nextToken());
		// 시작 정점
		V = Integer.parseInt(st.nextToken());
		
		// 인접리스트
		adList = new ArrayList[N+1];
		for(int node = 1;node<=N;++node) {
			adList[node] = new ArrayList<>();
		}
		
		// 간선입력
		for (int edgeCnt = 0; edgeCnt < M; ++edgeCnt) {
			st = new StringTokenizer(br.readLine().trim());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			// 양방향 연결
			adList[node1].add(node2);
			adList[node2].add(node1);
		}
		
		
		// DFS
		isVisited = new boolean[N+1];
		for (int node = 1; node <= N; ++node) {
			Collections.sort(adList[node], Collections.reverseOrder());
		}
		dfs();
		sb.append("\n");
		
		// BFS
		isVisited = new boolean[N+1];
		for (int node = 1; node <= N; ++node) {
			Collections.sort(adList[node]);
		}
		bfs();
		
		System.out.println(sb);
	}

	private static void dfs() {
		Stack<Integer> stk = new Stack<>();
		// 시작정점 추가
		stk.add(V);
		while (!stk.isEmpty()) {
			int currNode = stk.pop();
			if(isVisited[currNode])
				continue;
			isVisited[currNode] = true;
			sb.append(currNode).append(" ");
			// 인접 노드 확인
			for (int adNode : adList[currNode]) {
				if(isVisited[adNode])
					continue;
				// 방문한 적이 없다면 stack에 추가
				stk.add(adNode);
			}
		}
		
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque();
		// 시작정점 추가
		q.add(V);
		isVisited[V] = true;
		while (!q.isEmpty()) {
			int currNode = q.poll();
			sb.append(currNode).append(" ");
			// 인접 노드 확인
			for (int adNode : adList[currNode]) {
				if (isVisited[adNode])
					continue;
				// 방문한 적이 없다면 queue에 추가
				q.add(adNode);
				// 최적화를 위한 방문처리
				isVisited[adNode] = true;
			}
		}
	}
}