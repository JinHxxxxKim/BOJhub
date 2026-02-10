import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        // 우박수 계산 및 결과 저장 필요
        // 저장 자료구조 => Map
        Map<Integer, Integer> collatzMap = new HashMap<>();
        
        int tempK = k;
        int xPos = 0;
        int maxXpos = 0;
        // 우박수 계산
        while(tempK != 1) {
            collatzMap.put(xPos, tempK);
            // 콜라츠 계산
            // 짝수
            if(tempK % 2 == 0){ 
                tempK = tempK / 2;
            }
            // 홀수
            else{ 
                tempK = tempK * 3 + 1;
            }
            ++xPos;
            maxXpos = xPos;
        }
        // 콜라츠추측 후처리
        collatzMap.put(xPos, 1);
        
        // 정적분 진행
        int ansIdx = 0;
        for(int[] range : ranges) {
            int startXpos = range[0];
            int endXpos = maxXpos + range[1];
            if(startXpos > endXpos) {
                answer[ansIdx++] = -1;
                continue;
            }
            
            double localSum = 0.0;
            for(int xPosTemp = startXpos; xPosTemp < endXpos; ++xPosTemp){
                localSum += calArea(collatzMap.get(xPosTemp), collatzMap.get(xPosTemp + 1));
            }
            answer[ansIdx++] = localSum;
        }
        return answer;
    }
    
    // 면적계산 (사다리꼴)
    public double calArea(int y1 ,int y2){
        return (y1 + y2) / 2.0;
    }
}