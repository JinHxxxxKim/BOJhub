import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 상 하 9 12 3 6
    static final int[] dx = new int[]{0, 0, 0, 1, 0, -1};
    static final int[] dy = new int[]{0, 0, -1, 0, 1, 0};
    static final int[] dz = new int[]{1, -1, 0, 0, 0, 0};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int L, R, C;
    static boolean[][][] visited;
    static int[][][] dist;
    static char[][][] map;

    static int startX, startY, startZ;
    static int endX, endY, endZ;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine().trim());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            // 종료조건
            if(L == 0 && R == 0 && C == 0) break;

            visited = new boolean[L][R][C];
            dist = new int[L][R][C];
            map = new char[L][R][C];

            for (int[][] row : dist) {
                for (int[] elem : row) {
                    Arrays.fill(elem, Integer.MAX_VALUE);
                }
            }

            // 입력
            for(int height = 0; height < L; height++) {
                for(int row = 0; row < R; row++) {
                    String line = br.readLine().trim();
                    for(int col = 0; col < C; col++) {
                        map[height][row][col] = line.charAt(col);
                        if (line.charAt(col) == 'S') {
                            startX = row;
                            startY = col;
                            startZ = height;
                            map[height][row][col] = '.';
                        } else if (line.charAt(col) == 'E') {
                            endX = row;
                            endY = col;
                            endZ = height;
//                            map[height][row][col] = '.';
                        }
                    }
                }
                br.readLine();
            }

            // solution
            Queue<Pos> q = new LinkedList<>();
            q.add(new Pos(startX, startY, startZ));
            visited[startZ][startX][startY] = true;
            dist[startZ][startX][startY] = 0;
            while(!q.isEmpty()) {
                Pos currPos = q.poll();
                if(currPos.x == endX && currPos.y == endY && currPos.z == endZ) {
                    break;
                }

                for (int dir = 0; dir < 6; ++dir) {
                    int tempZ = currPos.z + dz[dir];
                    int tempX = currPos.x + dx[dir];
                    int tempY = currPos.y + dy[dir];

                    if(tempZ < 0 || tempZ >= L || tempX < 0 || tempX >= R || tempY < 0 || tempY >= C) continue;
                    if(visited[tempZ][tempX][tempY]) continue;
                    if(map[tempZ][tempX][tempY] == '#') continue;
                    if(dist[tempZ][tempX][tempY] <= dist[currPos.z][currPos.x][currPos.y] + 1) continue;

                    visited[tempZ][tempX][tempY] = true;
                    q.add(new Pos(tempX, tempY, tempZ));
                    dist[tempZ][tempX][tempY] = dist[currPos.z][currPos.x][currPos.y] + 1;
                }
            }

            if (dist[endZ][endX][endY] == Integer.MAX_VALUE) {
                sb.append("Trapped!\n");
            }else{
                sb.append(String.format("Escaped in %d minute(s).\n", dist[endZ][endX][endY]));
            }
        }
        System.out.println(sb.toString().trim());
    }

    static class Pos {
        int x, y, z;
        public Pos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
