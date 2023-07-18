import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, connectedComponent;
	private static int[][] adjacencyMatrix;
	private static int[] visited;
	
	private static int[] q;
	private static int front, rear;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjacencyMatrix = new int[N+1][N+1];
		visited = new int[N+1];
		q = new int[N*N];
		front = -1;
		rear = -1;
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			adjacencyMatrix[node1][node2] = 1;
			adjacencyMatrix[node2][node1] = 1;
		}
		
		for(int i=1;i<=N;i++) {
			if(visited[i] == 1)	continue;
			bfs(i);
		}
		System.out.println(connectedComponent);
	}	 
	private static void bfs(int num) {
		add(num);
		visited[num] = 1;
		while(rear!=front) {
			int currNum = pop();
			for(int i=1;i<=N;i++) {
				if(adjacencyMatrix[currNum][i] == 0 || visited[i] == 1)
					continue;
				add(i);
				visited[i] = 1;
			}
		}
		connectedComponent++;
	}
	
	private static void add(int num) {
		q[++rear] = num;
	}
	private static int pop() {
		return q[++front];
	}
}