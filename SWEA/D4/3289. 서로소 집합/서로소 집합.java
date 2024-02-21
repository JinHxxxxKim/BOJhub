
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 테스트 케이스의 수 TC를 입력받는다.
 * 2. n(1≤n≤1,000,000), m(1≤m≤100,000)을 입력받는다.
 * 		n: 노드의 개수, m: 연산의 개수
 * 		- Make-Set() 연산 실행
 * 3. m개의 연산에 대해 반복
 * 		- 1: 두 집합의 대표자를 찾고 같다면 1을 append, 아니라면 0을 append
 * 		- 0: 두 집합에 대해 union 연산을 실행한다.
 * 4. 출력
 */
public class Solution {
	private static final int UNION = 0;
	private static final int IS_SAME_SET = 1;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N, M;
	
	// 각 노드의 부모(대표자)를 저장하는 배열
	private static int[] parents;
	
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringBuilder localSb = new StringBuilder();
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			
			// Make-Set 연산
			for(int node = 1;node<=N;++node) {
				parents[node] = node;
			}
			
			// 연산 시작
			for (int opCnt = 0; opCnt < M; ++opCnt) {
				st = new StringTokenizer(br.readLine().trim());
				int op = Integer.parseInt(st.nextToken());
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				
				switch (op) {
				case UNION:
					union(node1, node2);
					break;
				case IS_SAME_SET:
					int node1Representative = getRepresentative(node1);
					int node2Representative = getRepresentative(node2);
					if(node1Representative == node2Representative) {
						localSb.append(1);
					}else {
						localSb.append(0);
					}
					break;
				}
			}
			sb.append(String.format("#%d %s\n", testCase, localSb.toString()));
		}
		System.out.println(sb);
	}


	private static void union(int node1, int node2) {
		int node1Representative = getRepresentative(node1);
		int node2Representative = getRepresentative(node2);
		
		parents[node1Representative] = node2Representative;
	}


	private static int getRepresentative(int node) {
		if (parents[node] == node)
			return node;
		else
			return parents[node] = getRepresentative(parents[node]);
	}
}