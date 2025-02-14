import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int W, H;
    static char[][] map;

    static int startRow, startCol;
    static ArrayList<Node> trashList;
    static int[] elements;
    static boolean[] selected;
    static int ans;

    static int[][] minDist;

    public static void main(String[] args) throws Exception {
        while (true) {
            st = new StringTokenizer(br.readLine().trim());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            // 종료조건
            if (W == 0 && H == 0) {
                break;
            }

            map = new char[H][W];
            trashList = new ArrayList<>();
            ans = Integer.MAX_VALUE;
            // 입력
            for (int row = 0; row < H; ++row) {
                String currRow = br.readLine().trim();
                for (int col = 0; col < W; ++col) {
                    char currChar = currRow.charAt(col);
                    // 시작위치
                    if (currChar == 'o') {
                        startRow = row;
                        startCol = col;
                        map[row][col] = '.';
                    } else if (currChar == '*') {
                        trashList.add(new Node(row, col, 0));
                    } else {
                        map[row][col] = currChar;
                    }

                }
            }

            // 완전탐색 (순열)
            minDist = new int[trashList.size() + 1][trashList.size()];
            boolean canClean = false;

            // 각 쓰래기에서 다른 쓰래기까지의 최소 거리를 구한다.
            for (int i = 0; i < trashList.size() + 1; ++i) {
                Node src = null;
                if (i == 0) {
                    src = new Node(startRow, startCol, 0);
                }else{
                    src = trashList.get(i - 1);
                }


                for (int j = 0; j < trashList.size(); ++j) {
                    Node target = trashList.get(j);
                    if (src.row == target.row && src.col == target.col) {
                        continue;
                    }
                    Queue<Node> q = new ArrayDeque<>();
                    boolean[][] visited = new boolean[H][W];
                    q.offer(src);
                    visited[src.row][src.col] = true;
                    while (!q.isEmpty()) {
                        Node currNode = q.poll();
                        if (currNode.row == target.row && currNode.col == target.col) {
                            // 목표지점 도달
                            canClean = true;
                            minDist[i][j] = currNode.dist;
                            break;
                        }
                        for (int dir = 0; dir < 4; ++dir) {
                            int tempRow = currNode.row + dx[dir];
                            int tempCol = currNode.col + dy[dir];
                            // 범위 체크
                            if (tempRow >= H || tempRow < 0 || tempCol >= W || tempCol < 0) {
                                continue;
                            }
                            // 가구 체크
                            if (map[tempRow][tempCol] == 'x') {
                                continue;
                            }
                            // 방문 체크
                            if (visited[tempRow][tempCol]) {
                                continue;
                            }
                            q.offer(new Node(tempRow, tempCol, currNode.dist + 1));
                            visited[tempRow][tempCol] = true;
                        }
                    }
                }
            }
            if(!canClean){
                sb.append(-1).append("\n");
            }else{
                elements = new int[trashList.size()];
                selected = new boolean[trashList.size()];
                permutation(0);
                if (ans != Integer.MAX_VALUE) {
                    sb.append(ans).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    private static void permutation(int selectedCnt) {
        // 기저조건 (청소 순서를 모두 결정함)
        if (selectedCnt == trashList.size()) {
            // 청소 로직 시작
//            System.out.println("elements = " + Arrays.toString(elements));
            int sum = minDist[0][elements[0]];
            for (int idx = 0; idx < elements.length - 1; ++idx) {
                sum += minDist[elements[idx] + 1][elements[idx + 1]];
                if (minDist[elements[idx] + 1][elements[idx + 1]] == 0) {
                    return;
                }
            }
//            System.out.println("sum = " + sum);
            if (sum == 0) {
                return;
            }
            ans = Math.min(ans, sum);
            return;
        }

        for (int idx = 0; idx < trashList.size(); ++idx) {
            if (selected[idx]) {
                continue;
            }
            elements[selectedCnt] = idx;
            selected[idx] = true;

            permutation(selectedCnt + 1);
            selected[idx] = false;
        }
    }

    static class Node {
        int row, col;
        int dist;

        public Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }
}
