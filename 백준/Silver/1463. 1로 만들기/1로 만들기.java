

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. 
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static final int GOAL = 1;
	private static int N;
	private static int[] dist;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		dist = new int[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[N] = 0;
		q.add(N);
		while (!q.isEmpty()) {
			int currNum = q.poll();
			if (currNum == GOAL)
				break;
			if (currNum % 3 == 0 && dist[currNum] + 1 < dist[currNum / 3]) {
				q.offer(currNum / 3);
				dist[currNum / 3] = dist[currNum] + 1;
			}
			if (currNum % 2 == 0 && dist[currNum] + 1 < dist[currNum / 2]) {
				q.offer(currNum / 2);
				dist[currNum / 2] = dist[currNum] + 1;
			}
			if (dist[currNum] + 1 < dist[currNum - 1]) {
				q.offer(currNum - 1);
				dist[currNum - 1] = dist[currNum] + 1;
			}
		}

		System.out.println(dist[GOAL]);
	}
}