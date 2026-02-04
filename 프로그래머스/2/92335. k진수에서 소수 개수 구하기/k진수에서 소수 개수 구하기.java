// 진수 변환 메소드 필요
// 소수판별 메소드 필요

import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String changeNumber = changeNumber(n, k);
        String[] chkArray = changeNumber.split("0");
        for(String str : chkArray){
            if(str.isEmpty()) continue;
            if(chkPrime(Long.parseLong(str))){
                ++answer;
            }
        }
        
        return answer;
    }
    
    // 숫자 n을 k진수로 변환하여 문자열로 반환
    public String changeNumber(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n % k);
            n = n / k;
        }
        return sb.reverse().toString();
    }
    
    // 소수판별
    public boolean chkPrime(long num) {
        if(num == 1) return false;
        if(num == 2) return true;
        // System.out.println("num:: " + num);
        // System.out.println("Math.ceil(Math.sqrt(num)):: " + Math.ceil(Math.sqrt(num)));
        for(long chkNum = 3; chkNum <= (long)Math.ceil(Math.sqrt(num)); ++chkNum){
            if(num % chkNum == 0L) return false;
        }
        return true;
    }
    
}