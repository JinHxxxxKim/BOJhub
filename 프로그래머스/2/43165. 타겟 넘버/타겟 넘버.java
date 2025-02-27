class Solution {
    static int N, T;
    static int ans;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        T = target;
    
        dfs(numbers, 0, 0);
        
        return ans;
    }
    
    public static void dfs(int[] numbers, int idx, int sum){
        // 기저조건
        if(idx == N){
            if(sum == T)
                ++ans;
            return;
        }
        
        dfs(numbers, idx + 1, sum + numbers[idx]);
        dfs(numbers, idx + 1, sum - numbers[idx]);
    }
}