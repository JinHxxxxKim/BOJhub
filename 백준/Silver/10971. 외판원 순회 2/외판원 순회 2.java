

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * 
 * 1. 도시를 방문한 순서를 정한다(순열)
 * 2. 기저조건(모든 도시를 다 골랐을 경우)
 * 		- 주어진 순열에 대해 인접행렬을 이용해 비용을 구한다.
 * 		- 마지막으로 도착지에서 출발지로 다시 돌아오는 경로의 비용을 구한다.
 * 			- 0일 경우 불가능한 경로 이므로 바로 return
 * 		- 비용을 모두 구했다면 최소비용과 비교하여 갱신한다.
 * 
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	static int N;
	static int[][] adMatrix;
	static int minCost;

	static int[] elementList;
	static boolean[] isSelected;
	static int[] selectList;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		adMatrix = new int[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < N; ++col) {
				adMatrix[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		elementList = new int[N];
		isSelected = new boolean[N];
		selectList = new int[N];
		for (int num = 0; num < N; ++num)
			elementList[num] = num;

		minCost = Integer.MAX_VALUE;
		makeDestSeq(0, 0);
		System.out.println(minCost);
	}

	private static void makeDestSeq(int selectIdx, int costSum) {
		if (costSum > minCost)
			return;
		// 기저조건
		if (selectIdx == N) {
			int srcNode = selectList[N-1];
			int destNode = selectList[0];
			// 최종 목적지에서 다시 출발점으로 돌아오는 비용 계산
			if (adMatrix[srcNode][destNode] == 0) {
				return;
			}
			costSum += adMatrix[srcNode][destNode];
			minCost = Math.min(minCost, costSum);
			return;
		}

		for (int idx = 0; idx < N; ++idx) {
			if (isSelected[idx])
				continue;
			
			if (selectIdx == 0) {
				// 전처리
				isSelected[idx] = true;
				selectList[selectIdx] = idx;
				makeDestSeq(selectIdx + 1, costSum);
				// 후처리
				isSelected[idx] = false;
			} else {
				int beforeNode = selectList[selectIdx - 1];
				int currNode = elementList[idx];

				if (adMatrix[beforeNode][currNode] == 0) {
					continue;
				}
				// 전처리
				isSelected[idx] = true;
				selectList[selectIdx] = idx;
				makeDestSeq(selectIdx + 1, costSum + adMatrix[beforeNode][currNode]);
				// 후처리
				isSelected[idx] = false;
			}
		}
	}
}