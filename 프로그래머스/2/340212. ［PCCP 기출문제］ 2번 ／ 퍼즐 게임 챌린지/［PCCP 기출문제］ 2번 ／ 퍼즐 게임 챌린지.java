class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answerCnt = 0;
        int answer = Integer.MAX_VALUE;
        
        int minLevel = 1;
        int maxLevel = Integer.MIN_VALUE;
        for(int level : diffs){
            maxLevel = Math.max(level, maxLevel);
        }
        
        long[] nDiffs = new long[diffs.length + 1];
        long[] nTimes = new long[times.length + 1];
        for(int idx = 0; idx < nDiffs.length; ++idx){
            if(idx == 0){
                nDiffs[idx] = 0;
                nTimes[idx] = 0;
            }else{
                nDiffs[idx] = diffs[idx - 1];
                nTimes[idx] = times[idx - 1];
            }
        }
        
        // 이분탐색
        while(minLevel <= maxLevel) {
            
            int midLevel = (minLevel + maxLevel) / 2;
            int localCnt = 0;
            
            long localTime = sol(midLevel, nDiffs, nTimes);
            // System.out.println("minLevel: " + minLevel);  
            // System.out.println("midLevel: " + midLevel);  
            // System.out.println("maxLevel: " + maxLevel);  
            // System.out.println("localTime: " + localTime);    
            
            if(localTime == limit){
                answer = midLevel;
                break;
            }else if (localTime < limit){
                if(midLevel < answer){
                    answer = midLevel;
                }
                maxLevel = midLevel - 1;
            }else{
                minLevel = midLevel + 1;
            } 
        }
        
        return answer;
    }
    
    static long sol(int level, long[] diffs, long[] times) {
        long sum = 0;
        for(int idx = 1; idx < diffs.length; ++idx) {
            if(level < diffs[idx]) {
                sum += (long)(diffs[idx] - (long)level) * (long)(times[idx] + times[idx - 1]) + times[idx];
            } else {
                sum += times[idx];
            }
            // System.out.println("SUM:: "+sum);
        }
        return sum;
    }
}