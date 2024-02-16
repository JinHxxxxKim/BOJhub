
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 총 테스트 케이스의 개수 TC를 입력받는다.
 * 2. 총 이동 시간(M), BC의 개수(A)를 입력받는다
 * 3. A와 B의 이동 정보를 입력받는다.
 * 4. M개의 줄에 걸쳐 BC의 정보를 입력받는다.
 * 
 * 0: 정지, 1: 상(열 감소), 2: 우(행 증가), 3: 하(열 증가), 4:좌(행 감소)
 * 
 * 이동 시간 만큼 반복하며 확인
 * 
 * 1. A, B의 좌표에서 모든 BC와의 거리를 확인한다.
 * 2. BC와의 거리가 BC의 사정거리 내에 있을 경우 연결 가능 
 * 3. 연결 가능한 BC 중 파워가 가장 큰 BC에 연결
 * 
 * 연결 가능한 BC가 있다면 각각의 BC리스트에 add
 * 		- 각각의 BC리스트의 크기가 0 == 해당 시간에 충전을 못하는 경우
 * 
 * A, B 둘이 서로 연결 가능한 BC가 겹치지 않을 경우는 문제X
 * 
 * 겹치는 경우가 문제
 * 해결 방법: A를 먼저 확인한 뒤, B를 확인한다.
 * 		- A가 가장 높은 출력에 연결 한 뒤, 연결했음을 표시
 * 		- B가 연결하려 할 때, 연결 표시가 있으면 일단 PASS
 * 			- 이미 연결 한 BC외에 BC가 없어도 문제 X (충전량은 항시 동일)
 * 
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int totalTime; // 총 이동 시간
	private static int totalBCNumber; // 총 BC 개수
	private static int[] movingA; // A의 이동정보
	private static int[] movingB; // B의 이동정보
	private static BC[] bcInfo; // BC정보
	private static int powerSum;
	
	private static Pos aPos;
	private static Pos bPos;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			// 총 이동 시간, 총 BC 개수 입력
			st = new StringTokenizer(br.readLine().trim());
			totalTime = Integer.parseInt(st.nextToken());
			totalBCNumber = Integer.parseInt(st.nextToken());
			
			// A의 이동정보, B의 이동정보 입력
			movingA = new int[totalTime];
			movingB = new int[totalTime];
			st = new StringTokenizer(br.readLine().trim());
			for(int timeCnt = 0;timeCnt<totalTime;++timeCnt) {
				movingA[timeCnt] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine().trim());
			for(int timeCnt = 0;timeCnt<totalTime;++timeCnt) {
				movingB[timeCnt] = Integer.parseInt(st.nextToken());
			}
			
			// BC정보 입력
			bcInfo = new BC[totalBCNumber];
			for (int bcCnt = 0; bcCnt < totalBCNumber; ++bcCnt) {
				st = new StringTokenizer(br.readLine().trim());
				int xPos = Integer.parseInt(st.nextToken())-1;
				int yPos = Integer.parseInt(st.nextToken())-1;
				int limitDist = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				bcInfo[bcCnt] = new BC(xPos, yPos, limitDist, power);
			}
			
			// 로직 시작
			powerSum = 0;
			aPos = new Pos(0, 0);
			bPos = new Pos(9, 9);
			// 출력이 강한 순서대로 정렬
			Arrays.sort(bcInfo);
			solution();
			sb.append(String.format("#%d %d\n", testCase, powerSum));
		}
		System.out.println(sb);
	}
	
	private static void solution() {
		// 모든 시간대 별로 확인
		// 초기 A의 위치 = [0, 0]
		// 초기 B의 위치 = [9, 9]
		for (int currTime = 0; currTime <= totalTime; ++currTime) {
//			System.out.println("===========");
//			System.out.println("time: "+currTime);
//			System.out.println(aPos);
//			System.out.println(bPos);
			// A, B의 현재 위치에 대해 연결 가능한 BC를 저장할 리스트
			List<BC> bcListA = new ArrayList<>();
			List<BC> bcListB = new ArrayList<>();
			
			// B가 BC에 연결하려 할 때, 이미 A가 연결한 상태인지 확인하기 위한 배열
			// BC 구분을 위해 BC의 좌표를 사용
			boolean[][] isAlreadyConnectedByA = new boolean[10][10];
			
			// 모든 BC 순회
			for (int bcNum = 0; bcNum < totalBCNumber; ++bcNum) {
				BC currCheckingBC = bcInfo[bcNum];
				int distAandBC = getDist(aPos, currCheckingBC.pos);
				int distBandBC = getDist(bPos, currCheckingBC.pos);
				
				// A가 현재 BC의 사거리 내에 있는 경우
				if(distAandBC<=currCheckingBC.limitDist) {
					bcListA.add(currCheckingBC);
				}
				// B가 현재 BC의 사거리 내에 있는 경우
				if(distBandBC<=currCheckingBC.limitDist) {
					bcListB.add(currCheckingBC);
				}
			}

			// 조합: bcListA에서 1개 선택, bcListB에서 한개 선택
			int currSum = 0;
			for (BC aBC : bcListA) {
				int localSum = 0;
				int xPosA = aBC.pos.xPos;
				int yPosA = aBC.pos.yPos;
				isAlreadyConnectedByA[xPosA][yPosA] = true;
				localSum += aBC.power;
				for (BC bBC : bcListB) {
					int xPosB = bBC.pos.xPos;
					int yPosB = bBC.pos.yPos;
					if (isAlreadyConnectedByA[xPosB][yPosB])
						continue;
					localSum += bBC.power;
					break;
				}
				isAlreadyConnectedByA[xPosA][yPosA] = false;
				currSum = Math.max(localSum, currSum);
			}

			// 조합: bcListB에서 1개 선택, bcListA에서 한개 선택
			for (BC bBC : bcListB) {
				int localSum = 0;
				int xPosB = bBC.pos.xPos;
				int yPosB = bBC.pos.yPos;
				isAlreadyConnectedByA[xPosB][yPosB] = true;
				localSum += bBC.power;
				for (BC aBC : bcListA) {
					int xPosA = aBC.pos.xPos;
					int yPosA = aBC.pos.yPos;
					if (isAlreadyConnectedByA[xPosA][yPosA])
						continue;
					localSum += aBC.power;
					break;
				}
				isAlreadyConnectedByA[xPosB][yPosB] = false;
				currSum = Math.max(localSum, currSum);
			}

			powerSum += currSum;
			
			// 한 턴이 끝나면 이동
			if(currTime==totalTime)
				break;
			move(currTime);
		}
		
	}

	// 이동 메소드
	// 0: 정지, 1: 상(열 감소), 2: 우(행 증가), 3: 하(열 증가), 4:좌(행 감소)
	private static void move(int time) {
		// A 이동
		switch (movingA[time]) {
		case 0:
			// 정지
			break;
		case 1:
			// 상
			aPos.yPos -= 1;
			break;
		case 2:
			// 우
			aPos.xPos += 1;
			break;
		case 3:
			// 하
			aPos.yPos += 1;
			break;
		case 4:
			// 좌
			aPos.xPos -= 1;
			break;
		default:
			break;
		}
		
		// B 이동
		switch (movingB[time]) {
		case 0:
			// 정지
			break;
		case 1:
			// 상
			bPos.yPos -= 1;
			break;
		case 2:
			// 우
			bPos.xPos += 1;
			break;
		case 3:
			// 하
			bPos.yPos += 1;
			break;
		case 4:
			// 좌
			bPos.xPos -= 1;
			break;
		default:
			break;
		}
	}

	// 두 점 사이의 거리 계산 메소드
	private static int getDist(Pos srcPos, Pos destPos) {
		int dist = 0;
		dist += Math.abs(srcPos.xPos-destPos.xPos);
		dist += Math.abs(srcPos.yPos-destPos.yPos);
		return dist;
	}

	static class Pos {
		int xPos;
		int yPos;

		public Pos(int xPos, int yPos) {
			super();
			this.xPos = xPos;
			this.yPos = yPos;
		}

		@Override
		public String toString() {
			return "Pos [xPos=" + xPos + ", yPos=" + yPos + "]";
		}
	}
	
	static class BC implements Comparable<BC>{
		Pos pos;
		int limitDist;
		int power;

		public BC(int xPos, int yPos, int limitDist, int power) {
			pos = new Pos(xPos, yPos);
			this.limitDist = limitDist;
			this.power = power;
		}

		// 출력이 강한 숭서대로 정렬하기 위함
		@Override
		public int compareTo(BC o) {
			return o.power - this.power;
		}

	}
}