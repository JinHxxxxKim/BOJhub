

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 구역의 개수 N을 입력받는다.
 * 2. 구역의 인구가 1번 구역부터 N번 구역까지 순서대로 입력받는다. 
 * 3. 구역의 정보를 입력받는다.
 * 
 * 총 구역의 개수가 10개를 넘지 않는다. 
 * 따라서 10 Combination 1 ~ 10 Combination 9까지 하나의 구역을 선택한 뒤 계산한다.
 * 
 * 4. Combinaiton 함수를 호출한다.
 * 		- 기저조건(selectIdx == 설정한 구역의 수)
 * 			- 선택된 구역(AREA A)에 대해 연결되어있는지 확인
 * 			- 선택이 되지 않은 구역(AREA B)에 대해 연결되어있는지 확인
 * 			- 위 두 조건이 모두 만족된다면, 인구수의 차를 구하고 최소값을 갱신한다.
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int areaNumber; // 영역의 개수
	private static int[] areaPeopleNumberList; // 인구수 저장 배열
	private static List<Integer>[] adList; // 인접 리스트

	// 조합 관련 변수
	private static int R; // areaNumber Combination R
	private static int[] areaASelected; // A영역에 속하는 영역 저장
	private static int[] areaBSelected; // B영역에 속하는 영역 저장
	private static int areaAPeopleNum; // A영역 인구수
	private static int areaBPeopleNum; // B영역 인구수
	private static int ans;

	// BFS 관련 변수
	private static boolean[] isVisited; // 방문 여부 확인 배열

	public static void main(String[] args) throws Exception {
		// 영역의 개수 입력
		areaNumber = Integer.parseInt(br.readLine().trim());
		areaPeopleNumberList = new int[areaNumber];

		// 인구수 입력
		st = new StringTokenizer(br.readLine().trim());
		for (int areaCnt = 0; areaCnt < areaNumber; ++areaCnt) {
			areaPeopleNumberList[areaCnt] = Integer.parseInt(st.nextToken());
		}

		adList = new List[areaNumber];
		for (int areaCnt = 0; areaCnt < areaNumber; ++areaCnt) {
			adList[areaCnt] = new ArrayList<Integer>();
		}

		// 그래프 상태 입력
		for (int areaCnt = 0; areaCnt < areaNumber; ++areaCnt) {
			st = new StringTokenizer(br.readLine().trim());
			int adAreaNum = Integer.parseInt(st.nextToken());
			for (int adCnt = 0; adCnt < adAreaNum; ++adCnt) {
				adList[areaCnt].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}

		// areaCnt Combination 1 ~ areaCnt Combination areaCnt - 1 까지 영역을 나누어보며 확인
		ans = Integer.MAX_VALUE;
		for (int r = 1; r <= areaNumber / 2 + 1; ++r) {
			R = r;
			areaASelected = new int[r];
			areaBSelected = new int[areaNumber - r];
			makeCombination(0, 0, 0);
		}
		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	/**
	 * 어떤 영역에 속할지 결정하는 메소드
	 * 
	 * @param areaACnt   선택한 A영역의 개수
	 * @param areaBCnt   선택한 B영역의 개수
	 * @param elementIdx 이번에 선택할 영역 번호
	 */
	private static void makeCombination(int areaACnt, int areaBCnt, int elementIdx) {
		// 기저조건
		// 모두 선택완료
		if (areaACnt == R && areaBCnt == areaNumber - R) {

			areaAPeopleNum = 0;
			areaBPeopleNum = 0;

			isVisited = new boolean[areaNumber];
			// 각 영역이 모두 연결 되어있는지 확인: A
			if (!isConnected(areaASelected, 'A')) {
				return;
			}
			// 각 영역이 모두 연결 되어있는지 확인: B
			if (!isConnected(areaBSelected, 'B')) {
				return;
			}

//			if(ans >  Math.abs(areaAPeopleNum - areaBPeopleNum)) {
//				System.out.println("A: " + Arrays.toString(areaASelected));
//				System.out.println("B: " + Arrays.toString(areaBSelected));
//			}
			ans = Math.min(ans, Math.abs(areaAPeopleNum - areaBPeopleNum));
			return;
		} else if (elementIdx == areaNumber) {
			return;
		} else if (areaACnt == R) {
			// A영역만 모두 다 고른 경우 -> 나머지 다 B에 배정
			areaBSelected[areaBCnt] = elementIdx;
			makeCombination(areaACnt, areaBCnt + 1, elementIdx + 1);
			return;
		} else if (areaBCnt == areaNumber - R) {
			// B영역만 모두 다 고른 경우 -> 나머지 다 A에 배정
			areaASelected[areaACnt] = elementIdx;
			makeCombination(areaACnt + 1, areaBCnt, elementIdx + 1);
			return;
		}

		// 이번 영역(elementIdx)를 A에 배정
		areaASelected[areaACnt] = elementIdx;
		makeCombination(areaACnt + 1, areaBCnt, elementIdx + 1);

		// 이번 영역(elementIdx)를 B에 배정
		areaBSelected[areaBCnt] = elementIdx;
		makeCombination(areaACnt, areaBCnt + 1, elementIdx + 1);
	}

	private static boolean isConnected(int[] areaList, char currArea) {
		// 목표 탐색 노드 수
		int targetNodeNum = areaList.length;
		// 현재 탐색 노드 수
		int searchNodeNum = 0;

		Queue<Integer> q = new ArrayDeque<>();
		if(areaList.length>0) {
			q.offer(areaList[0]);
			isVisited[areaList[0]] = true;
		}
		while (!q.isEmpty()) {
			int currNode = q.poll();
			++searchNodeNum;
			if (currArea == 'A') {
				areaAPeopleNum += areaPeopleNumberList[currNode];
			} else {
				areaBPeopleNum += areaPeopleNumberList[currNode];
			}

			// 인접 노드 중에 동일 영역이 있다면 큐에 추가
			for (int adNode : adList[currNode]) {
				for (int sameArea : areaList) {
					if (adNode == sameArea) {
						if (isVisited[adNode])
							continue;
						q.offer(adNode);
						isVisited[adNode] = true;
					}
				}
			}
		}

		if (targetNodeNum == searchNodeNum)
			return true;
		return false;
	}
}
