
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 아이디어: 가로가 증가하는 방향밖에 없으므로, 4방향에 대해 먼저 모두 소모한 뒤, 1칸씩 이동
 * 
 * 1. 가로 길이의 크기 확인
 * 	1-1. 4가지 방향을 모두 사용하기 위한 가로 값의 최소값 = 7
 *  1-2. 4가지 방향을 모두 사용하기 위한 세로 값의 최소값 = 2
 * 2. 총 4가지의 경우의 수 존재
 * 	2-1. 가로, 세로 모두 만족: 4방향 모두 이동 뒤, 남은 가로 만큼 이동가능
 * 	2-2. 가로만 만족: 상관 X
 * 	2-3. 세로만 만족: min(가로의 길이, 4)
 * 	2-4. 가로, 세로 모두 불만족:
 * 		2-4-1. 세로의 길이 = 1: 1
 * 		2-4-2. 세로의 길이 = 2: 1>3>5>7 방식으로 진행 가능 
 * 
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static final int MIN_WIDTH = 7;
	private static final int MIN_HEIGHT = 3;
	private static int width, height;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		int ans = 1;
		
		if (width >= MIN_WIDTH) {
			if (height >= MIN_HEIGHT) {
				ans += 4;
				ans += width - MIN_WIDTH;
			} else if (height == 1) {
				ans = 1;
			} else if (height == 2) {
				ans = Math.min(4, (width + 1) / 2);
			}
		} else {
			if (height >= MIN_HEIGHT) {
				ans = Math.min(4, width);
			} else if (height == 1) {
				ans = 1;
			} else if (height == 2) {
				ans = Math.min(4, (width+1)/2);
			}
		}
		
		System.out.println(ans);
	}
}