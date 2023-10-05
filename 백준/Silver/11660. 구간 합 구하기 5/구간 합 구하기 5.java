import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	private static int N, T;
	private static int[][] map;
	private static int[][] DP;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		DP = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		DP[1][1] = map[1][1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(i==1&&j==1)
					continue;
				else if(j==1)
					DP[i][j] = DP[i-1][j]+map[i][j];
				else
					DP[i][j] = DP[i][j-1]+DP[i-1][j]+map[i][j]-DP[i-1][j-1];
			}
		}
//		for(int i=0;i<=N;i++)
//			System.out.println(Arrays.toString(DP[i]));
		for(int test_case = 1;test_case<=T;test_case++) {
			st = new StringTokenizer(br.readLine());
			int x1, x2, y1, y2;
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			int ans = 0;
			ans = DP[x2][y2]-DP[x2][y1-1]-DP[x1-1][y2]+DP[x1-1][y1-1];
			sb.append(ans+"\n");
		}
		System.out.println(sb.toString());
	}
}