import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int solA = subSol(arrayA, arrayB);
        int solB = subSol(arrayB, arrayA);
        return Math.max(solA, solB);
    }
    
    private int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }
    
    public int subSol(int[] arrayA, int[] arrayB){
        int answer = 0;
        Arrays.sort(arrayA);
        // 최대공약수 계산
        int temp = arrayA[arrayA.length - 1];
        if(arrayA.length >= 2){
            temp = gcd(arrayA[arrayA.length - 1], arrayA[arrayA.length - 2]);    
        }
        for(int idx = arrayA.length - 2; idx >= 0; --idx) {
            temp = gcd(arrayA[idx], temp);     
        }
        int num = temp;
        int sqrt = (int)Math.sqrt(num);
        
        List<Integer> list = new  ArrayList<>();
        for(int i = 1; i <= sqrt; ++i){
            if(num % i == 0){
                list.add(i);
                if(num / i != i){
                    list.add(num / i);
                }
            }
        }
        Collections.sort(list);
        System.out.println(list);
        for(int i = list.size() - 1; i > -1; --i){
            int chkNum = list.get(i);
            boolean find = false;
            System.out.println(chkNum);
            for(int idx = 0; idx < arrayB.length; ++idx){
                if(arrayB[idx]%chkNum == 0){
                    break;
                }
                if(idx == arrayB.length - 1){
                   find = true;
                }
            }
            if(find){
                answer = chkNum;
                break;
            }
        }
        return answer;
    }
}