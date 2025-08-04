import java.util.*;

class Solution {
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    static int oilNum; // 석유덩어리 번호
    static boolean[][] visited;
    static int oilMap[][]; 
    
    static int N, M;
    
    static Map<Integer, Integer> map;
    
    public int solution(int[][] land) {
        int answer = 0;
        // 각 석유 덩어리의 총합 계산
        // 각 위치가 동일한 석유 덩어리인지 검증
        oilNum = 1;
        N = land.length;
        M = land[0].length;
        visited = new boolean[N][M];
        oilMap = new int[N][M];
        map = new HashMap<>();
        // 석유지도 계산
        for(int row = 0; row < N; ++row) {
            for(int col = 0; col < M; ++col){
                // 석유여부
                if(land[row][col] == 0){
                    continue;
                }
                // 방문여부
                if(visited[row][col]){
                    continue;
                }
                
                // 방문하지 않은 석유덩어리 시작점 발견
                int totalOil = dfs(1, row, col, land);
                
                map.put(oilNum++, totalOil);
            }
        }
        
        // 최대석유량 계산
        for(int col = 0; col < M; ++col){
            int localSum = 0;
            boolean[] isChk = new boolean[oilNum];
            for(int row = 0; row < N; ++row){
                if(oilMap[row][col] == 0){
                    continue;
                }else{
                    int currOilNum = oilMap[row][col];
                    if(isChk[currOilNum]){
                        continue;
                    }
                    isChk[currOilNum] = true;
                    localSum += map.get(currOilNum);
                }
            }
            answer = Math.max(localSum, answer);
        }
        
        // System.out.println(map);
        return answer;
    }
    
    static int dfs(int oilCnt, int row, int col, int[][] land){
        int localCnt = 1;
        // 방문처리
        visited[row][col] = true;
        oilMap[row][col] = oilNum;
        // 4방탐색
        for(int dir = 0; dir < 4; ++dir){
            int tempRow = row + dx[dir];
            int tempCol = col + dy[dir];
            // 범위검증
            if(tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M){
                continue;
            }
            // 석유여부
            if(land[tempRow][tempCol] == 0){
                continue;
            }
            // 방문여부
            if(visited[tempRow][tempCol]){
                continue;
            }
            // 방문하지 않은 석유덩어리 발견
            localCnt += dfs(oilCnt + 1, tempRow, tempCol, land);
        }
        return localCnt;
    }
}