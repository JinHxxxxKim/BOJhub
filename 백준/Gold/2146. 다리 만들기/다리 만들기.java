import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	private static int N, areaCnt, ans;
	private static Node[][] map;
	private static boolean[][] visited;
	private static int[][] dist;
	
	private static Queue<Node> q;
	
	private static final int[] dx = new int[] {-1, 1, 0, 0};
	private static final int[] dy = new int[] {0, 0, -1, 1};
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		areaCnt = 1;
		ans = Integer.MAX_VALUE;
		StringTokenizer st;
		map = new Node[N][N];
		visited = new boolean[N][N];
		q = new LinkedList<Main.Node>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				if(Integer.parseInt(st.nextToken())==1)
					map[i][j] = new Node(i, j, true);
				else
					map[i][j] = new Node(i, j, false);
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!map[i][j].isLand)
					continue;
				if(visited[map[i][j].row][map[i][j].col])
					continue;
				setNumBFS(i ,j);
				areaCnt++;
			}
		}
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!map[i][j].isBeach)
					continue;
				if(visited[i][j])
					continue;
				q.clear();
				dist = new int[N][N];
				//System.out.println(i+" "+j);
				getBridgeSizeBFS(i, j);
				visited = new boolean[N][N];
			}
		}
		System.out.println(ans);
	}
	private static void getBridgeSizeBFS(int row, int col) {
		int startNum = map[row][col].areaNum;
		visited[row][col] = true;
		q.offer(map[row][col]);
		while(!q.isEmpty()) {
			Node currNode = q.poll();
			if(currNode.isBeach&&currNode.isLand&&currNode.areaNum!=startNum) {
				//System.out.println("row: "+currNode.row);
				//System.out.println("col: "+currNode.col);
				//for(int i=0;i<N;i++)
				//	System.out.println(Arrays.toString(dist[i]));
				ans = Math.min(ans, dist[currNode.row][currNode.col]-1);
				//System.out.println(ans);
				break;
			}
			for(int i=0;i<4;i++) {
				int tempRow = currNode.row+dx[i];
				int tempCol = currNode.col+dy[i];
				if(tempRow<0||tempCol<0||tempRow>=N||tempCol>=N)
					continue;
				if(visited[tempRow][tempCol])
					continue;
				if(startNum==map[tempRow][tempCol].areaNum)
					continue;
			
				q.offer(map[tempRow][tempCol]);
				visited[tempRow][tempCol] = true;
				dist[tempRow][tempCol] = dist[currNode.row][currNode.col]+1;
			}
		}
		
	}
	
	private static void setNumBFS(int row, int col) {
		map[row][col].areaNum = areaCnt;
		visited[row][col] = true;
		q.offer(map[row][col]);
		while(!q.isEmpty()) {
			Node currNode = q.poll();
			for(int i=0;i<4;i++) {
				int tempRow = currNode.row+dx[i];
				int tempCol = currNode.col+dy[i];
				if(tempRow<0||tempCol<0||tempRow>=N||tempCol>=N)
					continue;
				if(visited[tempRow][tempCol])
					continue;
				if(!map[tempRow][tempCol].isLand) {
					map[currNode.row][currNode.col].isBeach = true;
					continue;
				}
				map[tempRow][tempCol].areaNum = areaCnt;
				visited[tempRow][tempCol] = true;
				q.offer(map[tempRow][tempCol]);
			}
		}
	}
	
	private static class Node{
		boolean isLand;
		boolean isBeach;
		int row, col;
		int areaNum;
		public Node(int row, int col, boolean l) {
			this.row = row;
			this.col = col;
			this.isLand = l;
		}
	}
}