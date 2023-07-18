import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] time, visited;
	private static int X, K, size;
	
	private static int[] q;
	private static int front, rear;
	
	private static final int[] dx = new int[] {-1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		size = 2*(Math.max(X, K))+1;
		q = new int[size];
		time = new int[size];
		visited = new int[size];
		
		if(X>=K) {
			System.out.println(X-K);
			return;
		}
		
		rear = -1;
		front = -1;
		bfs(X);
		//System.out.println(Arrays.toString(time));
		System.out.println(time[K]);
	}	 
	private static void bfs(int num) {
		add(num);
		visited[num] = 1;
		while(rear!=front) {
			int currNum = pop();
			
			for(int i=0;i<2;i++) {
				int tempNum = currNum + dx[i];
				if(tempNum<0||tempNum>=size)
					continue;
				if(visited[tempNum] == 1)
					continue;
				add(tempNum);
				visited[tempNum] = 1;
				time[tempNum] = time[currNum]+1;
				if(tempNum == K)
					return;
			}
			
			int tempNum = currNum*2;
			if(tempNum>=size)
				continue;
			if(visited[tempNum] == 1)
				continue;
			add(tempNum);
			visited[tempNum] = 1;
			time[tempNum] = time[currNum]+1;
			if(tempNum == K)
				return;
		}
	}
	
	private static void add(int num) {
		q[++rear] = num;
	}
	private static int pop() {
		return q[++front];
	}
}