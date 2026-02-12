import java.util.*;

// 완전 탐색
// 모든 응시자의 자리에서 거리가 2인 BFS 진행
class Solution {
    
    static final char PERSON = 'P';
    static final char TABLE = 'O';
    static final char PARTITION = 'X';
    
    static final int LIMIT_DIST = 2;
    
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int ansIdx = 0;
        
        // 모든 대기실 순차탐색
        for(String[] place : places) {
            char[][] map = new char[5][5];
            // init
            for(int idx = 0; idx < 5; idx++) {
                map[idx] = place[idx].toCharArray();
            }
            
            // logic
            boolean isValid = true;
            for(int row = 0; row < 5; ++row) {
                for(int col = 0; col < 5; ++col) {
                    char currItem = map[row][col];
                    if(currItem != PERSON) continue;
                    
                    Queue<Pos> q = new LinkedList<>();
                    boolean[][] visited = new boolean[5][5];
                    for(boolean[] temp : visited){
                        Arrays.fill(temp, false);
                    }
                    q.offer(new Pos(row, col, 0));
                    visited[row][col] = true;
                    
                    while(!q.isEmpty()){
                        Pos currPos = q.poll();
                        
                        // 거리검증
                        if(currPos.dist == 2) continue;
                        for(int dir = 0; dir < 4; ++dir) {
                            int nextRow = currPos.row + dx[dir];
                            int nextCol = currPos.col + dy[dir];
                            
                            // 범위검증
                            if(nextRow < 0 || nextCol < 0 || nextRow >= 5 || nextCol >= 5) {
                                continue;
                            }
                            
                            // 방문검증
                            if(visited[nextRow][nextCol]) {
                                continue;
                            }
                            
                            // 파티션검증
                            if(map[nextRow][nextCol] == PARTITION) {
                                continue;
                            }

                            // 거리두기 검증
                            if(currPos.dist + 1 <= 2 && map[nextRow][nextCol] == PERSON){
                                isValid = false;
                                continue;
                            }
                            
                            q.offer(new Pos(nextRow, nextCol, currPos.dist + 1));
                            visited[nextRow][nextCol] = true;
                        }
                    }
                    
                    if(!isValid) break;
                }
                if(!isValid) break;
            }
            if(isValid){
                answer[ansIdx++] = 1;
            }else{
                answer[ansIdx++] = 0;
            }
        }
        
        
        
        
        return answer;
    }
    
    static class Pos {
        int row, col, dist;
        
        public Pos(int row, int col, int dist){
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
        
        public String toString(){
            return "[Row : " + row + " / Col : " + col + " / dist : " + dist+ "]";
        }
    }
}