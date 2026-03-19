import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // INIT
        int[][] map = new int[n][m];
        for(int[] puddle : puddles) {
            if(puddle.length == 0) break;
            map[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        
        for(int col = 0; col < m; ++col) {
            if(map[0][col] == -1) break;
            map[0][col] = 1;
        }
        for(int row = 0; row < n; ++row) {
            if(map[row][0] == -1) break;
            map[row][0] = 1;
        }
        
        for(int row = 1; row < n; ++row){
            for(int col = 1; col < m; ++col){
                if(map[row][col] == -1) continue;
                int up = map[row - 1][col];
                int left = map[row][col - 1];
                
                if(up == -1) up = 0;
                if(left == -1) left = 0;
                map[row][col] = (up + left) % 1_000_000_007;
            }
        }
        
        // for(int[] arr : map){
        //     System.out.println(Arrays.toString(arr));
        // }
        
        return map[n - 1][m - 1];
    }
}