class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int N = sticker.length;
        
        int[][] selectFirstDp = new int[N][2];   // 0번 원소를 선택했을 경우 DP
        int[][] unSelectFirstDp = new int[N][2]; // 0번 원소를 선택하지 않았을 경우 DP
        
        // selectFirstDp
        selectFirstDp[0][0] = sticker[0];
        selectFirstDp[0][1] = 0;
        for(int idx = 1; idx < N; ++idx) {
            // 해당 스티커 선택(단, selected일 경우, 1번째, N번쨰 스티커는 선택불가)
            if(idx == 1) {
                selectFirstDp[idx][0] = 0;
            } else if(idx == N - 1) {
                selectFirstDp[idx][0] = Math.max(selectFirstDp[idx - 1][0], selectFirstDp[idx - 1][1]);
            } else {
                selectFirstDp[idx][0] = selectFirstDp[idx - 1][1] + sticker[idx];
            }
            // 해당 스티커 미선택
            selectFirstDp[idx][1] = Math.max(selectFirstDp[idx - 1][0], selectFirstDp[idx - 1][1]);
        }
        
        // unSelectFirstDp
        unSelectFirstDp[0][0] = 0;
        unSelectFirstDp[0][1] = 0;
        for(int idx = 1; idx < N; ++idx) {
            // 해당 스티커 선택
            unSelectFirstDp[idx][0] = unSelectFirstDp[idx - 1][1] + sticker[idx];
            // 해당 스티커 미선택
            unSelectFirstDp[idx][1] = Math.max(unSelectFirstDp[idx - 1][0], unSelectFirstDp[idx - 1][1]);
        }
        answer = Math.max(unSelectFirstDp[N - 1][0], unSelectFirstDp[N - 1][1]);
        answer = Math.max(answer, selectFirstDp[N - 1][0]);
        answer = Math.max(answer, selectFirstDp[N - 1][1]);

        return answer;
    }
}

/*
        14  6   5   11  3   9   2   10
선택     14  X  19  25  22  34  27  34
            14 14   19  25  25 34   34

미선택    0    6   5  17 9  26  19  36
              0   6  6  17  17 26  26



*/