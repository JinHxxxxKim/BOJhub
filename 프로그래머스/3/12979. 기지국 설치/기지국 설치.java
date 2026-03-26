class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int beforeEnd = 0;
        for(int station : stations) {
            int start = station - w;
            int end = station + w;
            // 이전 전파 범위와 겹치지 않을 경우
            if(beforeEnd + 1 < start) {
                int gap = start - beforeEnd - 1;
                // 해당 gap 에 필요한 station 수 계산
                // 하나의 station 이 커버할 수 있는 범위: w * 2 + 1
                answer += gap / (w * 2 + 1);
                if(gap % (w * 2 + 1) != 0) {
                    answer++;
                }
            }
            beforeEnd = end;
        }
        
        // TODO: 종료지점 검사 필요
        if(beforeEnd < n) {
            int gap = n - beforeEnd;
            answer += gap / (w * 2 + 1);
            if(gap % (w * 2 + 1) != 0) {
                answer++;
            }
        }
        
        return answer;
    }
}