import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 색종이의 수를 입력받는다.
 * 2. 색종이를 붙인 위치를 반복해서 입력받는다.
 * 3. boolean 2차원 배열에 대해 색종이가 붙은 위치를 계산해 true로 변경한다.
 * 4. boolean 2차원 배열을 순회하며 true인 개수를 카운팅한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	private static boolean[][] map;
	
	public static void main(String[] args) throws Exception {
		// 색종이의 수를 입력받는다.
		N = Integer.parseInt(br.readLine().trim());
		
		// 전체 도화지 배열
		map = new boolean[101][101];
		
		// 색종이를 붙인 위치를 반복해서 입력받는다.
		for (int paperCnt = 0; paperCnt < N; ++paperCnt) {
			st = new StringTokenizer(br.readLine().trim());
			int rowStart = Integer.parseInt(st.nextToken())+1;
			int colStart = Integer.parseInt(st.nextToken())+1;
			
			// boolean 2차원 배열에 대해 색종이가 붙은 위치를 계산해 true로 변경한다.
			for(int row = 0; row<10;++row) {
				for(int col = 0;col<10;++col) {
					map[rowStart+row][colStart+col] = true;
				}
			}
		}
		
		int ans = 0;
		// boolean 2차원 배열을 순회하며 true인 개수를 카운팅한다.
		for(int row = 1; row<101;++row) {
			for(int col = 1;col<101;++col) {
				if(map[row][col])
					++ans;
			}
		}
		
		System.out.println(ans);
	}
}
