import java.util.*;

class Solution {
    final int[] dx = {-1, 1, 0, 0};
    final int[] dy = {0, 0, -1, 1};
    static int N;
    static int M;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        // 50 * 50 = 2,500
        // 2,500 * 100 = 250,000
        
        N = storage.length;
        M = storage[0].length();
        
        char[][] map = new char[N][M]; // 2차원 storage
        boolean[][] isEnd = new boolean[N][M]; // 외부와 접했는지 여부
        boolean[][] isEndTemp = new boolean[N][M]; // 외부와 접했는지 여부 Temp
        
        for(int row = 0; row < N; ++row) {
            String str = storage[row];
            for(int col = 0; col < M; ++col) {
                map[row][col] = str.charAt(col);
            }
        }
        int cnt = 0;
        for(String req : requests){
            char c = req.charAt(0);
            // 지게차
            if(req.length() == 1){
                for(int row = 0; row < N; ++row){
                    for(int col = 0; col < M; ++col) {
                        if(map[row][col] != c || isEnd[row][col]) {
                            continue;
                        }
                        // 외부와 접했는지 확인
                        for(int dir = 0; dir < 4; ++dir){
                            int tempRow = row + dx[dir];
                            int tempCol = col + dy[dir];
                            if(tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                                isEndTemp[row][col] = true;
                                ++cnt;
                                break;
                            }
                            if(isEnd[tempRow][tempCol] && bfs(tempRow, tempCol, isEnd)){
                                isEndTemp[row][col] = true;
                                ++cnt;
                                break;
                            }
                        } 
                    }
                }
                for(int row = 0; row < N; ++row){
                    for(int col = 0; col < M; ++col) {
                        isEnd[row][col] = isEndTemp[row][col];
                    }
                }
            }
            // 지게차 + 크레인
            else{
                for(int row = 0; row < N; ++row){
                    for(int col = 0; col < M; ++col) {
                        if(map[row][col] != c) {
                            continue;
                        }
                        isEnd[row][col] = true;
                        isEndTemp[row][col] = true;
                        ++cnt;
                    }
                }
            }
            // System.out.println("============================== cnt:"+cnt);
            //  for(int row = 0; row < N; ++row){
            //     for(int col = 0; col < M; ++col) {
            //         System.out.print("\t" + isEnd[row][col] + "\t");    
            //     }
            //     System.out.println("\n");
            // }
        }
        for(int row = 0; row < N; ++row){
            for(int col = 0; col < M; ++col) {
                if(!isEnd[row][col]) ++answer;
            }
        }
        // for(int row = 0; row < N; ++row){
        //     for(int col = 0; col < M; ++col) {
        //         System.out.print("\t" + isEnd[row][col] + "\t");    
        //     }
        //     System.out.println("\n");
        // }
        return answer;
    }
        
    public boolean bfs(int row, int col, boolean[][] isEmpty){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {row, col});
        boolean[][] isVisited = new boolean[N][M];
        isVisited[row][col] = true;
        while(!q.isEmpty()) {
            int[] currPos = q.poll();
            int currRow = currPos[0];
            int currCol = currPos[1];
            for(int dir = 0; dir < 4; ++dir){
                int tempRow = currRow + dx[dir];
                int tempCol = currCol + dy[dir];
                
                if(tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                    return true;
                }
                
                if(!isEmpty[tempRow][tempCol]){
                    continue;
                }
                
                if(isVisited[tempRow][tempCol]){
                    continue;
                }
                
                isVisited[tempRow][tempCol] = true;
                q.add(new int[]{tempRow, tempCol});
            }
        }
        return false;
    }
}