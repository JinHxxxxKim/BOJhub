import java.util.*;

class Solution {
    
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int[][] dists;
    static int R, C;
    
    public int solution(int[][] maps) {
        int answer = 0;
        R = maps.length;
        C = maps[0].length;
        visited = new boolean[R][C];
        dists = new int[R][C];
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        dists[0][0] = 1;
        while(!q.isEmpty()) {
            int[] currNode = q.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];
            int currDist = dists[currRow][currCol];
            for(int dir = 0; dir < 4; ++dir){
                int tempRow = currRow + dx[dir];
                int tempCol = currCol + dy[dir];
                if(tempRow < 0 || tempCol < 0 || tempRow >= R || tempCol >= C)
                    continue;
                if(visited[tempRow][tempCol])
                    continue;
                if(maps[tempRow][tempCol] == 0)
                    continue;
                visited[tempRow][tempCol] = true;
                q.offer(new int[]{tempRow, tempCol});
                dists[tempRow][tempCol] = currDist + 1;
            }
        }
        if(dists[R - 1][C - 1] == 0)
            return -1;
        return dists[R - 1][C - 1];
    }
}