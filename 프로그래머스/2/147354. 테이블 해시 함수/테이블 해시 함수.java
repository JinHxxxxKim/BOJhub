import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        int rowSize = data.length;
        int colSize = data[0].length;
        
//         for(int i=0;i<rowSize;++i){
//             System.out.println(Arrays.toString(data[i]));
//         }
        Arrays.sort(data, new Comparator<int[]>(){
            @Override
            public int compare(int[] a1, int[] a2){
                if(a1[col-1]==a2[col-1]){
                    return a2[0]-a1[0];
                }
                return a1[col-1]-a2[col-1];   
            }
        });
        
//         for(int i=0;i<rowSize;++i){
//             System.out.println(Arrays.toString(data[i]));
//         }
        
        int sum = 0;
        for(int row=row_begin;row<=row_end;++row){
            int temp = 0;
            int[] currTuple = data[row-1];
            for(int j=0;j<colSize;++j){
                temp+=currTuple[j]%row;
            }
            sum=sum^temp;
        }
        
        return sum;
    }
}