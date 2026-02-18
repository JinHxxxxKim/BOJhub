import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left) + 1];
//         int[][] matrix = new int[n][n];
        
//         // 행렬 채우기
//         for(int i = 0; i < n; ++i) {
//             // i 행 i열까지 i+1 로 채우기
//             for(int row = 0; row <= i; ++row) {
//                 matrix[row][i] = i + 1;
//             }
//             for(int col = 0; col <= i; ++col) {
//                 matrix[i][col] = i + 1;
//             }
//         }
        
        int ansIdx = 0;
        for(long chk = left; chk <= right; ++chk) {
            // 좌표 변환
            int row = (int)(chk / n);
            int col = (int)(chk % n);
            // answer[ansIdx++] = matrix[row][col];
            // 행열번호에 따른 값 계산 필요
            answer[ansIdx++] = Math.max(row, col) + 1;
        }
        
        // for(int row = 0; row < n; ++row){
        //     System.out.println(Arrays.toString(matrix[row]));
        // }
        
        return answer;
    }
}