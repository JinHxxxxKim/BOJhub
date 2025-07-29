class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        // 시간별 증설 서버 개수 (0시 ~ 23시)
        int[] server = new int[24];
        // 각 시간 별 서버 현황 확인
        for(int hour = 0; hour < 24; ++hour){
            // System.out.println("=====================");
            int currPlayer = players[hour];
            // 서버 가용치 확인
            int serverCapacity = m + server[hour] * m - 1;
            // System.out.println("시간: " + hour + " / 가용치: " + serverCapacity);
            // 증설 필요 O
            if(currPlayer > serverCapacity) {
                // 필요 서버 계산 
                int reqServer = (currPlayer - (m - 1)) / m + 1;
                if((currPlayer - (m - 1)) % m == 0){
                    --reqServer;
                }
                reqServer -= server[hour];
                // System.out.println("시간: " + hour + " / 증설횟수: " + reqServer);
                answer += reqServer;
                for(int plus = 0; plus < k; ++plus) {
                    if(hour + plus >= 24){
                        break;
                    }
                    server[hour + plus] += reqServer;
                }
            }
            // 증설 필요 X
            else {
                // System.out.println("시간: " + hour + " / 증설횟수0: " + 0);
                continue;
            }
        }
        
        return answer;
    }
}