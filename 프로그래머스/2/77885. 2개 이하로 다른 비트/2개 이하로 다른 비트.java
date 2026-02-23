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
            for(int bitIdx = 50; bitIdx >= 0; --bitIdx) {
                if(bitArray[bitIdx] == 1) {
                    continue;
                }
                bitArray[bitIdx] = 1;
                zeroIdx = bitIdx;
                break;
            }
            
            for(int bitIdx = zeroIdx + 1; bitIdx < 51; ++bitIdx) {
                if(bitArray[bitIdx] == 0) continue;
                bitArray[bitIdx] = 0;
                break;
            }
            answer[idx] = toLong(bitArray);
        }
        return answer;
    }
    
    public int[] toBitString(long number) {
        int[] ret = new int[51];
        int idx = 50;
        while(number > 0 && idx > -1) {
            ret[idx--] = (int)(number % 2);
            number = number / 2;
        }
        return ret;
    }
    
    public long toLong(int[] array) {
        long ret = 0L;
        long sumNum = 2L;
        for(int idx = 50; idx >= 0; --idx) {
            ret += (long)array[idx] * (long)(Math.pow(2, 50 - idx));
        }
        return ret;
    }
}