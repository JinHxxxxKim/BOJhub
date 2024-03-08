
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * 
 * 동쪽은 1, 서쪽은 2, 남쪽은 3, 북쪽은 4
 * 
 * 1. 정배 방향 
 * 		- 1(동) -> 4(북)
 * 		- 2(서) -> 3(남)
 * 		- 3(남) -> 1(동)
 * 		- 4(북) -> 2(서)
 * 2. 앞 뒤로 동일한 방향으로움직이는 곳이 뺴야하는 위치
 * 		ex) 3 -> 1 -> 3 순서라면 앞에 두 넓이가 빼야하는 넓이다.
 * 3. 전체 넓이: 1,2 중의 최대 거리 * 3, 4중에 최대 거리
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int K = Integer.parseInt(br.readLine().trim());
		int[][] track = new int[6][2];
		for (int idx = 0; idx < 6; ++idx) {
			st = new StringTokenizer(br.readLine().trim());
			track[idx][0] = Integer.parseInt(st.nextToken());
			track[idx][1] = Integer.parseInt(st.nextToken());
		}

		int rowMax = 0, colMax = 0;
		// 전체 면적 구하기
		for (int idx = 0; idx < 6; ++idx) {
			if (track[idx][0] == 1 || track[idx][0] == 2)
				rowMax = Math.max(rowMax, track[idx][1]);
			else
				colMax = Math.max(colMax, track[idx][1]);
		}
		int totalArea = colMax * rowMax;

		int excludeArea = 0;
		// 빼야하는 면적 구하기
		for (int idx = 0; idx < 6; ++idx) {
			int dir1 = track[idx][0];
			int dir2 = track[(idx + 1) % 6][0];
			int dir3 = track[(idx + 2) % 6][0];
			int dir4 = track[(idx + 3) % 6][0];

			if (dir1 == dir3 && dir2 == dir4) {
				// 찾았다.
				excludeArea = track[(idx + 2) % 6][1] * track[(idx + 1) % 6][1];
				break;
			}
		}
//		System.out.println(totalArea);
//		System.out.println(excludeArea);

		// 참외 개수 구하기
		System.out.println((totalArea - excludeArea) * K);
	}
}