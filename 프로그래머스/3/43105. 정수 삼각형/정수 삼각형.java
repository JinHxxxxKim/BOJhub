class Solution {
    public int solution(int[][] triangle) {
        int answer = -1;
        
        // INIT
        int N = triangle.length;
        int[][] dp = new int[N][N];
        dp[0][0] = triangle[0][0];
        
        for(int row = 1; row < N; ++row) {
            for(int col = 0; col < triangle[row].length; ++col) {
                int currNum = triangle[row][col];
                if(col != 0){
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row - 1][col - 1]) + currNum;    
                }else{
                    dp[row][col] = dp[row - 1][col] + currNum;    
                }
                
            }
        }
        
        
        for(int col = 0; col < N; ++col) {
            answer = Math.max(answer, dp[N - 1][col]);
        }
        
        return answer;
    }
}


/**
[7]
[3, 8]
[8, 1, 0]
[2, 7, 4, 4]
[4, 5, 2, 6, 5]
*/
