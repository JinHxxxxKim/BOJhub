import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 조건1: 다리의 양 끝은 섬과 인접한 바다 위에 있어야 한다
 * 조건2: 한 다리의 방향이 중간에 바뀌면 안된다
 * 조건3: 다리의 길이는 2 이상
 * 
 * 다익스트라, union-find
 * 
 * 1. 각 섬을 번호를 증가시켜가며 식별 가능하도록 만든다.
 * 2. 모든 섬에서 다른 모든 섬으로 도달할 수 있는 거리를 PQ에 저장한다
 * 3. PQ에서 간선을 하나씩 꺼내며, 두 섬에 대해 find 연산을 진행한다.
 *   3-1. 두 섬의 root가 다르다면, union 연산 + 다리 길이 sum
 *   3-2. 두 섬의 root가 같다면, PASS
 * 4. 최종적으로 모든 섬이 연결되면, 모든 다리길이의 합을 출력한다.
 */
public class Main {
	static final int[] dx = {-1, 1, 0, 0};
	static final int[] dy = {0, 0, -1, 1};
	
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	// 기본 변수
	static int N, M;
	static int[][] map;
	static int islandNumber;
	static boolean[][] isVisited;
	static PriorityQueue<Edge> pq;
	
	// union-find 변수
	static int[] rootInfo;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		// 맵 입력
		for(int row =0; row<N;++row) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col<M;++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 식별
		isVisited = new boolean[N][M];
		islandNumber = 1;
		for(int row =0; row<N;++row) {
			for(int col = 0; col<M;++col) {
				if(map[row][col]>0) {
					if(isVisited[row][col])
						continue;
					identifyIsland(row, col);
				}
			}
		}
		
//		for(int[] row:map) {
//			System.out.println(Arrays.toString(row));
//		}
		
		// 각 섬에서 다른섬으로 도달할 수 있는 경로 저장
		pq = new PriorityQueue<>();
		for(int row =0; row<N;++row) {
			for(int col = 0; col<M;++col) {
				if(map[row][col]>0) {
					tryBridge(row, col);
				}
			}
		}
		
		// 하나의 그래프로 Union
		int ans = 0;
		rootInfo = new int[islandNumber];
		for (int idx = 1; idx < islandNumber; ++idx) {
			rootInfo[idx] = idx;
		}
		while(!pq.isEmpty()) {
			// 최소비용 간선 뽑기
			Edge currEdge = pq.poll();
			int src = currEdge.src;
			int dest = currEdge.dest;
			int dist = currEdge.dist;
			
			// 1. 두 노드가 현재 연결되어있는가 Check
			int srcRoot = find(src);
			int destRoot = find(dest);
			
			// 2. 연결되어있다.
			if(srcRoot == destRoot) {
				continue;
			}
			// 3. 연결되어있지 않다.
			else {
				union(srcRoot, destRoot);
				ans += dist;
			}
		}
		//System.out.println(Arrays.toString(rootInfo));
		// 모든 섬이 연결되어야한다.
		int totalRoot = find(1);
		for (int island = 1; island < islandNumber; ++island) {
			if (find(island) != totalRoot) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(ans);
	}

	private static void union(int rootNode1, int rootNode2) {
		rootInfo[find(rootNode1)] = find(rootNode2);
		
	}

	private static int find(int src) {
		if(rootInfo[src] == src) return src;
		else return rootInfo[src] = find(rootInfo[src]);
	}

	private static void tryBridge(int row, int col) {
		for (int dir = 0; dir < 4; ++dir) {
			int tempRow = row + dx[dir];
			int tempCol = col + dy[dir];
			int dist = 0;
			while(tempRow >= 0 && tempCol >= 0 && tempRow < N && tempCol < M) {
				// 동일한 섬이다
				if(map[tempRow][tempCol] == map[row][col])
					break;
				// 바다가 아니다
				if(map[tempRow][tempCol]>0) {
					// 거리가 1이 아니라면
					if(dist > 1) {
						// 다리 건설 후보 등록
						pq.offer(new Edge(map[row][col], map[tempRow][tempCol], dist));
						break;
					}
					// 거리가 1이라면
					else {
						break;
					}
				}else {
					++dist;
					tempRow += dx[dir];
					tempCol += dy[dir];
				}
			}
			
			// 범위 검증
			if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
				continue;
			}
			// 섬 검증
			if (map[tempRow][tempCol] == 0) {
				continue;
			}
		}
	}

	private static void identifyIsland(int row, int col) {
		// BFS를 바탕으로 섬 식별
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[]{row, col});
		isVisited[row][col] = true;
		while(!q.isEmpty()) {
			int[] currNode = q.poll();
			int currRow = currNode[0];
			int currCol = currNode[1];
			// 섬 식별
			map[currRow][currCol] = islandNumber;
			// 4방 탐색
			for (int dir = 0; dir < 4; ++dir) {
				int tempRow = currRow + dx[dir];
				int tempCol = currCol + dy[dir];

				// 범위 검증
				if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
					continue;
				}
				// 섬 검증
				if (map[tempRow][tempCol] == 0) {
					continue;
				}
				// 방문검증
				if (isVisited[tempRow][tempCol]) {
					continue;
				}
				isVisited[tempRow][tempCol] = true;
				q.offer(new int[] { tempRow, tempCol });
			}
		}
		++islandNumber;
	}

	static class Edge implements Comparable<Edge> {
		int src, dest, dist;

		public Edge(int src, int dest, int dist) {
			super();
			this.src = src;
			this.dest = dest;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dist, o.dist);
		}

		@Override
		public String toString() {
			return "Edge [src=" + src + ", dest=" + dest + ", dist=" + dist + "]";
		}
		
		
		
	}
}
