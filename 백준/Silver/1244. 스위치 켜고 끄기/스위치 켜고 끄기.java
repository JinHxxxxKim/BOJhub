import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 스위치의 수(sNum)입력받기
 * 2. 스위치 상태(isOn)입력받기
 * 3. 학생 수(numOfStudent)입력받기
 * 4. 학생 수 만큼 반복한다
 * 	  num을 입력받는다
 * 		4-1. switch case문 
 * 			남학생(1)일 경우: isOn을 num배수만큼 순회하며 토글 
 * 			여학생(2)일 경우: num 기준 좌우를 비교하여 같다면 토글, 아니라면 break
 * 5. 출력
 */
public class Main {

	private static final int BOY = 1;
	private static final int GIRL = 2;

	private static int sNum;
	private static boolean[] isOn;
	private static int numOfStudent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 1. 스위치의 수(sNum)입력받기
		sNum = Integer.parseInt(br.readLine().trim());
		isOn = new boolean[sNum + 1];
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		// 2. 스위치 상태(isOn)입력받기
		for (int idx = 1; idx <= sNum; ++idx) {
			int state = Integer.parseInt(st.nextToken());
			if (state == 1) {
				isOn[idx] = true;
			}
		}

		// 3. 학생 수(numOfStudent)입력받기
		numOfStudent = Integer.parseInt(br.readLine().trim());
		// 4. 학생 수 만큼 반복한다
		for (int cnt = 0; cnt < numOfStudent; ++cnt) {
			st = new StringTokenizer(br.readLine().trim());

			// 성별을 입력받는다
			int gender = Integer.parseInt(st.nextToken());
			// num을 입력받는다
			int num = Integer.parseInt(st.nextToken());

			// 4-1. switch case문
			switch (gender) {
			case BOY:
				// 남학생(1)일 경우: isOn을 num배수만큼 순회하며 토글
				for (int idx = 1; idx * num <= sNum; ++idx) {
					isOn[idx * num] = !isOn[idx * num];
				}
				break;
			case GIRL:
				// 여학생(2)일 경우: num 기준 좌우를 비교하여 같다면 토글, 아니라면 break
				isOn[num] = !isOn[num];
				int startIdx = num - 1;
				int endIdx = num + 1;
				while (startIdx >= 1 && endIdx <= sNum && (isOn[startIdx] == isOn[endIdx]) ) {
					// 좌우 대칭이고, 범위 내에서만 토글
					isOn[startIdx] = !isOn[startIdx];
					isOn[endIdx] = !isOn[endIdx];
					--startIdx;
					++endIdx;
				}
				break;
			}
		}
		int lineCnt = 0;
		for (int idx = 1; idx <= sNum; ++idx) {
			++lineCnt;
			if (isOn[idx]) 
				sb.append("1 ");
			else 
				sb.append("0 ");
			
			if(lineCnt==20) {
				sb.append("\n");
				lineCnt = 0;
			}
		}
		System.out.println(sb.toString());
	}
}
