

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * 
 * 1. 건물에 대한 정보를 노드로, 선행 건물에 대한 정보를 엣지로 인식
 * 2. A건물이 B건물의 선행건물일 경우, A -> B 의 형태로 그래프 형성
 * 3. 위상정렬을 수행하면서, 각 노드(건물)을 방문할 때(in-degree==0 일 떄) 소요시간을 함께 저장한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N; // 건물 개수
	private static List<Integer>[] adList; // 인접리스트
	private static int[] inDgree; // 진입차수
	private static int[] reqTime; // 건물을 짓는데 걸리는 시간
	private static int[] totalTime; // 선행 건물을 짓고 최종적으로 걸리는 시간
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		adList = new List[N + 1];
		reqTime = new int[N+1];
		totalTime = new int[N+1];
		inDgree = new int[N+1];
		for(int idx = 1;idx<N+1;++idx) {
			adList[idx] = new ArrayList<Integer>();
		}
		for(int idx = 1;idx<N+1;++idx) {
			st = new StringTokenizer(br.readLine().trim());
			reqTime[idx] = Integer.parseInt(st.nextToken());
			int reqNode = Integer.parseInt(st.nextToken());
			while (reqNode != -1) {
				adList[reqNode].add(idx);
				reqNode = Integer.parseInt(st.nextToken());
				inDgree[idx]++;
			}
		}
//		for(int idx = 0;idx<N+1;++idx) {
//			System.out.println(adList[idx]);
//		}
		
		// 위상정렬 시작
		Queue<Integer> q = new ArrayDeque<>();
		// 1. 진입차수가 0인 노드 삽입
		for (int idx = 1; idx < N + 1; ++idx) {
			if (inDgree[idx] == 0) {
				q.offer(idx);
				totalTime[idx] = reqTime[idx];
			}
		}

		// 2. Queue에서 꺼낸 노드의 인접노드 확인
		while (!q.isEmpty()) {
			int currNode = q.poll();
//			System.out.println("currNode: "+currNode);
//			System.out.println(Arrays.toString(totalTime));

			for (int adNode : adList[currNode]) {
				--inDgree[adNode];
				if (inDgree[adNode] == 0) {
					q.offer(adNode);
				}else {
					
				}
				totalTime[adNode] = Math.max(totalTime[currNode] + reqTime[adNode], totalTime[adNode]);
			}
		}
		for (int idx = 1; idx < N + 1; ++idx) {
			sb.append(totalTime[idx]).append("\n");
		}
		System.out.println(sb);
	}
}