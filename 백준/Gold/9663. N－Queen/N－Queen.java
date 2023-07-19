import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	private static int N;
	private static int[] board;
	private static int cnt;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N];
		N_Queen(0);
		System.out.println(cnt);
	}
	
	private static void N_Queen(int cdx) {
		if(cdx==N) {
			cnt++;
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(promising(i, cdx)) {
				board[cdx] = i;
				N_Queen(cdx+1);
			}
		}
	}

	private static boolean promising(int chk, int cdx) {
		for(int i=0;i<cdx;i++) {
			if(board[i]==chk||Math.abs(i-cdx)==Math.abs(chk-board[i]))
				return false;
		}
		return true;
	}
}