import java.util.*;

// 시뮬레이션
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        // init
        int[][] matrix = new int[rows][columns];
        int initNum = 1;
        for(int row = 0; row < rows; ++row){
            for(int col = 0; col < columns; ++col){
                matrix[row][col] = initNum++;
            }
        }
        
        // logic
        for(int idx = 0; idx < queries.length; ++idx) {
            int[] currQuery = queries[idx];
            int localMin = rotate(matrix, currQuery);
            answer[idx] = localMin;
            
            // for(int row = 0; row < rows; ++row){
            //     System.out.println(Arrays.toString(matrix[row]));
            // }
            // System.out.println("=========================");
        }
        
        return answer;
    }
    
    public int rotate(int[][] matrix, int[] currQuery){
        // 좌표 보정
        int row1 = currQuery[0] - 1;
        int col1 = currQuery[1] - 1;
        int row2 = currQuery[2] - 1;
        int col2 = currQuery[3] - 1;
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        // 시계방향 회전(1 ≤ x1 < x2 ≤ rows, 1 ≤ y1 < y2 ≤ columns)
        // 반드시 좌상단부터 우하단으로 진행
        int currRow = row1;
        int currCol = col1;
        boolean isFirst = true;
        int dir = 0; // 초기방향 설정(오른쪽)
        int prev = matrix[currRow][currCol];
        int temp = 0;
        int ret = matrix[currRow][currCol];
        
        // 다시 원래 위치로 돌아올떄까지 반복
        while(!(currRow == row1 && currCol == col1) || isFirst ) {
            if(isFirst) isFirst = false;
            
            int nextRow = currRow + dx[dir];
            int nextCol = currCol + dy[dir];
            // 범위 검증
            if(nextRow < row1 || nextRow > row2 || nextCol < col1 || nextCol > col2){
                dir = (dir + 1) % 4;
                nextRow = currRow + dx[dir];
                nextCol = currCol + dy[dir];
            }
            
            temp = matrix[nextRow][nextCol];
            matrix[nextRow][nextCol] = prev;
            prev = temp;
            
            ret = Math.min(ret, prev);
            currRow = nextRow;
            currCol = nextCol;
        }
        
        
        return ret;
    }
}