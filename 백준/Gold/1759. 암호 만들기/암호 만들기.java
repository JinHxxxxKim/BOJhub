

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 
 * 
 * 1. 두 정수 L, C를 입력받는다.
 * 		- L: 암호문의 길이
 * 		- C: 주어진 알파벳의 수
 * 2. C개의 문자들을 입력받는다.
 * 3. 주어진 문자들을 오름차순으로 정렬한다.
 * 4. 조합을 구성한다.
 * 		- 기저조건: selectIdx == L
 * 			- 자음, 모음의 수가 충족되지 않으면 append하지 않는다.
 * 		- 조합의 매개변수로 현재까지의 모음의 개수, 자음의 개수를 함께 넘긴다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static int L, C;
	private static char[] alphabet;
	private static int[] select;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alphabet = new char[C];
		
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < C; ++idx) {
			alphabet[idx] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alphabet);
//		System.out.println(Arrays.toString(alphabet));
		select = new int[L];
		makeCrypto(0, 0);
		
		System.out.println(sb);
	}

	private static void makeCrypto(int selectIdx, int elementIdx) {
		// 기저조건
		if(selectIdx == L) {
//			System.out.println(Arrays.toString(select));
			// 자음 모음 카운팅
			int gatherCnt = 0;
			int consonantCnt = 0;
			for (int idx = 0; idx < L; ++idx) {
				if (alphabet[select[idx]] == 'a' || alphabet[select[idx]] == 'e' || alphabet[select[idx]] == 'i' || alphabet[select[idx]] == 'o'
						|| alphabet[select[idx]] == 'u')
					gatherCnt++;
				else
					consonantCnt++;
			}
			if (gatherCnt > 0 && consonantCnt > 1) {
				for (int idx = 0; idx < L; ++idx) {
					sb.append(alphabet[select[idx]]);
				}
				sb.append("\n");
			}
			
			
			return;
		}
		if(elementIdx == C) {
			return;
		}
		select[selectIdx] = elementIdx;
		makeCrypto(selectIdx+1, elementIdx + 1);
		makeCrypto(selectIdx, elementIdx + 1);
	}
}