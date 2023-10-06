import java.util.*;

class Solution {
    private static final int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    
    public int solution(String[] board) {
        char[][] arr = new char[3][3];
        int xCnt = 0;
        int oCnt = 0;
        
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                arr[i][j] = board[i].charAt(j);
                if(arr[i][j]=='O')
                    oCnt++;
                else if(arr[i][j]=='X')
                    xCnt++;
            }
        }
        
        if(xCnt>oCnt)
            return 0;
        if(oCnt - xCnt > 1)
            return 0;
        
        boolean oFin = false;
        boolean xFin = false;
        
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(arr[i][j]=='O'){
                    for(int dir=0;dir<dx.length;++dir){
                        Queue<int[]> q = new LinkedList<>();
                        boolean[][] visited = new boolean[3][3];
                        q.offer(new int[]{i, j});
                        visited[i][j] = true;
                        
                        int cnt = 1;
                        while(!q.isEmpty()){
                            int[] currNode = q.poll();
                            int currRow = currNode[0];
                            int currCol = currNode[1];
                            
                            int tempRow = currRow + dx[dir];
                            int tempCol = currCol + dy[dir];
                            if(tempRow<0||tempCol<0||tempRow>=3||tempCol>=3)
                                continue;
                            if(arr[tempRow][tempCol]=='.'||arr[tempRow][tempCol]=='X')
                                continue;
                            if(visited[tempRow][tempCol])
                                continue;
                            
                            visited[tempRow][tempCol] = true;
                            cnt++;
                            if(cnt==3){
                                oFin = true;
                                break; 
                            }
                            q.offer(new int[]{tempRow, tempCol});   
                        }
                    }
                }
                
                if(arr[i][j]=='X'){
                    for(int dir=0;dir<dx.length;++dir){
                        Queue<int[]> q = new LinkedList<>();
                        boolean[][] visited = new boolean[3][3];
                        q.offer(new int[]{i, j});
                        visited[i][j] = true;
                        int cnt = 1;
                        while(!q.isEmpty()){
                            int[] currNode = q.poll();
                            int currRow = currNode[0];
                            int currCol = currNode[1];
                            
                            int tempRow = currRow + dx[dir];
                            int tempCol = currCol + dy[dir];
                            if(tempRow<0||tempCol<0||tempRow>=3||tempCol>=3)
                                continue;
                            if(arr[tempRow][tempCol]=='.'||arr[tempRow][tempCol]=='O')
                                continue;
                            if(visited[tempRow][tempCol])
                                continue;
                            
                            visited[tempRow][tempCol] = true;
                            cnt++;
                            if(cnt==3){
                                xFin = true;
                                break; 
                            }
                            q.offer(new int[]{tempRow, tempCol});   
                        }
                    }
                }
                
            }
        }
        if(xFin && oFin)
            return 0;
        if(xFin &&(oCnt!=xCnt))
            return 0;
        if(oFin &&(xCnt != oCnt-1))
            return 0;
        // O 승리
        // X 승리
        // 접전
        return 1;
    }
}