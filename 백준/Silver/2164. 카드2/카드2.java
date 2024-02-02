

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. N을 입력받는다.
 * 2. 1~N까지 큐에 삽입한다.
 * 3. 한번 뺴고, 재삽입을 반복한다. (size가 1이 될때까지)
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int N;
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int num=1;num<=N;++num) {
			q.offer(num);
		}
		boolean isTrash = true;
		while(q.size()>1) {
			if(isTrash) {
				q.poll();
				isTrash = false;
			}
			else {
				q.offer(q.poll());
				isTrash = true;
			}
		}
		System.out.println(q.poll());
	}
}
