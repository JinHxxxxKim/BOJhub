class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 1;
        
        for(int num = 2;num<=number;++num){
            int cnt = 0;
            for(int i=1;i*i<=num;++i){
                if(i*i==num){
                    ++cnt;
                } else if(num%i==0){
                    // 약수
                    cnt+=2;
                }
            }
            if(cnt>limit){
                answer+=power;
            }else{
                answer+=cnt;
            }
        }
        
        return answer;
    }
}