import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int islandCnt;

    private static int[][] map;
    private static boolean[][] visited;
    private static int[] rootInfo;

    private static final int[] dx = new int[]{-1, 1, 0, 0};
    private static final int[] dy = new int[]{0, 0, -1, 1};

    private static PriorityQueue<Edge17472> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 번호 붙이기
        islandCnt = 1;
        visited = new boolean[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (visited[i][j] || map[i][j] == 0) {
                    continue;
                }
                bfsNumbering(i, j);
                islandCnt++;
            }
        }

        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; ++j) {
                if (map[i][j] != 0) {
                    for (int dir = 0; dir < 4; ++dir) {
                        findEdge(i, j, dir);
                    }
                }
            }
        }
        rootInfo = new int[islandCnt];
        for (int i = 1; i < rootInfo.length; ++i) {
            rootInfo[i] = i;
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Edge17472 currEdge = pq.poll();
            int node1 = currEdge.getNode1();
            int node2 = currEdge.getNode2();
            int weight = currEdge.getWeight();

            int node1_root = find(node1);
            int node2_root = find(node2);
            if (node1_root != node2_root) {
                union(node1, node2);
                ans += weight;
            }
        }
        int root = find(1);
        for (int i = 1; i < rootInfo.length; ++i) {
            if (root != find(i)) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(ans);

//        for (int i = 0; i < N; ++i) {
//            System.out.println(Arrays.toString(map[i]));
//        }
//        while (!pq.isEmpty()) {
//            System.out.println(pq.poll());
//        }

    }

    private static void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            if (root1 < root2) {
                rootInfo[root2] = root1;
            } else {
                rootInfo[root1] = root2;
            }
        }
    }

    private static int find(int node) {
        if (rootInfo[node] == node) {
            return node;
        } else {
            return rootInfo[node] = find(rootInfo[node]);
        }
    }

    private static void findEdge(int i, int j, int dir) {
        int startIslandNum = map[i][j];
        int bridgeLen = 0;
        while (true) {
            i += dx[dir];
            j += dy[dir];
            if (i < 0 || j < 0 || i >= N || j >= M) {
                break;
            }
            if (map[i][j] == startIslandNum) {
                break;
            }
            if (map[i][j] == 0) {
                bridgeLen++;
                continue;
            }
            if (map[i][j] != startIslandNum) {
                if (bridgeLen != 1) {
                    pq.offer(new Edge17472(startIslandNum, map[i][j], bridgeLen));
                }
                break;
            }
        }
    }

    private static void bfsNumbering(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        visited[i][j] = true;
        q.offer(new int[]{i, j});
        while (!q.isEmpty()) {
            int[] currNode = q.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];
            map[currRow][currCol] = islandCnt;
            for (int dir = 0; dir < 4; ++dir) {
                int tempRow = currRow + dx[dir];
                int tempCol = currCol + dy[dir];
                if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                    continue;
                }
                if (map[tempRow][tempCol] == 0) {
                    continue;
                }
                if (visited[tempRow][tempCol]) {
                    continue;
                }
                q.offer(new int[]{tempRow, tempCol});
                visited[tempRow][tempCol] = true;
            }
        }
    }
}

class Edge17472 implements Comparable<Edge17472>{
    private int node1;
    private int node2;
    private int weight;

    public Edge17472(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge17472 edge) {
        return this.weight - edge.weight;
    }

    @Override
    public String toString() {
        return "Edge17472{" +
                "node1=" + node1 +
                ", node2=" + node2 +
                ", weight=" + weight +
                '}';
    }
}
