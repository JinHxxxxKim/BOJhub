
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	static int N;
	static int[] array;
	static boolean[] isOut;
	
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine().trim());
		array = new int[N];
		isOut = new boolean[N];
		int num = 1;
		int idx = 0;
		// N개의 카드를 뽑는다.
		for (int cnt = 1; cnt <= N; ++cnt) {
			// cnt만큼 이동한 뒤, 카드를 뽑아야한다.
			int moveCnt = 0;
			while (moveCnt < cnt) {
				if(isOut[idx]) {
					// 이미 제거된 카드라면 PASS
					idx = (idx + 1) % N;
				}else {
					idx = (idx + 1) % N;
					++moveCnt;
				}
			}
			
			while(isOut[idx]) {
				idx = (idx + 1) % N;
			}
			isOut[idx] = true;
			array[idx] = num++;
		}
		for(int seq:array) {
			sb.append(seq).append(" ");
		}
		System.out.println(sb.toString().trim());
	}
}
