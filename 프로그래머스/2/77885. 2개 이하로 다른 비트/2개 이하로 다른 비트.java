import java.util.*;
 
class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int idx = 0; idx < numbers.length; ++idx) {
            
            // 짝수
            if(numbers[idx] % 2 == 0){
                answer[idx] = numbers[idx] + 1;    
                continue;
            }
            
            // 홀수 
            int[] bitArray = toBitString(numbers[idx]);
            int zeroIdx = 50;
            // 가장 오른쪽 0을 1로 변환
            for(int bitIdx = 50; bitIdx >= 0; --bitIdx) {
                if(bitArray[bitIdx] == 1) {
                    continue;
                }
                // 변환 및 인덱스 저장
                bitArray[bitIdx] = 1;
                zeroIdx = bitIdx;
                break;
            }
            
            // zero인덱스 기준 가장 왼쪽 1을 0으로 변환
            for(int bitIdx = zeroIdx + 1; bitIdx < 51; ++bitIdx) {
                if(bitArray[bitIdx] == 0) continue;
                // 변환
                bitArray[bitIdx] = 0;
                break;
            }
            answer[idx] = toLong(bitArray);
        }
        return answer;
    }
    
    // 10진수 이진수 배열 변환
    public int[] toBitString(long number) {
        int[] ret = new int[51];
        int idx = 50;
        while(number > 0 && idx > -1) {
            ret[idx--] = (int)(number % 2);
            number = number / 2;
        }
        return ret;
    }
    
    // 이진수 배열 10진수 변환
    public long toLong(int[] array) {
        long ret = 0L;
        long sumNum = 2L;
        for(int idx = 50; idx >= 0; --idx) {
            ret += (long)array[idx] * (long)(Math.pow(2, 50 - idx));
        }
        return ret;
    }
}