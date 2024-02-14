

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 정수 N, r, c을 입력받는다.
 * 변의 길이(length) = Math.pow(2, N)
 * 기준 좌표(pivot) = length / 2
 * 
 * [r, c]에 대해 1구역(좌상단), 2구역(우상단), 3구역(좌하단), 4구역(우하단) 여부를 판단한다.
 * 
 * row < pivot && col < pivot : 1구역
 * row < pivot && col >= pivot : 2구역
 * row >= pivot && col < pivot : 3구역
 * row >= pivot && col >= pivot : 4구역
 * 
 * 재귀를 통해 row, col, n 값을 조정해서 호출한다
 * 		- row -= area * Math.pow(2, N/2)
 * 		- col -= area * Math.pow(2, N/2) 
 * 		- n = n / 2
 * 
 * n == 0 일 경우 1 반환 (기저조건)
 * 
 * 반환 받은 값에 대해 보정을 거친 뒤 반환
 * 
 */
public class Main {
	private static final int AREA_0 = 0;
	private static final int AREA_1 = 1;
	private static final int AREA_2 = 2;
	private static final int AREA_3 = 3;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N, r, c;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int result = getPosition(N, r, c);

		System.out.println(result);
	}

	private static int getPosition(int n, int row, int col) {
		// 기저조건
		if (n == 0)
			return 0;

		int size = (int) Math.pow(2, n);
		// 구역을 나눌 기준 인덱스
		int pivot = size / 2;

		if (row < pivot && col < pivot) {
			// 0구역
			int tempRow = row;
			int tempCol = col;
			int resPos = getPosition(n - 1, tempRow, tempCol);
			return resPos;
		} else if (row < pivot && col >= pivot) {
			// 1구역
			int tempRow = row;
			int tempCol = col - pivot;
			int resPos = getPosition(n - 1, tempRow, tempCol);
			return resPos + (AREA_1 * (int) Math.pow(4, n - 1));
		} else if (row >= pivot && col < pivot) {
			// 2구역
			int tempRow = row - pivot;
			int tempCol = col;
			int resPos = getPosition(n - 1, tempRow, tempCol);
			return resPos + (AREA_2 * (int) Math.pow(4, n - 1));
		} else {
			// 3구역
			int tempRow = row - pivot;
			int tempCol = col - pivot;
			int resPos = getPosition(n - 1, tempRow, tempCol);
			return resPos + (AREA_3 * (int) Math.pow(4, n - 1));
		}
	}
}