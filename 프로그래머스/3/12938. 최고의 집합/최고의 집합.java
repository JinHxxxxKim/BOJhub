class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int N = n;
        
        if(s < n) return new int[] {-1};
 
        int ansIdx = 0;
        
        while(ansIdx < N) {
            answer[ansIdx] = s / n;
            // 후처리
            n--;
            s -= answer[ansIdx];
            ansIdx++;
        }
        
        return answer;
    }
}