

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * 
 * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
 * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다
 * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
 * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * 
 * 1. 아기 상어의 현재 위치에서 BFS 탐색을 실행하며, 먹을 수 있는 물고기를 찾고 저장한다.
 * 2. 먹을 수 있는 물고기를 주어진 조건대로 정렬한다.
 * 3. 가장 앞에 있는 물고기를 먹는다.
 * 4. 먹은 물고기의 크기와 상어의 크기가 같다면, 상어의 물고기 카운트를 하나 증가시킨다.
 * 		4-1. 상어의 물고기 카운트가 상어의 크기와 같다면, 상어의 크기를 증가시키고 물고기 카운트를 0으로 초기화한다.
 * 5. 먹을 수 있는 물고기를 저장한 리스트의 크기가 0이라면 종료하고 소요 시간을 출력한다. 
 */
public class Main {
	static final int[] dx = new int[] { -1, 0, 1, 0 };
	static final int[] dy = new int[] { 0, 1, 0, -1 };
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	static int N;
	static int[][] map;
	static Shark shark;
	static List<Fish> eatableFish;
	static boolean[][] isVisited;
	static int[][] dist;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];

		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < N; ++col) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 9) {
					shark = new Shark(new Pos(row, col), 2, 0);
					map[row][col] = 0;
				}
			}
		}

		int totalTime = 0;

		while (canEatFish()) {
			Fish currEatFish = eatableFish.get(0);
//			System.out.println(shark);
//			System.out.println(eatableFish);
			
			shark.fishCnt++;
			if (shark.fishCnt == shark.size) {
				shark.size++;
				shark.fishCnt = 0;
			}

			totalTime += currEatFish.distFromShark;
			map[currEatFish.pos.x][currEatFish.pos.y] = 0;
			shark.pos.x = currEatFish.pos.x;
			shark.pos.y = currEatFish.pos.y;
			
		}

		System.out.println(totalTime);
	}

	private static boolean canEatFish() {
		eatableFish = new ArrayList<>();

		findFish();
		
		Collections.sort(eatableFish);
		if (eatableFish.size() == 0) {
			return false;
		}
		return true;
	}

	private static void findFish() {
		Queue<Pos> q = new ArrayDeque<>();
		isVisited = new boolean[N][N];
		dist = new int[N][N];
		
		q.offer(shark.pos);
		isVisited[shark.pos.x][shark.pos.y] = true;
		dist[shark.pos.x][shark.pos.y] = 0;
//		System.out.println("AA");
//		System.out.println(shark);
		while (!q.isEmpty()) {
			Pos currPos = q.poll();
//			System.out.println(currPos);
			int currRow = currPos.x;
			int currCol = currPos.y;
			int currDist = dist[currRow][currCol];

			for (int dir = 0; dir < 4; ++dir) {
				int tempRow = currRow + dx[dir];
				int tempCol = currCol + dy[dir];

//				System.out.println("TEMPROW: "+tempRow);
//				System.out.println("TEMPCOL: "+tempCol ); 6 15 1 6 3 
				
				
				if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= N) {
					continue;
				}
				if (isVisited[tempRow][tempCol]) {
					continue;
				}
				if (map[tempRow][tempCol] > shark.size) {
					continue;
				}
				
				q.offer(new Pos(tempRow, tempCol));
				isVisited[tempRow][tempCol] = true;
				dist[tempRow][tempCol] = currDist + 1;
				if (map[tempRow][tempCol] != 0 &&map[tempRow][tempCol] < shark.size) {
					eatableFish.add(new Fish(new Pos(tempRow, tempCol), map[tempRow][tempCol], currDist + 1));
				}
			}
		}
//		for(int[]d:map)
//			System.out.println(Arrays.toString(d));
//		System.out.println("=============================");
//		for(int[]d:dist)
//			System.out.println(Arrays.toString(d));
	}

	static class Fish implements Comparable<Fish> {
		Pos pos;
		int size;
		int distFromShark;

		public Fish(Pos pos, int size, int distFromShark) {
			super();
			this.pos = pos;
			this.size = size;
			this.distFromShark = distFromShark;
		}

		@Override
		public int compareTo(Fish o) {
			if (this.distFromShark == o.distFromShark) {
				if (this.pos.x == o.pos.x) {
					return Integer.compare(this.pos.y, o.pos.y);
				}
				return Integer.compare(this.pos.x, o.pos.x);
			}
			return Integer.compare(this.distFromShark, o.distFromShark);
		}

		@Override
		public String toString() {
			return "Fish [pos=" + pos + ", size=" + size + ", distFromShark=" + distFromShark + "]";
		}
		
		
	}

	static class Shark {
		Pos pos;
		int size;
		int fishCnt;
		
		public Shark(Pos pos, int size, int fishCnt) {
			super();
			this.pos = pos;
			this.size = size;
			this.fishCnt = fishCnt;
		}

		@Override
		public String toString() {
			return "Shark [pos=" + pos + ", size=" + size + ", fishCnt=" + fishCnt + "]";
		}
		
		
	}

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
	}
}