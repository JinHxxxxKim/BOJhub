
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. 사람의 수 N (5 ≤ N ≤ 2000)과 친구 관계의 수 M (1 ≤ M ≤ 2000)을 입력받는다.
 * 2. 친구 관계를 입력받는다.
 * 3. 각 정점에서 DFS 탐색을 진행한다.
 * 		- 재귀의 깊이가 5가 될 경우 문제의 조건이 만족된 것이므로 종료한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static List<Integer>[] adList;
	private static boolean[] isVisited;
	private static int N, M;
	private static boolean flag; // 만족하는 친구관계가 있는지 판별 플래그 
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adList = new List[N];
		isVisited = new boolean[N];
		for (int idx = 0; idx < N; ++idx) {
			adList[idx] = new ArrayList<>();
		}

		// 간선정보 저장
		for (int edgeCnt = 0; edgeCnt < M; ++edgeCnt) {
			st = new StringTokenizer(br.readLine().trim());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			adList[node1].add(node2);
			adList[node2].add(node1);
		}
		
		// 0번 정점부터 N-1번 정점까지 DFS 순회
		for(int nodeNum = 0;nodeNum<N;++nodeNum) {
			isVisited = new boolean[N];
			isCorrectConnection(nodeNum, 0);
			if(flag)
				break;
		}
		
		if(flag) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}

	private static void isCorrectConnection(int currNode, int depth) {
		// 기저조건(친구관계 만족)
		if(depth == 5) {
			flag = true;
			return;
		}
		if (flag) {
			return;
		}
		if(isVisited[currNode]) {
			return;
		}
//		System.out.println("currNode: "+currNode);
//		System.out.println("depth: "+depth);
		isVisited[currNode] = true;
		// 인접 노드 탐색
		for (int adNode : adList[currNode]) {
			isCorrectConnection(adNode, depth + 1);
		}
		isVisited[currNode] = false;
	}
}