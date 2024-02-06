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
 * 1. 회사의 직원 수 n명, 최초의 칭찬의 횟수 m을 입력받는다.
 * 2. 직원 n명의 직속 상사의 번호를 입력받는다.
 * 		=> 인접 리스트 초기화
 * 3. m줄에는 직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w를 입력받는다.
 * 4. i를 시작으로 BFS를 돌려서 각 직원의 칭찬점수에 w를 더한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, M;
	private static int[] score;
	private static ArrayList<Integer>[] members;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 칭찬점수 초기화
		score = new int[N+1];
		// 회사 인접리스트 초기화
		members = new ArrayList[N+1];
		for(int idx = 1;idx<=N;++idx) {
			members[idx] = new ArrayList<>();
		}
		
		// 관계 정립
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 1;idx<=N;++idx) {
			// idx = 사원
			// 상사 정보
			int superior = Integer.parseInt(st.nextToken());
			if (superior == -1) {
				continue;
			}
			members[superior].add(idx);
		}
		
		for (int cnt = 0; cnt < M; ++cnt) {
			st = new StringTokenizer(br.readLine().trim());
			// 칭찬 받은 사람
			int whoPraise = Integer.parseInt(st.nextToken());
			// 칭찬 점수
			int scoreAmount = Integer.parseInt(st.nextToken());
			score[whoPraise] += scoreAmount;
		}
		
		bfs(1);
		
		for (int idx = 1; idx <= N; ++idx) {
			sb.append(score[idx]).append(" ");
		}

		System.out.println(sb);
	}

	private static void bfs(int root) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {root, 0});

		while (!q.isEmpty()) {
			int[] boss = q.poll();
			// 내 번호
			int myNum = boss[0];
			
			// 직속 상사의 점수
			int bossScore = boss[1];
			
			score[myNum] += bossScore;

			for (int subordinate : members[myNum]) {
				q.offer(new int[] { subordinate, score[myNum] });
			}
		}
	}
}