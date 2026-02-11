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
        int row1 = currQuery[0] - 1;
        int col1 = currQuery[1] - 1;
        int row2 = currQuery[2] - 1;
        int col2 = currQuery[3] - 1;

        int[] dx = {0, 1, 0, -1};   // 우, 하, 좌, 상
        int[] dy = {1, 0, -1, 0};

        int currRow = row1;
        int currCol = col1;
        int dir = 0;

        int prev = matrix[currRow][currCol];
        int min = prev;

        boolean first = true;

        while (first || !(currRow == row1 && currCol == col1)) {
            first = false;

            int nextRow = currRow + dx[dir];
            int nextCol = currCol + dy[dir];

            // 범위 벗어나면 방향 전환
            if (nextRow < row1 || nextRow > row2 || nextCol < col1 || nextCol > col2) {
                dir = (dir + 1) % 4;
                nextRow = currRow + dx[dir];
                nextCol = currCol + dy[dir];
            }

            int temp = matrix[nextRow][nextCol];
            matrix[nextRow][nextCol] = prev;
            prev = temp;

            min = Math.min(min, prev);

            currRow = nextRow;
            currCol = nextCol;
        }

        return min;
    }
}